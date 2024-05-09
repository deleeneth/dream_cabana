package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class packagescontroller {
    public AnchorPane root;
    public AnchorPane Load;
    public TextField txtId;
    public DatePicker date;
    public TextField txtId1;
    public TextField txtId2;

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/homepage.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Homepage Form");
        stage.centerOnScreen();
    }

    public void btnOnActionSave(ActionEvent actionEvent) {

    }

    public void btnOnActionUpdate(ActionEvent actionEvent) {

    }

    public void btnOnActionDelete(ActionEvent actionEvent) {

    }
}
