package co.istad.testmobilebankingapi.features.account;

import co.istad.testmobilebankingapi.features.account.dto.AccountCreateRequest;
import co.istad.testmobilebankingapi.features.account.dto.AccountRenameRequest;
import co.istad.testmobilebankingapi.features.account.dto.AccountResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AccountService {
    Page<AccountResponse> findList(int page, int size);
    void createNew(AccountCreateRequest accountCreateRequest);
    AccountResponse findByActNo(String actNo);

    AccountResponse renameByActNo(String actNo, AccountRenameRequest accountRenameRequest);
    void hideAccount(String actNo);


}
