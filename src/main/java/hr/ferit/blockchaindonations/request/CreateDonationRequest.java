package hr.ferit.blockchaindonations.request;

import lombok.Data;

@Data
public class CreateDonationRequest {

    private String name;
    private String description;
    private Long ownerId;

}
