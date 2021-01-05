package ru.project.handwritten.validator;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.project.handwritten.logger.Logger;

@Getter
@Setter
@Builder
public class InputValidator {
    private String path;
    private String fonts;
    private String fontSize;

    //запускает валидацию, возвращает стринг как ответ
    public boolean check() {
        Logger.logCLEAR();
        Logger.logADD("Начинается процесс валидации\n");
        boolean result = true;
        if (path.equals("")) {
            Logger.logADD("Введите полный путь к файлу\n");
            result = false;
        }else {
            Logger.logADD("✔ Путь к файлу указан верно, файл найден\n");
        }

        if (fonts.equals("")) {
            Logger.logADD("Введите список шрифтов\n");
            result = false;
        }else {
            Logger.logADD("✔ Список шрифтов распознан\n");
        }

        if (fontSize.equals("")) {
            Logger.logADD("Введите размер шрифта\n");
            result = false;
        } else {
            try {
                Integer.parseInt(fontSize);
                Logger.logADD("✔ Размер шрифта определен\n");
            } catch (Exception e) {
                Logger.logADD("Размер шрифта должен быть текстом\n");
                result = false;
            }
        }
        return result;
    }

}
