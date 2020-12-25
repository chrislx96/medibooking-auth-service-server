package com.medibooking.authenticationserver.mappers;

import com.medibooking.authenticationserver.dtos.account.AccountGetDto;
import com.medibooking.authenticationserver.dtos.account.AccountPostDto;
import com.medibooking.authenticationserver.dtos.account.AccountPutDto;
import com.medibooking.authenticationserver.entities.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {

    @Mapping(source = "username", target = "username")
    @Mapping(source = "encodedPassword", target = "encodedPassword")
    @Mapping(source = "id", target = "id")
    AccountGetDto fromEntity(Account account);

    @Mapping(source = "username", target = "username")
    @Mapping(source = "encodedPassword", target = "encodedPassword")
    Account toEntity(AccountPostDto accountPostDto);

    @Mapping(source = "encodedPassword", target = "encodedPassword")
    void copy(AccountPutDto accountPutDto, @MappingTarget Account account);
}
