package ted.wguc195.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ted.wguc195.daos.CountryDaoImpl;
import ted.wguc195.daos.DivisionDaoImpl;
import ted.wguc195.models.Country;
import ted.wguc195.models.Division;
import javafx.scene.control.ListCell;

import java.sql.SQLException;

public abstract class CustomerController extends BaseController {
    @FXML
    private ComboBox<Country> comboBoxCountry;

    @FXML
    private ComboBox<Division> comboBoxDivision;

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

    CountryDaoImpl countryDao = new CountryDaoImpl();
    DivisionDaoImpl divisionDao = new DivisionDaoImpl();

    @FXML
    void onActionCountryCB(ActionEvent event) throws SQLException {
        comboBoxDivision.setItems(divisionDao.getDivisionsFromCountryID(comboBoxCountry.getValue().getCountryID()));
        comboBoxDivision.setDisable(false);
    }

    void setCountryCB() throws SQLException {
        comboBoxCountry.setItems(countryDao.getAllCountries());
    }

    void setDivisionCBPromptText() {
        comboBoxDivision.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Division item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText("Select a division!");
                } else {
                    setText(item.getDivision());
                }
            }
        });
    }
}
