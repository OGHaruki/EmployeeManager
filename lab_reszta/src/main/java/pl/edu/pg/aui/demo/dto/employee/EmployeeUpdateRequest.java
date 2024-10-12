package pl.edu.pg.aui.demo.dto.employee;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeUpdateRequest {
    private String name;
    private String seniority;
}
