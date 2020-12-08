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

    StringBuilder progressText = new StringBuilder();

    @FXML
    public void initialize() {
        generation.setOnMouseClicked(event -> {
            String font = inputFont.getText();
            log("Начинается процесс инициализации\n");
            DOCXDocument docxDocument = new DOCXDocument(inputPath.getText(),font);
            log("Файл найден, начинается процесс генерации\n");
            log("Ваш файл: " + docxDocument.getName());
            try {
                writeUtil.writeFileWithWrittenFont(docxDocument);
            } catch (IOException e) {
                e.printStackTrace();
            }
            log("\nФайл успешно сгенерирован!");
        });
    }

    public void log(String message){
        this.progressText.append(message);
        textArea.setText(progressText.toString());
    }

}
