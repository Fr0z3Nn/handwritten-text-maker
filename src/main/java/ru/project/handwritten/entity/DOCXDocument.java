package ru.project.handwritten.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import ru.project.handwritten.MakerController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Getter
@Setter
public class DOCXDocument {
    private String path;
    private String name;
    //параграфы
    private String[] paragraphs;
    //применяемые шрифты
    private String[] fonts;

    private int fontSize;
    //количество пробелов
    private int spaceNum;
    //процент ошибок
    private int mistakePercent;

    //уплотнение
    private double yplotnenie;

    public DOCXDocument(String path, String font, String fontSize, String spaceNum, String mistakePercent,String yplotnenie) {
        File file = new File(path);
        this.name = file.getName();
        this.fonts = font.split(",");
        this.fontSize = Integer.parseInt(fontSize);
        this.spaceNum = Integer.parseInt(spaceNum);
        this.mistakePercent = Integer.parseInt(mistakePercent);
        this.yplotnenie = Double.parseDouble(yplotnenie);
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(file.getAbsolutePath());
            this.path = file.getAbsolutePath();
        } catch (FileNotFoundException e) {
           // "Файл не был найден, введите правильный путь!");
            e.printStackTrace();
        }

        XWPFDocument xwpfDocument = null;
        try {
            xwpfDocument = new XWPFDocument(fis);
        } catch (IOException e) {
           // ("Ошибка в создании файла, обратитесь к разработчику");
            e.printStackTrace();
        }

        XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(xwpfDocument);
        String text = xwpfWordExtractor.getText();
        this.paragraphs = text.split("\n");
    }


}
