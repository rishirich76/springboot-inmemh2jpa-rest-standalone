/**
 * 
 */
package com.jrdtest.resttest.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Rishi Mishra
 *
 */
@Entity
public class Transaction {
    
    @Id
    private long txnId;

    private String type;

    private Long parentId;

    private Long amount;
    
    public Transaction() {}
    
    public Transaction(long txnId, String type) {
        this.txnId = txnId;
        this.type = type;
    }

    /**
     * @return the txnId
     */
    public long getTxnId() {
        return txnId;
    }

    /**
     * @param txnId the txnId to set
     */
    public void setTxnId(long txnId) {
        this.txnId = txnId;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the parentId
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * @param parentId the parentId to set
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * @return the amount
     */
    public Long getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(Long amount) {
        this.amount = amount;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Transaction [txnId=" + txnId + ", type=" + type + ", parentId=" + parentId + ", amount=" + amount + "]";
    }

}
