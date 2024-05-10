package lk.ijse.dreamcabana.repo;

import lk.ijse.dreamcabana.db.DbConnection;
import lk.ijse.dreamcabana.model.Bookingdetails;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Bookingdetailsrepo {
    public static boolean save(List<Bookingdetails> odList) throws SQLException {
        for (Bookingdetails od : odList) {
            if(!save(od)) {
                return false;
            }
        }
        return true;
    }

    private static boolean save(Bookingdetails od) throws SQLException {
        String sql = "INSERT INTO booking_detail VALUES(?, ?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
        pstm.setString(1, od.getBooking_id());
        pstm.setString(2, od.getRoom_id());

        return pstm.executeUpdate() > 0;
    }
}
