package com.digitalacademy.customer.controller;

import com.digitalacademy.customer.api.LoanApi;
import com.digitalacademy.customer.support.CustomerSupportTest;
import com.digitalacademy.customer.support.LoanSupportTest;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class LoanControllerTest {

    @Mock
    LoanApi loanApi;

    @InjectMocks
    LoanController loanController;

    private MockMvc mvc;

    public static final String urlLoan = "/loan/";

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        loanController = new LoanController(loanApi);
        mvc = MockMvcBuilders.standaloneSetup(loanController)
                .build();
    }

    @DisplayName("Test get loan info should return loan information")
    @Test
    void testGetLoanInfo()throws Exception{
        Long reqId = 1L;


        when(loanApi.getLoanInfo(reqId)).thenReturn(LoanSupportTest.getNewLoan());
        MvcResult mvcResult = mvc.perform(get(urlLoan + "/" + reqId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        JSONObject json = new JSONObject(mvcResult.getResponse().getContentAsString());
        assertEquals("1",json.get("id").toString());
        assertEquals("OK",json.get("status"));
        assertEquals("02-029444-933",json.get("account_payable"));
        assertEquals("193-938323-878",json.get("account_receivable"));
        assertEquals(100.00,json.get("principal_amount"));
    }

}
