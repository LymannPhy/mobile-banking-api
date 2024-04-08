package co.istad.testmobilebankingapi.features.account;

import co.istad.testmobilebankingapi.domain.Account;
import co.istad.testmobilebankingapi.domain.AccountType;
import co.istad.testmobilebankingapi.domain.User;
import co.istad.testmobilebankingapi.domain.UserAccount;
import co.istad.testmobilebankingapi.features.account.dto.AccountCreateRequest;
import co.istad.testmobilebankingapi.features.account.dto.AccountRenameRequest;
import co.istad.testmobilebankingapi.features.account.dto.AccountResponse;
import co.istad.testmobilebankingapi.features.account.dto.TransferLimitUpdateRequest;
import co.istad.testmobilebankingapi.features.account_type.AccountTypeRepository;
import co.istad.testmobilebankingapi.features.account_type.dto.AccountTypeResponse;
import co.istad.testmobilebankingapi.features.user.UserRepository;
import co.istad.testmobilebankingapi.mapper.AccountMapper;

import co.istad.testmobilebankingapi.mapper.AccountTypeMapper;
import co.istad.testmobilebankingapi.mapper.UserMapper;
import co.istad.testmobilebankingapi.util.RandomUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
    public Page<AccountResponse> findList(int page, int size) {
        //validate page and size
        if(page < 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Page must be greater than or equal to zero");
        }
        if(size < 1){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Size must be greater than or equal to one");
        }
        Sort sortByActName = Sort.by(Sort.Direction.ASC, "actName");

        PageRequest pageRequest = PageRequest.of(page, size, sortByActName);
        Page<Account> accounts = accountRepository.findAll(pageRequest);
        return accounts.map(accountMapper::toAccountResponse);
    }

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
        account.setActNo(RandomUtil.generate9Digits());
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
    public AccountResponse findByActNo(String actNo) {

        Account account = accountRepository.findByActNo(actNo)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account no is invalid!"
                ));
        return accountMapper.toAccountResponse(account);
    }
    @Override
    public AccountResponse renameByActNo(String actNo, AccountRenameRequest accountRenameRequest) {
        Account account = accountRepository.findByActNo(actNo)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account has been not found!"
                ));
        if(account.getAlias() != null && account.getAlias().equals(accountRenameRequest.newName())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "New name must not be the same as the old name"
            );
        }
        account.setAlias(accountRenameRequest.newName());
        account = accountRepository.save(account);
        return accountMapper.toAccountResponse(account);

    }

    @Transactional
    @Override
    public void hideAccount(String actNo) {
        if(accountRepository.existsByActNo(actNo)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Account has been not found");
        }
        try{
            accountRepository.hideAccountByActNo(actNo);
        }catch (Exception e){
           throw new ResponseStatusException(
                   HttpStatus.INTERNAL_SERVER_ERROR,
                   "Something went wrong!"
           );
        }
    }

    @Override
    public void updateTransferLimit(String actNo, TransferLimitUpdateRequest transferLimitUpdateRequest) {
        Account account = accountRepository.findByActNo(actNo)
                .orElseThrow(() -> new RuntimeException("Account not found with actNo: " + actNo));

        BigDecimal newTransferLimit = transferLimitUpdateRequest.newTransferLimit();
        account.setTransferLimit(newTransferLimit);
        accountRepository.save(account);
    }
}
