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
    private String spaceNum;
    //TODO обрабобать валидацию ниже
    private String mistakePercent;
    private String yplotnenie;

    //запускает валидацию, возвращает булеан как ответ
    public boolean check() {

        Logger.logCLEAR();

        Logger.logADD("НАЧИНАЕТСЯ ПРОЦЕСС ВАЛИДАЦИИ\n\n");

        boolean result = true;

        if (path.equals("")) {
            Logger.logADD("✖ Введите полный путь к файлу\n");
            result = false;
        }else {
            Logger.logADD("✔ Путь к файлу указан верно, файл найден\n");
        }

        if (fonts.equals("")) {
            Logger.logADD("✖ Введите список шрифтов\n");
            result = false;
        }else {
            Logger.logADD("✔ Список шрифтов распознан\n");
        }

        if (fontSize.equals("")) {
            Logger.logADD("✖ Введите размер шрифта\n");
            result = false;
        } else {
            try {
                Integer.parseInt(fontSize);
                Logger.logADD("✔ Размер шрифта определен\n");
            } catch (Exception e) {
                Logger.logADD("✖ Размер шрифта должен быть числом\n");
                result = false;
            }
        }

        if (spaceNum.equals("")) {
            Logger.logADD("✖ Введите кол-во пробелов\n");
            result = false;
        } else {
            try {
                Integer.parseInt(spaceNum);
                Logger.logADD("✔ Кол-во пробелов определено\n");
            } catch (Exception e) {
                Logger.logADD("✖ Кол-во пробелов должно быть числом\n");
                result = false;
            }
        }

        if (mistakePercent.equals("")) {
            Logger.logADD("✖ Введите процент ошибок\n");
            result = false;
        } else {
            try {
                Integer.parseInt(spaceNum);
                Logger.logADD("✔ Процент ошибок определен\n");
            } catch (Exception e) {
                Logger.logADD("✖ Процент ошибок должен быть числом\n");
                result = false;
            }
        }

        if (yplotnenie.equals("")) {
            Logger.logADD("✖ Введите значение уплотнения в пикселях\n");
            result = false;
        } else {
            try {
                Integer.parseInt(spaceNum);
                Logger.logADD("✔ Значение уплотнения определено\n");
            } catch (Exception e) {
                Logger.logADD("✖ Значение уплотнения должно быть числом\n");
                result = false;
            }
        }

        if (!result){
            Logger.logADD("\n✖ ПРОИЗОШЛА ОШИБКА ✖\n");
        }
        return result;
    }

}
