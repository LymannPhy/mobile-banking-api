package co.istad.testmobilebankingapi.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String uuid;
    @Column(length = 50)
    private String name;
    @Column(length = 8)
    private String gender;
    @Column(unique = true)
    private String oneSignalId;
    @Column(unique = true)
    private String studentIdCard;
    private Boolean isDeleted;
    private Boolean isStudent;
    @OneToMany(mappedBy = "user")
    private List<UserAccount> userAccountList;
}
