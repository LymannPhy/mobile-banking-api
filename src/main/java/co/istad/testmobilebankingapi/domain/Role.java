package co.istad.testmobilebankingapi.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false, length = 100)
    private String name;
    @OneToMany(mappedBy = "roles")
    private List<UserRole> userRoles;
}
