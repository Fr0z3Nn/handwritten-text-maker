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
    //паграфы
    private String[] paragraphs;
    //применяемый шрифт
    private String font;

    public DOCXDocument(String path, String font) {
        File file = new File(path);
        this.name = file.getName();
        this.font = font;
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
        this.paragraphs = text.split("\n");
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

    public String[] getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(String[] paragraphs) {
        this.paragraphs = paragraphs;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

}
