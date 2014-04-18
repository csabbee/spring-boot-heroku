package com.epam.tjhooker.mock;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epam.tjhooker.mock.employee.Employee;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@EnableAutoConfiguration
public class UpsaMockController {

    private static final String PRODUCES_JSON = "application/json";
    private final Logger logger = LoggerFactory.getLogger(UpsaMockController.class);
    
    @RequestMapping(value="/employees", method=RequestMethod.GET, produces=PRODUCES_JSON)
    public ResponseEntity<String> employees(@RequestParam String email){
        String employee = "{}";
        HttpHeaders responseHeaders = new HttpHeaders();
        HttpStatus status = HttpStatus.FOUND;
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory jsonFactory = new JsonFactory();
        try {
            JsonParser jsonParser = jsonFactory.createParser(UpsaMockController.class.getClassLoader().getResourceAsStream("employees.json"));
            List<Employee> employees = mapper.readValues(jsonParser, Employee.class).readAll();
            int i = 0;
            int listSize = employees.size();
            boolean found = false;
            for (Employee emp : employees) {
                logger.info(mapper.writeValueAsString(emp));
                if(!found){
                    if(email.equals(emp.getEmail())){
                        employee = mapper.writeValueAsString(emp);
                        found = true;
                    } else {
                        if(i == listSize - 1){
                            employee = "{\"message\": \"Object is not found\", \"exception\": \"com.epam.workload.common.ObjectNotFoundException\"}";
                            status = HttpStatus.NOT_FOUND;
                        }
                    }
                }
                i++;
            }
        } catch (JsonParseException e) {
            employee = "{\"Error\":\"Error while parsing employee\"}";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            e.printStackTrace();
        } catch (JsonMappingException e) {
            employee = "{\"Error\": \"Error while mapping employee\"}";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            e.printStackTrace();
        } catch (IOException e) {
            employee = "{\"Error\": \"IOException\"}";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            e.printStackTrace();
        }
        return new ResponseEntity<String>(employee, responseHeaders, status);
    }
    
    public static void main(String[] args) {
        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }
        System.setProperty("server.port", webPort);
        SpringApplication.run(UpsaMockController.class, args);
    }
}
