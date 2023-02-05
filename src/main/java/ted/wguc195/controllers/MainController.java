package ted.wguc195.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ted.wguc195.SchedulingApplication;
import ted.wguc195.daos.AppointmentDaoImpl;
import ted.wguc195.daos.CustomerDaoImpl;
import ted.wguc195.models.Appointment;
import ted.wguc195.models.Customer;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainController extends BaseController {

    @FXML
    private ToggleGroup appointmentFilter;

    @FXML
    private TableView<Appointment> appointmentsTableView;

    @FXML
    private TableColumn<Appointment, String> atvContact;

    @FXML
    private TableColumn<Appointment, Integer> atvCustomerID;

    @FXML
    private TableColumn<Appointment, String> atvDescription;

    @FXML
    private TableColumn<Appointment, LocalDateTime> atvEnd;

    @FXML
    private DatePicker atvFilterDate;

    @FXML
    private TableColumn<Appointment, Integer> atvID;

    @FXML
    private TableColumn<Appointment, String> atvLocation;

    @FXML
    private TableColumn<Appointment, LocalDateTime> atvStart;

    @FXML
    private TableColumn<Appointment, String> atvTitle;

    @FXML
    private TableColumn<Appointment, String> atvType;

    @FXML
    private TableColumn<Appointment, Integer> atvUserID;

    @FXML
    private TableColumn<Customer, String> ctvAddress;

    @FXML
    private TableColumn<Customer, Integer> ctvID;

    @FXML
    private TableColumn<Customer, String> ctvName;

    @FXML
    private TableColumn<Customer, String> ctvPhone;

    @FXML
    private TableColumn<Customer, String> ctvPostal;

    @FXML
    private TableColumn<Customer, String> ctvStateOrProvince;

    @FXML
    private TableView<Customer> customersTableView;

    @FXML
    private RadioButton rbMonth;

    @FXML
    private RadioButton rbWeek;

    private CustomerDaoImpl customerDao = new CustomerDaoImpl();
    private AppointmentDaoImpl appointmentDao = new AppointmentDaoImpl();

    @FXML
    void onActionAddAppointment(ActionEvent event) throws IOException {
        switchScene(event, "/views/AddAppointment.fxml");
    }

    @FXML
    void onActionAddCustomer(ActionEvent event) throws IOException {
        switchScene(event, "/views/AddCustomer.fxml");
    }

    @FXML
    void onActionDeleteAppointment(ActionEvent event) {

    }

    @FXML
    void onActionDeleteCustomer(ActionEvent event) {

    }

    @FXML
    void onActionATVFilterDate(ActionEvent event) {
        if (atvFilterDate.getValue() == null) {
            rbWeek.setDisable(true);
            rbMonth.setDisable(true);
        } else {
            rbWeek.setDisable(false);
            rbMonth.setDisable(false);
        }
    }

    @FXML
    void onActionFilterAll(ActionEvent event) throws SQLException {
        atvFilterDate.setValue(null);
        appointmentsTableView.setItems(appointmentDao.getAllAppointments());
    }

    @FXML
    void onActionFilterMonth(ActionEvent event) throws SQLException {
        if (atvFilterDate.getValue() == null) {
            errorBox("Date error", "No date selected", "Please select a date to filter by.");
            return;
        }
        appointmentsTableView.setItems(appointmentDao.getAppointmentsByMonth(atvFilterDate.getValue()));
    }

    @FXML
    void onActionFilterWeek(ActionEvent event) throws SQLException {
        if (atvFilterDate.getValue() == null) {
            errorBox("Date error", "No date selected", "Please select a date to filter by.");
            return;
        }
        appointmentsTableView.setItems(appointmentDao.getAppointmentsByWeek(atvFilterDate.getValue()));
    }

    @FXML
    void onActionLogout(ActionEvent event) throws IOException {
        Locale userLocale = Locale.getDefault();
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/views/LoginForm.fxml"), ResourceBundle.getBundle("bundles/lang", userLocale));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void onActionReports(ActionEvent event) throws IOException {
        switchScene(event, "/views/Reports.fxml");
    }

    @FXML
    void onActionUpdateAppointment(ActionEvent event) {

    }

    @FXML
    void onActionUpdateCustomer(ActionEvent event) {

    }

    private void setCustomersTableView() throws SQLException {
        customersTableView.setItems(customerDao.getAllCustomers());
        ctvID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        ctvName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        ctvAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        ctvPostal.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        ctvPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        ctvStateOrProvince.setCellValueFactory(cellData -> {
            try {
                return new SimpleStringProperty(cellData.getValue().getDivisionName());
            } catch (SQLException | RuntimeException e) {
                errorBox("SQL error", "Invalid foreign key for customer", "There was an error retrieving the state/province name.");
            }
            System.exit(0);
            return null;
        });
    }

    private void setAppointmentsTableView() throws SQLException {
        appointmentsTableView.setItems(appointmentDao.getAllAppointments());
        atvID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        atvTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        atvDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        atvLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        atvType.setCellValueFactory(new PropertyValueFactory<>("type"));
        atvStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        atvEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
        atvCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        atvUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));
        atvContact.setCellValueFactory(cellData -> {
            try {
                return new SimpleStringProperty(cellData.getValue().getContactName());
            } catch (SQLException | RuntimeException e) {
                errorBox("SQL error", "Invalid foreign key for appointment", "There was an error retrieving the contact name.");
            }
            System.exit(0);
            return null;
        });
    }

    public void initialize() throws SQLException {
        setCustomersTableView();
        setAppointmentsTableView();
    }
}
