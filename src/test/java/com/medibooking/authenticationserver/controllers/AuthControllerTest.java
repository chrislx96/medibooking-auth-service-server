package com.medibooking.authenticationserver.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medibooking.authenticationserver.auth.ApplicationUserService;
import com.medibooking.authenticationserver.dtos.account.AccountGetDto;
import com.medibooking.authenticationserver.dtos.auth.AuthPostDto;
import com.medibooking.authenticationserver.services.AccountService;
import com.medibooking.authenticationserver.utils.Utilities;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import javax.crypto.SecretKey;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AuthController.class)
@Import(AuthController.class)
@ContextConfiguration(classes = {Utilities.class})
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @MockBean
    private SecretKey secretKey;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private ApplicationUserService applicationUserService;

    @Autowired
    private Utilities utilities;

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void ShouldReturnAccountListGivenAccountsExists() throws Exception {
        AuthPostDto authPostDto = utilities.buildAuthPostDto("test");
        AccountGetDto accountGetDto = utilities.buildAccountGetDto(1L,
                "test",
                "test");
        BDDMockito.given(accountService.findAccountByUsername(anyString())).willReturn(accountGetDto);
        this.mockMvc.perform(post("/auth").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(authPostDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }
}
