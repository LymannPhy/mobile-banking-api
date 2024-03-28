package co.istad.testmobilebankingapi.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "account_types")
public class AccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false, length = 100)
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    private Boolean isDeleted;
    @OneToMany(mappedBy = "accountType")
    private List<Account> accounts;

}
