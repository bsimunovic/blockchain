package hr.ferit.blockchaindonations.service;

import hr.ferit.blockchaindonations.dto.TransactionResponse;
import hr.ferit.blockchaindonations.model.*;
import hr.ferit.blockchaindonations.repository.*;
import hr.ferit.blockchaindonations.request.TransactionRequest;
import hr.ferit.blockchaindonations.utils.HashUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final UserRepository userRepository;
    private final DonationRepository donationRepository;
    private final TransactionRepository transactionRepository;
    private final BlockRepository blockRepository;
    private final BlockchainRepository blockchainRepository;
    private final WalletRepository walletRepository;
    @Override
    @Transactional
    public Long makeTransaction(TransactionRequest transactionRequest) {
        User sender = userRepository.findById(transactionRequest.getSenderId()).orElse(null);
        Donation donation = donationRepository.findById(transactionRequest.getDonationId()).orElse(null);
        User receiver = donation.getOwner();
        Blockchain blockchain = blockchainRepository.getById(donation.getBlockchain().getId());
        Block lastBlock = blockchain.getLatestBlock();
        Transaction transaction = new Transaction(sender, receiver, transactionRequest.getAmount());
        if(lastBlock.getTransactions().size() >= 5) {
            Block newBlock = new Block(List.of(transaction), lastBlock.getHash(), new Timestamp(System.currentTimeMillis()));
            newBlock.setBlockchain(donation.getBlockchain());
            blockRepository.save(newBlock);
            transaction.setBlock(newBlock);
        } else {
            lastBlock.setHash(HashUtils.calculateHash(lastBlock));
            blockRepository.save(lastBlock);
            transaction.setBlock(lastBlock);
        }
        Wallet senderWallet = sender.getWallet();
        Wallet receiverWallet = receiver.getWallet();
        senderWallet.setBalance(senderWallet.getBalance().subtract(transactionRequest.getAmount()));
        receiverWallet.setBalance(receiverWallet.getBalance().add(transactionRequest.getAmount()));
        walletRepository.save(senderWallet);
        walletRepository.save(receiverWallet);
        transactionRepository.save(transaction);
        return transaction.getId();
    }

    @Override
    public List<TransactionResponse> getTransactions(Long donationId) {
        Donation donation = donationRepository.getById(donationId);
        Blockchain blockchain = donation.getBlockchain();
        List<Transaction> transactions = blockchain.getBlocks().stream().flatMap(block -> block.getTransactions().stream()).toList();
        return transactions.stream().map(transaction -> new TransactionResponse(transaction.getSender().getWallet().getAddress(), transaction.getReceiver().getWallet().getAddress(), transaction.getAmount(), transaction.getTimestamp())).toList();
    }

    @Override
    public TransactionResponse getTransaction(Long transactionId) {
        Transaction transaction = transactionRepository.getById(transactionId);
        return new TransactionResponse(transaction.getSender().getWallet().getAddress(), transaction.getReceiver().getWallet().getAddress(), transaction.getAmount(), transaction.getTimestamp());
    }
}
