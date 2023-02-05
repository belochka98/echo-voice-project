package authenticationService.controller;

import authenticationService.service.impl.AuthenticationServiceImpl;
import client.controller.AuthenticationController;
import component.response.ResultResponseFactory;
import dto.response.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Validated
@RestController
@CrossOrigin(methods = {GET, POST, DELETE})
@RequiredArgsConstructor
public class AuthenticationControllerImpl implements AuthenticationController {
    private final ResultResponseFactory responseFactory;
    private final AuthenticationServiceImpl service;

    @Override
    public ResultResponse<String> registration(@RequestParam String userName, @RequestParam String password) {
        return responseFactory.createResponseOk(
                service.registration(userName, password)
        );
    }

    @Override
    public ResultResponse<String> authenticate(@RequestParam String userName, @RequestParam String password) {
        return responseFactory.createResponseOk(
                service.authenticate(userName, password)
        );
    }
}
