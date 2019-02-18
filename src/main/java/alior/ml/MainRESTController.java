package alior.ml;

import java.util.List;

import alior.ml.EmployeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class MainRESTController {

    @Autowired
    private EmployeeDAO employeeDAO;

    @GetMapping("/welcome")
    public @ResponseBody    ResponseEntity<String> welcome() {
        System.out.println("(Service Side) Geting welcometext");
        return new ResponseEntity<String>("Welcome to RestTemplate Example.", HttpStatus.OK);
    }

    // URL:
    // http://localhost:8080/SomeContextPath/employees
    // http://localhost:8080/SomeContextPath/employees.xml
    // http://localhost:8080/SomeContextPath/employees.json

    @GetMapping(value = "/employees", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public @ResponseBody List<Employee> getEmployees() {

        System.out.println("(Service Side) Geting all employees");
        List<Employee> list = employeeDAO.getAllEmployees();
        return list;
    }

    // URL:
    // http://localhost:8080/SomeContextPath/employee/{empNo}
    // http://localhost:8080/SomeContextPath/employee/{empNo}.xml
    // http://localhost:8080/SomeContextPath/employee/{empNo}.json
    @GetMapping(value = "/employee/{empNo}", //
                    produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})

    public  @ResponseBody Employee getEmployee(@PathVariable("empNo") String empNo) {
        System.out.println("(Service Side) Geting employee : " + empNo);
        return employeeDAO.getEmployee(empNo);
    }

    // URL:
    // http://localhost:8080/SomeContextPath/employee
    // http://localhost:8080/SomeContextPath/employee.xml
    // http://localhost:8080/SomeContextPath/employee.json

    @PostMapping(value = "/employee", //
                 produces = {MediaType.APPLICATION_JSON_VALUE, //
                 MediaType.APPLICATION_XML_VALUE})
    public  @ResponseBody Employee addEmployee(@RequestBody Employee emp) {

        System.out.println("(Service Side) Creating employee: " + emp.getEmpNo());

        return employeeDAO.addEmployee(emp);
    }

    // URL:
    // http://localhost:8080/SomeContextPath/employee
    // http://localhost:8080/SomeContextPath/employee.xml
    // http://localhost:8080/SomeContextPath/employee.json
    @PutMapping(value = "/employee", //
                produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})

    public  @ResponseBody Employee updateEmployee(@RequestBody Employee emp) {

        System.out.println("(Service Side) Editing employee: " + emp.getEmpNo());

        return employeeDAO.updateEmployee(emp);
    }

    // URL:
    // http://localhost:8080/SomeContextPath/employee/{empNo}
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/employee/{empNo}", //
                  produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})

    public @ResponseBody void deleteEmployee(@PathVariable("empNo") String empNo) {


        System.out.println("(Service Side) Deleting employee: " + empNo);

        employeeDAO.deleteEmployee(empNo);


    }
}
