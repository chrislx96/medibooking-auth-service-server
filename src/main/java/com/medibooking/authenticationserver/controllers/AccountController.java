package com.medibooking.authenticationserver.controllers;

import com.medibooking.authenticationserver.dtos.account.AccountGetDto;
import com.medibooking.authenticationserver.dtos.account.AccountPostDto;
import com.medibooking.authenticationserver.dtos.account.AccountPutDto;
import com.medibooking.authenticationserver.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountGetDto>> getAll(){
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/{username}")
    public ResponseEntity<AccountGetDto> getByUsername(@PathVariable String username){
        return ResponseEntity.ok(accountService.findAccountByUsername(username));
    }

    @PostMapping
    public ResponseEntity<AccountGetDto> add(@RequestBody AccountPostDto accountPostDto) {
        AccountGetDto accountGetDto = new AccountGetDto();
        if(accountService.findAccountByUsername(accountPostDto.getUsername()) == null){
            accountGetDto = accountService.createPatientAccount(accountPostDto);
        }
        return ResponseEntity.ok(accountGetDto);
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<AccountGetDto> changePassword(@PathVariable Long accountId, @RequestBody AccountPutDto accountPutDto){
        AccountGetDto accountGetDto = accountService.changePassword(accountId,accountPutDto);
        return ResponseEntity.ok(accountGetDto);
    }

}
