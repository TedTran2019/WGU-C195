package ted.wguc195.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.sql.SQLException;

public class AddAppointmentController  extends AppointmentController{
    @FXML
    void onActionAddAppointment(ActionEvent event) {
        String title = textFieldTitle.getText();
        String description = textFieldDescription.getText();
        String location = textFieldLocation.getText();
        String type = textFieldType.getText();
        int contact = comboBoxContact.getValue().getContactID();

    }

    public void initialize() throws SQLException {
        setComboBoxes();
        setSpinners();
    }
}
