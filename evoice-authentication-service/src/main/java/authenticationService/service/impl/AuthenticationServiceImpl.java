package authenticationService.service.impl;

import authenticationService.component.UserAuthentication;
import authenticationService.mapper.UserMapper;
import authenticationService.repository.UserCredentialsRepository;
import authenticationService.service.AuthentificationService;
import client.feign.UserClient;
import dto.UserDto;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthentificationService {
    private final UserClient userClient;
    private final UserMapper userMapper;
    private final UserCredentialsRepository userCredentialsRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public String registration(@NotEmpty String userName, @NotEmpty String password) {
        final var userDto = userClient.saveUser(
                UserDto.builder()
                        .active(true)
                        .name(userName)
                        .surname(password)
                        .patronymic(null)
                        .dateBirthday(null)
                        .phone(null)
                        .sex(true)
                        .build()
        ).getData();

        final var userCredentials = userCredentialsRepository.save(
                userMapper.apply(userDto, passwordEncoder.encode(password))
        );

        return jwtService.generateToken(
                userMapper.apply(userDto, userCredentials)
        );
    }

    @Override
    public String authenticate(@NotEmpty String userName, @NotEmpty String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userName,
                        password
                )
        );

        return jwtService.generateToken(this.getByUserName(userName));
    }

    @Override
    public UserAuthentication getByUserName(String userName) {
        return userCredentialsRepository.findByName(userName).map(
                uc -> userMapper.apply(userClient.getUserById(uc.getId()).getData(), uc)
        ).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User not found by username: %s", userName)
        ));
    }
}
