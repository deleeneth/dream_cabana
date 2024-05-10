package lk.ijse.dreamcabana.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dreamcabana.model.Customer;
import lk.ijse.dreamcabana.model.tm.CustomerTm;
import lk.ijse.dreamcabana.repo.Customerrepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class customernewcontroller {

    public AnchorPane root;
    public AnchorPane Load;
    public TextField txtId;
     ;
    public TextField txtContact;
    public TableView <CustomerTm>custable;
    public TableColumn customeerid;
    public TableColumn name;
    public TableColumn address;
    public TableColumn contact;
    public TextField txtName;
    public TextField txtAddress;
    private List<Customer> customerList;


    public void initialize() {
        this.customerList = getAllCustomers();
        setCellValueFactory();
        loadCustomerTable();
    }

    private void setCellValueFactory() {
        customeerid.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        contact.setCellValueFactory(new PropertyValueFactory<>("contact"));
    }

    private void loadCustomerTable() {
        ObservableList<CustomerTm> tmList = FXCollections.observableArrayList();

        for (Customer customer : customerList) {
            CustomerTm customerTm = new CustomerTm(
                    customer.getCustomer_id(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getContact()
            );

            tmList.add(customerTm);
        }
        custable.setItems(tmList);
        CustomerTm selectedItem =  custable.getSelectionModel().getSelectedItem();
        System.out.println("selectedItem = " + selectedItem);
    }

    private List<Customer> getAllCustomers() {
        List<Customer> customerList = null;
        try {
            customerList = Customerrepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerList;
    }
    public void btnOnActionSave(ActionEvent actionEvent) {
        String customer_id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();

        Customer customer = new Customer(customer_id, name, address, contact);

        try {
            boolean isSaved = Customerrepo.save(customer);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();
                initialize();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnOnActionUpdate(ActionEvent actionEvent) {
        String customer_id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();

        Customer customer = new Customer(customer_id, name, address, contact);

        try {
            boolean isUpdated = Customerrepo.update(customer);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
                initialize();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnOnActionDelete(ActionEvent actionEvent) {
        String id = txtId.getText();

        try {
            boolean isDeleted = Customerrepo.delete(id);
            if (isDeleted) {
                new Alert           (Alert.AlertType.CONFIRMATION, "customer deleted!").show();
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
    }
    public void btnOnActionClear(ActionEvent event) {
        clearFields();
    }

    public void txtSearchOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {
            Customer customer = Customerrepo.searchById(id);

            if (customer != null) {
                txtId.setText(customer.getCustomer_id());
                txtName.setText(customer.getName());
                txtAddress.setText(customer.getAddress());
                txtContact.setText(customer.getContact());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
