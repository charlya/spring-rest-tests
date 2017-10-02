package com.worldline.fpl.recruitment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.worldline.fpl.recruitment.json.ErrorResponse;
import com.worldline.fpl.recruitment.json.TransactionResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * AdminTransaction controller
 * 
 * @author charly
 *
 */
@RequestMapping(value = "/accounts/{accountId}/transactions/{transactionId}")
public interface AdminTransactionController {

	@RequestMapping(value = "", method = RequestMethod.DELETE)
	@ApiOperation(value = "Delete a transaction related to an account", response = TransactionResponse.class, responseContainer = "List")
	@ApiResponses({
			@ApiResponse(code = 404, message = "Account and transaction not found, or transaction not associated to account", response = ErrorResponse.class) })
	ResponseEntity<Object> deleteTransactionByAccountAndTransaction(
			@ApiParam("Account ID") @PathVariable("accountId") String accountId,
			@ApiParam("Transaction ID") @PathVariable("transactionId") String transactionId);

}
