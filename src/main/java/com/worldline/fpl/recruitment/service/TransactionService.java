package com.worldline.fpl.recruitment.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.worldline.fpl.recruitment.dao.TransactionRepository;
import com.worldline.fpl.recruitment.entity.Transaction;
import com.worldline.fpl.recruitment.exception.ServiceException;
import com.worldline.fpl.recruitment.json.ErrorCode;
import com.worldline.fpl.recruitment.json.TransactionResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * Transaction service
 * 
 * @author A525125
 *
 */
@Slf4j
@Service
public class TransactionService {

    private AccountService accountService;

    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(AccountService accountService, TransactionRepository transactionRepository) {
        this.accountService = accountService;
        this.transactionRepository = transactionRepository;
    }

    /**
     * Get transactions by account
     * 
     * @param accountId
     *            the account id
     * @param p
     *            the pageable object
     * @return
     */
    public Page<TransactionResponse> getTransactionsByAccount(String accountId, Pageable p) {
        if (!accountService.isAccountExist(accountId)) {
            throw new ServiceException(ErrorCode.INVALID_ACCOUNT, "Account doesn't exist");
        }
        return new PageImpl<TransactionResponse>(transactionRepository.getTransactionsByAccount(accountId, p)
                .getContent().stream().map(this::map).collect(Collectors.toList()));
    }

    /**
     * Check if a transaction exists
     * 
     * @param transactionId
     *            the transaction id
     * @return true if the transaction exists
     */
    public boolean isTransactionExist(String transactionId) {
        log.debug("Is transaction {} exist", transactionId);
        if (transactionId == null) {
            throw new ServiceException(ErrorCode.INVALID_TRANSACTION, "Transaction is not defined");
        } else
            return transactionRepository.exists(transactionId);
    }

    /**
     * Check if a transaction is associated to an account
     * 
     * @param transactionId
     *            the transaction id
     * @param accountId
     *            the account id
     * @return true if the transaction is associated to an account
     */
    public boolean isTransactionAssociatedToAccount(String transactionId, String accountId) {
        log.debug("Is transaction {} associated to account {}", transactionId, accountId);
        if (transactionId == null) {
            throw new ServiceException(ErrorCode.INVALID_TRANSACTION, "Transaction is not defined");
        } else if (accountId == null) {
            throw new ServiceException(ErrorCode.INVALID_ACCOUNT, "Account is not defined");
        } else
            return transactionRepository.isTransactionAssociatedToAccount(transactionId, accountId);
    }

    /**
     * Map {@link Transaction} to {@link TransactionResponse}
     * 
     * @param transaction
     * @return
     */
    private TransactionResponse map(Transaction transaction) {
        TransactionResponse result = new TransactionResponse();
        result.setBalance(transaction.getBalance());
        result.setId(transaction.getId());
        result.setNumber(transaction.getNumber());
        return result;
    }

}
