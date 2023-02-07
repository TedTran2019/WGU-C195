package ted.wguc195.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;

public class ReportsController extends BaseController {
    @FXML
    protected ToggleGroup toggleReports;

    @FXML
    protected Label labelReportsCounter;

    @FXML
    private ComboBox<?> comboBoxReports;

    @FXML
    private TableView<?> tableViewReports;

    @FXML
    protected void onActionContactSchedule(ActionEvent event) throws IOException {
        switchScene(event, "/views/reports/ReportsContact.fxml");
    }

    @FXML
    protected void onActionCustAppMonth(ActionEvent event) throws IOException {
        switchScene(event, "/views/reports/ReportsMonth.fxml");
    }

    @FXML
    protected void onActionCustAppType(ActionEvent event) throws IOException {
        switchScene(event, "/views/reports/ReportsType.fxml");
    }

    @FXML
    protected void onActionUserLogins(ActionEvent event) throws IOException {
        switchScene(event, "/views/reports/ReportsUser.fxml");
    }

    public void initialize() {
        comboBoxReports.setVisible(false);
        labelReportsCounter.setVisible(false);
    }
}
