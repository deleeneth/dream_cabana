package lk.ijse.dreamcabana.repo;

import lk.ijse.dreamcabana.db.DbConnection;
import lk.ijse.dreamcabana.model.Menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class Menurepo {
    public static boolean save(Menu menu) throws SQLException {
        String sql = "INSERT INTO Menu VALUES(?, ?, ?, ?,?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, menu.getMenu_id());
        pstm.setObject(2, menu.getCustomer_id());
        pstm.setObject(3, menu.getName());
        pstm.setObject(4, menu.getPrice());
        pstm.setObject(5, menu.getDate());

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Menu menu) throws SQLException {
        String sql = "UPDATE Menu SET name = ?, price = ?, Date = ?, customer_id = ? WHERE menu_id = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, menu.getName());
        pstm.setObject(2, menu.getPrice());
        pstm.setObject(3, menu.getDate());
        pstm.setObject(4, menu.getCustomer_id());
        pstm.setObject(5, menu.getMenu_id());

        return pstm.executeUpdate() > 0;
    }

    public static Menu searchById(String id) throws SQLException {
        String sql = "SELECT * FROM Menu WHERE menu_id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, id);
        ResultSet resultSet = pstm.executeQuery();

        Menu menu = null;

        if (resultSet.next()) {
            String menu_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String price = resultSet.getString(3);
            String Date = resultSet.getString(4);
            String customer_id = resultSet.getString(5);

            menu = new Menu(menu_id, name, price, Date,customer_id);
        }
        return menu;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM Menu WHERE menu_id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static List<Menu> getAll() throws SQLException {
        String sql = "SELECT * FROM Menu";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Menu> menuList = new ArrayList<>();
        while (resultSet.next()) {
            String menu_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String price = resultSet.getString(3);
            String Date = resultSet.getString(4);
            String customer_id = resultSet.getString(5);

            Menu menu = new Menu(menu_id, name, price, Date,customer_id);
            menuList.add(menu);
        }
        return menuList;
    }

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT id FROM Menu";

        Connection connection = DbConnection.getInstance().getConnection();
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<String> idList = new ArrayList<>();

        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }
}
