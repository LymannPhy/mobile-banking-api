package co.istad.testmobilebankingapi.mapper;

import co.istad.testmobilebankingapi.domain.Account;
import co.istad.testmobilebankingapi.features.account.dto.AccountCreateRequest;
import co.istad.testmobilebankingapi.features.account.dto.AccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        UserMapper.class,
        AccountTypeMapper.class
})
public interface AccountMapper {
    Account fromAccountCreateRequest(AccountCreateRequest accountCreateRequest);
    @Mapping(source = "userAccountList", target = "userResponse",
            qualifiedByName = "mapUserResponse")
    AccountResponse toAccountResponse(Account account);

}
