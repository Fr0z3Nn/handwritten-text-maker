package ru.project.handwritten;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ru.project.handwritten.entity.DOCXDocument;
import ru.project.handwritten.logger.Logger;
import ru.project.handwritten.util.writeUtil;
import ru.project.handwritten.validator.InputValidator;

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



    @FXML
    public void initialize() {

        generation.setOnMouseClicked(event -> {
            InputValidator inputParams = InputValidator.builder()
                    .path(inputPath.getText())
                    .fontSize(fontSize.getText())
                    .fonts(inputFont.getText())
                    .build();
            //тру все ок не тру все плохо
            if (!inputParams.check()){
                textArea.setText(Logger.logSHOW());
                return;
            }

            Logger.logADD("Данные введены верно\n");

            Logger.logADD("Начинается процесс генерации\n");
            DOCXDocument docxDocument = new DOCXDocument(inputPath.getText(),inputFont.getText(),fontSize.getText());

            Logger.logADD("Ваш файл: " + docxDocument.getName());
            try {
                writeUtil.writeFileWithWrittenFont(docxDocument);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Logger.logADD("\nФайл успешно сгенерирован!\n");
            textArea.setText(Logger.logSHOW());

        });
    }


}
