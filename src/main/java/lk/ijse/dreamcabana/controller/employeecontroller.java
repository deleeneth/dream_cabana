package lk.ijse.dreamcabana.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dreamcabana.model.Customer;
import lk.ijse.dreamcabana.model.Employee;
import lk.ijse.dreamcabana.model.tm.EmployeeTm;
import lk.ijse.dreamcabana.repo.Employeerepo;

import java.sql.SQLException;
import java.util.List;

public class employeecontroller {
    public AnchorPane Load;
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtContact;
    public TextField date;
    public TextField txtjob;
    public TableColumn contact;
    public TableColumn job;
    public TableColumn address;
    public TableColumn name;
    public TableColumn employee;
    public TableView tblemployee;
    private List<Employee> employeeList;

    public void initialize() {
        this.employeeList = (List<Employee>) getAllEmployee();
        setCellValueFactory();
        loadEmployeeTable();
    }

    private void setCellValueFactory() {
        employee.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        contact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        job.setCellValueFactory(new PropertyValueFactory<>("job"));
    }

    private void loadEmployeeTable() {
        ObservableList<EmployeeTm> tmList = FXCollections.observableArrayList();

        for (Employee employee : employeeList) {
            EmployeeTm employeeTm = new EmployeeTm(
                    employee.getEmployee_id(),
                    employee.getName(),
                    employee.getAddress(),
                    employee.getContact(),
                    employee.getJob()

            );

            tmList.add(employeeTm);
        }
        tblemployee.setItems(tmList);
        EmployeeTm selectedItem = (EmployeeTm) tblemployee.getSelectionModel().getSelectedItem();
        System.out.println("selectedItem = " + selectedItem);

    }

    private Object getAllEmployee() {
        List<Employee> employeeList = null;
        try {
            employeeList = Employeerepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employeeList;
    }

    public void btnOnActionSave(ActionEvent actionEvent) {
        String employee_id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String job = txtjob.getText();

        Employee employee = new Employee(employee_id, name, address, contact,job);

        try {
            boolean isSaved = Employeerepo.save(employee);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "employee saved!").show();
                initialize();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnOnActionUpdate(ActionEvent actionEvent) {
        String emplpoyee_id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String job = txtjob.getText();

        Employee employee = new Employee(emplpoyee_id, name, address, contact,job);

        try {
            boolean isUpdated = Employeerepo.update(employee);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "employee updated!").show();
                initialize();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void btnOnActionDelete(ActionEvent actionEvent) {
        String id = txtId.getText();

        try {
            boolean isDeleted = Employeerepo.delete(id);
            if (isDeleted) {
                new Alert           (Alert.AlertType.CONFIRMATION, "employee deleted!").show();
                initialize();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }
    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtContact.setText("");
        txtjob.setText("");
    }

    public void btnOnActionClear(ActionEvent actionEvent) {

        clearFields();
    }

    public void txtSearchOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {
            Employee employee = Employeerepo.searchById(id);

            if (employee != null) {
                txtId.setText(employee.getEmployee_id());
                txtName.setText(employee.getName());
                txtAddress.setText(employee.getAddress());
                txtContact.setText(employee.getContact());
                txtjob.setText(employee.getJob());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
