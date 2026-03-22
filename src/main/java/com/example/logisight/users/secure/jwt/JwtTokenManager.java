package com.example.logisight.users.secure.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenManager {

    private final SecretKey secretKey;
    private final long expirationTime;

    public JwtTokenManager(
            @Value("${jwt.token.key}") String secretKey,
            @Value("${jwt.token.time}") long expirationTime
    ) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
        this.expirationTime = expirationTime;
    }

    public String generateToken(String username) {
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + (expirationTime * 1_000L));

        return Jwts.builder()
                .subject(username)
                .signWith(secretKey)
                .issuedAt(currentDate)
                .expiration(expireDate)
                .compact();
    }

    public Claims getClaims(String token) {
        Claims payload = null;
        try {
            payload = Jwts.parser()
                    .verifyWith(secretKey).build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (SignatureException e) {
            log.error("Неверная подпись", e);
        } catch (ExpiredJwtException e) {
            log.error("Истекший токен",e);
        } catch (MalformedJwtException e) {
            log.error("Неверный формат токена", e);
        } catch (UnsupportedJwtException e) {
            log.error("Неподдерживаемый тип токена", e);
        } catch (JwtException e) {
            log.error("Другие ошибки JWT", e);
        } catch (Exception e) {
            log.error("Общие ошибки", e);
        }
        return payload;
    }

    public boolean validateJwtToken(String token) {
        try {
            Date expiration = getClaims(token).getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            log.warn("Invalid JWT Token", e);
            return false;
        }
    }
}
