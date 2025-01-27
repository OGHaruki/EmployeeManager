package pl.edu.pg.aui.company.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    @Qualifier("defaultRestTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
