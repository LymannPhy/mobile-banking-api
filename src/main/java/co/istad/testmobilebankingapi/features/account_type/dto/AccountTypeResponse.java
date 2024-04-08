package co.istad.testmobilebankingapi.features.account_type.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountTypeResponse {
    private String alias;
    private String name;
    private String description;
}
