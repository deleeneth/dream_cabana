package lk.ijse.dreamcabana.repo;

import lk.ijse.dreamcabana.db.DbConnection;
import lk.ijse.dreamcabana.model.Customer;
import lk.ijse.dreamcabana.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class Supplierrepo {
    public static boolean save(Supplier supplier) throws SQLException {
        String sql = "INSERT INTO Supplier VALUES(?, ?, ?, ?,?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, supplier.getSupplier_id());
        pstm.setObject(2, supplier.getName());
        pstm.setObject(3, supplier.getAddress());
        pstm.setObject(4, supplier.getContact());
        pstm.setObject(5, supplier.getDate());

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Supplier supplier) throws SQLException {
        String sql = "UPDATE Supplier SET name = ?, address = ?, contact = ? , Date = ? WHERE supplier_id = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, supplier.getName());
        pstm.setObject(2, supplier.getAddress());
        pstm.setObject(3, supplier.getContact());
        pstm.setObject(4, supplier.getDate());
        pstm.setObject(5, supplier.getSupplier_id());

        return pstm.executeUpdate() > 0;
    }

    public static Supplier searchById(String id) throws SQLException {
        String sql = "SELECT * FROM Supplier WHERE supplier_id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, id);
        ResultSet resultSet = pstm.executeQuery();

        Supplier supplier = null;

        if (resultSet.next()) {
            String supplier_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String contact = resultSet.getString(4);
            String Date = resultSet.getString(5);

            supplier = new Supplier(supplier_id, name, address, contact, Date);
        }
        return supplier;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM Supplier WHERE supplier_id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static List<Supplier> getAll() throws SQLException {
        String sql = "SELECT * FROM Supplier";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Supplier> supplierList = new ArrayList<>();
        while (resultSet.next()) {
            String supplier_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String contact = resultSet.getString(4);
            String Date = resultSet.getString(5);

            Supplier supplier = new Supplier(supplier_id, name, address, contact,Date);
            supplierList.add(supplier);
        }
        return supplierList;
    }

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT id FROM Supplier";

        Connection connection = DbConnection.getInstance().getConnection();
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<String> idList = new ArrayList<>();

        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }
}
