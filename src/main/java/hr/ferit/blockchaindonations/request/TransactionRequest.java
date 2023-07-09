package hr.ferit.blockchaindonations.request;

import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
public class TransactionRequest {
    @NonNull
    private Long senderId;
    @NonNull
    private Long donationId;
    @NonNull
    private BigDecimal amount;
}
