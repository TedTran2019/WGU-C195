package ted.wguc195.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ted.wguc195.daos.CountryDaoImpl;
import ted.wguc195.daos.CustomerDaoImpl;
import ted.wguc195.daos.DivisionDaoImpl;
import ted.wguc195.models.Country;
import ted.wguc195.models.Customer;
import ted.wguc195.models.Division;
import javafx.scene.control.ListCell;

import java.sql.SQLException;

public abstract class CustomerController extends BaseController {
    @FXML
    protected ComboBox<Country> comboBoxCountry;

    @FXML
    protected ComboBox<Division> comboBoxDivision;

    @FXML
    protected Label errorAddress;

    @FXML
    protected Label errorName;

    @FXML
    protected Label errorPhone;

    @FXML
    protected Label errorPostal;

    @FXML
    protected Label labelDivision;

    @FXML
    protected TextField textFieldAddress;

    @FXML
    protected TextField textFieldName;

    @FXML
    protected TextField textFieldPhone;

    @FXML
    protected TextField textFieldPostal;
    @FXML
    protected TextField textFieldID;
    protected CountryDaoImpl countryDao = new CountryDaoImpl();
    protected DivisionDaoImpl divisionDao = new DivisionDaoImpl();
    protected CustomerDaoImpl customerDao = new CustomerDaoImpl();

    @FXML
    protected void onActionCountryCB(ActionEvent event) throws SQLException {
        int countryID = comboBoxCountry.getValue().getCountryID();
        comboBoxDivision.setItems(divisionDao.getDivisionsFromCountryID(countryID));
        comboBoxDivision.setValue(null);
        comboBoxDivision.setDisable(false);
        if (countryID == 1) {
            labelDivision.setText("State");
        } else if (countryID == 3) {
            labelDivision.setText("Province");
        } else {
            labelDivision.setText("Division");
        }
    }

    protected void setCountryCB() throws SQLException {
        comboBoxCountry.setItems(countryDao.getAllCountries());
    }

    protected void setDivisionCBPromptText() {
        comboBoxDivision.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Division item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "Select a division!" : item.getDivision());
            }
        });
    }

    /**
     * This method validates the fields in the add customer form. If I had to handle more errors, I would make a separate
     * method for each single field that would handle the error checking + text setting.
     * @param customerName
     * @param address
     * @param postalCode
     * @param phone
     * @returns boolean isValid
     */
    protected boolean validateFields(String customerName, String address, String postalCode, String phone) {
        boolean isValid = true;

        if (customerName.isEmpty()) {
            errorName.setText("Customer name is required.");
            isValid = false;
        } else {
            errorName.setText("");
        }
        if (address.isEmpty()) {
            errorAddress.setText("Address is required.");
            isValid = false;
        } else {
            errorAddress.setText("");
        }
        if (postalCode.isEmpty()) {
            errorPostal.setText("Postal code is required.");
            isValid = false;
        } else {
            errorPostal.setText("");
        }
        if (phone.isEmpty()) {
            errorPhone.setText("Phone number is required.");
            isValid = false;
        } else {
            errorPhone.setText("");
        }
        if (comboBoxDivision.getValue() == null) {
            errorBox("Division Error", "Please select a division.", "Division is required!");
            isValid = false;
        }
        return isValid;
    }
}
