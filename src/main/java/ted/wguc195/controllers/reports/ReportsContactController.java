package ted.wguc195.controllers.reports;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ted.wguc195.controllers.ReportsController;
import ted.wguc195.daos.AppointmentDaoImpl;
import ted.wguc195.daos.ContactDaoImpl;
import ted.wguc195.models.Appointment;
import ted.wguc195.models.Contact;

import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * Controller for the Reports Contact screen.
 * */
public class ReportsContactController extends ReportsController {
    @FXML
    private ComboBox<Contact> comboBoxReports;
    @FXML
    private TableView<Appointment> tableViewReports;

    /**
     * When a contact from the combo box is selected, the table view is updated with the selected contact's appointments.
     * @param event
     */
    @FXML
    void onActionComboBox(ActionEvent event) throws SQLException {
        ObservableList<Appointment> appointments = appointmentDao.getAllAppointmentsByContactID(comboBoxReports.getValue().getContactID());
        tableViewReports.setItems(appointments);
        labelReportsCounter.setText(comboBoxReports.getValue().getContactName() + " Appointments: " + appointments.size());
    }

    public void initialize() throws SQLException {
        radioButtonContact.setSelected(true);
        comboBoxReports.setItems(contactDao.getAllContacts());
        comboBoxReports.setPromptText("Select Contact");
        showExpiredAppointments();
        appointmentsInitialize();
    }

    /**
     * LAMBDA USED HERE. I used it to modify the table view so that appointments that have already passed are highlighted in red.
     * This makes it so that appointments that have already passed are highlighted in red.
     */
    private void showExpiredAppointments() {
        tableViewReports.setRowFactory(tableView -> new TableRow<Appointment>() {
            @Override
            protected void updateItem(Appointment appointment, boolean empty) {
                super.updateItem(appointment, empty);
                if (appointment == null || appointment.getStart() == null)
                    setStyle("");
                else if (appointment.getStart().isBefore(LocalDateTime.now())) {
                    setStyle("-fx-background-color: red;");
                } else
                    setStyle("");
            }
        });
    }
}
