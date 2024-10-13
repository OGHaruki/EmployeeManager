package pl.edu.pg.aui.employee.controller.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pg.aui.employee.controller.api.EmployeeController;
import pl.edu.pg.aui.employee.dto.EmployeeCreateRequest;
import pl.edu.pg.aui.employee.dto.EmployeeResponse;
import pl.edu.pg.aui.employee.dto.EmployeeUpdateRequest;
import pl.edu.pg.aui.employee.dto.EmployeesResponse;
import pl.edu.pg.aui.employee.entity.Company;
import pl.edu.pg.aui.employee.entity.Employee;
import pl.edu.pg.aui.employee.service.CompanyService;
import pl.edu.pg.aui.employee.service.EmployeeService;


import java.util.List;
import java.util.Optional;


@RestController
public class EmployeeControllerImpl implements EmployeeController {

    private final EmployeeService employeeService;
    private final CompanyService companyService;

    public EmployeeControllerImpl(EmployeeService employeeService, CompanyService companyService) {
        this.employeeService = employeeService;
        this.companyService = companyService;
    }

    @Override
    public ResponseEntity<EmployeesResponse> getAllEmployees() {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        if(allEmployees.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<EmployeeResponse> employees = allEmployees.stream()
                .map(employee -> EmployeeResponse.builder()
                        .id(employee.getUuid())
                        .name(employee.getName())
                        .seniority(employee.getSeniority())
                        .companyName(employee.getCompany().getName())
                        .build())
                .toList();
        EmployeesResponse employeesResponse = new EmployeesResponse(employees);
        return ResponseEntity.ok(employeesResponse);
    }
    @Override
    public ResponseEntity<EmployeesResponse> getEmployeesByCompany(String companyName) {
        if(companyService.getCompanyByName(companyName).isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<Employee> allEmployees = employeeService.getAllEmployees();
            List<EmployeeResponse> companyEmployees = allEmployees.stream()
                    .filter(employee -> employee.getCompany().getName().equals(companyName))
                    .map(employee -> EmployeeResponse.builder()
                            .id(employee.getUuid())
                            .name(employee.getName())
                            .seniority(employee.getSeniority())
                            .companyName(employee.getCompany().getName())
                            .build())
                    .toList();
            if(companyEmployees.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                EmployeesResponse employeesResponse = new EmployeesResponse(companyEmployees);
                return ResponseEntity.ok(employeesResponse);
            }
        }
    }

    @Override
    public ResponseEntity<EmployeeResponse> getEmployeeByName(String companyName, String employeeName) {
        if(companyService.getCompanyByName(companyName).isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<EmployeeResponse> companyEmployees = employeeService.getAllEmployees().stream()
                    .filter(employee -> employee.getCompany().getName().equals(companyName))
                    .map(employee -> EmployeeResponse.builder()
                            .id(employee.getUuid())
                            .name(employee.getName())
                            .seniority(employee.getSeniority())
                            .companyName(employee.getCompany().getName())
                            .build())
                    .toList();

            Optional<EmployeeResponse> employee = companyEmployees.stream()
                    .filter(employeeResponse -> employeeResponse.getName().equals(employeeName))
                    .findFirst();
            if(employee.isEmpty()) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(employee.get());
            }
        }
    }

    @Override
    public ResponseEntity<String> addEmployee(@PathVariable String companyName, @RequestBody EmployeeCreateRequest employeeCreateRequest) {
        Optional<Company> companyOptional = companyService.getCompanyByName(companyName);

        if (companyOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            Company company = companyOptional.get();
            Employee employee = Employee.builder()
                    .name(employeeCreateRequest.getName())
                    .seniority(employeeCreateRequest.getSeniority())
                    .company(company)
                    .build();
            try {
                employeeService.saveEmployee(employee);
                return ResponseEntity.ok("Employee added successfully!");
            } catch (Exception e) {
                return ResponseEntity.status(500).body("Error while adding employee: " + e.getMessage());
            }
        }
    }


    @Override
    public ResponseEntity<String> deleteEmployee(@PathVariable String companyName, @PathVariable String employeeName) {
        if(companyService.getCompanyByName(companyName).isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<Employee> companyEmployees = employeeService.getAllEmployees().stream()
                    .filter(employee -> employee.getCompany().getName().equals(companyName))
                    .toList();
            Optional<Employee> employee = companyEmployees.stream()
                    .filter(employee1 -> employee1.getName().equals(employeeName))
                    .findFirst();
            if(employee.isEmpty()) {
                return ResponseEntity.notFound().build();
            } else {
                employeeService.deleteEmployee(employee.get().getUuid());
                return ResponseEntity.ok("Employee deleted successfully!");
            }
        }
    }

    @Override
    public ResponseEntity<String> updateEmployee(@PathVariable String companyName, @PathVariable String employeeName, @RequestBody EmployeeUpdateRequest employeeUpdateRequest) {
        if(companyService.getCompanyByName(companyName).isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<Employee> companyEmployees = employeeService.getAllEmployees().stream()
                    .filter(employee -> employee.getCompany().getName().equals(companyName))
                    .toList();
            Optional<Employee> employee = companyEmployees.stream()
                    .filter(employee1 -> employee1.getName().equals(employeeName))
                    .findFirst();
            if(employee.isEmpty()) {
                return ResponseEntity.notFound().build();
            } else {
                employee.get().setName(employeeUpdateRequest.getName());
                employee.get().setSeniority(employeeUpdateRequest.getSeniority());
                employeeService.saveEmployee(employee.get());
                return ResponseEntity.ok("Employee updated successfully!");
            }
        }
    }
}
