package com.worldline.fpl.recruitment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.worldline.fpl.recruitment.dao.TransactionRepository;

/**
 * AdminTransaction service
 * 
 * @author charly
 *
 */
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
		transactionRepository.delete(transactionId);
	}

}
