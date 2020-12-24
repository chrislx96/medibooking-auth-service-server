package com.medibooking.authenticationserver.controllers;

import com.medibooking.authenticationserver.dtos.auth.AuthGetDto;
import com.medibooking.authenticationserver.dtos.auth.AuthPostDto;
import com.medibooking.authenticationserver.services.AccountService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final SecretKey secretKey;
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AuthGetDto> getAuth(@RequestBody AuthPostDto authPostDto) {
        AuthGetDto authGetDto = new AuthGetDto();
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(authPostDto.getToken());
            Claims body = claimsJws.getBody();
            String username = body.getSubject();
            var authorities = (List<Map<String, String>>) body.get("authorities");
            Set<SimpleGrantedAuthority> grantedAuthorities = authorities.stream()
                    .map(m -> new SimpleGrantedAuthority(m.get("authority")))
                    .collect(Collectors.toSet());
            authGetDto.setUsername(username);
            Long accountId = accountService.findAccountByUsername(username).getId();
            authGetDto.setAccountId(accountId);
            authGetDto.setGrantedAuthorities(grantedAuthorities);
        } catch (JwtException e) {
            return ResponseEntity.ok(authGetDto);
        }
        return ResponseEntity.ok(authGetDto);
    }
}
