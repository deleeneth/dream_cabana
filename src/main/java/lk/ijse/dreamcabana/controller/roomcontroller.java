package lk.ijse.dreamcabana.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dreamcabana.model.Customer;
import lk.ijse.dreamcabana.model.Room;
import lk.ijse.dreamcabana.model.tm.RoomTm;
import lk.ijse.dreamcabana.repo.Customerrepo;
import lk.ijse.dreamcabana.repo.Roomrepo;

import java.sql.SQLException;
import java.util.List;

public class roomcontroller {

    public TableColumn <?,?>date;
    @FXML
    private AnchorPane Load;

    @FXML
    private TableColumn<?, ?> Type;

    @FXML
    private TableColumn<?, ?> room_id;

    @FXML
    private TableView<RoomTm> roomtbl;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtType;
    private List<Room> roomList;


    public void initialize() {
        this.roomList = getAllCustomers();
        setCellValueFactory();
        loadRoomTable();
    }

    private void loadRoomTable() {
            ObservableList<RoomTm> tmList = FXCollections.observableArrayList();

            for (Room room : roomList) {
                RoomTm roomTm = new RoomTm(room.getRoom_id(),room.getType(), room.getPrice());

                tmList.add(roomTm);
            }
            roomtbl.setItems(tmList);
            RoomTm selectedItem = roomtbl.getSelectionModel().getSelectedItem();
            System.out.println("selectedItem = " + selectedItem);
        }

    private void setCellValueFactory() {
        room_id.setCellValueFactory(new PropertyValueFactory<>("room_id"));
        Type.setCellValueFactory(new PropertyValueFactory<>("type"));
        date.setCellValueFactory(new PropertyValueFactory<>("price"));
    }


    private List<Room> getAllCustomers() {
        List<Room> roomList = null;
        try {
            roomList = Roomrepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomList;
    }

    private void clearFields() {
        txtType.setText("");
txtPrice.setText("");
        txtId.setText("");
    }

    @FXML
    void btnOnActionClear(ActionEvent event) { clearFields();
    }

    @FXML
    void btnOnActionDelete(ActionEvent event) {
        String id = txtId.getText();

        try {
            boolean isDeleted = Roomrepo.delete(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "room deleted!").show();
                initialize();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnOnActionSave(ActionEvent event) {
        String room_id = txtId.getText();
        String type = txtType.getText();
        String price = txtPrice.getText();

        Room room = new Room(room_id, type, price);

        try {
            boolean isSaved = Roomrepo.save(room);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();
                initialize();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnOnActionUpdate(ActionEvent event) {
        String customer_id = txtId.getText();
        String name = txtType.getText();
        String address = txtPrice.getText();

        Room customer = new Room(customer_id, name, address);

        try {
            boolean isUpdated = Roomrepo.update(customer);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
                initialize();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {
            Room customer = Roomrepo.searchById(id);

            if (customer != null) {
                txtId.setText(customer.getRoom_id());
                txtType.setText(customer.getType());
                txtPrice.setText(customer.getPrice());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

}
