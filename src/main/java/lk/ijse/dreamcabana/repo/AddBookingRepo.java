package lk.ijse.dreamcabana.repo;

import lk.ijse.dreamcabana.db.DbConnection;
import lk.ijse.dreamcabana.model.AddBooking;

import java.sql.Connection;
import java.sql.SQLException;

public class AddBookingRepo {
    public static boolean save(AddBooking ab) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try {
            boolean isBookingSave = Bookingrepo.save(ab.getBooking());
            System.out.println(isBookingSave);
            if (isBookingSave) {
                boolean isRoomUpdate = Roomrepo.update(ab.getRoom());
                System.out.println(isRoomUpdate);
                if (isRoomUpdate) {
                    connection.commit();
                    return true;
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            connection.rollback();
            return false;

        } finally {
            connection.setAutoCommit(true);

        }
    }


}
