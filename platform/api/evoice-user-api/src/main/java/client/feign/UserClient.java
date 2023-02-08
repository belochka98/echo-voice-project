package client.feign;

import client.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("evoice-user-service/users")
public interface UserClient extends UserApi {
}
