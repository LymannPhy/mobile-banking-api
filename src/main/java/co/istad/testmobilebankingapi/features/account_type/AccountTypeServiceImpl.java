package co.istad.testmobilebankingapi.features.account_type;

import co.istad.testmobilebankingapi.domain.AccountType;
import co.istad.testmobilebankingapi.exception.AccountTypeException;
import co.istad.testmobilebankingapi.features.account_type.dto.AccountTypeResponse;
import co.istad.testmobilebankingapi.mapper.AccountTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class AccountTypeServiceImpl implements AccountTypeService {

    private final AccountTypeRepository accountTypeRepository;
    private final AccountTypeMapper accountTypeMapper;

    @Autowired
    public AccountTypeServiceImpl(AccountTypeRepository accountTypeRepository, AccountTypeMapper accountTypeMapper) {
        this.accountTypeRepository = accountTypeRepository;
        this.accountTypeMapper = accountTypeMapper;
    }

    @Override
    public List<AccountTypeResponse> findList() {
        try {
            List<AccountType> accountTypes = accountTypeRepository.findAll();
            return accountTypeMapper.toAccountTypeResponseList(accountTypes);
        } catch (Exception ex) {
            throw new AccountTypeException("Error occurred while fetching account types", ex);
        }
    }

    @Override
    public AccountTypeResponse findByAlias(String alias) {
        try {
            AccountType accountType = accountTypeRepository.findByAlias(alias)
                    .orElseThrow(() -> new AccountTypeException("Account type not found for alias: " + alias));
            return accountTypeMapper.toAccountTypeResponse(accountType);
        } catch (Exception ex) {
            throw new AccountTypeException("Error occurred while fetching account type by alias: " + alias, ex);
        }
    }
}
