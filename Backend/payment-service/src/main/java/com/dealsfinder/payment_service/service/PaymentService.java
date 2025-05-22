package com.dealsfinder.payment_service.service;



import com.braintreegateway.*;
import com.dealsfinder.payment_service.dto.PaymentRequestDto;
import com.dealsfinder.payment_service.dto.PaymentResponseDto;
import com.dealsfinder.payment_service.model.Payment;
import com.dealsfinder.payment_service.repository.PaymentRepository;
import com.dealsfinder.payment_service.util.PaymentUtil;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private BraintreeGateway gateway;

    @Autowired
    private PaymentRepository paymentRepository;

    public PaymentResponseDto processPayment(PaymentRequestDto requestDto) {
        TransactionRequest transactionRequest = new TransactionRequest()
                .amount(new java.math.BigDecimal(requestDto.getAmount()))
                .paymentMethodNonce(requestDto.getPaymentMethodNonce())
                .options()
                .submitForSettlement(true)
                .done();

        Result<Transaction> result = gateway.transaction().sale(transactionRequest);

        if (result.isSuccess()) {
            Transaction transaction = result.getTarget();
            Payment payment = Payment.builder()
                    .paymentMethodNonce(requestDto.getPaymentMethodNonce())
                    .amount(requestDto.getAmount())
                    .userEmail(requestDto.getUserEmail())
                    .dealId(requestDto.getDealId())
                    .transactionId(transaction.getId())
                    .status(transaction.getStatus().toString())
                    .build();
            paymentRepository.save(payment);
        }

        return PaymentUtil.buildResponse(result);
    }
}
