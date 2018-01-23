package com.jrdtest.resttest.service.schema;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author Rishi Mishra
 *
 */
 @JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "amount", "type", "parent_id", "status", "sum" })
public class TransactionRS {

    @JsonProperty("amount")
    private Long amount;

    @JsonProperty("type")
    private String type;

    @JsonProperty("parent_id")
    private Long parentId;

    @JsonProperty("status")
    private String status;
    
    @JsonProperty("sum")
    private Long sum;

    public TransactionRS() {}
    
    public TransactionRS(String status) {
        this.status = status;
    }
    /**
     * @return the amount
     */
    @JsonProperty("amount")
    public Long getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    @JsonProperty("amount")
    public void setAmount(Long amount) {
        this.amount = amount;
    }

    /**
     * @return the type
     */
    @JsonProperty("type")
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    @JsonProperty("type")
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
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the sum
     */
    public Long getSum() {
        return sum;
    }

    /**
     * @param sum the sum to set
     */
    public void setSum(Long sum) {
        this.sum = sum;
    }
    
}
