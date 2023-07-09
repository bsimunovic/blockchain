package hr.ferit.blockchaindonations.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DonationResponse {
    private Long id;
    private String name;
    private String description;
    private String owner;
}
