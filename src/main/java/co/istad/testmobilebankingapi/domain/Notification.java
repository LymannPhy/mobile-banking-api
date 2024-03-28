package co.istad.testmobilebankingapi.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, length = 1000)
    private String content;
    private LocalDate transactedAt;
    @ManyToOne
    private User sender;
    @ManyToOne
    private User receiver;
    @ManyToOne
    private Transaction transaction;
}
