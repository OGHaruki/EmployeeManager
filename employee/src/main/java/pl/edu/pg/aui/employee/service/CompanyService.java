package pl.edu.pg.aui.employee.service;

import org.springframework.stereotype.Service;
import pl.edu.pg.aui.employee.entity.Company;
import pl.edu.pg.aui.employee.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Optional<Company> getCompanyById(UUID id) {
        return companyRepository.findById(id);
    }

    public Optional<Company> getCompanyByName(String companyName) {
        return companyRepository.findByName(companyName);
    }

    public void saveCompany(Company company) {
        companyRepository.save(company);
    }

    public void deleteCompany(UUID id) {
        companyRepository.deleteById(id);
    }
}