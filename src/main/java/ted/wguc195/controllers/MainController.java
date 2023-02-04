package ted.wguc195.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import ted.wguc195.SchedulingApplication;
import ted.wguc195.models.Appointment;
import ted.wguc195.models.Customer;
import java.io.IOException;
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
    void onActionFilterAll(ActionEvent event) {

    }

    @FXML
    void onActionFilterMonth(ActionEvent event) {

    }

    @FXML
    void onActionFilterWeek(ActionEvent event) {

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
    void onActionReports(ActionEvent event) {

    }

    @FXML
    void onActionUpdateCustomer(ActionEvent event) {

    }

    @FXML
    public void initialize() {

    }
}
