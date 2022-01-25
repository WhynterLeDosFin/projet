package projetFX.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public abstract class View extends Scene {
    public View(String fxmlPath) {
        super(load(fxmlPath));
    }

    private static Parent load(String fxmlPath) {
        FXMLLoader loader = new FXMLLoader();
        var resource = MenuView.class.getResourceAsStream(fxmlPath);
        try {
            return loader.load(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
