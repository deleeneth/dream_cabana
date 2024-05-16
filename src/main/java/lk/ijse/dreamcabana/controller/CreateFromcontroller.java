package lk.ijse.dreamcabana.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dreamcabana.db.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateFromcontroller {

    @FXML
    private AnchorPane load;

    @FXML
    private ImageView root;

    @FXML
    private TextField txtpw;

    @FXML
    private TextField txtuser;

    @FXML
    void btnSignup(ActionEvent event) {
        String user_id = txtuser.getText();
        String password = txtpw.getText();

        saveUser(user_id, password);
    }

    private void saveUser(String user_id, String password) {

        try {
            String sql = "INSERT INTO user VALUES(?, ?)";
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1, user_id);
            pstm.setObject(2, password);
            if(pstm.executeUpdate() > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "user saved!").show();
                navigateToDashbord();
            }
        } catch (SQLException | IOException e) {
            new Alert(Alert.AlertType.ERROR, "something happend!").show();
        }
    }

    private void navigateToDashbord() throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/homepage.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(scene);
        stage.setTitle("Sign up");
        stage.centerOnScreen();
    }


}
