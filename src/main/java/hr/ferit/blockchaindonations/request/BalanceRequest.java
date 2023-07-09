package hr.ferit.blockchaindonations.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BalanceRequest {
    private Long userId;
    private BigDecimal amount;
}
