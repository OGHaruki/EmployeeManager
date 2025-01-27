package pl.edu.pg.aui.demo.dto.company;

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
