package co.istad.testmobilebankingapi.features.account;

import co.istad.testmobilebankingapi.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByActNo(String actNo);
}
