package userService.configuration;

import dto.response.ResultResponseFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestClientConfiguration {
    @Bean
    public ResultResponseFactory getResultResponseFactory() {
        return new ResultResponseFactory();
    }
}
