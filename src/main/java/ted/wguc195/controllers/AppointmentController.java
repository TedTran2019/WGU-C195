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

/**
 * Abstract controller for the Appointment views
 */
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

    /**
     * Sets up the combo boxes
     */
    protected void setComboBoxes() throws SQLException {
        comboBoxCustomer.setItems(customerDao.getAllCustomers());
        comboBoxUser.setItems(userDao.getAllUsers());
        comboBoxContact.setItems(contactDao.getAllContacts());
    }

    /**
     * Sets up the spinners
     * The listeners are to prevent the user from entering non-numeric values and causing an error
     */
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

    /**
     * LAMBDA USED HERE. I used the lambda to check if the newValue is a number, if it's not, I reset back to the previous.
     * Sets the listener for the spinner to prevent non-numeric values from being entered
     * @param spinner The spinner to set the listener for
     */
    private void setSpinnerListener(Spinner<Integer> spinner) {
        spinner.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d+")) {
                spinner.getEditor().setText(oldValue);
            }
        });
    }

    /**
     * Validates the fields and returns true if they are valid
     * @param title The title of the appointment
     * @param description The description of the appointment
     * @param location The location of the appointment
     * @param type The type of the appointment
     * @return True if the fields are valid, otherwise false
     */
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

    /**
     * Checks if any of the non-text fields are unselected (Combo boxes, date pickers, and spinners)
     * @return True if any of the non-text fields are unselected, otherwise false
     */
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

    /**
     * Validates the date and time fields and returns true if they are valid
     * Ensures the times are in the future and that the start time is before the end time
     * @param start The start date and time
     * @param end The end date and time
     * @return True if the date and time fields are valid, otherwise false
     */
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

    /**
     * Validates that the start time is within EST office hours
     * @param start The start date and time
     * @return True if the start time is within EST office hours, otherwise false
     */
    private boolean validateWithinOfficeHours(LocalDateTime start) {
        boolean isValid = true;

        ZonedDateTime startEST = convertLocalToEST(start);
        if (outOfOfficeHours(startEST)) {
            errorBox("Date/Time Error", "Start must be within EST office hours", "Please select a start time between 8am and 10pm EST");
            isValid = false;
        }
        return isValid;
    }

    /**
     * Checks if the time is outside of EST office hours (8am - 10pm)
     * @param time The time to check
     * @return True if the time is outside of EST office hours, otherwise false
     */
    private boolean outOfOfficeHours(ZonedDateTime time) {
        return (time.getHour() < 8 || time.getHour() > 22 || (time.getHour() == 22 && time.getMinute() > 0));
    }

    /**
     * Creates a LocalDateTime object from the date picker and time spinners
     * @param date The date from the date picker
     * @param hours The hours from the hours spinner
     * @param minutes The minutes from the minutes spinner
     * @return A LocalDateTime object
     */
    protected LocalDateTime getLocalDateTime(LocalDate date, int hours, int minutes) {
        return date.atTime(hours, minutes);
    }
}
