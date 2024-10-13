package pl.edu.pg.aui.employee.dto;

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
