package lk.ijse.dreamcabana.repo;

import lk.ijse.dreamcabana.db.DbConnection;
import lk.ijse.dreamcabana.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class Employeerepo {
    public static boolean save(Employee employee) throws SQLException {
        String sql = "INSERT INTO Employee VALUES(?, ?, ?, ?,?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, employee.getEmployee_id());
        pstm.setObject(2, employee.getName());
        pstm.setObject(3, employee.getAddress());
        pstm.setObject(4, employee.getContact());
        pstm.setObject(5, employee.getJob());

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Employee employee) throws SQLException {
        String sql = "UPDATE Employee SET name = ?, address = ?, contact = ?, job = ? WHERE employee_id = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, employee.getName());
        pstm.setObject(2, employee.getAddress());
        pstm.setObject(3, employee.getContact());
        pstm.setObject(4, employee.getJob());
        pstm.setObject(5, employee.getEmployee_id());

        return pstm.executeUpdate() > 0;
    }

    public static Employee searchById(String id) throws SQLException {
        String sql = "SELECT * FROM Employee WHERE employee_id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, id);
        ResultSet resultSet = pstm.executeQuery();

        Employee employee = null;

        if (resultSet.next()) {
            String employee_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String contact = resultSet.getString(4);
            String job = resultSet.getString(5);

            employee = new Employee(employee_id, name, address, contact,job);
        }
        return employee;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM Employee WHERE employee_id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static List<Employee> getAll() throws SQLException {
        String sql = "SELECT * FROM Employee";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Employee> employeeList = new ArrayList<>();
        while (resultSet.next()) {
            String employee_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String contact = resultSet.getString(4);
            String job = resultSet.getString(5);

            Employee employee = new Employee(employee_id, name, address, contact,job);
            employeeList.add(employee);
        }
        return employeeList;
    }

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT id FROM Employee";

        Connection connection = DbConnection.getInstance().getConnection();
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<String> idList = new ArrayList<>();

        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }
}
