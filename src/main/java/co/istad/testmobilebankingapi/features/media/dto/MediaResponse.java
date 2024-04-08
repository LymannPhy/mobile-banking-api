package co.istad.testmobilebankingapi.features.media.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
public record MediaResponse(
        String name, //uniquwe
        String contentType, //jpg, png, web, mp4
        String uri,
        String extension,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        Long size
) {
}
