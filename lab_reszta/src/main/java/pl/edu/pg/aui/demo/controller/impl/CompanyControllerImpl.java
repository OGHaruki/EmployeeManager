package pl.edu.pg.aui.demo.controller.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pg.aui.demo.controller.api.CompanyController;
import pl.edu.pg.aui.demo.dto.company.CompaniesResponse;
import pl.edu.pg.aui.demo.dto.company.CompanyCreateRequest;
import pl.edu.pg.aui.demo.dto.company.CompanyResponse;
import pl.edu.pg.aui.demo.dto.company.CompanyUpdateRequest;
import pl.edu.pg.aui.demo.entity.Company;
import pl.edu.pg.aui.demo.service.CompanyService;

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
    public ResponseEntity<String> addCompany(@RequestBody CompanyCreateRequest companyCreateRequest) {
        Company company = Company.builder().name(companyCreateRequest.getName()).sector(companyCreateRequest.getSector()).build();
        if(companyService.getCompanyByName(company.getName()).isPresent()) {
            return ResponseEntity.badRequest().body("Company with this name already exists!");
        } else {
            companyService.saveCompany(company);
            return ResponseEntity.ok("Company added successfully!");
        }
    }

    @Override
    public ResponseEntity<String> deleteCompany(String companyName) {
        Company company = companyService.getCompanyByName(companyName).orElse(null);
        if(company == null) {
            return ResponseEntity.notFound().build();
        } else {
            companyService.deleteCompany(company.getUuid());
            return ResponseEntity.ok("Company deleted successfully!");
        }
    }

    @Override
    public ResponseEntity<String> updateCompany(String companyName, CompanyUpdateRequest companyUpdateRequest) {
        Optional<Company> company = companyService.getCompanyByName(companyName);
        if(company.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            company.get().setName(companyUpdateRequest.getName());
            company.get().setSector(companyUpdateRequest.getSector());
            companyService.saveCompany(company.get());
            return ResponseEntity.ok("Company updated successfully!");
        }
    }
}
