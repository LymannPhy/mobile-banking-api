package co.istad.testmobilebankingapi.features.account;

import co.istad.testmobilebankingapi.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
}

