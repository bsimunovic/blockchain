package hr.ferit.blockchaindonations.service;

import hr.ferit.blockchaindonations.dto.BlockchainResponse;
import hr.ferit.blockchaindonations.request.CreateDonationRequest;

public interface BlockchainService {
    BlockchainResponse createBlockchain(CreateDonationRequest request);
}
