package ru.project.handwritten.util;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import ru.project.handwritten.entity.DOCXDocument;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class writeUtil {

    private static volatile int fontSize;
    private static boolean needStrike = false;

    public static void writeFileWithWrittenFont(DOCXDocument docxDocument) throws IOException {

        //создание документа в той же директории
        String[] nameToCreate = docxDocument.getName().split("\\.");
        String newName = nameToCreate[0] + "-handMaker." + nameToCreate[1];
        String newPath = docxDocument.getPath().replace(docxDocument.getName(), newName);
        File writeFile = new File(newPath);

        FileOutputStream fos = new FileOutputStream(writeFile);
        XWPFDocument writeDOC = new XWPFDocument();

        //генерация ошибок в параграфе
        ArrayList<String> ParagraphsWithRndMistake = new ArrayList<>();
        for (String stroke : docxDocument.getParagraphs()){
            StringBuilder strokeBuilder = new StringBuilder();
            String[] words = stroke.split(" ");
            for (String word : words){
                if(isNeededToDuplicate(docxDocument.getMistakePercent())){
                    strokeBuilder.append("<").append(word).append(">").append(" ");
                }
                strokeBuilder.append(word).append(" ");
            }
            ParagraphsWithRndMistake.add(strokeBuilder.toString());
        }

        //создание параграфов с рандомным количеством пробелов
        ArrayList<String> ParagraphsWithRndSpace = new ArrayList<>();
        //создание рандомного количества пробелов
        for (String stroke : ParagraphsWithRndMistake){
            StringBuilder strokeBuilder = new StringBuilder();
            for (char symbol : stroke.toCharArray()){
                if(symbol == ' '){
                    //пользовательское число пробелов
                    for (int i = 0; i < docxDocument.getSpaceNum(); i++) {
                        strokeBuilder.append(symbol);
                    }
                    //добавляем рандомное число пробелов
                    int space = createRandomNumberForSpace();
                    for (int i = 0; i < space; i++) {
                        strokeBuilder.append(' ');
                    }
                }else{
                    strokeBuilder.append(symbol);
                }
            }
            ParagraphsWithRndSpace.add(strokeBuilder.toString());
        }

        //создание листов с шрифтами для заполнения
        List<String> fonts = createListOfFonts(docxDocument);

        //создание параграфов
        for (String paragraph : ParagraphsWithRndSpace) {
            XWPFParagraph tempParagraph = writeDOC.createParagraph();
            //наполнение параграфов
            for (char letter : paragraph.toCharArray()) {
                XWPFRun tempRUN = tempParagraph.createRun();

                // исходя из того что ошибки заключаются в <> зачеркиваем ошибки
                //ставим тру в начале и фолс в конце зачеркивания
                if (letter == '<') {needStrike = true; continue;}
                if (letter == '>') {needStrike = false; continue;}


                //задаем текст
                tempRUN.setText(String.valueOf(letter));

                // зачеркивания исходя из ошибок
                if (needStrike){
                    tempRUN.setStrikeThrough(true);
                }

                //ставим рандом высоту
                int rnd = createRandomNumberForFont();
                fontSize = docxDocument.getFontSize() + rnd;
                tempRUN.setFontSize(fontSize);

                //шафлим коллекцию для рандомного значения
                Collections.shuffle(fonts);
                tempRUN.setCharacterSpacing(-1000);
                //ставим рандом шрифт
                tempRUN.setFontFamily(fonts.get(0));
                //ставим уплотнение
                tempRUN.setCharacterSpacing(fromPXtoTwips(docxDocument.getYplotnenie()));
            }
        }

        writeDOC.write(fos);
        //writeDOC.close();
    }

    //метод на дублирование слов
    //true-дублируем false-нет
    private static boolean isNeededToDuplicate(int chanceOfMistake){
        return (int)(Math.random()*100) < chanceOfMistake;
    }

    //метод генерации рандомного диапозона пробелов
    private static int createRandomNumberForSpace() {
        List<Integer> list = new ArrayList<>(Arrays.asList(0,0,0,0,0,1,1,1,2,2));
        Collections.shuffle(list);
        return list.get(0);
    }

    //метод генерации рандомного диапозона шрифта
    private static int createRandomNumberForFont() {
        List<Integer> list = new ArrayList<>(Arrays.asList(0,-1,1));
        Collections.shuffle(list);
        return list.get(0);
    }

    private static List<String> createListOfFonts(DOCXDocument docxDocument) {
        List<String> listOfFonts = new ArrayList<>();
        for (String font : docxDocument.getFonts()) {
            listOfFonts.add(font.trim());
        }
        return listOfFonts;
    }

    private static int fromPXtoTwips(double PX){
        return (int) (-15.002846 * PX);
    }
}

// C:\Users\Sergey\IdeaProjects\handwritten-text-maker\src\main\resources\test.docx
