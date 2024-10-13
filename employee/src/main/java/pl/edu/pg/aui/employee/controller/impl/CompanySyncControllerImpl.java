package pl.edu.pg.aui.employee.controller.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pg.aui.employee.controller.api.CompanySyncController;
import pl.edu.pg.aui.employee.entity.Company;
import pl.edu.pg.aui.employee.service.CompanyService;

import java.util.UUID;

@RestController
public class CompanySyncControllerImpl implements CompanySyncController {

    private final CompanyService companyService;

    public CompanySyncControllerImpl(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Override
    public ResponseEntity<String> addCompany(UUID companyId) {
        if(companyService.getCompanyById(companyId).isPresent()) {
            return ResponseEntity.badRequest().body("Company already exists");
        } else {
            Company newCompany = Company.builder().uuid(companyId).build();
            companyService.saveCompany(newCompany);
            return ResponseEntity.ok("Company added");
        }
    }

    @Override
    public ResponseEntity<String> deleteCategory(UUID companyId) {
        if(companyService.getCompanyById(companyId).isEmpty()) {
            return ResponseEntity.badRequest().body("Company does not exist");
        } else {
            companyService.deleteCompany(companyId);
            return ResponseEntity.ok("Company deleted");
        }
    }
}
