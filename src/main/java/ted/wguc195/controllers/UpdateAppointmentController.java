package ted.wguc195.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ted.wguc195.SchedulingApplication;
import ted.wguc195.models.Appointment;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class UpdateAppointmentController extends AppointmentController {
    private Appointment appointment;
    @FXML
    void onActionUpdateAppointment(ActionEvent event) throws SQLException, IOException {
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
        if (!appointmentDao.getOverlappingAppointmentsMinusSelf(start, end, this.appointment.getAppointmentID()).isEmpty()) {
            errorBox("Date/Time Error", "Overlapping Appointments", "There is already an appointment scheduled during this time block");
            return;
        }
        int customer = comboBoxCustomer.getValue().getCustomerID();
        int user = comboBoxUser.getValue().getUserID();
        int contact = comboBoxContact.getValue().getContactID();
        Appointment appointment = new Appointment(this.appointment.getAppointmentID(), title, description, location, type, start, end,
                this.appointment.getCreateDate(), this.appointment.getCreatedBy(), LocalDateTime.now(), SchedulingApplication.getUser(),
                customer, user, contact);
        appointmentDao.updateAppointment(appointment);
        switchScene(event, "/views/Main.fxml");
    }

    public void setAppointment(Appointment appointment) throws SQLException {
        this.appointment = appointment;
        textFieldAppointmentID.setText(Integer.toString(appointment.getAppointmentID()));
        textFieldTitle.setText(appointment.getTitle());
        textFieldDescription.setText(appointment.getDescription());
        textFieldLocation.setText(appointment.getLocation());
        textFieldType.setText(appointment.getType());
        datePickerStart.setValue(appointment.getStart().toLocalDate());
        datePickerEnd.setValue(appointment.getEnd().toLocalDate());
        setComboBoxes();
        setSpinners();
        spinnerStartHours.getValueFactory().setValue(appointment.getStart().getHour());
        spinnerStartMinutes.getValueFactory().setValue(appointment.getStart().getMinute());
        spinnerEndHours.getValueFactory().setValue(appointment.getEnd().getHour());
        spinnerEndMinutes.getValueFactory().setValue(appointment.getEnd().getMinute());
        comboBoxCustomer.setValue(customerDao.getCustomer(appointment.getCustomerID()));
        comboBoxUser.setValue(userDao.getUser(appointment.getUserID()));
        comboBoxContact.setValue(contactDao.getContact(appointment.getContactID()));
    }
}
