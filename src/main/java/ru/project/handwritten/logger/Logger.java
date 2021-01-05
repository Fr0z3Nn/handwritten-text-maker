package ru.project.handwritten.logger;


import ru.project.handwritten.MakerController;

public class Logger {
    private static StringBuilder LoggerInfo = new StringBuilder();

    public static void logADD(String message){
        LoggerInfo.append(message);
        MakerController.textAreaLOG.setText(LoggerInfo.toString());
    }

    public static void logCLEAR(){
        LoggerInfo = new StringBuilder();
    }


}
