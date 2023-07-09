package hr.ferit.blockchaindonations.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "blockchain")
@NoArgsConstructor
public class Blockchain implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "blockchain", cascade = CascadeType.ALL)
    private List<Block> blocks;

    @OneToOne(mappedBy = "blockchain", cascade = CascadeType.ALL)
    private Donation donation;
    public Blockchain(Donation donation) {
        this.blocks = new ArrayList<>();
        this.donation = donation;
        this.blocks.add(createGenesisBlock(donation));
    }

    private Block createGenesisBlock(Donation donation) {
       Block genesisBlock = new Block();
       genesisBlock.setPreviousHash("0");
       genesisBlock.setTimestamp(new Timestamp(System.currentTimeMillis()));
       genesisBlock.setTransactions(new ArrayList<>());
       genesisBlock.setHash(calculateHash(genesisBlock, donation));

       return genesisBlock;
    }

    private String calculateHash(Block block, Donation donation) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String dataToHash = block.getTimestamp() + block.getPreviousHash() + block.getTransactions() + donation;
            byte[] hashBytes = digest.digest(dataToHash.getBytes(StandardCharsets.UTF_8));

            StringBuilder hashBuilder = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) {
                    hashBuilder.append('0');
                }
                hashBuilder.append(hex);
            }
            return hashBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Block getLatestBlock() {
        return this.blocks.get(this.blocks.size() - 1);
    }
}
