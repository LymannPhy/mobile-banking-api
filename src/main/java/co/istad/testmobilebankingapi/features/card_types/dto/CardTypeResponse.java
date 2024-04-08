package co.istad.testmobilebankingapi.features.card_types.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CardTypeResponse {
    private Integer id;
    private String name;
    private Boolean isDeleted;
    // Add any other fields you want to include in the response
}
