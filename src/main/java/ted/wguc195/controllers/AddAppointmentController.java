package ted.wguc195.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ted.wguc195.SchedulingApplication;
import ted.wguc195.models.Appointment;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AddAppointmentController  extends AppointmentController{
    @FXML
    void onActionAddAppointment(ActionEvent event) throws SQLException, IOException {
        String title = textFieldTitle.getText();
        String description = textFieldDescription.getText();
        String location = textFieldLocation.getText();
        String type = textFieldType.getText();
        if (!validateFields(title, description, location, type)) {
            return;
        }
        LocalDateTime start = getLocalDateTime(datePickerStart.getValue(), spinnerStartHours.getValue(), spinnerStartMinutes.getValue());
        LocalDateTime end = getLocalDateTime(datePickerEnd.getValue(), spinnerEndHours.getValue(), spinnerEndMinutes.getValue());
        int customer = comboBoxCustomer.getValue().getCustomerID();
        int user = comboBoxUser.getValue().getUserID();
        int contact = comboBoxContact.getValue().getContactID();
        Appointment appointment = new Appointment(1337, title, description, location, type, start, end,
                LocalDateTime.now(), SchedulingApplication.getUser(), LocalDateTime.now(), SchedulingApplication.getUser(),
                customer, user, contact);
        appointmentDao.addAppointment(appointment);
        switchScene(event, "/views/Main.fxml");
    }

    private LocalDateTime getLocalDateTime(LocalDate date, int hours, int minutes) {
        return date.atTime(hours, minutes);
    }

    public void initialize() throws SQLException {
        setComboBoxes();
        setSpinners();
    }
}
