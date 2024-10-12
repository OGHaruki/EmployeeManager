package pl.edu.pg.aui.demo.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pg.aui.demo.service.CompanyService;
import pl.edu.pg.aui.demo.service.EmployeeService;
import pl.edu.pg.aui.demo.entity.Company;
import pl.edu.pg.aui.demo.entity.Employee;

import java.util.List;

@Component
public class DataInitializer implements InitializingBean {

    private final CompanyService companyService;
    private final EmployeeService employeeService;

    @Autowired
    public DataInitializer(CompanyService companyService, EmployeeService employeeService) {
        this.companyService = companyService;
        this.employeeService = employeeService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (companyService.getAllCompanies().isEmpty() && employeeService.getAllEmployees().isEmpty()) {
            initializeData();
        }
    }

    private void initializeData() {
        List<Company> companies = List.of(
                Company.builder().name("Apple").sector("Technology").build(),
                Company.builder().name("Microsoft").sector("Technology").build(),
                Company.builder().name("Zara").sector("Fashion").build(),
                Company.builder().name("H&M").sector("Fashion").build(),
                Company.builder().name("mBank").sector("Finance").build(),
                Company.builder().name("PKO").sector("Finance").build()
        );

        companies.forEach(companyService::saveCompany);

        List<Employee> employees = List.of(
                Employee.builder().name("Jan Kowalski").seniority("Junior").company(companies.get(0)).build(),
                Employee.builder().name("Anna Nowak").seniority("Mid").company(companies.get(1)).build(),
                Employee.builder().name("Piotr Wiśniewski").seniority("Senior").company(companies.get(2)).build(),
                Employee.builder().name("Katarzyna Dąbrowska").seniority("Junior").company(companies.get(3)).build(),
                Employee.builder().name("Michał Lewandowski").seniority("Mid").company(companies.get(4)).build(),
                Employee.builder().name("Karolina Woźniak").seniority("Senior").company(companies.get(5)).build()
        );

        for (Employee employee : employees) {
            Company company = employee.getCompany();
            company.addEmployee(employee);
        }

        employees.forEach(employeeService::saveEmployee);
    }
}
