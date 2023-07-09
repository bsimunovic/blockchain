package hr.ferit.blockchaindonations.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.utils.Numeric;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

@Getter
@Setter
@Entity
@Table(name = "wallet")
public class Wallet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String publicKey;
    @Column(columnDefinition = "TEXT")
    private String privateKey;
    private BigDecimal balance;
    private String address;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User user;

    public Wallet() {
        generateKeyPair();
        generateEthereumAddress();
        this.balance = BigDecimal.ZERO;
    }
    private void generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            KeyPair keyPair = keyGen.generateKeyPair();
            byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
            byte[] publicKeyBytes = keyPair.getPublic().getEncoded();

            BigInteger privateKeyBigInt = new BigInteger(1, privateKeyBytes);
            BigInteger publicKeyBigInt = new BigInteger(1, publicKeyBytes);
            this.privateKey = Numeric.toHexStringWithPrefix(privateKeyBigInt);
            this.publicKey = Numeric.toHexStringWithPrefix(publicKeyBigInt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private void generateEthereumAddress() {
        try {
            ECKeyPair ecKeyPair = ECKeyPair.create(Numeric.toBigInt(privateKey));
            this.address = "0x" + Keys.getAddress(ecKeyPair);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
