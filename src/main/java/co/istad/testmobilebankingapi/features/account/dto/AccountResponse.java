package co.istad.testmobilebankingapi.features.account.dto;

import co.istad.testmobilebankingapi.features.account_type.dto.AccountTypeResponse;
import co.istad.testmobilebankingapi.features.user.dto.UserResponse;

import java.math.BigDecimal;

public record AccountResponse(
        //UserResponse user,
        String actNo,
        String actName,
        String alias,
        BigDecimal balance,
        AccountTypeResponse accountTypeResponse,
        UserResponse userResponse
) {
}

