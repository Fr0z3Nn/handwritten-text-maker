package ru.project.handwritten.util;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import ru.project.handwritten.entity.DOCXDocument;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class writeUtil {

    private static volatile int fontSize;

    public static void writeFileWithWrittenFont(DOCXDocument docxDocument) throws IOException {
        String[] nameToCreate = docxDocument.getName().split("\\.");
        String newName = nameToCreate[0] + "-handMaker." + nameToCreate[1];
        String newPath = docxDocument.getPath().replace(docxDocument.getName(),newName);
        File writeFile = new File(newPath);
        FileOutputStream fos = new FileOutputStream(writeFile);
        XWPFDocument writeDOC = new XWPFDocument();


        for(String paragraph : docxDocument.getParagraphs()){
            XWPFParagraph tempParagraph = writeDOC.createParagraph();
            for(char letter : paragraph.toCharArray()){
                XWPFRun tempRUN = tempParagraph.createRun();
                tempRUN.setText(String.valueOf(letter));
                    fontSize = (int)(Math.random()*10) + 15;
                    tempRUN.setFontSize(fontSize);

                int a = tempRUN.getFontSize();
                tempRUN.setFontFamily(docxDocument.getFont());

            }
        }

        writeDOC.write(fos);
        //writeDOC.close();
    }

}

// C:\Users\Sergey\IdeaProjects\handwritten-text-maker\src\main\resources\test.docx
