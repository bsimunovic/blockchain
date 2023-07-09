package hr.ferit.blockchaindonations.utils;

import hr.ferit.blockchaindonations.model.Block;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
public class HashUtils {
    public static String calculateHash(Block block) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String dataToHash = block.getTimestamp() + block.getPreviousHash() + block.getTransactions();
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
