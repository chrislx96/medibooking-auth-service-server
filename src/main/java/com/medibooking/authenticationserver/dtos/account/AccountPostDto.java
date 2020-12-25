package com.medibooking.authenticationserver.dtos.account;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
public class AccountPostDto {
    private String username;
    private String encodedPassword;
}
