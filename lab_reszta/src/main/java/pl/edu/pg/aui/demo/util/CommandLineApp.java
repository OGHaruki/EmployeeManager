package pl.edu.pg.aui.demo.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.edu.pg.aui.demo.entity.Company;
import pl.edu.pg.aui.demo.entity.Employee;
import pl.edu.pg.aui.demo.service.CompanyService;
import pl.edu.pg.aui.demo.service.EmployeeService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class CommandLineApp implements CommandLineRunner {

    private final CompanyService companyService;
    private final EmployeeService employeeService;
    private final Scanner scanner = new Scanner(System.in);

    public CommandLineApp(CompanyService companyService, EmployeeService employeeService) {
        this.companyService = companyService;
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) throws Exception {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("1. List companies");
            System.out.println("2. List employees");
            System.out.println("3. Add employee");
            System.out.println("4. Delete employee");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch(option) {
                case 1:
                    List<Company> companies = companyService.getAllCompanies();
                    companies.forEach(System.out::println);
                    break;
                case 2:
                    employeeService.getAllEmployees().forEach(System.out::println);
                    break;
                case 3:
                    System.out.print("Enter employee name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter employee seniority: ");
                    String seniority = scanner.nextLine();

                    System.out.print("Select company: ");
                    companyService.getAllCompanies().forEach(System.out::println);
                    String companyName = scanner.nextLine();
                    Optional<Company> company = companyService.getCompanyByName(companyName);
                    if (company.isEmpty()) {
                        System.out.println("Company not found.");
                        break;
                    }
                    Employee newEmployee = Employee.builder()
                            .name(name)
                            .seniority(seniority)
                            .company(company.get())
                            .build();
                    company.get().addEmployee(newEmployee);
                    employeeService.saveEmployee(newEmployee);
                    System.out.println("New employee added successfully.");
                    break;
                case 4:
                    System.out.print("Enter employee name: ");
                    String employeeName = scanner.nextLine();
                    System.out.print("Enter employee seniority: ");
                    String employeeSeniority = scanner.nextLine(); // Dodane: wprowadzenie stażu
                    System.out.print("Enter company name: ");
                    companyName = scanner.nextLine();

                    Optional<List<Employee>> employees = employeeService.getEmployeeByName(employeeName);
                    if (employees.isPresent() && !employees.get().isEmpty()) {
                        Optional<Company> companyOptional = companyService.getCompanyByName(companyName);
                        if (companyOptional.isPresent()) {
                            Company company1 = companyOptional.get();
                            Employee employeeToRemove = employees.get().stream()
                                    .filter(emp -> emp.getSeniority().equalsIgnoreCase(employeeSeniority) && emp.getCompany().equals(company1))
                                    .findFirst()
                                    .orElse(null);

                            if (employeeToRemove != null) {
                                company1.removeEmployee(employeeToRemove);
                                employeeService.deleteEmployee(employeeToRemove.getUuid());
                                System.out.println("Employee deleted successfully.");
                            } else {
                                System.out.println("Employee with specified seniority not found in the company.");
                            }
                        } else {
                            System.out.println("Company not found.");
                        }
                    } else {
                        System.out.println("Employee not found.");
                    }
                    break;
                case 5:
                    isRunning = false;
                    break;
            }
        }
        System.exit(0);
    }
}