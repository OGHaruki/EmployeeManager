package pl.edu.pg.aui.demo.dto.company;

import lombok.*;
import pl.edu.pg.aui.demo.entity.Company;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompaniesResponse {
    List<CompanyResponse> companies;
}
