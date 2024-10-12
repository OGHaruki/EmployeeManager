package pl.edu.pg.aui.demo.dto.employee;

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
