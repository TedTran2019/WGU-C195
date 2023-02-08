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

/**
 * Controller for the Main view.
 */
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

    /**
     * Switches the scene to the AddAppointment view.
     * @param event
     */
    @FXML
    void onActionAddAppointment(ActionEvent event) throws IOException {
        switchScene(event, "/views/AddAppointment.fxml");
    }

    /**
     * Switches the scene to the AddCustomer view.
     * @param event
     */
    @FXML
    void onActionAddCustomer(ActionEvent event) throws IOException {
        switchScene(event, "/views/AddCustomer.fxml");
    }

    /**
     * Deletes the selected appointment.
     * @param event
     */
    @FXML
    void onActionDeleteAppointment(ActionEvent event) {
        if (appointmentsTableView.getSelectionModel().getSelectedItem() == null) {
            errorBox("Appointment not selected", "Please select an appointment to delete", "Please try again!");
            return;
        }
        Appointment appointment = appointmentsTableView.getSelectionModel().getSelectedItem();
        if (confirmBox("Cancel Appointment", "Are you sure you want to cancel " + appointment.getTitle() + "?",
                "This action cannot be undone.")) {
            return;
        }
        try {
            appointmentDao.deleteAppointment(appointment.getAppointmentID());
            appointmentsTableView.setItems(appointmentDao.getAllAppointments());
            errorBox("Appointment cancelled", "Appointment " + appointment.getTitle() + " has been cancelled.",
                    "Appointment ID: " + appointment.getAppointmentID() + " Type: " + appointment.getType() + " has been cancelled.");
        } catch (SQLException e) {
            errorBox("Database error", "Could not delete appointment", "Please try again.");
        }
    }

    /**
     * Deletes the selected customer.
     * @param event
     */
    @FXML
    void onActionDeleteCustomer(ActionEvent event) {
        if (customersTableView.getSelectionModel().getSelectedItem() == null) {
            errorBox("Customer not selected", "Please select a customer to delete", "Please try again!");
            return;
        }
        Customer customer = customersTableView.getSelectionModel().getSelectedItem();
        if (confirmBox("Delete Customer", "Are you sure you want to delete " + customer.getCustomerName() + "?",
                "This action will also delete all appointments associated with this customer. This action cannot be undone.")) {
            return;
        }
        try {
            customerDao.deleteCustomer(customer.getCustomerID());
            customersTableView.setItems(customerDao.getAllCustomers());
            appointmentsTableView.setItems(appointmentDao.getAllAppointments());
            errorBox("Customer deleted", "Customer " + customer.getCustomerName() + " has been deleted.",
                    "All associated appointments have also been removed.");
        } catch (SQLException e) {
            errorBox("Database error", "Could not delete customer", "Please try again.");
        }
    }

    /**
     * If a date has been selected, enables the week and month radio buttons.
     * @param event
     */
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

    /**
     * Sets the appointments table view to show all appointments.
     * @param event
     */
    @FXML
    void onActionFilterAll(ActionEvent event) throws SQLException {
        atvFilterDate.setValue(null);
        appointmentsTableView.setItems(appointmentDao.getAllAppointments());
    }

    /**
     * Sets the appointments table view to show appointments for the selected month.
     * @param event
     */
    @FXML
    void onActionFilterMonth(ActionEvent event) throws SQLException {
        if (atvFilterDate.getValue() == null) {
            errorBox("Date error", "No date selected", "Please select a date to filter by.");
            return;
        }
        appointmentsTableView.setItems(appointmentDao.getAppointmentsByMonth(atvFilterDate.getValue()));
    }

    /**
     * Sets the appointments table view to show appointments for the selected week.
     * @param event
     */
    @FXML
    void onActionFilterWeek(ActionEvent event) throws SQLException {
        if (atvFilterDate.getValue() == null) {
            errorBox("Date error", "No date selected", "Please select a date to filter by.");
            return;
        }
        appointmentsTableView.setItems(appointmentDao.getAppointmentsByWeek(atvFilterDate.getValue()));
    }

    /**
     * Switches the scene to the LoginForm view aka logouts the user.
     * @param event
     */
    @FXML
    void onActionLogout(ActionEvent event) throws IOException {
        Locale userLocale = Locale.getDefault();
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/views/LoginForm.fxml"), ResourceBundle.getBundle("bundles/lang", userLocale));
        stage.setScene(new Scene(root));
        SchedulingApplication.setUser(null);
        SchedulingApplication.setLocation("/views/LoginForm.fxml");
        stage.show();
    }

    /**
     * Switches the scene to the Reports view.
     * @param event
     */
    @FXML
    void onActionReports(ActionEvent event) throws IOException {
        switchScene(event, "/views/Reports.fxml");
    }

    /**
     * Switches the scene to the UpdateAppointment view.
     * @param event
     */
    @FXML
    void onActionUpdateAppointment(ActionEvent event) throws IOException, SQLException {
        if (appointmentsTableView.getSelectionModel().getSelectedItem() == null) {
            errorBox("Appointment not selected", "Please select an appointment to update", "Please try again!");
            return;
        }
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/UpdateAppointment.fxml"));
        loader.load();
        UpdateAppointmentController updateAppointmentController = loader.getController();
        updateAppointmentController.setAppointment(appointmentsTableView.getSelectionModel().getSelectedItem());
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        root = loader.getRoot();
        stage.setScene(new Scene(root));
        SchedulingApplication.setLocation("/views/UpdateAppointment.fxml");
        stage.show();
    }

    /**
     * Switches the scene to the UpdateCustomer view.
     * @param event
     */
    @FXML
    void onActionUpdateCustomer(ActionEvent event) throws IOException, SQLException {
        if (customersTableView.getSelectionModel().getSelectedItem() == null) {
            errorBox("Customer not selected", "Please select a customer to update", "Please try again!");
            return;
        }
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/UpdateCustomer.fxml"));
        loader.load();
        UpdateCustomerController updateCustomerController = loader.getController();
        updateCustomerController.setCustomer(customersTableView.getSelectionModel().getSelectedItem());
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        root = loader.getRoot();
        stage.setScene(new Scene(root));
        SchedulingApplication.setLocation("/views/UpdateCustomer.fxml");
        stage.show();
    }

    /**
     * Sets up the customers TableView
     */
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

    /**
     * Sets up the appointments TableView
     */
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
