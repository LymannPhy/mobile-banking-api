package co.istad.testmobilebankingapi.features.account_type;

import co.istad.testmobilebankingapi.features.account_type.dto.AccountTypeResponse;
import java.util.List;

public interface AccountTypeService {
    List<AccountTypeResponse> findList();
    AccountTypeResponse findByAlias(String alias);
}

