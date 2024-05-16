package lk.ijse.dreamcabana.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dreamcabana.model.Customer;
import lk.ijse.dreamcabana.model.Employee;
import lk.ijse.dreamcabana.model.Menu;
import lk.ijse.dreamcabana.model.tm.EmployeeTm;
import lk.ijse.dreamcabana.model.tm.MenuTm;
import lk.ijse.dreamcabana.repo.Employeerepo;
import lk.ijse.dreamcabana.repo.Menurepo;

import java.sql.SQLException;
import java.util.List;

public class menucontroller {

    public DatePicker date;
    public TextField txtId;
    public AnchorPane Load;
    public TextField txtcusId;
    public TextField txtPrice;
    public TextField txtDate;
    public TextField txtName;
    public TableColumn Date;
    public TableColumn price;
    public TableColumn name;
    public TableColumn menuId;
    public TableView tblMenu;
    public TableColumn customerId;
    public TableColumn customer_id;
    private List<Menu> menuList;


    public void initialize() {
        this.menuList = getAllMenu();
        setCellValueFactory();
        loadMenuTable();
    }



    private void setCellValueFactory() {
        menuId.setCellValueFactory(new PropertyValueFactory<>("menu_id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        customerId.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
    }
    private void loadMenuTable() {
        ObservableList<MenuTm> tmList = FXCollections.observableArrayList();

        for (Menu menu : menuList) {
            MenuTm menuTm = new MenuTm(
                    menu.getMenu_id(),
                    menu.getName(),
                    menu.getPrice(),
                    menu.getDate(),
                    menu.getCustomer_id()

            );

            tmList.add(menuTm);
        }
        tblMenu.setItems(tmList);
        MenuTm selectedItem = (MenuTm) tblMenu.getSelectionModel().getSelectedItem();
        System.out.println("selectedItem = " + selectedItem);

    }

    private List<Menu> getAllMenu() {
        List<Menu> menuList = null;
        try {
            menuList = Menurepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return menuList;
    }



    public void btnOnActionSave(ActionEvent actionEvent) {
        String menu_id = txtId.getText();
        String name = txtName.getText();
        String price = txtPrice.getText();
        String Date = txtDate.getText();
        String customer_id = txtcusId.getText();

        //String menu_id, String name, String price, String date, String customer_id
        lk.ijse.dreamcabana.model.Menu menu = new lk.ijse.dreamcabana.model.Menu(menu_id, name, price, Date,customer_id);

        try {
            boolean isSaved = Menurepo.save(menu);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "menu saved!").show();
                initialize();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void btnOnActionUpdate(ActionEvent actionEvent) {
        String menu_id = txtId.getText();
        String name = txtName.getText();
        String price = txtPrice.getText();
        String Date = txtDate.getText();
        String customer_id = txtcusId.getText();

        lk.ijse.dreamcabana.model.Menu menu = new lk.ijse.dreamcabana.model.Menu(menu_id, name, price, Date,customer_id);

        try {
            boolean isUpdated = Menurepo.update(menu);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "menu updated!").show();
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
                new Alert           (Alert.AlertType.CONFIRMATION, "menu deleted!").show();
                initialize();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtPrice.setText("");
        txtDate.setText("");
        txtcusId.setText("");
    }

    public void btnOnActionClear(ActionEvent actionEvent) {
        clearFields();
    }

    public void txtSearchOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {
            Menu menu = Menurepo.searchById(id);

            if (menu != null) {
                txtId.setText(menu.getMenu_id());
                txtName.setText(menu.getName());
                txtPrice.setText(menu.getPrice());
                txtDate.setText(menu.getDate());
                txtcusId.setText(menu.getCustomer_id());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
