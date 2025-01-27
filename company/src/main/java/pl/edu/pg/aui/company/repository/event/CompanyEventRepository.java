package pl.edu.pg.aui.company.repository.event;

import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface CompanyEventRepository {

    void sendCreateCompanyEvent(UUID companyId);

    void sendDeleteCompanyEvent(UUID companyId);
}
