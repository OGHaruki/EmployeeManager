package pl.edu.pg.aui.demo.dto.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.edu.pg.aui.demo.entity.Employee;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeesResponse {
    List<EmployeeResponse> employees;
}
