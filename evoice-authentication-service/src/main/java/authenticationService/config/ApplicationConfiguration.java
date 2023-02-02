package authenticationService.config;

import authenticationService.component.UserAuthentication;
import authenticationService.mapper.UserMapper;
import authenticationService.repository.UserCredentialsRepository;
import client.feign.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {
  private final UserClient userClient;
  private final UserMapper userMapper;
  private final UserCredentialsRepository userCredentialsRepository;

  @Bean
  public UserDetailsService userDetailsService() {
    return this::getByUserName;
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    final var authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  private UserAuthentication getByUserName(String userName) {
    return userCredentialsRepository.findByName(userName).map(
            uc -> userMapper.apply(userClient.getUserById(uc.getId()).getData(), uc)
    ).orElseThrow(() -> new UsernameNotFoundException(
            String.format("User not found by username: %s", userName)
    ));
  }
}
