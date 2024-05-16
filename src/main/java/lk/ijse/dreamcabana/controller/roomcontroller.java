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
import java.text.CollationElementIterator;
import java.util.List;

public class roomcontroller {

    public TableColumn <?,?>date;

    public TableColumn states;

    @FXML
    private ComboBox<String> cmbStates;
    @FXML
    private ComboBox<String> cmbType;
    @FXML
    private ComboBox<String> cmbPrice;
    @FXML
    private ComboBox<String> cmbId;

    @FXML
    private AnchorPane Load;

    @FXML
    private TableColumn<?, ?> Type;

    @FXML
    private TableColumn<?, ?> room_id;

    @FXML
    private TableView<RoomTm> roomtbl;
    @FXML
    private TextField txtType;
    private List<Room> roomList;


    public void initialize() {
        this.roomList = getAllCustomers();
        setCellValueFactory();
        loadRoomTable();
        getStates();
        getId();
        getType();
        getPrice();
    }

    private void getId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        String [] codeList = new String[]{"R001","R002","R003","R004","R005","R006","R007","R008","R009","R010","R011","R012"};
        for (String code : codeList) {
            obList.add(code);
        }

        cmbId.setItems(obList);
    }

    private void getPrice() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        String [] codeList = new String[]{"2000.00","3000.00"};
        for (String code : codeList) {
            obList.add(code);
        }

        cmbPrice.setItems(obList);
    }

    private void getType() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        String [] codeList = new String[]{"NON-AC","AC"};
        for (String code : codeList) {
            obList.add(code);
        }

        cmbType.setItems(obList);

    }


    private void getStates() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        String [] codeList = new String[]{"Available","Unavailable"};
        for (String code : codeList) {
            obList.add(code);
        }

        cmbStates.setItems(obList);

    }

    private void loadRoomTable() {
            ObservableList<RoomTm> tmList = FXCollections.observableArrayList();

            for (Room room : roomList) {
                RoomTm roomTm = new RoomTm(room.getRoom_id(),room.getType(), room.getPrice(), room.getStates());

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
        states.setCellValueFactory(new PropertyValueFactory<>("states"));
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
        cmbType.setValue("");
        cmbPrice.setValue("");
        cmbId.setValue("");
        cmbStates.setValue("");
    }

    @FXML
    void btnOnActionClear(ActionEvent event) { clearFields();
    }

    @FXML
    void btnOnActionDelete(ActionEvent event) {
        String id = cmbId.getValue();

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
        String room_id = cmbId.getValue();
        String type = cmbType.getValue();
        String price = cmbPrice.getValue();
        String states =cmbStates.getValue();

        Room room = new Room(room_id, type, price,states );

        try {
            boolean isSaved = Roomrepo.save(room);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "room saved!").show();
                initialize();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnOnActionUpdate(ActionEvent event) {
        String room_id = cmbId.getValue();
        String type = cmbType.getValue();
        String price = cmbPrice.getValue();
        String states = cmbStates.getValue();

        Room room = new Room(room_id, type, price,states);

        try {
            boolean isUpdated = Roomrepo.update(room);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "room updated!").show();
                initialize();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String id = cmbId.getValue();

        try {
            Room room = Roomrepo.searchById(id);

            if (room != null) {
                cmbId.setValue(room.getRoom_id());
                cmbType.setValue(room.getType());
                cmbPrice.setValue(room.getPrice());
                cmbStates.setValue(room.getStates());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }
}
