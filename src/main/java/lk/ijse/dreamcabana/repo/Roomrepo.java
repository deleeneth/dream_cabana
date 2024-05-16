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
        String sql = "INSERT INTO room VALUES(?, ?, ?,?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, room.getRoom_id());
        pstm.setObject(2, room.getType());
        pstm.setObject(3, room.getPrice());
        pstm.setObject(4, room.getStates());

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Room room) throws SQLException {
        String sql = "UPDATE room SET type = ?, price = ?, states = ? WHERE room_id = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, room.getType());
        pstm.setObject(2, room.getPrice());
        pstm.setObject(3, room.getStates());
        pstm.setObject(4, room.getRoom_id());

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
            String states = resultSet.getString(4);

            room = new Room(room_id, type ,  price, states);
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
            String states = resultSet.getString(4);

            Room room = new Room(room_id, type, price, states);
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

    public static List<String> getRoomIds() {
        List<String> roomIds = new ArrayList<>();
        try {
            List<Room> rooms = getAll();
            for (Room room : rooms) {
                roomIds.add(room.getRoom_id());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomIds;
    }
/*
    public static List<String> getRoomStates() {
        List<String> states = new ArrayList<>();
        try {
            List<Room> rooms = getAll();
            for (Room room : rooms) {
                states.add(room.getStates());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return states;
    }
*/
public static Room searchByCode(String code) throws SQLException {
        String sql = "SELECT * FROM room WHERE room_id = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
        pstm.setObject(1, code);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return new Room(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
        }
        return null;
    }
}

