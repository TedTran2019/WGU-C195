package ted.wguc195.controllers.reports;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import ted.wguc195.controllers.ReportsController;
import ted.wguc195.models.Appointment;

import java.sql.SQLException;

public class ReportsTypeController extends ReportsController {
    @FXML
    private ComboBox<String> comboBoxReports;

    @FXML
    private TableView<Appointment> tableViewReports;

    @FXML
    void onActionComboBox(ActionEvent event) throws SQLException {
        ObservableList<Appointment> appointments = appointmentDao.getAllAppointmentsByType(comboBoxReports.getValue());
        tableViewReports.setItems(appointments);
        labelReportsCounter.setText(comboBoxReports.getValue() + " Appointments: " + appointments.size());
    }

    public void initialize() throws SQLException {
        radioButtonType.setSelected(true);
        comboBoxReports.setItems(appointmentDao.getAllAppointmentTypes());
        comboBoxReports.setPromptText("Select Type");
        appointmentsInitialize();
    }
}
