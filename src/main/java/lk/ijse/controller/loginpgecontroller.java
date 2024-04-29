package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class loginpgecontroller {
    @FXML
    public AnchorPane homepage;
//    @FXML
//    void gotoSignupPage(ActionEvent event)throws IOException {
//        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/signupPageForm.fxml"));
//        Scene scene = new Scene(anchorPane);
//        Stage stage = (Stage) .getScene().getWindow();
//
//        stage.setScene(scene);
//        stage.setTitle("Sign up");
//        stage.centerOnScreen();
//    }
@FXML
void gotoHomePage(ActionEvent event)throws IOException {
    AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/homepage.fxml"));
    Scene scene = new Scene(anchorPane);
    Stage stage = (Stage) homepage.getScene().getWindow();

    stage.setScene(scene);
    stage.setTitle("Sign up");
    stage.centerOnScreen();
}
}
