package pl.edu.pg.aui.company.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pg.aui.company.service.CompanyService;
import pl.edu.pg.aui.company.entity.Company;

import java.util.List;

@Component
public class DataInitializer implements InitializingBean {

    private final CompanyService companyService;

    @Autowired
    public DataInitializer(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (companyService.getAllCompanies().isEmpty()) {
            initializeData();
        }
    }

    private void initializeData() {
        List<Company> companies = List.of(
                Company.builder().name("Apple").sector("Technology").build(),
                Company.builder().name("Microsoft").sector("Technology").build(),
                Company.builder().name("Zara").sector("Fashion").build(),
                Company.builder().name("H&M").sector("Fashion").build(),
                Company.builder().name("mBank").sector("Finance").build(),
                Company.builder().name("PKO").sector("Finance").build()
        );

        companies.forEach(companyService::saveCompany);
    }
}
