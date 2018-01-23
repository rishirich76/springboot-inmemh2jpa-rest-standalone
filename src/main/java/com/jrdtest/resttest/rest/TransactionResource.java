/**
 * 
 */
package com.jrdtest.resttest.rest;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jrdtest.resttest.service.TranscationService;
import com.jrdtest.resttest.service.schema.TransactionRS;
import com.jrdtest.resttest.service.to.TransactionTO;
import com.jrdtest.resttest.util.TransactionUtil;

/**
 * @author Rishi
 *
 */
@RequestMapping("/transactionservice")
@RestController
public class TransactionResource {

    @Autowired
    private TranscationService transactionService;

    @RequestMapping(value = "/transaction/{txnID}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveOrUpdateTransaction(@RequestBody TransactionRS request, BindingResult bindingResult,
            @PathVariable(required = true) Long txnID) {
        transactionService.saveTransactionRecord(TransactionUtil.transformSchemaToServiceObject(request, txnID));
        TransactionRS transactionResponse = new TransactionRS();
        transactionResponse.setStatus("ok");
        return new ResponseEntity<TransactionRS>(transactionResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/transaction/{txnID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTransactionByID(@PathVariable(required = true) Long txnID) {
        TransactionTO transactionRecord = transactionService.retrieveTransactionRecord(txnID);
        if (null != transactionRecord) {
            TransactionRS transactionResponse = (TransactionRS) TransactionUtil.copyProperties(transactionRecord,
                    new TransactionRS());
            // transactionResponse.setStatus("ok");
            return new ResponseEntity<>(transactionResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(new TransactionRS("No Data Found"), HttpStatus.OK);
    }

    @RequestMapping(value = "/transaction/{txnID}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteTransaction(@PathVariable(required = true) Long txnID) {
        transactionService.deleteTransactionRecord(txnID);
        TransactionRS transactionResponse = new TransactionRS();
        transactionResponse.setStatus("ok");
        return new ResponseEntity<TransactionRS>(transactionResponse, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/types/{type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getIDsOfGivenType(@PathVariable(required = true) String type) {
        List<Long> idList = transactionService.retrieveIDsByType(type);
        if (CollectionUtils.isNotEmpty(idList)) {
            // transactionResponse.setStatus("ok");
            return new ResponseEntity<>(idList, HttpStatus.OK);
        }
        return new ResponseEntity<>(new TransactionRS("No Data Found"), HttpStatus.OK);
    }

    @RequestMapping(value = "/sum/{txnID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getSumOfRelatedTxns(@PathVariable(required = true) Long txnID) {
        Long sum = transactionService.sumOfTxns(txnID);
        if (null != sum) {
            TransactionRS transactionResponse = new TransactionRS();
            transactionResponse.setSum(sum);
            return new ResponseEntity<>(transactionResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(new TransactionRS("No Data Found"), HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(new TransactionRS("Error --> " + ex.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);

    }
}
