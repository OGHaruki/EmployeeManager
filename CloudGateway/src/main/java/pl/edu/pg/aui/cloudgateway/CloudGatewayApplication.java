package pl.edu.pg.aui.cloudgateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class CloudGatewayApplication {

    private final String companiesUri;
    private final String employeesUri;

    public CloudGatewayApplication(@Value("${company-service.url}") String companiesUri,
                                   @Value("${employee-service.url}") String employeesUri) {
        this.companiesUri = companiesUri;
        this.employeesUri = employeesUri;
    }

    public static void main(String[] args) {
        SpringApplication.run(CloudGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route("companies", r -> r
                        .path(
                                "/companies",
                                "/companies/{companyName}")
                        .uri(companiesUri))
                .route("employees", r -> r
                        .path(
                                "/companies/employees",
                                "/companies/{companyName}/employees",
                                "/companies/{companyName}/employees/{employeeName}",
                                "/companies/{companyName}/employees",
                                "/companies/{companyName}/employees/{employeeName}",
                                "/companies/{companyName}/employees/{employeeName}")
                        .uri(employeesUri))
                .build();
    }
}
