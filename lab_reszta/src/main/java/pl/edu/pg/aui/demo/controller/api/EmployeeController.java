package pl.edu.pg.aui.demo.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pg.aui.demo.dto.employee.EmployeeCreateRequest;
import pl.edu.pg.aui.demo.dto.employee.EmployeeResponse;
import pl.edu.pg.aui.demo.dto.employee.EmployeeUpdateRequest;
import pl.edu.pg.aui.demo.dto.employee.EmployeesResponse;

public interface EmployeeController {

    @GetMapping("/companies/{companyName}/employees")
    ResponseEntity<EmployeesResponse> getEmployeesByCompany(@PathVariable String companyName);

    @GetMapping("/companies/{companyName}/employees/{employeeName}")
    ResponseEntity<EmployeeResponse> getEmployeeByName(@PathVariable String companyName, @PathVariable String employeeName);

    @PostMapping("/companies/{companyName}/employees")
    ResponseEntity<String> addEmployee(@PathVariable String companyName, EmployeeCreateRequest employeeCreateRequest);

    @DeleteMapping("/companies/{companyName}/employees/{employeeName}")
    ResponseEntity<String> deleteEmployee(@PathVariable String companyName, @PathVariable String employeeName);

    @PutMapping("/companies/{companyName}/employees/{employeeName}")
    ResponseEntity<String> updateEmployee(@PathVariable String companyName, @PathVariable String employeeName, EmployeeUpdateRequest employeeUpdateRequest);
}
