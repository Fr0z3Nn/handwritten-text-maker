package ru.project.handwritten.logger;

public class Logger {
    private static StringBuilder LoggerInfo = new StringBuilder();

    public static void logADD(String message){
        LoggerInfo.append(message);
    }

    public static void logCLEAR(){
        LoggerInfo = new StringBuilder();
    }

    public static String logSHOW(){
        return LoggerInfo.toString();
    }
}
