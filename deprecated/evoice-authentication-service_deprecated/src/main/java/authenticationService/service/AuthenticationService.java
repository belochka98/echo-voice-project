package authenticationService.service;

import dto.AuthenticationUserDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public interface AuthenticationService {
    String registration(@NotNull AuthenticationUserDto userDto);

    String authenticate(@NotEmpty String userName, @NotEmpty String password);
}
