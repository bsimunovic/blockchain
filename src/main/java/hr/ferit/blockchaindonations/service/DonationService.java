package hr.ferit.blockchaindonations.service;

import hr.ferit.blockchaindonations.dto.DonationDetailsDto;
import hr.ferit.blockchaindonations.dto.DonationResponse;

import java.util.List;

public interface DonationService {
    List<DonationResponse> getDonations();
    DonationDetailsDto getDonation(Long donationId);
    List<DonationResponse> getDonationsByUser(Long userId);
}
