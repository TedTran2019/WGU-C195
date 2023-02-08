package ted.wguc195.controllers.reports;
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

public class ReportsUserController extends ReportsController {
    @FXML
    private ComboBox<String> comboBoxReports;
    @FXML
    private TableView<UserLog> tableViewReports;
    @FXML
    private TableColumn<?, ?> colAttemptDate;

    @FXML
    private TableColumn<?, ?> colFailureReason;

    @FXML
    private TableColumn<?, ?> colLoginSuccess;

    @FXML
    private TableColumn<?, ?> colUserName;

    private UserDaoImpl userDao = new UserDaoImpl();

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

    private void setReportsLogTable() {
        colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colAttemptDate.setCellValueFactory(new PropertyValueFactory<>("attemptDate"));
        colLoginSuccess.setCellValueFactory(new PropertyValueFactory<>("loginSuccess"));
        colFailureReason.setCellValueFactory(new PropertyValueFactory<>("failureReason"));
    }
}
