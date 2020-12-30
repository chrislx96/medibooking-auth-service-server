package com.medibooking.authenticationserver.repositories;

import com.medibooking.authenticationserver.AuthenticationServerApplication;
import com.medibooking.authenticationserver.entities.Account;
import com.medibooking.authenticationserver.utils.Utilities;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AuthenticationServerApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AccountRepositoryTest {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private Utilities utility;

    @Test
    public void shouldReturnAccountListGivenAccountsHasBeenInserted() {
        // Create a new account.
        Account account1 = utility.buildAccount(66L, "llgjlyf","dfsdf");
        Account account2 = utility.buildAccount(666L, "llglyf","dfsdf");
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
        Account account = utility.buildAccount(35L,"df","sd");
        Account savedAccount = accountRepository.save(account);

        // When
        Account returnedAccount = accountRepository.findByUsername("df");

        // Then
        assertNotNull(returnedAccount);
        assertEquals(35L, returnedAccount.getId());
    }

}
