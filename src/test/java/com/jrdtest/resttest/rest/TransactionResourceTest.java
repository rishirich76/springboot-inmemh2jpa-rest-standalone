/**
 * 
 */
package com.jrdtest.resttest.rest;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrdtest.resttest.RestTestApplication;
import com.jrdtest.resttest.service.schema.TransactionRS;

/**
 * @author Rishi Mishra
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RestTestApplication.class)
@ActiveProfiles("test")
public class TransactionResourceTest {

    @InjectMocks
    TransactionResource transactionResource;

    @Autowired
    WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void initTests() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testCreateUpdateAndRetrieve() throws Exception {
        long txnID = 100L;
        // CREATE TXN
        mockMvc.perform(
                put("/transactionservice/transaction/" + txnID)
                        .content(toJson(populateMockTxnObjectWithGivenIDAndAmount(1000L, null, "shopping")))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful()).andReturn();
        // RETRIEVE TXN
        mockMvc.perform(get("/transactionservice/transaction/" + txnID).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$.amount", is(1000)));

        // Update TXN
        mockMvc.perform(
                put("/transactionservice/transaction/" + txnID)
                        .content(toJson(populateMockTxnObjectWithGivenIDAndAmount(2000L, null, "shopping")))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful()).andReturn();

        // RETRIEVE TXN
        mockMvc.perform(get("/transactionservice/transaction/" + txnID).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$.amount", is(2000)));

        // DELETE TXN
        mockMvc.perform(delete("/transactionservice/transaction/" + txnID).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void testTxnTypeService() throws Exception {
        long txnID = 100L;

        // CREATE TXN
        mockMvc.perform(
                put("/transactionservice/transaction/" + txnID)
                        .content(toJson(populateMockTxnObjectWithGivenIDAndAmount(1000L, null, "shopping")))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful()).andReturn();
        mockMvc.perform(
                put("/transactionservice/transaction/" + 200L)
                        .content(toJson(populateMockTxnObjectWithGivenIDAndAmount(1000L, null, "shopping")))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful()).andReturn();
        mockMvc.perform(
                put("/transactionservice/transaction/" + 300L)
                        .content(toJson(populateMockTxnObjectWithGivenIDAndAmount(1000L, null, "billing")))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful()).andReturn();

        // RETRIEVE TXN
        mockMvc.perform(get("/transactionservice/types/shopping").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$").value(Matchers.hasSize(2)));

        // DELETE TXN
        mockMvc.perform(delete("/transactionservice/transaction/" + txnID).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
        mockMvc.perform(delete("/transactionservice/transaction/" + 200L).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
        mockMvc.perform(delete("/transactionservice/transaction/" + 300L).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void testSumTxn() throws Exception {
        long txnID = 100L;

        // CREATE TXN
        mockMvc.perform(
                put("/transactionservice/transaction/" + txnID)
                        .content(toJson(populateMockTxnObjectWithGivenIDAndAmount(1000L, null, "shopping")))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful()).andReturn();
        mockMvc.perform(
                put("/transactionservice/transaction/" + 200L)
                        .content(toJson(populateMockTxnObjectWithGivenIDAndAmount(2000L, 100L, "shopping")))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful()).andReturn();
        mockMvc.perform(
                put("/transactionservice/transaction/" + 300L)
                        .content(toJson(populateMockTxnObjectWithGivenIDAndAmount(1000L, 100L, "billing")))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful()).andReturn();

        // RETRIEVE TXN
        mockMvc.perform(get("/transactionservice/sum/" + txnID).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$.sum", is(4000)));

        // DELETE TXN
        mockMvc.perform(delete("/transactionservice/transaction/" + txnID).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
        mockMvc.perform(delete("/transactionservice/transaction/" + 200L).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
        mockMvc.perform(delete("/transactionservice/transaction/" + 300L).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

    }

    private TransactionRS populateMockTxnObjectWithGivenIDAndAmount(long amt, Long parentId, String type) {
        TransactionRS trans = new TransactionRS();
        trans.setAmount(amt);
        trans.setParentId(parentId);
        trans.setType(type);
        return trans;
    }

    private byte[] toJson(Object r) throws Exception {
        ObjectMapper map = new ObjectMapper();
        return map.writeValueAsString(r).getBytes();
    }

}
