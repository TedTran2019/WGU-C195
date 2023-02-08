package ted.wguc195.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import ted.wguc195.daos.AppointmentDaoImpl;
import ted.wguc195.daos.ContactDaoImpl;
import ted.wguc195.daos.CustomerDaoImpl;
import ted.wguc195.daos.UserDaoImpl;
import ted.wguc195.models.Appointment;
import ted.wguc195.models.Contact;
import ted.wguc195.models.Customer;
import ted.wguc195.models.User;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public abstract class AppointmentController extends BaseController {
    @FXML
    protected ComboBox<Contact> comboBoxContact;

    @FXML
    protected ComboBox<Customer> comboBoxCustomer;

    @FXML
    protected ComboBox<User> comboBoxUser;

    @FXML
    protected DatePicker datePickerEnd;

    @FXML
    protected DatePicker datePickerStart;

    @FXML
    protected Label errorDescription;

    @FXML
    protected Label errorLocation;

    @FXML
    protected Label errorTitle;

    @FXML
    protected Label errorType;

    @FXML
    protected TextField textFieldAppointmentID;

    @FXML
    protected TextField textFieldDescription;

    @FXML
    protected TextField textFieldLocation;

    @FXML
    protected TextField textFieldTitle;
    @FXML
    protected TextField textFieldType;

    @FXML
    protected Spinner<Integer> spinnerEndHours;

    @FXML
    protected Spinner<Integer> spinnerEndMinutes;

    @FXML
    protected Spinner<Integer> spinnerStartHours;

    @FXML
    protected Spinner<Integer> spinnerStartMinutes;

    protected CustomerDaoImpl customerDao = new CustomerDaoImpl();
    protected UserDaoImpl userDao = new UserDaoImpl();
    protected ContactDaoImpl contactDao = new ContactDaoImpl();
    protected AppointmentDaoImpl appointmentDao = new AppointmentDaoImpl();

    protected void setComboBoxes() throws SQLException {
        comboBoxCustomer.setItems(customerDao.getAllCustomers());
        comboBoxUser.setItems(userDao.getAllUsers());
        comboBoxContact.setItems(contactDao.getAllContacts());
    }

    protected void setSpinners() {
        spinnerStartHours.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0));
        spinnerStartMinutes.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));
        spinnerEndHours.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0));
        spinnerEndMinutes.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));
        setSpinnerListener(spinnerStartHours);
        setSpinnerListener(spinnerStartMinutes);
        setSpinnerListener(spinnerEndHours);
        setSpinnerListener(spinnerEndMinutes);
    }

    private void setSpinnerListener(Spinner<Integer> spinner) {
        spinner.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d+")) {
                spinner.getEditor().setText(oldValue);
            }
        });
    }

    protected boolean validateFields(String title, String description, String location, String type) {
        boolean isValid = true;

        if (title.isEmpty()) {
            errorTitle.setText("Title is required");
            isValid = false;
        } else {
            errorTitle.setText("");
        }
        if (description.isEmpty()) {
            errorDescription.setText("Description is required");
            isValid = false;
        } else {
            errorDescription.setText("");
        }
        if (location.isEmpty()) {
            errorLocation.setText("Location is required");
            isValid = false;
        } else {
            errorLocation.setText("");
        }
        if (type.isEmpty()) {
            errorType.setText("Type is required");
            isValid = false;
        } else {
            errorType.setText("");
        }
        if (unselectedChecks()) {return false;}
        return isValid;
    }

    private boolean unselectedChecks() {
        boolean unselected = false;

        if (comboBoxCustomer.getValue() == null) {
            comboBoxCustomer.setStyle("-fx-border-color: red");
            unselected = true;
        } else {
            comboBoxCustomer.setStyle("");
        }
        if (comboBoxUser.getValue() == null) {
            comboBoxUser.setStyle("-fx-border-color: red");
            unselected = true;
        } else {
            comboBoxUser.setStyle("");
        }
        if (comboBoxContact.getValue() == null) {
            comboBoxContact.setStyle("-fx-border-color: red");
            unselected = true;
        } else {
            comboBoxContact.setStyle("");
        }
        if (datePickerStart.getValue() == null) {
            datePickerStart.setStyle("-fx-border-color: red");
            unselected = true;
        } else {
            datePickerStart.setStyle("");
        }
        if (datePickerEnd.getValue() == null) {
            datePickerEnd.setStyle("-fx-border-color: red");
            unselected = true;
        } else {
            datePickerEnd.setStyle("");
        }
        if (spinnerStartHours.getValue() == null) {
            spinnerStartHours.setStyle("-fx-border-color: red");
            unselected = true;
        } else {
            spinnerStartHours.setStyle("");
        }
        if (spinnerStartMinutes.getValue() == null) {
            spinnerStartMinutes.setStyle("-fx-border-color: red");
            unselected = true;
        } else {
            spinnerStartMinutes.setStyle("");
        }
        if (spinnerEndHours.getValue() == null) {
            spinnerEndHours.setStyle("-fx-border-color: red");
            unselected = true;
        } else {
            spinnerEndHours.setStyle("");
        }
        if (spinnerEndMinutes.getValue() == null) {
            spinnerEndMinutes.setStyle("-fx-border-color: red");
            unselected = true;
        } else {
            spinnerEndMinutes.setStyle("");
        }
        return unselected;
    }

    protected boolean validateTimes(LocalDateTime start, LocalDateTime end) {
        if (!start.isBefore(end)) {
            errorBox("Date/Time Error", "Start must be before end", "Please select valid start and end times");
            return false;
        }
        if (start.isBefore(LocalDateTime.now())) {
            errorBox("Date/Time Error", "Start must be in the future", "Please select a start time in the future");
            return false;
        }
        if (!validateWithinOfficeHours(start)) { return false;}

        return true;
    }

    private boolean validateWithinOfficeHours(LocalDateTime start) {
        boolean isValid = true;

        ZonedDateTime startEST = convertLocalToEST(start);
        if (outOfOfficeHours(startEST)) {
            errorBox("Date/Time Error", "Start must be within EST office hours", "Please select a start time between 8am and 10pm EST");
            isValid = false;
        }
        return isValid;
    }

    private boolean outOfOfficeHours(ZonedDateTime time) {
        return (time.getHour() < 8 || time.getHour() > 22 || (time.getHour() == 22 && time.getMinute() > 0));
    }

    protected LocalDateTime getLocalDateTime(LocalDate date, int hours, int minutes) {
        return date.atTime(hours, minutes);
    }
}
