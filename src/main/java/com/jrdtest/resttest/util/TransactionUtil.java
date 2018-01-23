/**
 * 
 */
package com.jrdtest.resttest.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.DateConverter;

import com.jrdtest.resttest.service.schema.TransactionRS;
import com.jrdtest.resttest.service.to.TransactionTO;

/**
 * @author Rishi Mishra
 *
 */
public class TransactionUtil {

    /**
     * @param request
     * @param txnID
     * @return
     */
    public static TransactionTO transformSchemaToServiceObject(TransactionRS request, long txnID) {
        if (null != request) {
            TransactionTO transactionTO = (TransactionTO) copyProperties(request, new TransactionTO());
            transactionTO.setTxnId(txnID);
            return transactionTO;
        }
        return null;
    }

    public static Object copyProperties(Object fromSource, Object toDestination) {
        try {
            if (fromSource != null) {
                ConvertUtils.register(new DateConverter(null), java.sql.Date.class);
                ConvertUtils.register(new DateConverter(null), java.util.Date.class);
                ConvertUtils.register(new BigDecimalConverter(null), java.math.BigDecimal.class);

                BeanUtils.copyProperties(toDestination, fromSource);
                return toDestination;
            }
        }
        catch (IllegalAccessException | InvocationTargetException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
