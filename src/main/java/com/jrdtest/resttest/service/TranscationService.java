package com.jrdtest.resttest.service;

import java.util.List;

import com.jrdtest.resttest.service.to.TransactionTO;

/**
 * @author Rishi
 *
 */

public interface TranscationService {

    public void saveTransactionRecord(TransactionTO transactionTO);

    public TransactionTO retrieveTransactionRecord(Long txnId);

    public Long sumOfTxns(Long txnId);

    public List<Long> retrieveIDsByType(String type);
}
