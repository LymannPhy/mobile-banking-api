package co.istad.testmobilebankingapi.features.transaction;

import co.istad.testmobilebankingapi.features.transaction.dto.TransactionCreateRequest;
import co.istad.testmobilebankingapi.features.transaction.dto.TransactionResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor

public class TransactionController {
    private final TransactionService transactionService;
    @PostMapping
    TransactionResponse transfer(@Valid @RequestBody TransactionCreateRequest transactionCreateRequest){
        return transactionService.transfer(transactionCreateRequest);
    }

    @GetMapping
    Page<TransactionResponse> findList(@RequestParam(required = false, defaultValue = "0") int page,
                                       @RequestParam(required = false, defaultValue = "25") int size, @RequestParam String sortOrder){
        return transactionService.findList(page, size, sortOrder);
    }
}
