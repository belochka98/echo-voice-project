package client.feign;

import client.api.AccountApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "evoice-user-service", url = "")
public interface AccountClient extends AccountApi {
}
