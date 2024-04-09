package co.istad.testmobilebankingapi.mapper;

import co.istad.testmobilebankingapi.domain.Transaction;
import co.istad.testmobilebankingapi.features.transaction.dto.TransactionCreateRequest;
import co.istad.testmobilebankingapi.features.transaction.dto.TransactionResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = AccountMapper.class)
public interface TransactionMapper {
    TransactionResponse toTransactionResponse(Transaction transaction);
    Transaction fromTransactionCreateRequest(TransactionCreateRequest transactionCreateRequest);
}
