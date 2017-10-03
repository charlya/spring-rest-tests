package com.worldline.fpl.recruitment.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.worldline.fpl.recruitment.dao.TransactionRepository;
import com.worldline.fpl.recruitment.exception.ServiceException;
import com.worldline.fpl.recruitment.service.AdminTransactionService;

/**
 * AdminTransactionService test
 * 
 * @author charly
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class AdminTransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepositoryMock;

    @InjectMocks
    private AdminTransactionService systemUnderTest;

    @Test
    public void deleteTransaction() {
        // Install stub
        String transactionId = "transaction123";

        // Call method to test
        systemUnderTest.deleteTransaction(transactionId);

        // Verify behaviors
        Mockito.verify(transactionRepositoryMock).delete(transactionId);
    }

    @Test(expected = ServiceException.class)
    public void deleteTransactionOnNullTransactionArg() {
        // Install stub
        String transactionId = null;

        // Call method to test
        systemUnderTest.deleteTransaction(transactionId);
    }

}
