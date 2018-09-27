package com.capstone.truckerfrontend.models;

import java.math.BigInteger;
import java.nio.charset.Charset;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

//from https://www.owasp.org/index.php/Hashing_Java
public class HashedPassword {
    public final String string;

    public HashedPassword(String password, String username, int iterations, int keyLength) {
        byte[] salt = username.getBytes();

        string = hash(password.toCharArray(), salt, iterations, keyLength);
    }

    private String hash(char[] password, byte[] salt, int iterations, int keyLength) {
        String hash = null;
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keyLength);
            SecretKey key = skf.generateSecret(spec);
            hash = new String(key.getEncoded());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return hash;
    }
}
