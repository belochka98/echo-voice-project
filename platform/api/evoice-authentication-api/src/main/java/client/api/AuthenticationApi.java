package client.api;

import dto.AuthenticationUserDto;
import dto.response.ResultResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface AuthenticationApi {
    @PostMapping("/registration")
    ResultResponse<String> registration(@RequestBody AuthenticationUserDto userDto);

    @PostMapping("/authenticate")
    ResultResponse<String> authenticate(@RequestParam String userName, @RequestParam String password);
}
