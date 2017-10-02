package com.worldline.fpl.recruitment.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.worldline.fpl.recruitment.controller.AdminTransactionController;
import com.worldline.fpl.recruitment.exception.ServiceException;
import com.worldline.fpl.recruitment.json.ErrorCode;
import com.worldline.fpl.recruitment.service.AccountService;
import com.worldline.fpl.recruitment.service.AdminTransactionService;
import com.worldline.fpl.recruitment.service.TransactionService;

import lombok.extern.slf4j.Slf4j;

/**
 * Implementation of {@link AdminTransactionController}
 * 
 * @author charly
 *
 */
@Slf4j
@RestController
public class AdminTransactionControllerImpl implements AdminTransactionController {

	private AccountService accountService;

	private TransactionService transactionService;

	private AdminTransactionService adminTransactionService;

	@Autowired
	public AdminTransactionControllerImpl(AccountService accountService, TransactionService transactionService,
			AdminTransactionService adminTransactionService) {
		this.accountService = accountService;
		this.transactionService = transactionService;
		this.adminTransactionService = adminTransactionService;
	}

	@Override
	public ResponseEntity<Object> deleteTransactionByAccountAndTransaction(@PathVariable("accountId") String accountId,
			@PathVariable("transactionId") String transactionId) {
		log.debug("Delete transaction {0} from account {1}", transactionId, accountId);
		if (!accountService.isAccountExist(accountId)) {
			throw new ServiceException(ErrorCode.INVALID_ACCOUNT, "Account doesn't exist");
		}
		if (!transactionService.isTransactionExist(transactionId)) {
			throw new ServiceException(ErrorCode.INVALID_TRANSACTION, "Transaction doesn't exist");
		}
		if (!transactionService.isTransactionAssociatedToAccount(transactionId, accountId)) {
			throw new ServiceException(ErrorCode.TRANSACTION_NOT_ASSOCIATED_TO_ACCOUNT,
					"Transaction is not associated to this account");
		}
		adminTransactionService.deleteTransaction(transactionId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}

}
