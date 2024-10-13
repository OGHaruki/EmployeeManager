package pl.edu.pg.aui.company.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pg.aui.company.dto.CompaniesResponse;
import pl.edu.pg.aui.company.dto.CompanyCreateRequest;
import pl.edu.pg.aui.company.dto.CompanyResponse;
import pl.edu.pg.aui.company.dto.CompanyUpdateRequest;

public interface CompanyController {

    @GetMapping("/companies")
    ResponseEntity<CompaniesResponse> getCompanies();

    @GetMapping("/companies/{companyName}")
    ResponseEntity<CompanyResponse> getCompanyByName(@PathVariable String companyName);

    @PutMapping("/companies")
    ResponseEntity<String> addCompany(CompanyCreateRequest companyCreateRequest);

    @DeleteMapping("/companies/{companyName}")
    ResponseEntity<String> deleteCompany(@PathVariable String companyName);

    @PutMapping("/companies/{companyName}")
    ResponseEntity<String> updateCompany(@PathVariable String companyName, @RequestBody CompanyUpdateRequest companyUpdateRequest);

}
