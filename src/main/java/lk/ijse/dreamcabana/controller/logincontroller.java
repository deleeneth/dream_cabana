package lk.ijse.dreamcabana.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dreamcabana.db.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class logincontroller {
    @FXML
    public AnchorPane homepage;
    public Hyperlink lnkCreate;
    public TextField txtUser;
    public TextField txtPw;
    public AnchorPane root;

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
void gotoHomePage()throws IOException {
    AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/homepage.fxml"));
    Scene scene = new Scene(anchorPane);
    Stage stage = (Stage) root.getScene().getWindow();

    stage.setScene(scene);
    stage.setTitle("Sign up");
    stage.centerOnScreen();
}

    private void checkCredential(String userId, String password) throws SQLException, IOException {
        String sql = "SELECT userId, password FROM user WHERE userId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, userId);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            String dbPw = resultSet.getString(2);

            if(dbPw.equals(password)) {
                gotoHomePage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Password is incorrect!").show();
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "user id not found!").show();
        }
    }

    public void btnLoginOnAction(ActionEvent actionEvent) {
        String userId = txtUser.getText();
        String password = txtPw.getText();

        try {
            checkCredential(userId, password);
        } catch (SQLException | IOException e) {
            new Alert(Alert.AlertType.ERROR, "OOPS! something went wrong").show();
        }
    }
}
