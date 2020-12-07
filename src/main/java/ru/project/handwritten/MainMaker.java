package ru.project.handwritten;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainMaker {
    public static void main(String[] args) {
        String path = "C:\\Users\\Sergey\\IdeaProjects\\handwritten-text-maker\\src\\main\\resources\\test.docx";
        String text = null;
        try {
            text = readTextAndGetDOCX(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(text);
    }

    private static String readTextAndGetDOCX(String path) throws IOException {
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file.getAbsolutePath());
        XWPFDocument xwpfDocument = new XWPFDocument(fis);
        XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(xwpfDocument);
        String text = xwpfWordExtractor.getText();
        return text;
    }
}
