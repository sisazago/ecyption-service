package com.bbva.encryption;

import com.bbva.encryption.common.enums.ExecutionEnum;
import com.bbva.encryption.service.EncryptionService;
import com.bbva.encryption.util.ConsoleCommandUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class BbvaEncryptionApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(BbvaEncryptionApplication.class);

	@Autowired
	private EncryptionService encryptionService;

	public static void main(String[] args) {
		SpringApplication.run(BbvaEncryptionApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		logger.info("[Start][BbvaEncryptionApplication][run]");

		logger.info("Select type of function to apply: {}, {}", ExecutionEnum.ENCRYPT_3DES_KEY, ExecutionEnum.DECRYPT_3DES_KEY);

		Scanner scanner = new Scanner(System.in);

		String step = scanner.next();

		if (ExecutionEnum.valueOf(step).equals(ExecutionEnum.ENCRYPT_3DES_KEY)){
			logger.info("Input 3Des first part Key: ");
			String tripelDesKeyFirstPart = scanner.next();
			ConsoleCommandUtil.clearConsole();

			logger.info("Input 3Des second part Key: ");
			String tripelDesKeySecondPart = scanner.next();
			ConsoleCommandUtil.clearConsole();

			String tripelDesKey = tripelDesKeyFirstPart+tripelDesKeySecondPart;

			logger.info("Input KEK key: ");
			String key = scanner.next();
			ConsoleCommandUtil.clearConsole();

			logger.info("Input KEK iv: ");
			String vi = scanner.next();
			ConsoleCommandUtil.clearConsole();

			logger.info("Encrypted value : {}", encryptionService.kekEncryptionKey(tripelDesKey, key, vi));
		}else if(ExecutionEnum.valueOf(step).equals(ExecutionEnum.DECRYPT_3DES_KEY)){
			logger.info("Input Encrypted key: ");

			String encryptedKey = scanner.next();
			ConsoleCommandUtil.clearConsole();

			logger.info("Input KEK key: ");

			String key = scanner.next();
			ConsoleCommandUtil.clearConsole();

			logger.info("Input KEK iv: ");

			String vi = scanner.next();
			ConsoleCommandUtil.clearConsole();

			logger.info("Decrypted value : {}", encryptionService.kekDecryptionKey(encryptedKey, key, vi));
		}

		logger.info("[End][BbvaEncryptionApplication][run]");
	}
}

