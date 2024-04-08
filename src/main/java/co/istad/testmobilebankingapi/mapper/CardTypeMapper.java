package co.istad.testmobilebankingapi.mapper;

import co.istad.testmobilebankingapi.domain.CardType;
import co.istad.testmobilebankingapi.features.card_types.dto.CardTypeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CardTypeMapper {
    @Mapping(target = "id", source = "cardType.id")
    @Mapping(target = "name", source = "cardType.name")
    @Mapping(target = "isDeleted", source = "cardType.isDeleted")
    CardTypeResponse toCardTypeResponse(CardType cardType);

    List<CardTypeResponse> toCardTypeResponseList(List<CardType> cardTypes);
}

