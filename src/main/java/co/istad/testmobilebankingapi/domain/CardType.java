package co.istad.testmobilebankingapi.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "type_cards")
public class CardType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false, length = 100)
    private String name;
    private Boolean isDeleted;
    @OneToMany(mappedBy = "cardType")
    private List<Card> cards;
}
