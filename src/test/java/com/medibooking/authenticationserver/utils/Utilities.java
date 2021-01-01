package com.medibooking.authenticationserver.utils;

import com.medibooking.authenticationserver.dtos.account.AccountGetDto;
import com.medibooking.authenticationserver.dtos.account.AccountPostDto;
import com.medibooking.authenticationserver.dtos.account.AccountPutDto;
import com.medibooking.authenticationserver.dtos.auth.AuthGetDto;
import com.medibooking.authenticationserver.dtos.auth.AuthPostDto;
import com.medibooking.authenticationserver.entities.Account;
import com.medibooking.authenticationserver.entities.Authority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;

public class Utilities {

    public AccountGetDto buildAccountGetDto(Long id,
                                            String username,
                                            String encodedPassword){
        AccountGetDto accountGetDto = new AccountGetDto();
        accountGetDto.setId(id);
        accountGetDto.setUsername(username);
        accountGetDto.setEncodedPassword(encodedPassword);
        return accountGetDto;
    }

    public Account buildAccount(Long id,
                                 String username,
                                 String encodedPassword) {

        Account account = new Account();
        account.setId(id);
        account.setUsername(username);
        account.setEncodedPassword(encodedPassword);
        return account;
    }

    public Account buildAccountWithoutId(String username,
                                String encodedPassword) {

        Account account = new Account();
        account.setUsername(username);
        account.setEncodedPassword(encodedPassword);
        return account;
    }

    public AccountPostDto buildAccountPostDto(String username,
                                              String encodedPassword){
        AccountPostDto accountPostDto = new AccountPostDto();
        accountPostDto.setUsername(username);
        accountPostDto.setEncodedPassword(encodedPassword);
        return accountPostDto;
    }

    public AccountPutDto buildAccountPutDto(String encodedPassword){
        AccountPutDto accountPutDto = new AccountPutDto();
        accountPutDto.setEncodedPassword(encodedPassword);
        return accountPutDto;
    }

    public AuthGetDto buildAuthGetDto(Long accountId,
                                      String username,
                                      Set<SimpleGrantedAuthority> grantedAuthorities){
        AuthGetDto authGetDto = new AuthGetDto();
        authGetDto.setAccountId(accountId);
        authGetDto.setUsername(username);
        authGetDto.setGrantedAuthorities(grantedAuthorities);
        return authGetDto;
    }

    public AuthPostDto buildAuthPostDto(String token){
        AuthPostDto authPostDto = new AuthPostDto();
        authPostDto.setToken(token);
        return authPostDto;
    }

    public Authority buildAuthority(Long id,
                                    String permission){
        Authority authority=new Authority();
        authority.setId(id);
        authority.setPermission("permission");
        return authority;
    }
}
