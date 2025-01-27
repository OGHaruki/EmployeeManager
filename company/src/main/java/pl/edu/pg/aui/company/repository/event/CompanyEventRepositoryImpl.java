package pl.edu.pg.aui.company.repository.event;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Repository
public class CompanyEventRepositoryImpl implements CompanyEventRepository {

    @Qualifier("employeeRestTemplate")
    private final RestTemplate employeeRestTemplate;

    public CompanyEventRepositoryImpl(RestTemplate employeeRestTemplate) {
        this.employeeRestTemplate = employeeRestTemplate;
    }
    @Override
    public void sendCreateCompanyEvent(UUID companyId) {
        employeeRestTemplate.put("/companies/" + companyId, null);
    }

    @Override
    public void sendDeleteCompanyEvent(UUID companyId) {
        employeeRestTemplate.delete("/companies/" + companyId);
    }
}
