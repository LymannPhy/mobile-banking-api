package co.istad.testmobilebankingapi.features.account;

import co.istad.testmobilebankingapi.domain.Account;
import co.istad.testmobilebankingapi.domain.AccountType;
import co.istad.testmobilebankingapi.domain.User;
import co.istad.testmobilebankingapi.domain.UserAccount;
import co.istad.testmobilebankingapi.features.account.dto.AccountCreateRequest;
import co.istad.testmobilebankingapi.features.account.dto.AccountResponse;
import co.istad.testmobilebankingapi.features.account_type.AccountTypeRepository;
import co.istad.testmobilebankingapi.features.account_type.dto.AccountTypeResponse;
import co.istad.testmobilebankingapi.features.user.UserRepository;
import co.istad.testmobilebankingapi.features.user.dto.UserResponse;
import co.istad.testmobilebankingapi.mapper.AccountMapper;

import co.istad.testmobilebankingapi.mapper.AccountTypeMapper;
import co.istad.testmobilebankingapi.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{
    private final UserAccountRepository userAccountRepository;
    private final AccountRepository accountRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final UserRepository userRepository;
    private final AccountMapper accountMapper;
    private final AccountTypeMapper accountTypeMapper;
    private final UserMapper userMapper;

    @Override
    public void createNew(AccountCreateRequest accountCreateRequest) {

        // check account type
        AccountType accountType = accountTypeRepository.findByAlias(accountCreateRequest.accountTypeAlias())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Invalid account type"));

        // check user by UUID
        User user = userRepository.findByUuid(accountCreateRequest.userUuid())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "User has not been found"));

        // map account dto to account entity
        Account account = accountMapper.fromAccountCreateRequest(accountCreateRequest);
        account.setAccountType(accountType);
        account.setActName(user.getName());
        account.setActNo("123456789");
        account.setTransferLimit(BigDecimal.valueOf(5000));
        account.setIsHidden(false);

        UserAccount userAccount = new UserAccount();
        userAccount.setAccount(account);
        userAccount.setUser(user);
        userAccount.setIsDeleted(false);
        userAccount.setIsBlocked(false);
        userAccount.setCreatedAt(LocalDateTime.now());

        userAccountRepository.save(userAccount);
    }
    @Override
    public AccountResponse findAccountByAccountNo(String actNo) {
        Account account = accountRepository.findByActNo(actNo).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Account with ActNo not found. Please try again."));

        User user = account.getUserAccountList().stream().findFirst()
                .map(UserAccount::getUser)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "User associated with Account not found."));
//        log.info(account.getUserAccountList().toString());
        AccountTypeResponse accountTypeResponse = accountTypeMapper.toAccountTypeResponse(account.getAccountType());

        AccountResponse accountResponse = accountMapper.toAccountResponse(account);

        UserResponse userResponse = userMapper.toUserResponse(user);
        accountResponse = new AccountResponse(
                accountResponse.actNo(),
                accountResponse.actName(),
                accountResponse.alias(),
                accountResponse.balance(),
                accountTypeResponse,
                userResponse
        );

        return accountResponse;
    }
}
