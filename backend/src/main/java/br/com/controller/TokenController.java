package br.com.controller;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.controller.dto.LoginRequest;
import br.com.controller.dto.LoginResponse;


@RestController
public class TokenController {

    private final JwtEncoder jwtEncoder;
    private final AuthenticationManager authenticationManager;
    


    public TokenController(JwtEncoder jwtEncoder, AuthenticationManager authenticationManager) {
        this.jwtEncoder = jwtEncoder;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){

       Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password())
        );


       var now = Instant.now();
       var expiresIn = 300L;

       String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

       var claims = JwtClaimsSet.builder()
            .issuer("mybackend")
            .subject(authentication.getName())
            .claim("scope", scope)
            .issuedAt(now)
            .expiresAt(now.plusSeconds(expiresIn))
            .build();

            var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
            
            return ResponseEntity.ok(new LoginResponse(jwtValue, expiresIn));
    }
    
}
