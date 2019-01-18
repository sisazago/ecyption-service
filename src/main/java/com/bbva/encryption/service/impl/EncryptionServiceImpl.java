package com.bbva.encryption.service.impl;

import com.bbva.encryption.service.EncryptionService;
import com.bbva.encryption.util.EncryptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EncryptionServiceImpl implements EncryptionService {

    private static final Logger logger = LoggerFactory.getLogger(EncryptionServiceImpl.class);

    @Override
    public String kekEncryptionKey(String desKey, String key, String vi) {
        logger.debug("[Start][EncryptionServiceImpl][kekEncryptionKey]");
        String encryptedValue = "";

        encryptedValue = EncryptionUtil.aesEncrypt(desKey, key, vi);

        logger.debug("[End][EncryptionServiceImpl][kekEncryptionKey]");
        return encryptedValue;
    }

    @Override
    public String kekDecryptionKey(String encryptedValue, String key, String vi) {
        logger.debug("[Start][EncryptionServiceImpl][kekDecryptionKey]");
        String decryptedValue = "";

        decryptedValue = EncryptionUtil.aesDecrypt(encryptedValue, key, vi);

        logger.debug("[End][EncryptionServiceImpl][kekDecryptionKey]");
        return decryptedValue;
    }
}
