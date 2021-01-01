package com.medibooking.authenticationserver.repositories;

import com.medibooking.authenticationserver.AuthenticationServerApplication;
import com.medibooking.authenticationserver.entities.Account;
import com.medibooking.authenticationserver.jwt.JwtConfig;
import com.medibooking.authenticationserver.utils.Utilities;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AuthenticationServerApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ContextConfiguration(classes = {Utilities.class, SecretKey.class})
public class AccountRepositoryTest {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private Utilities utility;

    @Test
    public void shouldReturnAccountListGivenAccountsHasBeenInserted() {
        // Create a new account.
        Account account1 = utility.buildAccountWithoutId("test","aaaa");
        Account account2 = utility.buildAccountWithoutId("testtest","aa");
        Account savedAccount = accountRepository.save(account1);
        accountRepository.save(account2);

        List<Account> accountList = accountRepository.findAll();
        assertNotNull(accountList);
        assertTrue(accountList.size() >= 2);
    }

    @Test
    public void shouldReturnAccountGivenUsername() {
        // Given
        // Create a new account.
        Account account = utility.buildAccountWithoutId("test","aaaa");
        Account savedAccount = accountRepository.save(account);

        // When
        Account returnedAccount = accountRepository.findByUsername("test");

        // Then
        assertNotNull(returnedAccount);
        assertEquals("test", returnedAccount.getUsername());
    }

}
