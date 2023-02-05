package client.controller;

import client.api.AuthenticationApi;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
public interface AuthenticationController extends AuthenticationApi {
}
