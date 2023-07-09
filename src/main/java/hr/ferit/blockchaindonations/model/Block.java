package hr.ferit.blockchaindonations.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "block")
public class Block implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hash;
    private String previousHash;
    @OneToMany(mappedBy = "block", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Transaction> transactions;
    private Timestamp timestamp;
    @ManyToOne
    @JoinColumn(name = "blockchain_id")
    private Blockchain blockchain;
    public Block(List<Transaction> transactions, String previousHash, Timestamp timestamp) {
        for (Transaction transaction : transactions) {
            transaction.setBlock(this);
        }
        this.transactions = transactions;
        this.previousHash = previousHash;
        this.timestamp = timestamp;
        this.hash = calculateHash();
    }

    private String calculateHash() {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String dataToHash = this.timestamp + this.previousHash + this.transactions;
            byte[] hashBytes = digest.digest(dataToHash.getBytes(UTF_8));

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
}
