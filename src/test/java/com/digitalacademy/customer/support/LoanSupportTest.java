package com.digitalacademy.customer.support;

import com.digitalacademy.customer.model.Customer;
import com.digitalacademy.customer.model.response.GetLoanInfoResponse;

public class LoanSupportTest {
    public static GetLoanInfoResponse getNewLoan() {
        GetLoanInfoResponse loan = new GetLoanInfoResponse();
        loan.setId(1L);
        loan.setStatus("OK");
        loan.setAccountPayable("02-029444-933");
        loan.setAccountReceivable("193-938323-878");
        loan.setPrincipalAmount(100.00);
        return loan;
    }
}
