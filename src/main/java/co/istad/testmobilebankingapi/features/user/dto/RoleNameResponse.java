package co.istad.testmobilebankingapi.features.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public record RoleNameResponse(
        String name
) {
}
