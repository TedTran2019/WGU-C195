package ted.wguc195.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public abstract class CustomerController extends BaseController {
    @FXML
    private ComboBox<?> comboBoxCountry;

    @FXML
    private ComboBox<?> comboBoxDivision;

    @FXML
    private TextArea errorAddress;

    @FXML
    private TextArea errorName;

    @FXML
    private TextArea errorPhone;

    @FXML
    private TextArea errorPostal;

    @FXML
    private Label labelDivision;

    @FXML
    private TextField textFieldAddress;

    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldPhone;

    @FXML
    private TextField textFieldPostal;

    @FXML
    void onActionCountryCB(ActionEvent event) {

    }
}
