package com.medibooking.authenticationserver.dtos.auth;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;

@Data
@NoArgsConstructor
public class AuthGetDto {
    private Long accountId;
    private String username;
    private Set<SimpleGrantedAuthority> grantedAuthorities;
}
