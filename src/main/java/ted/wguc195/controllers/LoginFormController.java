package ted.wguc195.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ted.wguc195.daos.UserDaoImpl;
import ted.wguc195.models.User;

import java.sql.SQLException;
import java.time.ZoneId;

public class LoginFormController extends BaseController {
    @FXML
    private ComboBox<?> languageComboBox;
    @FXML
    private PasswordField password;
    @FXML
    private TextField userName;
    @FXML
    private Label zoneID;

    @FXML
    private UserDaoImpl userDao = new UserDaoImpl();

    @FXML
    void onActionLogin(ActionEvent event) throws SQLException {
        String userName = this.userName.getText();
        String password = this.password.getText();
    }

    @FXML
    public void initialize() {
        zoneID.setText(ZoneId.systemDefault().toString());
    }
}

