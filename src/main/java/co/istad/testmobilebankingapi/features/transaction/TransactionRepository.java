package co.istad.testmobilebankingapi.features.transaction;

import co.istad.testmobilebankingapi.domain.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Page<Transaction> findAllByTransactionType(String transactionType, Pageable pageable);
}
