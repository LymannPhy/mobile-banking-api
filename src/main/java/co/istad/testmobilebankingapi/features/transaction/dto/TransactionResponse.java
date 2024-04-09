package co.istad.testmobilebankingapi.features.transaction.dto;

import co.istad.testmobilebankingapi.features.account.dto.AccountSnippetResponse;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionResponse(
        AccountSnippetResponse owner,

        AccountSnippetResponse transferReceiver,

        BigDecimal amount,

        String remark,

        String transactionType,

        Boolean status,

        LocalDate transactionAt

) {
}
