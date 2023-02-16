package authenticationService.configuration;

import client.feign.UserClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = UserClient.class)
public class FeignConfiguration {
}
