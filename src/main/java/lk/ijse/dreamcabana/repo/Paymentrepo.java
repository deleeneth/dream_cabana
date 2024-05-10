package lk.ijse.dreamcabana.repo;

import javafx.scene.control.Alert;
import lk.ijse.dreamcabana.db.DbConnection;
import lk.ijse.dreamcabana.model.Payment;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Paymentrepo {
    public static boolean save(Payment payment) throws SQLException, IOException {
        String sql = "INSERT INTO payment VALUES( ? ,?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, payment.getPayment_id());
        pstm.setObject(2, payment.getBooking_id());
        pstm.setObject(3, payment.getDate());

        int row = pstm.executeUpdate();

        if(row>0) {
            return true;
        } else {
            new Alert(Alert.AlertType.ERROR, "something went wrong").show();
        }return false;
    }
 public static String currentid() throws SQLException {
        String sql = "SELECT payment_id FROM payment ORDER BY payment_id desc LIMIT 1";

     Connection connection = DbConnection.getInstance().getConnection();
     PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }
public static List<Payment> getAll() throws SQLException {
        String sql = "SELECT * FROM payment order by paymentId desc";

    Connection connection = DbConnection.getInstance().getConnection();
    PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Payment> paymentList = new ArrayList<>();
        while (resultSet.next()) {
            String payment_id = resultSet.getString(1);
            String booking_id = resultSet.getString(2);
            String date = resultSet.getString(3);

            Payment payment = new Payment(payment_id, booking_id, date);
            paymentList.add(payment);
        }
        return paymentList;
    }
}
