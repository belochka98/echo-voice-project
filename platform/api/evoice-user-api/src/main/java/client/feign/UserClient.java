package client.feign;

import client.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("evoice-user-service")
public interface UserClient extends UserApi {
}
