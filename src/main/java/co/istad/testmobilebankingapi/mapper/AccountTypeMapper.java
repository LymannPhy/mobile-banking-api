package co.istad.testmobilebankingapi.mapper;

import co.istad.testmobilebankingapi.domain.AccountType;
import co.istad.testmobilebankingapi.features.account_type.dto.AccountTypeResponse;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountTypeMapper {
    AccountTypeResponse toAccountTypeResponse(AccountType accountType);
    List<AccountTypeResponse> toAccountTypeResponseList(List<AccountType> accountTypes);
}
