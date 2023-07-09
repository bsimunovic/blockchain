package hr.ferit.blockchaindonations.service;

import hr.ferit.blockchaindonations.dto.BlockchainResponse;
import hr.ferit.blockchaindonations.model.Blockchain;
import hr.ferit.blockchaindonations.model.Donation;
import hr.ferit.blockchaindonations.model.User;
import hr.ferit.blockchaindonations.repository.BlockRepository;
import hr.ferit.blockchaindonations.repository.BlockchainRepository;
import hr.ferit.blockchaindonations.request.CreateDonationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlockchainServiceImpl implements BlockchainService {
    private final BlockchainRepository blockchainRepository;
    private final BlockRepository blockRepository;
    private final UserService userService;
    @Override
    public BlockchainResponse createBlockchain(CreateDonationRequest request) {
        User user = userService.getUser(request.getOwnerId());
        Donation donation = new Donation(request.getName(), request.getDescription(), user);
        Blockchain blockchain = new Blockchain(donation);
        donation.setBlockchain(blockchain);
        blockchain.getBlocks().get(0).setBlockchain(blockchain);
        blockchainRepository.save(blockchain);
        blockRepository.saveAll(blockchain.getBlocks());
        return new BlockchainResponse(blockchain.getId(), donation.getId());
    }
}
