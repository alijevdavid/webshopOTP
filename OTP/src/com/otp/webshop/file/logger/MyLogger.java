package com.otp.webshop.file.logger;

import java.io.IOException;
import java.util.*;
import java.util.logging.*;

public class MyLogger {
    private static final List<Logger> loggers = new ArrayList<Logger>();
    private static final List<FileHandler> fileHandlers = new ArrayList<>();

    public static Logger getLogger(String logFileName) {
        Logger logger = Logger.getLogger(logFileName);
        try {
            FileHandler fileHandler = getFileHandler(logFileName);
            logger.addHandler(fileHandler);
        }
        
        catch (IOException e) {
            System.err.println("Failed to initialize logging: " + e.getMessage());
            e.printStackTrace();
        }
        return logger;
    }

    private static FileHandler getFileHandler(String logFileName) throws IOException {
        try {
            FileHandler fileHandler = new FileHandler(logFileName);
            fileHandler.setLevel(Level.ALL);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            return fileHandler;
        }
        
        catch (IOException e) {
            System.err.println("Failed to initialize logging: " + e.getMessage());
            throw new IOException("Failed to initialize logging for file: " + logFileName, e);
        }
    }

    public static void closeLoggers() {
        for(FileHandler handler : fileHandlers) {
            handler.close();
        }
        
        loggers.clear();
        fileHandlers.clear();
    }
}
