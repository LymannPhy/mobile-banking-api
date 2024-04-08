package co.istad.testmobilebankingapi.features.account_type;

import co.istad.testmobilebankingapi.features.account_type.dto.AccountTypeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/account-types")
public class AccountTypeController {

    private final AccountTypeService accountTypeService;

    @Autowired
    public AccountTypeController(AccountTypeService accountTypeService) {
        this.accountTypeService = accountTypeService;
    }

    @GetMapping("")
    public ResponseEntity<List<AccountTypeResponse>> getAccountTypeList() {
        List<AccountTypeResponse> accountTypeList = accountTypeService.findList();
        return new ResponseEntity<>(accountTypeList, HttpStatus.OK);
    }

    @GetMapping("/{alias}")
    public ResponseEntity<AccountTypeResponse> getAccountTypeByAlias(@PathVariable String alias) {
        AccountTypeResponse accountType = accountTypeService.findByAlias(alias);
        return new ResponseEntity<>(accountType, HttpStatus.OK);
    }

}
