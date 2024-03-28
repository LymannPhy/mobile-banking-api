package co.istad.testmobilebankingapi.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, length = 9)
    private String actNo;
    @Column(unique = true, nullable = false, length = 100)
    private String actName;
    private BigDecimal transferLimit;
    //Account has a type
    @ManyToOne
    private AccountType accountType;
    @OneToMany(mappedBy = "account")
    private List<UserAccount> userAccountList;

}