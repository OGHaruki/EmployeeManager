package pl.edu.pg.aui.company.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyCreateRequest {
    private String name;
    private String sector;
}
