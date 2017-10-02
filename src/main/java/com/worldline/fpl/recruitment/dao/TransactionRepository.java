package com.worldline.fpl.recruitment.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.worldline.fpl.recruitment.entity.Transaction;

/**
 * Transaction repository
 * 
 * @author A525125
 *
 */
public interface TransactionRepository {

	/**
	 * Get transactions by account
	 * 
	 * @param accountId
	 *            the account id
	 * @param p
	 *            the pageable information
	 * @return
	 */
	Page<Transaction> getTransactionsByAccount(String accountId, Pageable p);

	/**
	 * Check if a transaction exists
	 * 
	 * @param transactionId
	 *            the transaction id
	 * @return true if the transaction exists
	 */
	boolean exists(String accountId);

	/**
	 * Check if a transaction is associated to an account
	 * 
	 * @param transactionId
	 *            the transaction id
	 * @param accountId
	 *            the account id
	 * @return true if the transaction is associated to an account
	 */
	boolean isTransactionAssociatedToAccount(String transactionId, String accountId);
	
	/**
	 * Delete a transaction
	 * 
	 * @param transactionId
	 *            the transaction id
	 * @return
	 */
	void delete(String transactionId);

}
