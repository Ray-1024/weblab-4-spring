package ray1024.weblab4.data;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class Hasher {
    private final MessageDigest digest;

    public Hasher() throws NoSuchAlgorithmException {
        digest = MessageDigest.getInstance("SHA-256");
    }

    private String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(hash.length << 1);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public String hash(String str) {
        try {
            return bytesToHex(digest.digest(str.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            return "";
        }
    }
}
