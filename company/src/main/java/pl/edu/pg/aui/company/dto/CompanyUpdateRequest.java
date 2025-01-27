package pl.edu.pg.aui.company.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyUpdateRequest {
    private String name;
    private String sector;
}
