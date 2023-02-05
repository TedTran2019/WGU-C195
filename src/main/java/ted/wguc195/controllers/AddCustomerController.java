package ted.wguc195.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.sql.SQLException;

public class AddCustomerController extends CustomerController {
    @FXML
    void onActionAddCustomer(ActionEvent event) {

    }

    public void initialize() throws SQLException {
        setCountryCB();
        setDivisionCBPromptText();
    }
}
