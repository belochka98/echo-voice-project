package authenticationService.service;

import authenticationService.component.UserAuthentication;

public interface AuthentificationService {
    String registration(String userName, String password);

    String authenticate(String userName, String password);

    UserAuthentication getByUserName(String userName);
}
