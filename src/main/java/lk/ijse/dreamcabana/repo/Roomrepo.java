package lk.ijse.dreamcabana.repo;

import lk.ijse.dreamcabana.db.DbConnection;
import lk.ijse.dreamcabana.model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class Roomrepo {
    public static boolean save(Room room) throws SQLException {
        String sql = "INSERT INTO room VALUES(?, ?, ?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, room.getRoom_id());
        pstm.setObject(2, room.getType());
        pstm.setObject(3, room.getPrice());

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Room room) throws SQLException {
        String sql = "UPDATE room SET type = ?, price = ? WHERE room_id = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, room.getType());
        pstm.setObject(2, room.getPrice());
        pstm.setObject(3, room.getRoom_id());

        return pstm.executeUpdate() > 0;
    }

    public static Room searchById(String id) throws SQLException {
        String sql = "SELECT * FROM room WHERE room_id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, id);
        ResultSet resultSet = pstm.executeQuery();

        Room room = null;

        if (resultSet.next()) {
            String room_id = resultSet.getString(1);
            String type = resultSet.getString(2);
            String price = resultSet.getString(3);

            room = new Room(room_id, type , price);
        }
        return room;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM room WHERE room_id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static List<Room> getAll() throws SQLException {
        String sql = "SELECT * FROM room";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Room> roomsList = new ArrayList<>();
        while (resultSet.next()) {
            String room_id = resultSet.getString(1);
            String type = resultSet.getString(2);
            String price = resultSet.getString(3);

            Room room = new Room(room_id, type, price);
            roomsList.add(room);
        }
        return roomsList;
    }

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT id FROM room";

        Connection connection = DbConnection.getInstance().getConnection();
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<String> idList = new ArrayList<>();

        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }
}

