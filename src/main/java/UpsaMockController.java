import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@EnableAutoConfiguration
public class UpsaMockController {

    private static final String PRODUCES_JSON = "application/json";
    
    @RequestMapping(value="/employees", method=RequestMethod.GET, produces=PRODUCES_JSON)
    public ResponseEntity<String> employees(@RequestParam String email){
        String employee;
        HttpHeaders responseHeaders = new HttpHeaders();
        
        if("John_Doe@epam.com".equals(email)){
            employee = "["+
             "{"+
               "'uid': '620000',"+
               "'employeeId': '32563187465294591',"+
               "'primarySkill': 'JAVA',"+
               "'jobFunctionId': '1',"+
               "'fired': '',"+
               "'phone': '44444',"+
               "'fullParticipationList': ["+
                 "{"+
                   "'participationRoleName': 'Dummy',"+
                   "'participationRoleId': '1',"+
                   "'projectStateId': '2',"+
                   "'projectId': '7',"+
                   "'projectStateName': 'Production',"+
                   "'projectName': 'TJHooker'"+
                 "}"+
               "],"+
               "'locationId': '2',"+
               "'dismissal': {"+
                 "'activeDismissalDate': '',"+
                 "'isPendingDismissal': 'false'"+
               "},"+
               "'proposalList': ["+
                 "{"+
                   "'positionBillingName': 'DEV1',"+
                   "'created': '2013-02-05',"+
                   "'positionId': '2',"+
                   "'projectId': '7',"+
                   "'isBooked': 'true',"+
                   "'positionBillingId': '2',"+
                   "'projectName': 'TJHooker',"+
                   "'comments': ["+
                     "{"+
                       "'created': '2014-01-28',"+
                       "'userId': '77777',"+
                       "'action': {"+
                         "'id': '1',"+
                         "'authority': 'none',"+
                         "'description': '',"+
                         "'name': 'Randy Manager'"+
                       "},"+
                       "'userName': 'RandyM',"+
                       "'notes': 'Awesome workforce',"+
                       "'itemId': '5',"+
                       "'commentId': '5',"+
                       "'itemTypeId': '1'"+
                     "}"+
                   "]"+
                 "}"+
               "],"+
               "'probation': {"+
                 "'actualEndDate': '2013-06-05',"+
                 "'probationStatus': {"+
                   "'id': '1',"+
                   "'name': 'string'"+
                 "},"+
                 "'originalEndDate': '2013-06-05',"+
                 "'endDate': '2013-06-05',"+
                 "'prolongationDate': ''"+
               "},"+
               "'statusId': '1',"+
               "'title': 'Software Engineer',"+
               "'bookedCount': {},"+
               "'hired': '2013-03-05',"+
               "'primarySkillId': 'JAVA',"+
               "'gender': 'male',"+
               "'reporterId': '2',"+
               "'proposalsCount': '1',"+
               "'jobFunction': 'Development Level 2',"+
               "'participationList': ["+
                 "{"+
                   "'participationRole': 'Developer',"+
                   "'projectId': '3',"+
                   "'lastMonth': 'true',"+
                   "'projectName': 'AnotherAwesomeProject',"+
                   "'lastWorkload': '1.0'"+
                 "}"+
               "],"+
               "'status': 'hired',"+
               "'managerName': 'Randy Manager',"+
               "'categoryId': '2',"+
               "'fullLocation': 'Budapest',"+
               "'reporterName': 'Ann Recruiter',"+
               "'workstation': 'EPHUBUDW0007',"+
               "'isActive': 'true',"+
               "'category': 'dunno',"+
               "'email': 'John_Doe@epam.com',"+
               "'unitName': 'string',"+
               "'managerId': '245513',"+
               "'fullName': 'John Doe Wasowski',"+
               "'unitId': '777'"+
             "}"+
           "]";
        } else {
            employee = "{'message': Object is not found, 'exception': 'com.epam.workload.common.ObjectNotFoundException'}";
        }
        
        return new ResponseEntity<String>(employee, responseHeaders, HttpStatus.CREATED);
    }
    
    public static void main(String[] args) throws Exception {
        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }
        System.setProperty("server.port", webPort);
        SpringApplication.run(UpsaMockController.class, args);
    }
}
