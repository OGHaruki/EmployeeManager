import dto.EmployeeDto;
import entity.Company;
import entity.Employee;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Company> companies = new ArrayList<>();

        companies = List.of(
                Company.builder().name("Apple").sector("Technology").build(),
                Company.builder().name("Microsoft").sector("Technology").build(),
                Company.builder().name("Zara").sector("Fashion").build(),
                Company.builder().name("H&M").sector("Fashion").build(),
                Company.builder().name("mBank").sector("Finance").build(),
                Company.builder().name("PKO").sector("Finance").build()
        );

        Employee employee1 = Employee.builder().name("Jan Kowalski").seniority("Junior").company(companies.get(0)).build();
        Employee employee2 = Employee.builder().name("Anna Nowak").seniority("Mid").company(companies.get(1)).build();
        Employee employee3 = Employee.builder().name("Piotr Wiśniewski").seniority("Senior").company(companies.get(2)).build();
        Employee employee4 = Employee.builder().name("Katarzyna Dąbrowska").seniority("Junior").company(companies.get(3)).build();
        Employee employee5 = Employee.builder().name("Michał Lewandowski").seniority("Mid").company(companies.get(4)).build();
        Employee employee6 = Employee.builder().name("Karolina Woźniak").seniority("Senior").company(companies.get(5)).build();
        Employee employee7 = Employee.builder().name("Adam Kowalczyk").seniority("Junior").company(companies.get(5)).build();

        companies.get(0).setEmployees(List.of(employee1));
        companies.get(1).setEmployees(List.of(employee2));
        companies.get(2).setEmployees(List.of(employee3));
        companies.get(3).setEmployees(List.of(employee4));
        companies.get(4).setEmployees(List.of(employee5));
        companies.get(5).setEmployees(List.of(employee6, employee7));


        System.out.println("Task 1:");
        companies.forEach(company -> {
            System.out.println("Company: " + company.getName());
            company.getEmployees().forEach(employee -> {
                System.out.println("Employee: " + employee.getName());
            });
        });

        System.out.println("\nTask 2:");
        Set<Employee> allEmployees = companies.stream()
                .flatMap(company -> company.getEmployees().stream())
                .collect(Collectors.toSet());

        allEmployees.forEach(employee ->
                System.out.println("Employee: " + employee.getName())
        );

        System.out.println("\nTask 3:");
        allEmployees.stream()
                .filter(employee -> employee.getSeniority().equals("Senior"))
                .sorted(Comparator.comparing(Employee::getName))
                .forEach(employee ->
                        System.out.println("Employee: " + employee.getName() + " Seniority: " + employee.getSeniority())
                );

        System.out.println("\nTask 4:");
        List<EmployeeDto> employeeDtos = allEmployees.stream()
                .map(employee -> new EmployeeDto(employee.getName(), employee.getSeniority(), employee.getCompany().getName()))
                .sorted()
                .toList();

        employeeDtos.forEach(employeeDto ->
                System.out.println("Employee: " + employeeDto.getName() + " Seniority: " + employeeDto.getSeniority() + " Company: " + employeeDto.getCompanyName())
        );

        System.out.println("\nTask 5:");
        serializeCompanies(companies);
        List<Company> deserializedCompanies = deserializeCompanies();

        if (deserializedCompanies != null) {
            deserializedCompanies.forEach(company -> {
                System.out.println("Company: " + company.getName());
                company.getEmployees().forEach(employee -> {
                    System.out.println("Employee: " + employee.getName());
                });
            });
        }

        System.out.println("\nTask 6:");
        int[] poolSize = {2, 4, 8};

        for(int size : poolSize) {
            System.out.println("Pool size: " + size);
            long startTime = System.nanoTime();

            ForkJoinPool forkJoinPool = getForkJoinPool(size, companies);
            try {
                if (forkJoinPool.awaitTermination(60, java.util.concurrent.TimeUnit.SECONDS)) {
                    long endTime = System.nanoTime();
                    long duration = endTime - startTime;
                    System.out.println("Duration: " + duration / 1000000 + " ms");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static ForkJoinPool getForkJoinPool(int size, List<Company> companies) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(size);

        forkJoinPool.submit(() ->
                companies.parallelStream().forEach((company) -> {
                    company.getEmployees().forEach((employee) -> {
                        try {
                            System.out.println("Employee: " + employee.getName());
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    });
                }));
        forkJoinPool.shutdown();
        return forkJoinPool;
    }

    public static void serializeCompanies(List<Company> companies) {
        try {
            FileOutputStream fileOut = new FileOutputStream("companies.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(companies);
            out.flush();
            out.close();
            System.out.println("Serialization completed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Company> deserializeCompanies() {
        try {
            FileInputStream fileIn = new FileInputStream("companies.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            List<Company> companies = (List<Company>) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Deserialization completed.");
            return companies;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
