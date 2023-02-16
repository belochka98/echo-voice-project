package authenticationService.service.impl;

import authenticationService.entity.UserAccount;
import authenticationService.repository.AccountTypeRepository;
import authenticationService.repository.RoleRepository;
import authenticationService.repository.UserAccountRepository;
import authenticationService.service.AuthenticationService;
import client.feign.UserClient;
import dto.AuthenticationUserDto;
import dto.UserDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtServiceImpl jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserClient userClient;
    private final AccountTypeRepository accountTypeRepository;
    private final RoleRepository roleRepository;
    private final UserAccountRepository userAccountRepository;

    @Override
    @Transactional
    public String registration(@NotNull AuthenticationUserDto authenticationUserDto) {
        if (userAccountRepository.existsByUserNameAndPassword(authenticationUserDto.getUserName(), passwordEncoder.encode(authenticationUserDto.getPassword()))) {
            throw new UnsupportedOperationException(String.format(
                    "UserAccount already exists with data: {%s, %s}",
                    authenticationUserDto.getUserName(),
                    passwordEncoder.encode(authenticationUserDto.getPassword())
            ));
        }

        final var accountType = accountTypeRepository.findByAccountTypeName(authenticationUserDto.getAccountTypeName()).orElseThrow(
                () -> new NotFoundException(String.format("AccountType not found by name: [%s]", authenticationUserDto.getAccountTypeName()))
        );

        final var accountRoles = roleRepository.findAllByNameIn(authenticationUserDto.getUserRoles());
        accountRoles.addAll(accountType.getDefaultRoles());

        final var userAccount = userAccountRepository.save(
                UserAccount.builder()
                        .active(true)
                        .userName(authenticationUserDto.getUserName())
                        .password(passwordEncoder.encode(authenticationUserDto.getPassword()))
                        .accountType(accountType)
                        .userRoles(accountRoles)
                        .build()
        );

        final var userDto = userClient.saveUser(
                UserDto.builder()
                        .active(true)
                        .name(authenticationUserDto.getName())
                        .surname(authenticationUserDto.getSurname())
                        .patronymic(authenticationUserDto.getPatronymic())
                        .dateBirthday(authenticationUserDto.getDateBirthday())
                        .phone(authenticationUserDto.getPhone())
                        .sex(authenticationUserDto.isSex())
                        .build()
        ).getData();

        userAccount.setUserId(userDto.getId());

        return jwtService.generateToken(userAccount);
    }

    @Override
    public String authenticate(@NotEmpty String userName, @NotEmpty String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userName,
                        password
                )
        );

        return jwtService.generateToken(
                userAccountRepository.findByUserName(userName).orElseThrow(
                        () -> new UsernameNotFoundException(String.format("User not found by username: %s", userName))
                )
        );
    }
}
