package ted.wguc195.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ted.wguc195.daos.AppointmentDaoImpl;
import ted.wguc195.daos.ContactDaoImpl;
import ted.wguc195.models.Appointment;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ReportsController extends BaseController {
    @FXML
    protected ToggleGroup toggleReports;
    @FXML
    protected Label labelReportsCounter;
    @FXML
    protected RadioButton radioButtonContact;

    @FXML
    protected RadioButton radioButtonMonth;

    @FXML
    protected RadioButton radioButtonType;

    @FXML
    protected RadioButton radioButtonUser;

    @FXML
    protected TableColumn<Appointment, Integer> colCustomerID;

    @FXML
    protected TableColumn<Appointment, String> colDescription;

    @FXML
    protected TableColumn<Appointment, LocalDateTime> colEnd;

    @FXML
    protected TableColumn<Appointment, Integer> colID;

    @FXML
    protected TableColumn<Appointment, LocalDateTime> colStart;

    @FXML
    protected TableColumn<Appointment, String> colTitle;

    @FXML
    protected TableColumn<Appointment, String> colType;

    @FXML
    private ComboBox<?> comboBoxReports;
    @FXML
    private TableView<Appointment> tableViewReports;
    protected ContactDaoImpl contactDao = new ContactDaoImpl();
    protected AppointmentDaoImpl appointmentDao = new AppointmentDaoImpl();

    @FXML
    private void onActionComboBox(ActionEvent event) {
    }

    @FXML
    protected void onActionContactSchedule(ActionEvent event) throws IOException {
        switchSceneRadioButton(event, "/views/reports/ReportsContact.fxml");
    }

    @FXML
    protected void onActionCustAppMonth(ActionEvent event) throws IOException {
        switchSceneRadioButton(event, "/views/reports/ReportsMonth.fxml");
    }

    @FXML
    protected void onActionCustAppType(ActionEvent event) throws IOException {
        switchSceneRadioButton(event, "/views/reports/ReportsType.fxml");
    }

    @FXML
    protected void onActionUserLogins(ActionEvent event) throws IOException {
        switchSceneRadioButton(event, "/views/reports/ReportsUser.fxml");
    }

    public void initialize() throws SQLException {
        comboBoxReports.setVisible(false);
        labelReportsCounter.setVisible(false);
    }

    protected void appointmentsInitialize() throws SQLException {
        setReportsAppointmentsTable();
        ObservableList<Appointment> appointments = appointmentDao.getAllAppointmentsSortedByDate();
        tableViewReports.setItems(appointments);
        labelReportsCounter.setText("Total Appointments: " + appointments.size());
    }

    private void setReportsAppointmentsTable() throws SQLException {
        colID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        colEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
    }
}
