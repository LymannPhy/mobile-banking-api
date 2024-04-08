package co.istad.testmobilebankingapi.features.account.dto;

import java.math.BigDecimal;

public record TransferLimitUpdateRequest(
        BigDecimal newTransferLimit
) {
}
