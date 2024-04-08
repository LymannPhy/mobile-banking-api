package co.istad.testmobilebankingapi.features.card_types;

import co.istad.testmobilebankingapi.domain.CardType;
import co.istad.testmobilebankingapi.features.card_types.CardTypeRepository;
import co.istad.testmobilebankingapi.mapper.CardTypeMapper;
import co.istad.testmobilebankingapi.features.card_types.dto.CardTypeResponse;
import co.istad.testmobilebankingapi.features.card_types.exception.CardTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CardTypeServiceImpl implements CardTypeService {

    private final CardTypeRepository cardTypeRepository;
    private final CardTypeMapper cardTypeMapper;

    @Autowired
    public CardTypeServiceImpl(CardTypeRepository cardTypeRepository, CardTypeMapper cardTypeMapper) {
        this.cardTypeRepository = cardTypeRepository;
        this.cardTypeMapper = cardTypeMapper;
    }

    @Override
    public List<CardTypeResponse> findList() {
        try {
            List<CardType> cardTypes = cardTypeRepository.findAll();
            return cardTypeMapper.toCardTypeResponseList(cardTypes);
        } catch (Exception ex) {
            throw new CardTypeException("Error occurred while fetching card types", ex);
        }
    }

    @Override
    public CardTypeResponse findByName(String name) {
        try {
            CardType cardType = cardTypeRepository.findByName(name)
                    .orElseThrow(() -> new CardTypeException("Card type not found for name: " + name));
            return cardTypeMapper.toCardTypeResponse(cardType);
        } catch (Exception ex) {
            throw new CardTypeException("Error occurred while fetching card type by name: " + name, ex);
        }
    }
}
