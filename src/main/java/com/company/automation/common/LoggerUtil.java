package com.company.automation.common;// Import necessary classes

import org.testng.log4testng.Logger;

/**
 * LoggerUtil is a utility class that provides an interface for logging.
 * It wraps around the Log4J logger providing methods to log at different levels like info, error, debug, etc.
 */
public class LoggerUtil {
    // Create a Log4J logger instance
    private static Logger logger = Logger.getLogger(LoggerUtil.class);

    /**
     * Logs a message with level INFO
     *
     * @param message - the message string to be logged
     */
    public static void logInfo(String message) {
        logger.info(message);
    }

    /**
     * Logs a message with level ERROR
     *
     * @param message - the message string to be logged
     */
    public static void logError(String message) {
        logger.error(message);
    }

    /**
     * Logs a message with level ERROR along with the details of an exception
     *
     * @param message - the message string to be logged
     * @param e - the exception that occurred
     */
    public static void logError(String message, Throwable e) {
        logger.error(message, e);
    }

    /**
     * Logs a message with level DEBUG
     *
     * @param message - the message string to be logged
     */
    public static void logDebug(String message) {
        logger.debug(message);
    }
}
