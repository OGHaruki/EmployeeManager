package pl.edu.pg.aui.demo.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pg.aui.demo.dto.company.CompaniesResponse;
import pl.edu.pg.aui.demo.dto.company.CompanyCreateRequest;
import pl.edu.pg.aui.demo.dto.company.CompanyResponse;
import pl.edu.pg.aui.demo.dto.company.CompanyUpdateRequest;

public interface CompanyController {

    @GetMapping("/companies")
    ResponseEntity<CompaniesResponse> getCompanies();

    @GetMapping("/companies/{companyName}")
    ResponseEntity<CompanyResponse> getCompanyByName(@PathVariable String companyName);

    @PostMapping("/companies")
    ResponseEntity<String> addCompany(CompanyCreateRequest companyCreateRequest);

    @DeleteMapping("/companies/{companyName}")
    ResponseEntity<String> deleteCompany(@PathVariable String companyName);

    @PutMapping("/companies/{companyName}")
    ResponseEntity<String> updateCompany(@PathVariable String companyName, @RequestBody CompanyUpdateRequest companyUpdateRequest);

}
