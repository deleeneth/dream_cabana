package lk.ijse.dreamcabana.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class homepagecontroller {
    public AnchorPane rootNode;
    public AnchorPane load;
    public Label lbldate;
    public Label lbltime;

    public void btnCustomerOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/customernew.fxml"));
        Pane registerPane = fxmlLoader.load();
        load.getChildren().clear();
        load.getChildren().add(registerPane);

        /* URL resource = getClass().getResource("/view/customer.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        Load.getChildren().clear();
        Load.getChildren().add(load);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), Load);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();*/
    }

    public void btnOnActionPackages(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/booking.fxml"));
        Pane registerPane = fxmlLoader.load();
        load.getChildren().clear();
        load.getChildren().add(registerPane);
    }

    public void btnExitOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/customernew.fxml"));
        Pane registerPane = fxmlLoader.load();
        load.getChildren().clear();
        load.getChildren().add(registerPane);
    }

    public void btnOnActionMenu(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/menu.fxml"));
        Pane registerPane = fxmlLoader.load();
        load.getChildren().clear();
        load.getChildren().add(registerPane);
    }

    public void btnOnActionEmployee(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/employee.fxml"));
        Pane registerPane = fxmlLoader.load();
        load.getChildren().clear();
        load.getChildren().add(registerPane);
    }

    public void btnOnActionSupplier(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/supplier.fxml"));
        Pane registerPane = fxmlLoader.load();
        load.getChildren().clear();
        load.getChildren().add(registerPane);
    }

    public void btnOnActionRoom(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/room.fxml"));
        Pane registerPane = fxmlLoader.load();
        load.getChildren().clear();
        load.getChildren().add(registerPane);
    }

    public void btnOnActionPayment(ActionEvent actionEvent) throws IOException {

    }

    public void btnOnActionLogout(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();
        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Login Form");
        stage.centerOnScreen();
    }
    public void initialize(){
        setTimeLine();
    }
    private void setTimeLine() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateClock()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateClock() {
        lbldate.setText(datenow());
        lbltime.setText(timeNow());
    }

    public static String timeNow() {
        SimpleDateFormat dateFormat=new SimpleDateFormat("hh:mm:ss"); //In 12hr Format
        return dateFormat.format(new Date()) ;
    }
    public static String datenow() {
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd.MM.yyyy");
        return dateFormat.format(new Date()) ;
    }

    public void btnOnActionBooking(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/booking.fxml"));
        Pane registerPane = fxmlLoader.load();
        load.getChildren().clear();
        load.getChildren().add(registerPane);
    }
}
