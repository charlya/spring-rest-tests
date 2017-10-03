package com.worldline.fpl.recruitment.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.worldline.fpl.recruitment.dao.TransactionRepository;
import com.worldline.fpl.recruitment.exception.ServiceException;
import com.worldline.fpl.recruitment.service.AccountService;
import com.worldline.fpl.recruitment.service.TransactionService;

/**
 * TransactionService test
 * 
 * @author charly
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

    @Mock
    private AccountService accountServiceMock;

    @Mock
    private TransactionRepository transactionRepositoryMock;

    @InjectMocks
    private TransactionService systemUnderTest;

    @Test
    public void isTransactionAssociatedToAccountReturnsTrue() {
        // Install stub
        String transactionId = "transaction123";
        String accountId = "account123";
        Mockito.doReturn(true).when(transactionRepositoryMock).isTransactionAssociatedToAccount(transactionId,
                accountId);

        // Call method to test
        Assert.assertTrue("Transaction should be associated to account",
                systemUnderTest.isTransactionAssociatedToAccount(transactionId, accountId));

        // Verify behaviors
        Mockito.verify(transactionRepositoryMock).isTransactionAssociatedToAccount(transactionId, accountId);
    }

    @Test
    public void isTransactionAssociatedToAccountReturnsFalse() {
        // Install stub
        String transactionId = "transaction123";
        String accountId = "account123";
        Mockito.doReturn(false).when(transactionRepositoryMock).isTransactionAssociatedToAccount(transactionId,
                accountId);

        // Call method to test
        Assert.assertFalse("Transaction should not be associated to account",
                systemUnderTest.isTransactionAssociatedToAccount(transactionId, accountId));

        // Verify behaviors
        Mockito.verify(transactionRepositoryMock).isTransactionAssociatedToAccount(transactionId, accountId);
    }

    @Test(expected = ServiceException.class)
    public void isTransactionAssociatedToAccountOnNullTransactionArg() {
        // Install stub
        String transactionId = null;
        String accountId = "account123";

        // Call method to test
        systemUnderTest.isTransactionAssociatedToAccount(transactionId, accountId);
    }

    @Test(expected = ServiceException.class)
    public void isTransactionAssociatedToAccountOnNullAccountArg() {
        // Install stub
        String transactionId = "transaction123";
        String accountId = null;

        // Call method to test
        systemUnderTest.isTransactionAssociatedToAccount(transactionId, accountId);
    }

    @Test
    public void isTransactionExist() {
        // Install stub
        String transactionId = "transaction123";
        Mockito.doReturn(true).when(transactionRepositoryMock).exists(transactionId);

        // Call method to test
        Assert.assertTrue("Transaction should exist", systemUnderTest.isTransactionExist(transactionId));

        // Verify behaviors
        Mockito.verify(transactionRepositoryMock).exists(transactionId);
    }

    @Test
    public void isTransactionExistOnUnexistingTransaction() {
        // Install stub
        String transactionId = "unknownTransaction";
        Mockito.doReturn(false).when(transactionRepositoryMock).exists(transactionId);

        // Call method to test
        Assert.assertFalse("Transaction should not exist", systemUnderTest.isTransactionExist(transactionId));

        // Verify behaviors
        Mockito.verify(transactionRepositoryMock).exists(transactionId);
    }

    @Test(expected = ServiceException.class)
    public void isTransactionExistOnNullTransaction() {
        // Install stub
        String transactionId = null;

        // Call method to test
        systemUnderTest.isTransactionExist(transactionId);
    }

}
