package com.example.smartContactManager.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
@AllArgsConstructor
public class JwtUtils {
    private final Algorithm algorithm;
    private final JwtProps jwtProps;

    public String createToken(Long userId) {
        Date expiresAt = getDateAfter(Calendar.MONTH, 1);
        return JWT.create()
                .withSubject(String.valueOf(userId))
                .withIssuer(jwtProps.getIssuer())
                .withIssuedAt(new Date())
                .withExpiresAt(expiresAt)
                .withClaim("id", userId)
                .sign(algorithm);
    }

    public String createTemporaryToken(String userId, String role, String username) {
        Date expiresAt = getDateAfter(Calendar.MINUTE, 10);

        return JWT.create()
                .withSubject(userId)
                .withIssuer(jwtProps.getIssuer())
                .withIssuedAt(new Date())
                .withExpiresAt(expiresAt)
                .withClaim("role", role)
                .withClaim("username", username)
                .sign(algorithm);

    }

    public DecodedJWT verify(String token) {
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(jwtProps.getIssuer())
                .build();
        return verifier.verify(token);
    }

    private Date getDateAfter(int field, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(field, amount);
        return calendar.getTime();
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().parseClaimsJws(token).getBody();
    }
}
