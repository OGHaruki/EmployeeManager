package pl.edu.pg.aui.employee.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pg.aui.employee.dto.EmployeeCreateRequest;
import pl.edu.pg.aui.employee.dto.EmployeeResponse;
import pl.edu.pg.aui.employee.dto.EmployeeUpdateRequest;
import pl.edu.pg.aui.employee.dto.EmployeesResponse;

public interface EmployeeController {


    @GetMapping("/companies/employees")
    ResponseEntity<EmployeesResponse> getAllEmployees();

    @GetMapping("/companies/{companyName}/employees")
    ResponseEntity<EmployeesResponse> getEmployeesByCompany(@PathVariable String companyName);

    @GetMapping("/companies/{companyName}/employees/{employeeName}")
    ResponseEntity<EmployeeResponse> getEmployeeByName(@PathVariable String companyName, @PathVariable String employeeName);

    @PutMapping("/companies/{companyName}/employees")
    ResponseEntity<String> addEmployee(@PathVariable String companyName, EmployeeCreateRequest employeeCreateRequest);

    @DeleteMapping("/companies/{companyName}/employees/{employeeName}")
    ResponseEntity<String> deleteEmployee(@PathVariable String companyName, @PathVariable String employeeName);

    @PatchMapping("/companies/{companyName}/employees/{employeeName}")
    ResponseEntity<String> updateEmployee(@PathVariable String companyName, @PathVariable String employeeName, EmployeeUpdateRequest employeeUpdateRequest);
}
