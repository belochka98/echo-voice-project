package client.feign;

import client.api.AccountApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("evoice-user-service")
public interface AccountClient extends AccountApi {
}
