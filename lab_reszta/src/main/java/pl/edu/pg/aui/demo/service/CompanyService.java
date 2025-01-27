package pl.edu.pg.aui.demo.service;

import org.springframework.stereotype.Service;
import pl.edu.pg.aui.demo.entity.Company;
import pl.edu.pg.aui.demo.repository.CompanyRepository;

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