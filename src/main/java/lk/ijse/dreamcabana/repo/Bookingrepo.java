package lk.ijse.dreamcabana.repo;

import lk.ijse.dreamcabana.db.DbConnection;
import lk.ijse.dreamcabana.model.Booking;
import lk.ijse.dreamcabana.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Bookingrepo {
    public static String currentId() throws SQLException {
        String sql = "SELECT booking_id FROM booking ORDER BY booking_id desc LIMIT 1";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    public static boolean save(Booking booking) throws SQLException {
        String sql = "INSERT INTO Booking VALUES(?, ?, ?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
        pstm.setString(1, booking.getBooking_id());
        pstm.setString(3, booking.getDate());
        pstm.setString(2, booking.getCustomer_id());

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Booking booking) throws SQLException {
        String sql = "UPDATE Booking SET date = ?, customer_id = ?WHERE booking_id = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, booking.getDate());
        pstm.setObject(2, booking.getCustomer_id());
        pstm.setObject(3, booking.getBooking_id());

        return pstm.executeUpdate() > 0;
    }

    public static Booking searchById(String id) throws SQLException {
        String sql = "SELECT * FROM booking WHERE booking_id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, id);
        ResultSet resultSet = pstm.executeQuery();

        Booking booking = null;

        if (resultSet.next()) {
            String booking_id = resultSet.getString(1);
            String date = resultSet.getString(2);
            String customer_id = resultSet.getString(3);

            booking = new Booking(booking_id,date,customer_id);
        }
        return booking;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM Booking WHERE booking_id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }
    public static List<Booking> getAll() throws SQLException {
        String sql = "SELECT * FROM Booking";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Booking> bookingList = new ArrayList<>();
        while (resultSet.next()) {
            String booking_id = resultSet.getString(1);
            String date = resultSet.getString(2);
            String customer_id = resultSet.getString(3);

            Booking booking = new Booking(booking_id, date,customer_id);
            bookingList.add(booking);
        }
        return bookingList;
    }
    public static List<String> getIds() throws SQLException {
        String sql = "SELECT id FROM Booking";

        Connection connection = DbConnection.getInstance().getConnection();
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<String> idList = new ArrayList<>();

        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }
}
