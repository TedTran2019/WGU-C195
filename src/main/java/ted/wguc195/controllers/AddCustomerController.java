package ted.wguc195.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ted.wguc195.SchedulingApplication;
import ted.wguc195.models.Customer;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * Controller for the Add Customer view.
 */
public class AddCustomerController extends CustomerController {
    /**
     * Adds a new customer to the database.
     * @param event The event that triggered this method.
     * @throws SQLException If there is an error with the database.
     * @throws IOException If there is an error with the FXML file.
     */
    @FXML
    void onActionAddCustomer(ActionEvent event) throws SQLException, IOException {
        String customerName = textFieldName.getText();
        String address = textFieldAddress.getText();
        String postalCode = textFieldPostal.getText();
        String phone = textFieldPhone.getText();
        String createdBy = SchedulingApplication.getUser();
        if (!validateFields(customerName, address, postalCode, phone)) {
            return;
        }
        int divisionID = comboBoxDivision.getValue().getDivisionID();
        Customer customer = new Customer(1337, customerName, address, postalCode, phone, LocalDateTime.now(), createdBy, LocalDateTime.now(), createdBy, divisionID);
        customerDao.addCustomer(customer);
        switchScene(event, "/views/Main.fxml");
    }

    public void initialize() throws SQLException {
        setCountryCB();
        setDivisionCBPromptText();
    }
}
