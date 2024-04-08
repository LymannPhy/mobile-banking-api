package co.istad.testmobilebankingapi.features.card_types;

import co.istad.testmobilebankingapi.features.card_types.dto.CardTypeResponse;
import java.util.List;

public interface CardTypeService {
    List<CardTypeResponse> findList();
    CardTypeResponse  findByName(String name);
}
