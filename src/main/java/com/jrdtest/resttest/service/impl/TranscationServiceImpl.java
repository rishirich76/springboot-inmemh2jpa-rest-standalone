/**
 * 
 */
package com.jrdtest.resttest.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrdtest.resttest.entity.Transaction;
import com.jrdtest.resttest.repo.TransactionRepository;
import com.jrdtest.resttest.service.TranscationService;
import com.jrdtest.resttest.service.to.TransactionTO;
import com.jrdtest.resttest.util.TransactionUtil;

/**
 * @author Rishi
 *
 */
@Service
public class TranscationServiceImpl implements TranscationService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public void saveTransactionRecord(TransactionTO transactionTO) {
        transactionRepository.save((Transaction) TransactionUtil.copyProperties(transactionTO, new Transaction()));
    }

    @Override
    public TransactionTO retrieveTransactionRecord(Long txnId) {
        if (null != txnId) {
            Transaction txn = transactionRepository.findOne(txnId);
            return (TransactionTO) TransactionUtil.copyProperties(txn, new TransactionTO());
        }
        return null;
    }

    @Override
    public Long sumOfTxns(Long txnId) {
        if (null != txnId) {
            return Optional.ofNullable(transactionRepository.findAll()).orElseGet(Collections::emptyList).stream()
                    .filter(e -> (e.getTxnId() == txnId) || (null != e.getParentId() && e.getParentId() == txnId))
                    .mapToLong(Transaction::getAmount).sum();
        }
        return null;
    }

    @Override
    public List<Long> retrieveIDsByType(String type) {
        if (StringUtils.isNotBlank(type)) {
            return Optional.ofNullable(transactionRepository.findAll()).orElseGet(Collections::emptyList).stream()
                    .filter(e -> StringUtils.equalsIgnoreCase(type, e.getType())).map(Transaction::getTxnId)
                    .collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public void deleteTransactionRecord(Long txnID) {
        if (null != txnID) {
            transactionRepository.delete(txnID);
        }
    }

}
