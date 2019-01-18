package com.bbva.encryption.util;

import com.bbva.encryption.common.constants.PropertiesConstants;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;

public class EncryptionUtil {

    private static final Logger logger = LoggerFactory.getLogger(EncryptionUtil.class);

    public static String tripelDesEncryptionHex(String planText, String key) throws Exception{

        logger.trace("[Start][EncryptionUtil][tripelDesEncryptionHex]");

        String value = "";

        try {
            final byte[] secretKeyAsBytes = Hex.decodeHex(key.toCharArray());
            KeySpec keySpec = new DESedeKeySpec(secretKeyAsBytes);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(PropertiesConstants.DESEDE_TRANSFORMATION);
            Cipher cipher = Cipher.getInstance(PropertiesConstants.DESEDE_CIPHER);
            SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] plainTextBytes = Hex.decodeHex(planText.toCharArray());
            byte[] encryptedTextBytes = cipher.doFinal(plainTextBytes);

            String encryptedText = Hex.encodeHexString(encryptedTextBytes);

            value = encryptedText.toUpperCase();
        } catch (Exception e) {
            throw new Exception();
        }

        logger.trace("[Start][EncryptionUtil][tripelDesEncryptionHex]");
        return value;
    }

    public static String desedeDecryptHex(String encryptedText, String key) throws Exception {

        try {
            final byte[] secretKeyBytes = Hex.decodeHex(key.toCharArray());
            KeySpec keySpec = new DESedeKeySpec(secretKeyBytes);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(PropertiesConstants.DESEDE_TRANSFORMATION);
            Cipher cipher = Cipher.getInstance(PropertiesConstants.DESEDE_CIPHER);
            SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            byte[] decodeHexBytes = Hex.decodeHex(encryptedText.toLowerCase().toCharArray());
            byte[] decryptedBytes = cipher.doFinal(decodeHexBytes);
            String decryptedText = new String(Hex.encodeHex(decryptedBytes));

            return decryptedText.toUpperCase();
        } catch (Exception e) {
            throw new Exception();
        }
    }

    /**
     * AES 256 encrypt plaintext with provided key and IV
     *
     * @param plainText - text to be encrypted
     * @param key       - Base64 encoded key
     * @param iv        - Base64 endoded IV
     * @return
     */
    public static String aesEncrypt(String plainText, String key, String iv) {

        logger.trace("[Start][EncryptionUtil][aesEncrypt]");

        final byte[] secretKey = DatatypeConverter.parseHexBinary(key);
        final byte[] initVector = DatatypeConverter.parseHexBinary(iv);

        Cipher cipher;
        String encrypted = "";
        try {
            cipher = Cipher.getInstance(PropertiesConstants.AES_TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(secretKey, PropertiesConstants.AES), new IvParameterSpec(initVector, 0, cipher.getBlockSize()));
            encrypted = Base64.encodeBase64String(cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8)));

        } catch (Exception e) {
            logger.error("[Error][EncryptionUtil][aesEncrypt] Error encrypting the key.", e);
        }

        logger.trace("[Start][EncryptionUtil][aesEncrypt]");

        return encrypted;
    }

    public static String aesDecrypt(String encrypted, String key, String iv) {

        logger.trace("[Start][EncryptionUtil][aesDecrypt]");

        String decryptedValue = "";
        try {
            final byte[] secretKey = javax.xml.bind.DatatypeConverter.parseHexBinary(key);
            final byte[] initVector = javax.xml.bind.DatatypeConverter.parseHexBinary(iv);
            final Cipher cipher = Cipher.getInstance(PropertiesConstants.AES_TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(secretKey, PropertiesConstants.AES), new IvParameterSpec(initVector, 0, cipher.getBlockSize()));

            byte[] decodedValue = new Base64().decode(encrypted);
            byte[] decValue = cipher.doFinal(decodedValue);
            decryptedValue = new String(decValue);

        } catch (Exception e) {
            logger.error("[Error][EncryptionUtil][aesDecrypt] Error decrypting the key.", e);
        }

        logger.trace("[End][EncryptionUtil][aesDecrypt]");

        return decryptedValue;
    }
}
