package co.istad.testmobilebankingapi.features.account;

import co.istad.testmobilebankingapi.features.account.dto.AccountCreateRequest;
import co.istad.testmobilebankingapi.features.account.dto.AccountResponse;

public interface AccountService {
    void createNew(AccountCreateRequest accountCreateRequest);
    AccountResponse findAccountByAccountNo(String actNo);
}
