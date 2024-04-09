package co.istad.testmobilebankingapi.features.transaction;

import co.istad.testmobilebankingapi.features.transaction.dto.TransactionCreateRequest;
import co.istad.testmobilebankingapi.features.transaction.dto.TransactionResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TransactionService {
    TransactionResponse transfer(TransactionCreateRequest transactionCreateRequest);
    Page<TransactionResponse> findList(int page, int size, String sortOrder);
}
