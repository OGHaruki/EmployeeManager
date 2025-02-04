package pl.edu.pg.aui.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pg.aui.employee.entity.Employee;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    Optional<List<Employee>> findByName(String employeeName);

}
