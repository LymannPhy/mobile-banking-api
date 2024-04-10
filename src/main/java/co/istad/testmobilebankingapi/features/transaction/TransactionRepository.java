package co.istad.testmobilebankingapi.features.transaction;

import co.istad.testmobilebankingapi.domain.Transaction;
import co.istad.testmobilebankingapi.features.transaction.dto.TransactionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Page<Transaction> findAllByTransactionType(String transactionType, Pageable pageable);
}
