package client.controller;

import client.api.AccountApi;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/accounts")
public interface AccountController extends AccountApi {
}
