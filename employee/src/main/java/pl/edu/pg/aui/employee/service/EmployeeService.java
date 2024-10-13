package pl.edu.pg.aui.employee.service;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.edu.pg.aui.employee.entity.Company;
import pl.edu.pg.aui.employee.entity.Employee;
import pl.edu.pg.aui.employee.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {

    @PersistenceContext
    private EntityManager entityManager;
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<List<Employee>> getEmployeeByName(String employeeName) {
        return employeeRepository.findByName(employeeName);
    }

    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Transactional
    public void deleteEmployee(UUID id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            Company company = employee.getCompany();
            if (company != null) {
                company.removeEmployee(employee);
            }
        }
        employeeRepository.deleteById(id);
        entityManager.flush();
    }
}
