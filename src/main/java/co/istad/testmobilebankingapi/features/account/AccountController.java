package co.istad.testmobilebankingapi.features.account;

import co.istad.testmobilebankingapi.features.account.dto.AccountCreateRequest;
import co.istad.testmobilebankingapi.features.account.dto.AccountRenameRequest;
import co.istad.testmobilebankingapi.features.account.dto.AccountResponse;
import co.istad.testmobilebankingapi.features.account.dto.TransferLimitUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createNew(@Valid @RequestBody AccountCreateRequest accountCreateRequest) {
        accountService.createNew(accountCreateRequest);
    }

    @GetMapping("/{actNo}")
    AccountResponse findByActNo(@PathVariable String actNo) {
        return accountService.findByActNo(actNo);
    }
    @PutMapping("/{actNo}/rename")
    AccountResponse renameByActNo(@PathVariable String actNo, @Valid @RequestBody AccountRenameRequest accountRenameRequest){
        return accountService.renameByActNo(actNo, accountRenameRequest);
    }
    @PutMapping("/{actNo}/hide")
    public void hideActByActNo(@PathVariable String actNo){
        accountService.hideAccount(actNo);
    }
    @GetMapping
    Page<AccountResponse> findList(
         @RequestParam(required = false, defaultValue = "0") int page,
         @RequestParam(required = false, defaultValue = "25") int size
    ){
        return accountService.findList(page, size);
    }

    @PutMapping("/{actNo}/transfer-limit")
    public void updateTransferLimit(@PathVariable String actNo, @RequestBody TransferLimitUpdateRequest request) {
        accountService.updateTransferLimit(actNo, request);
    }




}

