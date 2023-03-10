package ted.wguc195.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ted.wguc195.SchedulingApplication;
import ted.wguc195.models.Appointment;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 * Controller for the Add Appointment view.
 */
public class AddAppointmentController  extends AppointmentController{
    /**
     * Adds an appointment to the database.
     * @param event The event that triggered this method.
     * @throws SQLException If there is an error with the database.
     * @throws IOException If there is an error with the file system.
     */
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
        if (!validateTimes(start, end)) {
            return;
        }
        int customer = comboBoxCustomer.getValue().getCustomerID();
        int user = comboBoxUser.getValue().getUserID();
        int contact = comboBoxContact.getValue().getContactID();
        if (!appointmentDao.getOverlappingAppointments(start, end, customer).isEmpty()) {
            errorBox("Date/Time Error", "Overlapping Appointments", "There is already an appointment scheduled during this time block");
            return;
        }
        Appointment appointment = new Appointment(1337, title, description, location, type, start, end,
                LocalDateTime.now(), SchedulingApplication.getUser(), LocalDateTime.now(), SchedulingApplication.getUser(),
                customer, user, contact);
        appointmentDao.addAppointment(appointment);
        switchScene(event, "/views/Main.fxml");
    }

    public void initialize() throws SQLException {
        setComboBoxes();
        setSpinners();
    }
}
