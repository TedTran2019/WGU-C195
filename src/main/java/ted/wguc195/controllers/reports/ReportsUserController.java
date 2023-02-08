package ted.wguc195.controllers.reports;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ted.wguc195.controllers.ReportsController;
import ted.wguc195.daos.UserDaoImpl;
import ted.wguc195.models.UserLog;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Controller for the Reports User screen.
 */
public class ReportsUserController extends ReportsController {
    @FXML
    private ComboBox<String> comboBoxReports;
    @FXML
    private TableView<UserLog> tableViewReports;
    @FXML
    private TableColumn<UserLog, String> colAttemptDate;

    @FXML
    private TableColumn<UserLog, String> colFailureReason;

    @FXML
    private TableColumn<UserLog, Boolean> colLoginSuccess;

    @FXML
    private TableColumn<UserLog, String> colUserName;

    private UserDaoImpl userDao = new UserDaoImpl();

    /**
     * When a user from the combo box is selected, the table view is updated with the selected user's login attempts.
     * @param event
     */
    @FXML
    void onActionComboBox(ActionEvent event) {
        ObservableList<UserLog> userLogs = UserLog.getAllByUser(comboBoxReports.getValue());
        tableViewReports.setItems(userLogs);
        labelReportsCounter.setText(comboBoxReports.getValue() + " Login Attempts: " + userLogs.size());
    }

    public void initialize() throws SQLException {
        radioButtonUser.setSelected(true);
        comboBoxReports.setItems(userDao.getAllUserNames());
        comboBoxReports.setPromptText("Select User");
        setReportsLogTable();
        ObservableList<UserLog> userLogs = UserLog.getAll();
        tableViewReports.setItems(userLogs);
        labelReportsCounter.setText("Total User Login Attempts: " + userLogs.size());
    }

    /**
     * LAMBDA USED HERE. I used it to format the date column to display the date and time in a more readable format.
     * Sets the table view columns to the correct properties of the UserLog class.
     * Also formats the date column to display the date and time in a more readable format.
     */
    private void setReportsLogTable() {
        colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colLoginSuccess.setCellValueFactory(new PropertyValueFactory<>("loginSuccess"));
        colFailureReason.setCellValueFactory(new PropertyValueFactory<>("failureReason"));
        colAttemptDate.setCellValueFactory(cellData -> {
            LocalDateTime time = cellData.getValue().getAttemptDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy  |  HH:mm");
            return new SimpleStringProperty(time.format(formatter));
        });
    }
}
