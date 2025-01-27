package pl.edu.pg.aui.company.repository.company;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pg.aui.company.entity.Company;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {

    Optional<Company> findByName(String companyName);
}
