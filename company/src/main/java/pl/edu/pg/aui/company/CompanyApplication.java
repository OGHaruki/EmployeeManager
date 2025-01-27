package pl.edu.pg.aui.company;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CompanyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanyApplication.class, args);
	}

	@Bean
	@Qualifier("employeeRestTemplate")
	public RestTemplate employeeRestTemplate() {
		return new RestTemplateBuilder()
				.rootUri("http://employee-service:8083/api")
				.build();
	}
}
