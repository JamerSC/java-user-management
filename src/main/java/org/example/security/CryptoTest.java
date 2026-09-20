package org.example.security;

public class CryptoTest {

    public static void main(String[] args) {

        int id = 15;

        // Encrypt
        String encrypted =
                CryptoUtil.encrypt(String.valueOf(id));

        System.out.println("Original: " + id);
        System.out.println("Encrypted: " + encrypted);

        // Decrypt
        String decrypted =
                CryptoUtil.decrypt(encrypted);

        System.out.println("Decrypted: " + decrypted);

        int originalId =
                Integer.parseInt(decrypted);

        System.out.println("Original ID: " + originalId);
    }
}
