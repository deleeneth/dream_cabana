package lk.ijse.dreamcabana.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dreamcabana.model.AddBooking;
import lk.ijse.dreamcabana.model.Booking;
import lk.ijse.dreamcabana.model.Room;
import lk.ijse.dreamcabana.model.tm.BookingDetailTm;
import lk.ijse.dreamcabana.model.tm.BookingTm;
import lk.ijse.dreamcabana.repo.AddBookingRepo;
import lk.ijse.dreamcabana.repo.Bookingrepo;
import lk.ijse.dreamcabana.repo.Roomrepo;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


public class Bookingontroller {

    public DatePicker txtdate;
    @FXML
    private TableColumn<?, ?> Date;

    @FXML
    private AnchorPane Load;

    @FXML
    private TableColumn<?, ?> bookingid;

    @FXML
    private TableView<BookingDetailTm> bookingtbl;

    @FXML
    private ComboBox<String> cmdroomid;

    @FXML
    private TableColumn<?, ?> customerid;

    @FXML
    private TableColumn<?, ?> payment;

    @FXML
    private TableColumn<?, ?> roomid;

    @FXML
    private TableColumn<?, ?> states;

    @FXML
    private TextField txtStates;

    @FXML
    private TextField txtType;

    @FXML
    private TextField txtbookid;

    @FXML
    private TextField txtcusid;


    @FXML
    private TextField txtpayment;

    @FXML
    private TableColumn<?, ?> type;

    private List<Booking> bookingList;

    public void initialize() throws SQLException {
        this.bookingList = getAllBooking();
        setCellValueFactory();
        loadBookingTable();
        getRoomId();
        setDate();
    }

   /* private void getStates() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<String> codeList = Roomrepo.getRoomStates();
        for (String code : codeList) {
            obList.add(code);
        }
        cmdStates.setItems(obList);
    }

*/
    private void getRoomId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<String> codeList = Roomrepo.getRoomIds();
        for (String code : codeList) {
            obList.add(code);
        }

        cmdroomid.setItems(obList);

    }

    private void setCellValueFactory() {
        bookingid.setCellValueFactory(new PropertyValueFactory<>("booking_id"));
        customerid.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        payment.setCellValueFactory(new PropertyValueFactory<>("payment"));
        Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        roomid.setCellValueFactory(new PropertyValueFactory<>("room_id"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        states.setCellValueFactory(new PropertyValueFactory<>("states"));

    }

    private void loadBookingTable() throws SQLException {
        ObservableList<BookingDetailTm> tmList = FXCollections.observableArrayList();

        for (Booking booking : bookingList) {
            Room room = Roomrepo.searchById(booking.getRoom_id());
            BookingDetailTm bookingTm = new BookingDetailTm(
                    booking.getBooking_id() ,
                    booking.getCustomer_id(),
                    booking.getPayment(),
                    booking.getDate(),
                    booking.getRoom_id(),
                    room.getType(),
                    room.getStates()
            );


            tmList.add(bookingTm);
        }
        bookingtbl.setItems(tmList);
        BookingDetailTm selectedItem =  bookingtbl.getSelectionModel().getSelectedItem();
        System.out.println("selectedItem = " + selectedItem);
    }

    private List<Booking> getAllBooking() {
        List<Booking> customerList = null;
        try {
            customerList = Bookingrepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerList;
    }

    public void btnOnActionSave(ActionEvent actionEvent) {
        String booking_id = txtbookid.getText();
        String customer_id = txtcusid.getText();
        String payment = txtpayment.getText();
        String date = String.valueOf(txtdate.getValue());
        String room_id = cmdroomid.getValue();
        String roomType = txtType.getText();
        String roomStates = txtStates.getText();

        if(roomStates.equals("Available")){
            roomStates = "Unavailable";
            System.out.println( roomStates);
        }


        Booking booking = new Booking(booking_id, customer_id,payment,date,room_id);
        Room room = new Room(room_id,roomType,payment,roomStates);
        AddBooking ab = new AddBooking(booking,room);
        try {
            System.out.println(booking);
            System.out.println(room);
            boolean isSaved = AddBookingRepo.save(ab);
            System.out.println(isSaved);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "booking saved!").show();
                initialize();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }
    public void txtSearchOnAction(ActionEvent event) {
        String id = txtbookid.getText();

        try {
            Booking booking = Bookingrepo.searchById(id);

            if (booking != null) {
                txtbookid.setText(booking.getBooking_id());
                txtcusid.setText(booking.getCustomer_id());
                txtpayment.setText(booking.getPayment());
                txtdate.setValue(LocalDate.parse(booking.getDate()));
                cmdroomid.setValue(booking.getRoom_id());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void btnOnActionUpdate(ActionEvent actionEvent) {
        String booking_id = txtbookid.getText();
        String customer_id = txtcusid.getText();
        String payment = txtpayment.getText();
        String date = String.valueOf(txtdate.getValue());
        String room_id = cmdroomid.getValue();


        Booking booking = new Booking(booking_id,customer_id,payment,date,room_id);

        try {
            boolean isUpdated = Bookingrepo.update(booking);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "booking updated!").show();
                initialize();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void btnOnActionDelete(ActionEvent actionEvent) {
        String booking_id = txtbookid.getText();

        try {
            boolean isDeleted = Bookingrepo.delete(booking_id);
            if (isDeleted) {
                new Alert           (Alert.AlertType.CONFIRMATION, "booking deleted!").show();
                initialize();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }
    private void clearFields() {
        txtbookid.setText("");
        txtcusid.setText("");
        txtpayment.setText("");
        txtdate.setValue(LocalDate.parse("select"));
        cmdroomid.setValue("");
    }

    public void btnOnActionClear(ActionEvent actionEvent) { clearFields();}


    public void txtSearchOAction(ActionEvent actionEvent) {

    }

    public void cmbOnActionRoom(ActionEvent actionEvent) {
        String code = cmdroomid.getValue();
        try {
            Room room = Roomrepo.searchByCode(code);
            if (room != null) {
                txtType.setText(room.getType());
                txtStates.setText(room.getStates());
                txtpayment.setText(room.getPrice());

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void setDate() {
        LocalDate now = LocalDate.now();
        txtdate.setValue(LocalDate.parse(String.valueOf(now)));
    }


    public void cmbOnActionType(ActionEvent actionEvent) {
    }

    public void cmbOnActionStates(ActionEvent actionEvent) {
    }
}
