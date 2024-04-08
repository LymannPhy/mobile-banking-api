package co.istad.testmobilebankingapi.features.account.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AccountRenameRequest(
        @NotBlank(message = "New name is required")
                @Size(message = "Account Name must be equal to 100")
        String newName
) {
}
