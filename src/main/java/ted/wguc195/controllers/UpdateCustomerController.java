package ted.wguc195.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ted.wguc195.SchedulingApplication;
import ted.wguc195.models.Country;
import ted.wguc195.models.Customer;
import ted.wguc195.models.Division;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * Controller for the UpdateCustomer form
 */
public class UpdateCustomerController extends CustomerController {
    private Customer customer;

    /**
     * Updates the customer in the database and switches to the main form
     * @param event
     * @throws SQLException
     * @throws IOException
     */
    @FXML
    void onActionUpdateCustomer(ActionEvent event) throws SQLException, IOException {
        String customerName = textFieldName.getText();
        String address = textFieldAddress.getText();
        String postalCode = textFieldPostal.getText();
        String phone = textFieldPhone.getText();
        String updatedBy = SchedulingApplication.getUser();
        if (!validateFields(customerName, address, postalCode, phone)) {
            return;
        }
        int divisionID = comboBoxDivision.getValue().getDivisionID();
        Customer updatedCustomer = new Customer(customer.getCustomerID(), customerName, address, postalCode, phone,
                customer.getCreateDate(), customer.getCreatedBy(), LocalDateTime.now(), updatedBy, divisionID);
        customerDao.updateCustomer(updatedCustomer);
        switchScene(event, "/views/Main.fxml");
    }

    /**
     * Sets the customer to be updated
     * @param customer
     * @throws SQLException
     */
    public void setCustomer(Customer customer) throws SQLException {
        this.customer = customer;
        textFieldID.setText(Integer.toString(customer.getCustomerID()));
        textFieldName.setText(customer.getCustomerName());
        textFieldAddress.setText(customer.getAddress());
        textFieldPostal.setText(customer.getPostalCode());
        textFieldPhone.setText(customer.getPhone());
        setCountryCB();

        Division division = divisionDao.getDivision(customer.getDivisionID());
        Country country = countryDao.getCountry(division.getCountryID());
        ObservableList<Division> divisions = divisionDao.getDivisionsFromCountryID(country.getCountryID());
        comboBoxCountry.setValue(country);
        comboBoxDivision.setItems(divisions);
        comboBoxDivision.setValue(division);
    }

    public void initialize() throws SQLException {
        setDivisionCBPromptText();
    }
}
