package esgi.clicfootbackend.clicfootbackend.Hash;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Hash {
    public static String hashThis(String password) throws NoSuchAlgorithmException {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            String hashedPassword = DatatypeConverter
                    .printHexBinary(digest).toUpperCase();
            return hashedPassword;
    }
}
