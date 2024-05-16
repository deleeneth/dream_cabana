package lk.ijse.dreamcabana;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class AppInitiazializer extends Application {
    public static void main(String[] args) { launch(args);

    }
    @Override
    public void start(Stage stage) throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/homepage.fxml"));

        Scene scene = new Scene(rootNode);
        stage.setScene(scene);
        stage.centerOnScreen();

        stage.initStyle(StageStyle.DECORATED);
        stage.show();


    }
}