package ted.wguc195.controllers.reports;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ted.wguc195.controllers.ReportsController;
import ted.wguc195.daos.AppointmentDaoImpl;
import ted.wguc195.daos.ContactDaoImpl;
import ted.wguc195.models.Appointment;
import ted.wguc195.models.Contact;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class ReportsContactController extends ReportsController {
    @FXML
    private ComboBox<Contact> comboBoxReports;
    @FXML
    private TableView<Appointment> tableViewReports;
    @FXML
    private TableColumn<Appointment, Integer> colCustomerID;

    @FXML
    private TableColumn<Appointment, String> colDescription;

    @FXML
    private TableColumn<Appointment, LocalDateTime> colEnd;

    @FXML
    private TableColumn<Appointment, Integer> colID;

    @FXML
    private TableColumn<Appointment, LocalDateTime> colStart;

    @FXML
    private TableColumn<Appointment, String> colTitle;

    @FXML
    private TableColumn<Appointment, String> colType;

    private ContactDaoImpl contactDao = new ContactDaoImpl();
    private AppointmentDaoImpl appointmentDao = new AppointmentDaoImpl();

    @FXML
    void onActionComboBox(ActionEvent event) {

    }

    @Override
    public void initialize() throws SQLException {
        radioButtonContact.setSelected(true);
        comboBoxReports.setItems(contactDao.getAllContacts());
        comboBoxReports.setPromptText("Select Contact");
        setReportsAppointmentsTable();
        labelReportsCounter.setText("Total Appointments: " + appointmentDao.getAllAppointments().size());
    }

    private void setReportsAppointmentsTable() throws SQLException {
        tableViewReports.setItems(appointmentDao.getAllAppointments());
        colID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        colEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
    }
}
