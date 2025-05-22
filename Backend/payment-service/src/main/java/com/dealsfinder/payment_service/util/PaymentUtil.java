package com.dealsfinder.payment_service.util;


import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.dealsfinder.payment_service.dto.PaymentResponseDto;

public class PaymentUtil {

    public static PaymentResponseDto buildResponse(Result<Transaction> result) {
        if (result.isSuccess()) {
            Transaction transaction = result.getTarget();
            return PaymentResponseDto.builder()
                    .transactionId(transaction.getId())
                    .status(transaction.getStatus().toString())
                    .message("Payment successful")
                    .build();
        } else {
            return PaymentResponseDto.builder()
                    .transactionId(null)
                    .status("FAILED")
                    .message(result.getMessage())
                    .build();
        }
    }
}
