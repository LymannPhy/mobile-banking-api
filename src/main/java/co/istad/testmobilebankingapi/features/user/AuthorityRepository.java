package co.istad.testmobilebankingapi.features.user;

import co.istad.testmobilebankingapi.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
