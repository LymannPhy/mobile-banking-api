package co.istad.testmobilebankingapi.features.transaction;

import co.istad.testmobilebankingapi.domain.Account;
import co.istad.testmobilebankingapi.domain.Transaction;
import co.istad.testmobilebankingapi.features.account.AccountRepository;
import co.istad.testmobilebankingapi.features.transaction.dto.TransactionCreateRequest;
import co.istad.testmobilebankingapi.features.transaction.dto.TransactionResponse;
import co.istad.testmobilebankingapi.mapper.TransactionMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final AccountRepository accountRepository;

    @Transactional
    @Override
    public TransactionResponse transfer(TransactionCreateRequest transactionCreateRequest) {
        log.info("transfer(TransactionCreateRequest transactionCreateRequest): {}", transactionCreateRequest);
        //Validate owner account no
        Account owner = accountRepository.findByActNo(transactionCreateRequest.ownerActNo())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account owner has been not found"
                ));
        //Validate transferReceiver account no
        Account transferReceiver = accountRepository.findByActNo(transactionCreateRequest.transferReceiverActNo())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account Receiver has been not found"
                ));

        //check transfer limit
        if(owner.getBalance().doubleValue() < transactionCreateRequest.amount().doubleValue()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "You don't have enough money to transfer!");
        }
        //Check amount transfer with transfer limit
        if (transactionCreateRequest.amount().doubleValue() >= owner.getBalance().doubleValue()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Transaction has been over the transfer limit");
        }

        // Substract the money from the owner account
        owner.setBalance(owner.getBalance().subtract(transactionCreateRequest.amount()));

        transferReceiver.setBalance(transferReceiver.getBalance().add(transactionCreateRequest.amount()));

        Transaction transaction = transactionMapper.fromTransactionCreateRequest(transactionCreateRequest);
        transaction.setOwner(owner);
        transaction.setTransferReceiver(transferReceiver);
        transaction.setTransactionType("TRANSFER");
        transaction.setTransactionAt(LocalDate.now());
        transaction.setStatus(true);
        transaction = transactionRepository.save(transaction);

        return transactionMapper.toTransactionResponse(transaction);
    }

    @Override
    public Page<TransactionResponse> findList(int page, int size, String sortOrder,String transactionType) {
        // Validate page and size
        if (page < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Page must be greater than or equal to zero");
        }
        if (size < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Size must be greater than or equal to one");
        }

        Sort.Direction sortDirection = Sort.Direction.DESC;
        if ("asc".equalsIgnoreCase(sortOrder)) {
            sortDirection = Sort.Direction.ASC;
        } else if (!"desc".equalsIgnoreCase(sortOrder)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Invalid sortOrder parameter. It must be 'asc' or 'desc'");
        } else if ("desc".equalsIgnoreCase(sortOrder)) {
            sortDirection = Sort.Direction.DESC;
        }

        Sort sortByTransactionDate = Sort.by(sortDirection, "transactionAt");
        PageRequest pageRequest = PageRequest.of(page, size, sortByTransactionDate);
        Page<Transaction> transactions = transactionRepository.findAllByTransactionType(transactionType ,pageRequest);

        return transactions.map(transactionMapper::toTransactionResponse);
    }

}
