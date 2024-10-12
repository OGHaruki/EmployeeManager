package pl.edu.pg.aui.demo.dto.company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyResponse {
    private UUID id;
    private String name;
    private String sector;
}
