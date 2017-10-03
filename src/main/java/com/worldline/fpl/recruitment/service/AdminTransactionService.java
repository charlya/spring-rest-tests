package com.worldline.fpl.recruitment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.worldline.fpl.recruitment.dao.TransactionRepository;
import com.worldline.fpl.recruitment.exception.ServiceException;
import com.worldline.fpl.recruitment.json.ErrorCode;

import lombok.extern.slf4j.Slf4j;

/**
 * AdminTransaction service
 * 
 * @author charly
 *
 */
@Slf4j
@Service
public class AdminTransactionService {

    private TransactionRepository transactionRepository;

    @Autowired
    public AdminTransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    /**
     * Delete a transaction
     * 
     * @param transactionId
     *            the transaction id
     */
    public void deleteTransaction(String transactionId) {
        log.debug("Delete transaction {}", transactionId);
        if (transactionId == null) {
            throw new ServiceException(ErrorCode.INVALID_TRANSACTION, "Transaction is not defined");
        } else
            transactionRepository.delete(transactionId);
    }

}
