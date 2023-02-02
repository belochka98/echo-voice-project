package client.controller;

import client.api.UserApi;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/users")
public interface UserController extends UserApi {
}
