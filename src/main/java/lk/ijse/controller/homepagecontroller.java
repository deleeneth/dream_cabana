package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class homepagecontroller {
    public AnchorPane rootNode;
    public AnchorPane load;

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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/packages.fxml"));
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

    public void btnOnActionMenu(ActionEvent actionEvent) {
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/payment.fxml"));
        Pane registerPane = fxmlLoader.load();
        load.getChildren().clear();
        load.getChildren().add(registerPane);
    }

    public void btnOnActionLogout(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();
        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Login Form");
        stage.centerOnScreen();
    }

    public void btnHomeOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/homepage.fxml"));
        Pane registerPane = fxmlLoader.load();
        load.getChildren().clear();
        load.getChildren().add(registerPane);
    }
}
