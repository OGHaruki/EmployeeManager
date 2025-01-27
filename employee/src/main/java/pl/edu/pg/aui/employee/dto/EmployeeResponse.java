package pl.edu.pg.aui.employee.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {
    private UUID id;
    private String name;
    private String seniority;
    private String companyName;
}
