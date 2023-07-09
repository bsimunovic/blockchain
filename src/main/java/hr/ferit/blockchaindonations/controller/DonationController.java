package hr.ferit.blockchaindonations.controller;

import hr.ferit.blockchaindonations.dto.BlockchainResponse;
import hr.ferit.blockchaindonations.dto.DonationDetailsDto;
import hr.ferit.blockchaindonations.dto.DonationResponse;
import hr.ferit.blockchaindonations.request.CreateDonationRequest;
import hr.ferit.blockchaindonations.service.BlockchainService;
import hr.ferit.blockchaindonations.service.DonationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/donations")
@RequiredArgsConstructor
@CrossOrigin
public class DonationController {
    private final BlockchainService blockchainService;
    private final DonationService donationService;
    @PostMapping
    public ResponseEntity<BlockchainResponse> createDonation(@Valid @RequestBody CreateDonationRequest request) {
       return ResponseEntity.ok(blockchainService.createBlockchain(request));
    }

    @GetMapping
    public ResponseEntity<List<DonationResponse>> getDonations() {
        return ResponseEntity.ok(donationService.getDonations());
    }

    @GetMapping("/{donationId}")
    public ResponseEntity<DonationDetailsDto> getDonationDetails(@PathVariable Long donationId) {
        return ResponseEntity.ok(donationService.getDonation(donationId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DonationResponse>> getDonationsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(donationService.getDonationsByUser(userId));
    }
}
