package co.istad.testmobilebankingapi.features.mail.dto;

import jakarta.validation.constraints.NotBlank;

public record MailRequest(
        @NotBlank(message = "Email is required")
        String to,
        @NotBlank(message = "Email subject is required")
        String subject,
        String text
) {
}
