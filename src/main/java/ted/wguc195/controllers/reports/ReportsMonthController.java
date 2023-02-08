package ted.wguc195.controllers.reports;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import ted.wguc195.controllers.ReportsController;
import ted.wguc195.models.Appointment;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * Controller for the Reports Month view.
 */
public class ReportsMonthController extends ReportsController {
    @FXML
    private ComboBox<String> comboBoxReports;
    @FXML
    private TableView<Appointment> tableViewReports;
    private HashMap<String, Integer> monthMap = new HashMap<>();
    private ObservableList<String> months = FXCollections.observableArrayList();

    /**
     * When a month from the combo box is selected, the table view is updated with the selected month's appointments.
     * @param event
     */
    @FXML
    void onActionComboBox(ActionEvent event) throws SQLException {
        ObservableList<Appointment> appointments = appointmentDao.getAllAppointmentsByMonth(monthMap.get(comboBoxReports.getValue()));
        tableViewReports.setItems(appointments);
        labelReportsCounter.setText(comboBoxReports.getValue() + " Appointments: " + appointments.size());
    }

    public void initialize() throws SQLException {
        fillMonthMap();
        fillMonths();
        radioButtonMonth.setSelected(true);
        comboBoxReports.setItems(months);
        comboBoxReports.setPromptText("Select Month");
        appointmentsInitialize();
    }

    /**
     * Fills the monthMap with the month name and the corresponding month number.
     */
    private void fillMonthMap() {
        monthMap.put("January", 1);
        monthMap.put("February", 2);
        monthMap.put("March", 3);
        monthMap.put("April", 4);
        monthMap.put("May", 5);
        monthMap.put("June", 6);
        monthMap.put("July", 7);
        monthMap.put("August", 8);
        monthMap.put("September", 9);
        monthMap.put("October", 10);
        monthMap.put("November", 11);
        monthMap.put("December", 12);
    }

    /**
     * Fills the months ObservableList with the month names.
     * I did this rather than using the monthMap.keySet() because I wanted the months to be in order.
     */
    private void fillMonths() {
        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");
    }
}
