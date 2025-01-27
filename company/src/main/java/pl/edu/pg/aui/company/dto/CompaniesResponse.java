package pl.edu.pg.aui.company.dto;

import lombok.*;
import pl.edu.pg.aui.company.entity.Company;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompaniesResponse {
    List<CompanyResponse> companies;
}
