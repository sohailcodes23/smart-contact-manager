package com.example.smartContactManager.config.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized.");
        PrintWriter writer = response.getWriter();
        writer.println("Access Denied !! " + authException.getMessage());
    }
}

class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private final Long subject;
    private final String token;

    private JwtAuthenticationToken(
            String token,
            Long subject,
            Collection<? extends GrantedAuthority> authorities,
            boolean isAuthenticated) {
        super(authorities);
        this.token = token;
        this.subject = subject;
        setAuthenticated(isAuthenticated);
    }

    public JwtAuthenticationToken(String token) {
        this(token, null, null, false);
    }

    public JwtAuthenticationToken(Long subject, Collection<? extends GrantedAuthority> authorities) {
        this(null, subject, authorities, true);
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return subject;
    }
}

@AllArgsConstructor
class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        resolveHeader(request).ifPresent(this::setSecurityContext);
        chain.doFilter(request, response);
    }

    private Optional<JwtAuthenticationToken> resolveHeader(HttpServletRequest request) {
        String headerValue = request.getHeader("Authorization");
        System.out.println("HEADER " + headerValue);
        if (headerValue != null && headerValue.startsWith("Bearer ")) {
            String tokenValue = headerValue.substring(7);
            JwtAuthenticationToken token = new JwtAuthenticationToken(tokenValue);
            return Optional.of(token);
        }

        return Optional.empty();
    }

    private void setSecurityContext(Authentication authentication) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }
}

@AllArgsConstructor
class JwtAuthenticationProvider implements AuthenticationProvider {
    private final JwtUtils jwtUtils;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            String token = ((String) authentication.getCredentials());
            DecodedJWT jwt = jwtUtils.verify(token);
            return new JwtAuthenticationToken(
                    Long.parseLong(jwt.getSubject()),
                    Collections.singletonList(new SimpleGrantedAuthority(jwt.getClaim("role").asString())));
        } catch (Exception e) {
            throw new BadCredentialsException(e.getMessage());
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return JwtAuthenticationToken.class.isAssignableFrom(aClass);
    }
}


@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private static final String[] WHITELIST_URLS = {
            "/swagger-ui/**",
            "/v3/apis-docs/**",
            "/swagger-resources/**",
            "/swagger-resources",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/h2-console/**",
            "/auth/**"
    };


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .headers(httpSecurityHeadersConfigurer -> {
                    httpSecurityHeadersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable);
                })
                .authorizeRequests()
                .requestMatchers(WHITELIST_URLS).permitAll()
                .requestMatchers("/general/current-user").hasRole("ADMIN")
                .requestMatchers("/general/user").permitAll()
                .anyRequest()
                .authenticated()
                .and().exceptionHandling(ex -> ex.authenticationEntryPoint(new JwtAuthenticationEntryPoint()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}