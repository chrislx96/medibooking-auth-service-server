package com.medibooking.authenticationserver.dtos.account;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountPutDto {
    private String encodedPassword;
}
