package pl.edu.pg.aui.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pg.aui.demo.entity.Company;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {

    Optional<Company> findByName(String companyName);
}
