package co.istad.testmobilebankingapi.mapper;

import co.istad.testmobilebankingapi.domain.Account;
import co.istad.testmobilebankingapi.features.account.dto.AccountCreateRequest;
import co.istad.testmobilebankingapi.features.account.dto.AccountResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account fromAccountCreateRequest(AccountCreateRequest accountCreateRequest);
    AccountResponse toAccountResponse(Account account);

}
