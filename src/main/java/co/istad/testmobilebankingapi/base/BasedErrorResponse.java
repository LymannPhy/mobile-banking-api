package co.istad.testmobilebankingapi.base;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasedErrorResponse {
    private BaseError error;
}
