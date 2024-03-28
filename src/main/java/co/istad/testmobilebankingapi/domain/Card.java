package co.istad.testmobilebankingapi.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String number;
    @Column(nullable = false)
    private String cvv;
    private String holder;
    private LocalDate issueAt;
    private LocalDate expiredAt;
    private Boolean isDeleted;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private CardType cardType;
    @OneToOne
    private Account account;
}
