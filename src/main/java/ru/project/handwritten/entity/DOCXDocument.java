package ru.project.handwritten.entity;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import ru.project.handwritten.MakerController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DOCXDocument {
    private String path;
    private String name;
    //пока не массив
    private String paragraphs;

    public DOCXDocument(String path) {
        File file = new File(path);
        this.name = file.getName();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file.getAbsolutePath());
            this.path = file.getAbsolutePath();
        } catch (FileNotFoundException e) {
           // MakerController.textArea.setText("Файл не был найден, введите правильный путь!");
            e.printStackTrace();
        }
        XWPFDocument xwpfDocument = null;
        try {
            xwpfDocument = new XWPFDocument(fis);
        } catch (IOException e) {
           // MakerController.textArea.setText("Ошибка в создании файла, обратитесь к разработчику");
            e.printStackTrace();
        }
        XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(xwpfDocument);
        String text = xwpfWordExtractor.getText();
        this.paragraphs = text;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(String paragraphs) {
        this.paragraphs = paragraphs;
    }
}
