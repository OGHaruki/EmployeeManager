package pl.edu.pg.aui.company.controller.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pg.aui.company.controller.api.CompanyController;
import pl.edu.pg.aui.company.dto.CompaniesResponse;
import pl.edu.pg.aui.company.dto.CompanyCreateRequest;
import pl.edu.pg.aui.company.dto.CompanyResponse;
import pl.edu.pg.aui.company.dto.CompanyUpdateRequest;
import pl.edu.pg.aui.company.entity.Company;
import pl.edu.pg.aui.company.service.CompanyService;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class CompanyControllerImpl implements CompanyController {

    private final CompanyService companyService;

    public CompanyControllerImpl(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Override
    public ResponseEntity<CompaniesResponse> getCompanies() {
        List<Company> companies = companyService.getAllCompanies();
        if(companies.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<CompanyResponse> companyResponses = companies.stream()
                    .map(company -> new CompanyResponse(company.getUuid(), company.getName(), company.getSector()))
                    .collect(Collectors.toList());
            CompaniesResponse companiesResponse = new CompaniesResponse(companyResponses);
            return ResponseEntity.ok(companiesResponse);
        }
    }

    @Override
    public ResponseEntity<CompanyResponse> getCompanyByName(String companyName) {
        Optional<Company> company = companyService.getCompanyByName(companyName);
        if(company.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(new CompanyResponse(company.get().getUuid(), company.get().getName(), company.get().getSector()));
        }
    }

    @Override
    public ResponseEntity<Void> addCompany(@RequestBody CompanyCreateRequest companyCreateRequest) {
        Company company = Company.builder().name(companyCreateRequest.getName()).sector(companyCreateRequest.getSector()).build();
        if(companyService.getCompanyByName(company.getName()).isPresent()) {
            return ResponseEntity.badRequest().build();
        } else {
            companyService.saveCompany(company);
            return ResponseEntity.ok().build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteCompany(String companyName) {
        Company company = companyService.getCompanyByName(companyName).orElse(null);
        if(company == null) {
            return ResponseEntity.notFound().build();
        } else {
            companyService.deleteCompany(company.getUuid());
            return ResponseEntity.noContent().build();
        }
    }

    @Override
    public ResponseEntity<Void> updateCompany(String companyName, CompanyUpdateRequest companyUpdateRequest) {
        try {
            Optional<Company> company = companyService.getCompanyByName(companyName);
            if (company.isEmpty()) {
                return ResponseEntity.notFound().build();
            } else {
                company.get().setName(companyUpdateRequest.getName());
                company.get().setSector(companyUpdateRequest.getSector());
                companyService.updateCompany(company.get());
                return ResponseEntity.ok().build();
            }
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
