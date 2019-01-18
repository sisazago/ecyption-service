package com.bbva.encryption.service;

public interface EncryptionService {

    /**
     * This method encrypt the tripel des key with the key and iv vector to generate
     * kek security.
     * @param desKey - key provided by the user
     * @param key - encryption factor
     * @param vi - encryption vector
     * */
    String kekEncryptionKey(String desKey, String key, String vi);

    /**
     * This method is to decrypt the key and return the value
     * @param encryptedValue - encrypted key value
     * @param key - kek key.
     * @param vi - kek vector
     * */
    String kekDecryptionKey(String encryptedValue, String key, String vi);
}
