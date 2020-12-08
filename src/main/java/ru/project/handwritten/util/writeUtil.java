package ru.project.handwritten.util;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import ru.project.handwritten.entity.DOCXDocument;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class writeUtil {

    private static volatile int fontSize;


    public static void writeFileWithWrittenFont(DOCXDocument docxDocument) throws IOException {

        //создание документа в той же директории
        String[] nameToCreate = docxDocument.getName().split("\\.");
        String newName = nameToCreate[0] + "-handMaker." + nameToCreate[1];
        String newPath = docxDocument.getPath().replace(docxDocument.getName(), newName);
        File writeFile = new File(newPath);


        FileOutputStream fos = new FileOutputStream(writeFile);
        XWPFDocument writeDOC = new XWPFDocument();

        //создание листов с шрифтами для заполнения
        List<String> fonts = createListOfFonts(docxDocument);

        //создание параграфов
        for (String paragraph : docxDocument.getParagraphs()) {
            XWPFParagraph tempParagraph = writeDOC.createParagraph();
            //наполнение параграфов
            for (char letter : paragraph.toCharArray()) {
                XWPFRun tempRUN = tempParagraph.createRun();

                //задаем текст
                tempRUN.setText(String.valueOf(letter));

                //ставим рандом высоту
                fontSize = (int) (Math.random() * 10) + 15;
                tempRUN.setFontSize(fontSize);

                //шафлим коллекцию для рандомного значения
                Collections.shuffle(fonts);

                //ставим рандом шрифт
                tempRUN.setFontFamily(fonts.get(0));

            }
        }

        writeDOC.write(fos);
        //writeDOC.close();
    }

    private static List<String> createListOfFonts(DOCXDocument docxDocument) {
        List<String> listOfFonts = new ArrayList<>();
        for (String font : docxDocument.getFonts()) {
            listOfFonts.add(font.trim());
        }
        return listOfFonts;
    }
}

// C:\Users\Sergey\IdeaProjects\handwritten-text-maker\src\main\resources\test.docx
