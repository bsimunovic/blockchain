package hr.ferit.blockchaindonations.service;

import hr.ferit.blockchaindonations.dto.DonationDetailsDto;
import hr.ferit.blockchaindonations.dto.DonationResponse;
import hr.ferit.blockchaindonations.dto.TransactionResponse;
import hr.ferit.blockchaindonations.model.Donation;
import hr.ferit.blockchaindonations.model.Transaction;
import hr.ferit.blockchaindonations.repository.DonationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DonationServiceImpl implements DonationService {
    private final DonationRepository donationRepository;
    private final TransactionService transactionService;
    @Override
    public List<DonationResponse> getDonations() {
        return donationRepository.findAll().stream().map(donation -> new DonationResponse(donation.getId(), donation.getName(), donation.getDescription(), donation.getOwner().getWallet().getAddress())).toList();
    }

    @Override
    public DonationDetailsDto getDonation(Long donationId) {
        List<TransactionResponse> transactions = transactionService.getTransactions(donationId);
        Donation donation = donationRepository.getById(donationId);
        return new DonationDetailsDto(donationId, donation.getName(), donation.getDescription(), donation.getOwner().getWallet().getAddress(), transactions);
    }

    @Override
    public List<DonationResponse> getDonationsByUser(Long userId) {
        return donationRepository.findAllByOwnerId(userId).stream().map(donation -> new DonationResponse(donation.getId(), donation.getName(), donation.getDescription(), donation.getOwner().getWallet().getAddress())).toList();
    }

}
