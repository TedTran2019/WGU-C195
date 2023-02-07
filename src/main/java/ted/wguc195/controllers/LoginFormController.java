package ted.wguc195.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import ted.wguc195.SchedulingApplication;
import ted.wguc195.daos.AppointmentDaoImpl;
import ted.wguc195.daos.UserDaoImpl;
import ted.wguc195.models.User;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginFormController extends BaseController {
    @FXML
    private ComboBox<String> languageComboBox;
    @FXML
    private PasswordField password;
    @FXML
    private TextField userName;
    @FXML
    private Label zoneID;
    private UserDaoImpl userDao = new UserDaoImpl();

    private AppointmentDaoImpl appointmentDao = new AppointmentDaoImpl();
    private Locale userLocale = Locale.getDefault();

    @FXML
    void onActionLogin(ActionEvent event) throws SQLException, IOException {
        String userName = this.userName.getText();
        String password = this.password.getText();
        User user = userDao.getUser(userName);
        if (user == null) {
            if (userLocale.getLanguage().equals("fr")) {
                errorBox("Nom d'utilisateur invalide", "Utilisateur non trouvé", "Merci d'entrer un nom d'utilisateur valide");
            }
            else {
                errorBox("Invalid username", "User not found", "Please enter a valid username");
            }
            return;
        }
        if (!user.getPassword().equals(password)) {
            if (userLocale.getLanguage().equals("fr")) {
                errorBox("Mot de passe incorrect", "Mot de passe incorrect", "Entrer un mot de passe valide s'il vous plait");
            }
            else {
                errorBox("Invalid password", "Password incorrect", "Please enter a valid password");
            }
            return;
        }
        SchedulingApplication.setUser(user.getUserName());
        switchScene(event, "/views/Main.fxml");
        if (appointmentDao.getAppointmentsWithin15Minutes(LocalDateTime.now()).isEmpty()) {
            confirmBox("Relax", "No appointment within 15 minutes", "You can relax!");
        } else {
            confirmBox("Alert!", "Appointment within 15 minutes", "Chop chop, you have an appointment coming up!");
        }
    }

    @FXML
    void languageComboBoxAction(ActionEvent event) throws IOException {
        String language = languageComboBox.getValue();
        Locale newLocale;
        if (language.equals("French") || language.equals("Français")) {
            newLocale = new Locale("fr", "FR");
            Locale.setDefault(newLocale);
        } else {
            newLocale = new Locale("en", "US");
            Locale.setDefault(newLocale);
        }
        stage = (Stage)((ComboBox)event.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/views/LoginForm.fxml"), ResourceBundle.getBundle("bundles/lang", newLocale));
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void setupComboBox() {
        if (userLocale.getLanguage().equals("fr")) {
            languageComboBox.setValue("Français");
            languageComboBox.setItems(FXCollections.observableArrayList("Anglais", "Français"));
        } else {
            languageComboBox.setItems(FXCollections.observableArrayList("English", "French"));
            languageComboBox.setValue("English");
        }
    }

    public void initialize() {
        zoneID.setText(ZoneId.systemDefault().toString());
        setupComboBox();
    }
}
