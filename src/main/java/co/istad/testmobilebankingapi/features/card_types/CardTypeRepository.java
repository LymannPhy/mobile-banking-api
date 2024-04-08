package co.istad.testmobilebankingapi.features.card_types;

import co.istad.testmobilebankingapi.domain.CardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CardTypeRepository extends JpaRepository<CardType, Integer> {
    Optional<CardType> findByName(String name);
}
