package pl.edu.pg.aui.demo.dto.company;

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
