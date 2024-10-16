package org.example.view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.*;
import org.example.controller.BaseController;
import org.example.controller.MainWindowController;

import java.io.IOException;

public class ViewFactory {

    public void showMainWindow() {
        BaseController baseController = new MainWindowController(this, "/view/MainWindow.fxml");
        initializeStage(baseController);
    }

    private void initializeStage(BaseController baseController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(baseController.getFxmlName()));
        fxmlLoader.setController(baseController);
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
