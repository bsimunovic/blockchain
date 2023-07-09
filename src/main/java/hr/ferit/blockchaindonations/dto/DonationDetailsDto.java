package hr.ferit.blockchaindonations.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DonationDetailsDto extends DonationResponse {
    List<TransactionResponse> transactions;

    public DonationDetailsDto(Long id, String name, String description, String address, List<TransactionResponse> transactions) {
        super(id, name, description, address);
        this.transactions = transactions;
    }
}
