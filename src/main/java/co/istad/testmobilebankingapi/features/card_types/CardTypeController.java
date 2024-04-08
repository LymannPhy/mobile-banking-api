package co.istad.testmobilebankingapi.features.card_types;

import co.istad.testmobilebankingapi.features.card_types.dto.CardTypeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/card-types")
public class CardTypeController {

    private final CardTypeService cardTypeService;

    @Autowired
    public CardTypeController(CardTypeService cardTypeService) {
        this.cardTypeService = cardTypeService;
    }

    @GetMapping
    public ResponseEntity<List<CardTypeResponse>> getCardTypeList() {
        List<CardTypeResponse> cardTypeList = cardTypeService.findList();
        return new ResponseEntity<>(cardTypeList, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<CardTypeResponse> getCardTypeByName(@PathVariable String name) {
        CardTypeResponse cardType = cardTypeService.findByName(name);
        return new ResponseEntity<>(cardType, HttpStatus.OK);
    }
}
