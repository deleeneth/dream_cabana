package lk.ijse.dreamcabana.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dreamcabana.model.Booking;
import lk.ijse.dreamcabana.model.Customer;
import lk.ijse.dreamcabana.model.Room;
import lk.ijse.dreamcabana.model.tm.BookingTm;
import lk.ijse.dreamcabana.model.tm.CustomerTm;
import lk.ijse.dreamcabana.repo.Bookingrepo;
import lk.ijse.dreamcabana.repo.Customerrepo;
import lk.ijse.dreamcabana.repo.Roomrepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class Bookingontroller {

    public AnchorPane Load;
    public TextField txtId;
    public DatePicker date;
    public TextField txtbookid;
    public TableView <BookingTm> bookingtbl;
    public TextField txtcusid;
    public TextField txtdate;
    public TableColumn packageid;
    public TableColumn customerid;
    public TableColumn Date;
    private List<Booking> bookingList;

    public void initialize() {
        this.bookingList = getAllCustomers();
        setCellValueFactory();
        loadCustomerTable();
    }

    private void setCellValueFactory() {
        packageid.setCellValueFactory(new PropertyValueFactory<>("booking_id"));
        Date.setCellValueFactory(new PropertyValueFactory<>("date"));
        customerid.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
    }

    private void loadCustomerTable() {
        ObservableList<BookingTm> tmList = FXCollections.observableArrayList();

        for (Booking customer : bookingList) {
            BookingTm customerTm = new BookingTm(
                    customer.getBooking_id(),
                    customer.getCustomer_id(),
                    customer.getDate());

            tmList.add(customerTm);
        }
        bookingtbl.setItems(tmList);
        BookingTm selectedItem =  bookingtbl.getSelectionModel().getSelectedItem();
        System.out.println("selectedItem = " + selectedItem);
    }

    private List<Booking> getAllCustomers() {
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
        String date = txtdate.getText();

        Booking booking = new Booking(booking_id, customer_id,date);

        try {
            boolean isSaved = Bookingrepo.save(booking);
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
            Booking customer = Bookingrepo.searchById(id);

            if (customer != null) {
                txtbookid.setText(customer.getBooking_id());
                txtcusid.setText(customer.getCustomer_id());
                txtdate.setText(customer.getDate());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void btnOnActionUpdate(ActionEvent actionEvent) {
        String booking_id = txtbookid.getText();
        String date = txtdate.getText();
        String customer_id = txtcusid.getText();

        Booking booking = new Booking(booking_id,customer_id,date);

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
        txtdate.setText("");
        txtcusid.setText("");
    }

    public void btnOnActionClear(ActionEvent actionEvent) { clearFields();}



}
