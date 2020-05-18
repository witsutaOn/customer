package com.digitalacademy.customer.api;

import com.digitalacademy.customer.model.response.GetLoanInfoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class LoanServiceApiTest {
    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    LoanApi loanApi;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        loanApi = new LoanApi(restTemplate);
    }

    public static ResponseEntity<String> prepareResponseSuccess(){
        return ResponseEntity.ok("{\n" +
                "    \"status\": {\n" +
                "        \"code\": \"0\",\n" +
                "        \"message\": \"success\"\n" +
                "    },\n" +
                "    \"data\": {\n" +
                "        \"id\": 1,\n" +
                "        \"status\": \"OK\",\n" +
                "        \"account_payable\": \"102-222-222\",\n" +
                "        \"account_receivable\": \"10-232-445-78\",\n" +
                "        \"principal_amount\": 3000.0\n" +
                "    }\n" +
                "}");
    }

    @DisplayName("Test get loan info should return loan information")
    @Test
    void testGetLoanInfo() throws Exception{
        Long reqId = 1L;

        when(restTemplate.exchange(
                ArgumentMatchers.<RequestEntity<String>>any(),
                ArgumentMatchers.<Class<String>>any()))
                .thenReturn(this.prepareResponseSuccess());

        GetLoanInfoResponse resp = loanApi.getLoanInfo(reqId);

        assertEquals("1",resp.getId().toString());
        assertEquals("OK",resp.getStatus());
        assertEquals("102-222-222",resp.getAccountPayable());
        assertEquals("10-232-445-78",resp.getAccountReceivable());
        assertEquals(3000.0, resp.getPrincipalAmount(),0);

        verify(restTemplate,times(1))
                .exchange(ArgumentMatchers.<RequestEntity<String>>any(),
                        ArgumentMatchers.<Class<String>>any());

    }

    public static  ResponseEntity<String> prepareResponseEntityLOAN4002(){
        return ResponseEntity.ok("{\n" +
                "    \"status\": {\n" +
                "        \"code\": \"LOAN4002\",\n" +
                "        \"message\": \"Loan information not found\"\n" +
                "    }\n" +
                "}");
    }

    @DisplayName("Test get loan info should return LOAN4002")
    @Test
    void testGetLoanInfoLOAN4002()throws Exception{
        Long reqId = 2L;

        when(restTemplate.exchange(
                ArgumentMatchers.<RequestEntity<String>>any(),
                ArgumentMatchers.<Class<String>>any()))
                .thenReturn(this.prepareResponseEntityLOAN4002());

        GetLoanInfoResponse resp = loanApi.getLoanInfo(reqId);

        assertEquals(null,resp.getId());
        assertEquals(null,resp.getStatus());
        assertEquals(null,resp.getAccountPayable());
        assertEquals(null,resp.getAccountReceivable());
        assertEquals(0,resp.getPrincipalAmount(),0);
    }

    public static  ResponseEntity<String> prepareResponseEntityLOAN4001(){
        return ResponseEntity.ok("{\n" +
                "    \"status\": {\n" +
                "        \"code\": \"LOAN4001\",\n" +
                "        \"message\": \"Cannot get loan information\"\n" +
                "    },\n" +
                "    \"data\": \"Cannot get loan information\"\n" +
                "}");

    }

    @DisplayName("Test get loan info should return LOAN4001")
    @Test
    void testGetLoanInfoLOAN4001()throws Exception{
        Long reqId = 3L;

        when(restTemplate.exchange(
                ArgumentMatchers.<RequestEntity<String>>any(),
                ArgumentMatchers.<Class<String>>any()))
                .thenReturn(this.prepareResponseEntityLOAN4001());

        GetLoanInfoResponse resp = loanApi.getLoanInfo(reqId);

        assertEquals(null,resp.getId());
        assertEquals(null,resp.getStatus());
        assertEquals(null,resp.getAccountPayable());
        assertEquals(null,resp.getAccountReceivable());
        assertEquals(0,resp.getPrincipalAmount(),0);

    }

//    @DisplayName("Test get loan info should return client")
}
