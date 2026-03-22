package com.example.logisight.users.secure;

import com.example.logisight.users.model.SignInRequest;
import com.example.logisight.users.secure.jwt.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenManager jwtTokenManager;

    public String auth(SignInRequest request) {
        var authentication = new UsernamePasswordAuthenticationToken(
                request.username(),
                request.password()
        );
        authenticationManager.authenticate(authentication);

        return jwtTokenManager.generateToken(request.username());
    }
}
