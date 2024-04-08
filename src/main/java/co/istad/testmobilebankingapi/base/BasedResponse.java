package co.istad.testmobilebankingapi.base;

import lombok.*;
import org.springframework.stereotype.Service;

@Builder
public record BasedResponse<T>(
        T payload
) {
}
