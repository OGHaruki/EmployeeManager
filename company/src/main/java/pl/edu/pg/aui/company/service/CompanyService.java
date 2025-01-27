package pl.edu.pg.aui.company.service;

import org.springframework.stereotype.Service;
import pl.edu.pg.aui.company.entity.Company;
import pl.edu.pg.aui.company.repository.company.CompanyRepository;
import pl.edu.pg.aui.company.repository.event.CompanyEventRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyEventRepository eventRepository;

    public CompanyService(CompanyRepository companyRepository, CompanyEventRepository eventRepository) {
        this.companyRepository = companyRepository;
        this.eventRepository = eventRepository;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Optional<Company> getCompanyByName(String companyName) {
        return companyRepository.findByName(companyName);
    }

    public void saveCompany(Company company) {
        companyRepository.save(company);
        eventRepository.sendCreateCompanyEvent(company.getUuid());
    }

    public void deleteCompany(UUID id) {
        companyRepository.deleteById(id);
        eventRepository.sendDeleteCompanyEvent(id);
    }

    public void updateCompany(Company company) {
        companyRepository.save(company);
    }
}