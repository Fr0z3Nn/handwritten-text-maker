package ru.project.handwritten;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ru.project.handwritten.entity.DOCXDocument;
import ru.project.handwritten.logger.Logger;
import ru.project.handwritten.util.writeUtil;
import ru.project.handwritten.validator.InputValidator;

import java.io.File;
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
    public TextField mistakePercent;
    @FXML
    public TextField spaceNum;

    public static TextArea textAreaLOG;
    //для работы с окнами
    //private Desktop desktop = Desktop.getDesktop();

    // класс позволяющий выбирать файл
    final FileChooser fileChooser = new FileChooser();

    @FXML
    public Button chooseFile;

    @FXML
    public Slider sliderMistake;
    @FXML
    public Slider sliderFont;

    @FXML
    public void initialize() {
        //присваиваем нашему логеру поле техареа с которым он будет работать
        textAreaLOG = textArea;
        //название для окошка выбора файла
        fileChooser.setTitle("Выбор файла для генерации");

        //при генерации сразу начинаем валидацию входных данных
        generation.setOnMouseClicked(event -> {
            InputValidator inputParams = InputValidator.builder()
                    .path(inputPath.getText())
                    .fontSize(fontSize.getText())
                    .fonts(inputFont.getText())
                    .spaceNum(spaceNum.getText())
                    .mistakePercent(mistakePercent.getText())
                    .yplotnenie(fontYplotnenie.getText())
                    .build();
            //если не проходим валидацию, то брейкаем и выводим ошибку
            if (!inputParams.check()){
                return;
            }

            Logger.logADD("✔ Данные введены верно ✔\n");

            Logger.logADD("Начинается процесс генерации\n");

            DOCXDocument docxDocument = new DOCXDocument(inputPath.getText(),inputFont.getText(),fontSize.getText(),spaceNum.getText(),mistakePercent.getText(),fontYplotnenie.getText());

            Logger.logADD("Ваш файл: " + docxDocument.getName());
            try {
                writeUtil.writeFileWithWrittenFont(docxDocument);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Logger.logADD("\nФайл успешно сгенерирован!\n");
        });

        chooseFile.setOnAction(event -> {
            textArea.clear();
            Stage window = new Stage();
            File file = fileChooser.showOpenDialog(window);
            if (file != null) {
                inputPath.setText(file.getAbsolutePath());
            }
        });

        //слайдер для процента ошибок
        sliderMistake.valueProperty().addListener((observable, oldValue, newValue) -> mistakePercent.setText(String.valueOf(newValue.intValue())));
        //слайдер для размера шрифтов
        sliderFont.valueProperty().addListener((observable, oldValue, newValue) -> fontSize.setText(String.valueOf(newValue.intValue())));


    }


}
