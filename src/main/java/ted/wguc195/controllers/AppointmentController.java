package ted.wguc195.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import ted.wguc195.daos.ContactDaoImpl;
import ted.wguc195.daos.CustomerDaoImpl;
import ted.wguc195.daos.UserDaoImpl;
import ted.wguc195.models.Contact;
import ted.wguc195.models.Customer;
import ted.wguc195.models.User;

import java.sql.SQLException;

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

    private CustomerDaoImpl customerDao = new CustomerDaoImpl();
    private UserDaoImpl userDao = new UserDaoImpl();
    private ContactDaoImpl contactDao = new ContactDaoImpl();

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
    }
}
