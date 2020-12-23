package com.medibooking.authenticationserver.services;

import com.medibooking.authenticationserver.dtos.account.AccountGetDto;
import com.medibooking.authenticationserver.dtos.account.AccountPostDto;
import com.medibooking.authenticationserver.dtos.account.AccountPutDto;
import com.medibooking.authenticationserver.entities.Account;
import com.medibooking.authenticationserver.entities.Authority;
import com.medibooking.authenticationserver.mappers.AccountMapper;
import com.medibooking.messages.Patient;
import com.medibooking.authenticationserver.repositories.AccountRepository;
import com.medibooking.authenticationserver.repositories.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final AuthorityRepository authorityRepository;
    @Autowired
    RabbitTemplate rabbitTemplate;

    public AccountGetDto createAccount(AccountPostDto accountPostDto){
        Account account = accountMapper.toEntity(accountPostDto);
        Set<Authority> authorities = Stream.of(authorityRepository.getOne(Long.valueOf(2))).collect(Collectors.toSet());
        account.setAuthorities(authorities);
        Account dbAccount = accountRepository.save(account);
        Patient patient = new Patient();
        patient.setAccountId(dbAccount.getId());
        patient.setFirstName(accountPostDto.getFirstName());
        patient.setLastName(accountPostDto.getLastName());
        patient.setAge(accountPostDto.getAge());
        patient.setGender(accountPostDto.getGender());
        rabbitTemplate.convertAndSend("Auth", patient);
        return accountMapper.fromEntity(dbAccount);
    }

    public AccountGetDto findAccountByUsername(String username){
        Account account = accountRepository.findByUsername(username);
        return accountMapper.fromEntity(account);
    }

    public List<AccountGetDto> getAllAccounts(){
        return accountRepository.findAll().stream()
                .map(account -> accountMapper.fromEntity(account))
                .collect(Collectors.toList());
    }

    public String findUsernameById(Long accountId){
        Account account = accountRepository.getOne(accountId);
        return account.getUsername();
    }

    public AccountGetDto changePassword(Long accountId, AccountPutDto accountPutDto) {
        Account account = new Account();
        accountMapper.copy(accountPutDto, account);
        account.setId(accountId);
        account.setUsername(findUsernameById(accountId));
        Set<Authority> authorities = Stream.of(authorityRepository.getOne(Long.valueOf(2))).collect(Collectors.toSet());
        account.setAuthorities(authorities);
        return accountMapper.fromEntity(accountRepository.save(account));
    }

    public void deleteAccount(Long accountId){
        accountRepository.deleteById(accountId);
    }
}
