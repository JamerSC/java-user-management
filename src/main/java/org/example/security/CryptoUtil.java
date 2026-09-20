package org.example.security;


import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

public class CryptoUtil {

    // This class can be used for cryptographic operations in the future.
    private static final String ALGORITHM = "AES/GCM/NoPadding";
    private static final int IV_LENGTH = 12;
    private static final int TAG_LENGTH = 128;

    private static final byte[] KEY =
            "12345678901234567890123456789012"
                    .getBytes(StandardCharsets.UTF_8);

    private CryptoUtil() {
    }

    // ENCRYPT
    public static String encrypt(String value) {

        try {
            byte[] iv = new byte[IV_LENGTH];

            SecureRandom secureRandom = new SecureRandom();
            secureRandom.nextBytes(iv);

            Cipher cipher = Cipher.getInstance(ALGORITHM);

            SecretKeySpec secretKey =
                    new SecretKeySpec(KEY, "AES");

            GCMParameterSpec parameterSpec =
                    new GCMParameterSpec(TAG_LENGTH, iv);

            cipher.init(
                    Cipher.ENCRYPT_MODE,
                    secretKey,
                    parameterSpec
            );

            byte[] encrypted =
                    cipher.doFinal(
                            value.getBytes(StandardCharsets.UTF_8)
                    );

            // Store IV + encrypted data together
            byte[] result = new byte[iv.length + encrypted.length];

            System.arraycopy(
                    iv,
                    0,
                    result,
                    0,
                    iv.length
            );

            System.arraycopy(
                    encrypted,
                    0,
                    result,
                    iv.length,
                    encrypted.length
            );

            return Base64.getUrlEncoder()
                    .withoutPadding()
                    .encodeToString(result);

        } catch (Exception e) {
            throw new RuntimeException(
                    "Encryption failed",
                    e
            );
        }
    }

    // DECRYPT
    public static String decrypt(String encryptedValue) {

        try {
            byte[] combined =
                    Base64.getUrlDecoder()
                            .decode(encryptedValue);

            byte[] iv =
                    new byte[IV_LENGTH];

            byte[] encrypted =
                    new byte[combined.length - IV_LENGTH];

            System.arraycopy(
                    combined,
                    0,
                    iv,
                    0,
                    IV_LENGTH
            );

            System.arraycopy(
                    combined,
                    IV_LENGTH,
                    encrypted,
                    0,
                    encrypted.length
            );

            Cipher cipher =
                    Cipher.getInstance(ALGORITHM);

            SecretKeySpec secretKey =
                    new SecretKeySpec(KEY, "AES");

            GCMParameterSpec parameterSpec =
                    new GCMParameterSpec(
                            TAG_LENGTH,
                            iv
                    );

            cipher.init(
                    Cipher.DECRYPT_MODE,
                    secretKey,
                    parameterSpec
            );

            byte[] decrypted =
                    cipher.doFinal(encrypted);

            return new String(
                    decrypted,
                    StandardCharsets.UTF_8
            );

        } catch (Exception e) {
            throw new RuntimeException(
                    "Decryption failed",
                    e
            );
        }
    }
}
