/**
 * 
 */
package com.jrdtest.resttest.service.to;

/**
 * @author Rishi
 *
 */
public class TransactionTO {

	private long txnId;
	
	private String type;
	
	private Long parentId;
	
	private long amount;

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
    public long getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(long amount) {
        this.amount = amount;
    }
}
