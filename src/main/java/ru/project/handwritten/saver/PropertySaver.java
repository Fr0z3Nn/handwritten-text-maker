package ru.project.handwritten.saver;

import java.io.*;

public class PropertySaver {

    private PropertyDOCX propertyDOCX;

    public PropertySaver(PropertyDOCX propertyDOCX) {
        this.propertyDOCX = propertyDOCX;
    }

    public PropertySaver() {
    }

    //метод сохораняющий текущие поля
    public void save() {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("SAVE.ser"))) {
            os.writeObject(propertyDOCX);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //метод загружающий последние сохраненные поля
    public PropertyDOCX load() {
        PropertyDOCX propertyDOCX = new PropertyDOCX();
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream("SAVE.ser"))) {
            propertyDOCX = (PropertyDOCX) is.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return propertyDOCX;
    }


}
