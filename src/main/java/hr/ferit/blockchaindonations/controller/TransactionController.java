package hr.ferit.blockchaindonations.controller;

import hr.ferit.blockchaindonations.dto.TransactionResponse;
import hr.ferit.blockchaindonations.request.TransactionRequest;
import hr.ferit.blockchaindonations.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/transactions")
@RequiredArgsConstructor
@CrossOrigin
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Long> createTransaction(@Valid @RequestBody TransactionRequest transactionRequest) {
        return ResponseEntity.ok(transactionService.makeTransaction(transactionRequest));
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getTransactions(@RequestParam Long donationId) {
        return ResponseEntity.ok(transactionService.getTransactions(donationId));
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionResponse> getTransaction(@PathVariable Long transactionId) {
        return ResponseEntity.ok(transactionService.getTransaction(transactionId));
    }
}
