package client.feign;

import client.api.AuthenticationApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("evoice-authentication-service")
public interface AuthenticationClient extends AuthenticationApi {
}
