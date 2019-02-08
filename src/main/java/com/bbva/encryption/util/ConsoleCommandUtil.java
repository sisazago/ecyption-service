package com.bbva.encryption.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ConsoleCommandUtil {

    private static final Logger logger = LoggerFactory.getLogger(ConsoleCommandUtil.class);

    public static void clearConsole(){
        logger.trace("[Start][ConsoleCommandUtil][clearConsole]");
        try {
            final String operativeSystem = System.getProperty("os.name");

            if (operativeSystem.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }catch (IOException e){
            logger.error("[Error][ConsoleCommandUtil][clearConsole]Error clearing the console.", e);
        }

        logger.trace("[End][ConsoleCommandUtil][clearConsole]");
    }
}
