package client.feign;

import client.api.RevisionApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("evoice-user-service")
public interface RevisionClient extends RevisionApi {
}
