package com.example.smartContactManager.config.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {
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

