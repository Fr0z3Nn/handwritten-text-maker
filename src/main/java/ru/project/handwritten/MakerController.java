package ru.project.handwritten;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ru.project.handwritten.entity.DOCXDocument;
import ru.project.handwritten.util.writeUtil;

import java.io.IOException;


public class MakerController {
    @FXML
    public Button generation;
    @FXML
    public TextArea textArea;
    @FXML
    public TextField inputPath;
    @FXML
    public TextField inputFont;
    @FXML
    public TextField fontSize;
    @FXML
    public TextField fontYplotnenie;
    @FXML
    public Button instruction;

    StringBuilder progressText = new StringBuilder();

    @FXML
    public void initialize() {
        generation.setOnMouseClicked(event -> {

            //тру все ок не тру все плохо
            if (!validationInputs()){return;}
            log("Данные введены верно\n");

            log("Файл найден, начинается процесс генерации\n");
            DOCXDocument docxDocument = new DOCXDocument(inputPath.getText(),inputFont.getText(),fontSize.getText());

            log("Ваш файл: " + docxDocument.getName());
            try {
                writeUtil.writeFileWithWrittenFont(docxDocument);
            } catch (IOException e) {
                e.printStackTrace();
            }

            log("\nФайл успешно сгенерирован!\n");
        });
    }

    public boolean validationInputs(){
        logClear();
        log("Начинается процесс валидации\n");
        boolean result = true;
        if (inputPath.getText().equals("")){
            log("Введите полный путь к файлу\n");
            result = false;
        }

        if (inputFont.getText().equals("")){
            log("Введите список шрифтов\n");
            result = false;
        }

        if(fontSize.getText().equals("")){
            log("Введите размер шрифта\n");
            result = false;
        }else{
            try {
                Integer.parseInt(fontSize.getText());
            }catch (Exception e){
                log("Размер шрифта должен быть текстом\n");
                result = false;
            }
        }

        return result;
    }

    public void log(String message){
        this.progressText.append(message);
        textArea.setText(progressText.toString());
    }

    public void logClear(){
        this.progressText = new StringBuilder();
    }

}
