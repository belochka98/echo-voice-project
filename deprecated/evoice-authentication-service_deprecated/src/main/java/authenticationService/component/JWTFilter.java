package authenticationService.component;

import authenticationService.service.impl.JwtServiceImpl;
import authenticationService.utills.ApplicationUtills;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.logging.log4j.util.Strings;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    private final JwtServiceImpl jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    @SneakyThrows
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) {
        final var authHeader = request.getHeader(ApplicationUtills.AUTH_HEADER);
        if (Strings.isEmpty(authHeader) || !authHeader.startsWith(ApplicationUtills.TOKEN_PREVIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        final var jwt = authHeader.replace(ApplicationUtills.TOKEN_PREVIX, "");
        final var userName = jwtService.extractUsername(jwt);

        if (Strings.isNotEmpty(userName) && SecurityContextHolder.getContext().getAuthentication() == null) {
            final var userDetails = this.userDetailsService.loadUserByUsername(userName);

            if (jwtService.isTokenValid(jwt, userDetails)) {
                final var authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
