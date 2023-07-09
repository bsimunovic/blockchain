package hr.ferit.blockchaindonations.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlockchainResponse {
    private Long blockchainId;
    private Long donationId;
}
