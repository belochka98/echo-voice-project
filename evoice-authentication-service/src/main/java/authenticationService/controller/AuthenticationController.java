package authenticationService.controller;

import authenticationService.service.impl.AuthenticationServiceImpl;
import client.api.AuthenticationApi;
import component.response.ResultResponseFactory;
import dto.AuthenticationUserDto;
import dto.response.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Validated
@RestController
@RequestMapping("/auth")
@CrossOrigin(methods = {GET, POST, DELETE})
@RequiredArgsConstructor
public class AuthenticationController implements AuthenticationApi {
    private final ResultResponseFactory responseFactory;
    private final AuthenticationServiceImpl authenticationService;

    @Override
    public ResultResponse<String> registration(@RequestBody AuthenticationUserDto userDto) {
        return responseFactory.createResponseOk(
                authenticationService.registration(userDto)
        );
    }

    @Override
    public ResultResponse<String> authenticate(@RequestParam String userName, @RequestParam String password) {
        return responseFactory.createResponseOk(
                authenticationService.authenticate(userName, password)
        );
    }
}
