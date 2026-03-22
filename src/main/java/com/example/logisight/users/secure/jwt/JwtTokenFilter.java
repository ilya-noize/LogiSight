package com.example.logisight.users.secure.jwt;

import com.example.logisight.users.secure.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    private final JwtTokenManager jwtTokenManager;
    private final UserDetailsServiceImpl userDetailService;

    public JwtTokenFilter(
            JwtTokenManager jwtTokenManager,
            UserDetailsServiceImpl userDetailService
    ) {
        this.jwtTokenManager = jwtTokenManager;
        this.userDetailService = userDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwtToken = extractToken(request);
        if (jwtToken == null) {
            filterChain.doFilter(request, response);
            return;
        }

        Claims claims;
        try {
            if (!jwtTokenManager.validateJwtToken(jwtToken)) {
                logger.warn("Invalid JWT token: " + jwtToken);
                filterChain.doFilter(request, response);
                return;
            }
            claims = jwtTokenManager.getClaims(jwtToken);
        } catch (Exception e) {
            logger.warn("Could not retrieve the claim from this invalid Token", e);
            filterChain.doFilter(request, response);
            return;
        }

        String token = claims.getSubject();
        UserDetails user = userDetailService.loadUserByUsername(token);
        var authToken = new UsernamePasswordAuthenticationToken(
                user,
                null,
                user.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String prefix = "BEARER ";
        String bearer = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (bearer != null && !bearer.isBlank() && bearer.startsWith(prefix)) {
            return bearer.substring(prefix.length());
        }
        return null;
    }
}
