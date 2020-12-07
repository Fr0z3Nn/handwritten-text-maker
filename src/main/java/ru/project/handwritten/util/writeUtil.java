package ru.project.handwritten.util;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import ru.project.handwritten.entity.DOCXDocument;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class writeUtil {
    public static void writeFileWithWrittenFont(DOCXDocument docxDocument) throws IOException {
        String[] nameToCreate = docxDocument.getName().split("\\.");
        String newName = nameToCreate[0] + "-handMaker." + nameToCreate[1];
        String newPath = docxDocument.getPath().replace(docxDocument.getName(),newName);
        File writeFile = new File(newPath);
        FileOutputStream fos = new FileOutputStream(writeFile);
        XWPFDocument writeDOC = new XWPFDocument();

        /*String[] textParagraphs = text.split("\n");
        System.out.println(Arrays.toString(textParagraphs));*/

        XWPFParagraph tempParagraph = writeDOC.createParagraph();


        for(char letter : docxDocument.getParagraphs().toCharArray()){
            XWPFRun tempRUN = tempParagraph.createRun();
            tempRUN.setText(String.valueOf(letter));
            tempRUN.setFontSize((int)(Math.random() * 20));
        }
        writeDOC.write(fos);

    }
}
