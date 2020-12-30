package com.medibooking.authenticationserver.services;

import com.medibooking.authenticationserver.dtos.account.AccountGetDto;
import com.medibooking.authenticationserver.entities.Account;
import com.medibooking.authenticationserver.mappers.*;
import com.medibooking.authenticationserver.repositories.AccountRepository;
import com.medibooking.authenticationserver.repositories.AuthorityRepository;
import com.medibooking.authenticationserver.utils.Utilities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {AccountMapperImpl.class, Utilities.class})
public class AccountServicesTest {

    @Mock
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private Utilities utility;

    AccountService accountService;

    @BeforeEach
    void setup() {
        accountService = new AccountService(accountRepository, accountMapper,authorityRepository);
    }

    @Test
    public void shouldReturnAccountListGivenAccountsExist() {
        Account account1 = utility.buildAccount(55L,"accName","sfd");
        Account account2 = utility.buildAccount(555L,"accName","sfd");

        when(accountRepository.findAll()).thenReturn(List.of(account1, account2));
        List<AccountGetDto> returnedAccountList = accountService.getAllAccounts();
        assertNotNull(returnedAccountList);
        assertEquals(2, returnedAccountList.size());
    }

    @Test
    public void shouldReturnAccountListGivenUsername() {
        Account account1 = utility.buildAccount(55L,"accName","sfd");

        when(accountRepository.findByUsername("accName")).thenReturn(account1);
        AccountGetDto returnedAccount = accountService.findAccountByUsername("accName");
        assertNotNull(returnedAccount);
        assertEquals(account1.getId(), returnedAccount.getId());
    }

    @Test
    public void shouldReturnAccountUsernameGivenId() {
        Account account1 = utility.buildAccount(55L,"accName","sfd");

        when(accountRepository.getOne(55L)).thenReturn(account1);
        String returnedAccountUsername = accountService.findUsernameById(55L);
        assertNotNull(returnedAccountUsername);
        assertEquals("accName", returnedAccountUsername);
    }

    @Test
    public void NumberOfAccountsShouldBeLessAfterDeletion() {
        accountService.deleteAccount(306L);
        verify(accountRepository).deleteById(306L);
    }

//    @Test
//    public void shouldThrowExceptionGivenInvalidUsername() {
//        when(accountRepository.findByUsername(any())).thenReturn(Optional.empty());
//        assertThrows(InvalidAccountException.class,() -> accountService.findAccountByUsername(111));
//    }

}
