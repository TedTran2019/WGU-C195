package ted.wguc195.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;

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
    private ComboBox<?> comboBoxReports;

    @FXML
    private TableView<?> tableViewReports;

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
}
