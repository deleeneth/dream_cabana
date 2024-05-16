package lk.ijse.dreamcabana.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dreamcabana.model.Customer;
import lk.ijse.dreamcabana.model.Employee;
import lk.ijse.dreamcabana.model.Supplier;
import lk.ijse.dreamcabana.model.tm.CustomerTm;
import lk.ijse.dreamcabana.model.tm.SupplierTm;
import lk.ijse.dreamcabana.repo.Supplierrepo;
import java.sql.SQLException;
import java.util.List;

public class suppliercontroller {
    public AnchorPane Load;
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtContact;
    public TextField txtdate;
    public TableView<SupplierTm> suptbl;
    public TableColumn name;
    public TableColumn address;
    public TableColumn contact;
    public TableColumn Date;
    public TableColumn supplier_id;
    private List<Supplier> supplierList;



    public void initialize() {
        this.supplierList = (List<Supplier>) getAllSupplier();
        setCellValueFactory();
        loadSupplierTable();
    }

    private void setCellValueFactory() {
        supplier_id.setCellValueFactory(new PropertyValueFactory<>("supplier_id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        contact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
    }
    private void loadSupplierTable() {
        ObservableList<SupplierTm> tmList = FXCollections.observableArrayList();

        for (Supplier supplier : supplierList) {
            SupplierTm supplierTm = new SupplierTm(
                    supplier.getSupplier_id(),
                    supplier.getName(),
                    supplier.getAddress(),
                    supplier.getContact(),
                    supplier.getDate()
            );

            tmList.add(supplierTm);
        }
        suptbl.setItems(tmList);
        SupplierTm selectedItem =  suptbl.getSelectionModel().getSelectedItem();
        System.out.println("selectedItem = " + selectedItem);
    }


    private Object getAllSupplier() {
        List<Supplier> supplierList = null;
        try {
            supplierList = Supplierrepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return supplierList;
    }

    public void btnOnActionSave(ActionEvent actionEvent) {
        String supplier_id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String Date = txtdate.getText();

        Supplier supplier = new Supplier(supplier_id, name, address, contact,Date);

        try {
            boolean isSaved = Supplierrepo.save(supplier);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "supplier saved!").show();
                initialize();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void btnOnActionUpdate(ActionEvent actionEvent) {
        String supplier_id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String Date = txtdate.getText();

        Supplier supplier = new Supplier(supplier_id, name, address, contact,Date);

        try {
            boolean isUpdated = Supplierrepo.update(supplier);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "supplier updated!").show();
                initialize();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }
    public void btnOnActionDelete(ActionEvent actionEvent) {
        String id = txtId.getText();

        try {
            boolean isDeleted = Supplierrepo.delete(id);
            if (isDeleted) {
                new Alert           (Alert.AlertType.CONFIRMATION, "supplier deleted!").show();
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
        txtdate.setText("");
    }

    public void btnOnActionClear(ActionEvent actionEvent) {
        clearFields();
    }


    public void txtSearchOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {
            Supplier supplier = Supplierrepo.searchById(id);

            if (supplier != null) {
                txtId.setText(supplier.getSupplier_id());
                txtName.setText(supplier.getName());
                txtAddress.setText(supplier.getAddress());
                txtContact.setText(supplier.getContact());
                txtdate.setText(supplier.getDate());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
