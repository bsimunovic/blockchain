package hr.ferit.blockchaindonations.service;

import hr.ferit.blockchaindonations.dto.TransactionResponse;
import hr.ferit.blockchaindonations.request.TransactionRequest;

import java.util.List;

public interface TransactionService {
    Long makeTransaction(TransactionRequest transactionRequest);
    List<TransactionResponse> getTransactions(Long donationId);
    TransactionResponse getTransaction(Long transactionId);
}
