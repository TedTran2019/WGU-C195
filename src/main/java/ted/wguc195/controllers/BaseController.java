package ted.wguc195.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import ted.wguc195.SchedulingApplication;
import ted.wguc195.daos.UserDaoImpl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

/**
 * This class is the base class for all controllers.
 * It contains methods that are common to all controllers.
 */
public abstract class BaseController {
    protected Stage stage;
    protected Parent root;

    protected Scene scene;
    protected static ZoneId ESTzoneID = ZoneId.of("America/New_York");
    protected static ZoneId UTCzoneID = ZoneId.of("UTC");
    protected static ZoneId defaultZoneID = ZoneId.systemDefault();

    /**
     * This method loads the scene specified by sceneName.
     * @param event
     * @param sceneName The name (fxml filename) of the scene to load
     */
    @FXML
    protected void switchScene(ActionEvent event, String sceneName) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource(sceneName));
        stage.setScene(new Scene(root));
        SchedulingApplication.setLocation(sceneName);
        stage.show();
    }

    /**
     * This method loads the scene specified by sceneName (but for RadioButton events).
     * @param event
     * @param sceneName The name (fxml filename) of the scene to load
     */
    @FXML
    protected void switchSceneRadioButton(ActionEvent event, String sceneName) throws IOException {
        stage = (Stage)((RadioButton)event.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource(sceneName));
        stage.setScene(new Scene(root));
        SchedulingApplication.setLocation(sceneName);
        stage.show();
    }

    /**
     * This method is called when the user clicks on the 'Cancel' button.
     * It returns the user to the main screen.
     * @param event
     */
    @FXML
    protected void onActionMain(ActionEvent event) throws IOException {
        switchScene(event, "/views/Main.fxml");
    }

    /**
     * It displays an error box with the given title, header, and content.
     * @param title The title of the error box
     * @param header The header of the error box
     * @param content The content of the error box
     */
    protected void errorBox(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * It displays a confirmation box with the given title, header, and content.
     * @param title The title of the error box
     * @param header The header of the error box
     * @param content The content of the error box
     * @return true if the user clicks cancel, false otherwise
     */
    protected boolean confirmBox(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
        return alert.getResult() == ButtonType.CANCEL;
    }

    /**
     * It translates the given text into the language specified by the user.
     * @param text The text to translate given as a list of strings
     * @param rb The ResourceBundle containing the translations
     * @return translated text
     */
    protected ObservableList<String> translate(ObservableList<String> text, ResourceBundle rb) {
        ObservableList<String> translatedText = FXCollections.observableArrayList();
        for (String str : text) {
            translatedText.add(rb.getString(str));
        }
        return translatedText;
    }

    /**
     * Converts a LocalDateTime to a ZonedDateTime in the EST time zone.
     */
    protected ZonedDateTime convertLocalToEST(LocalDateTime time) {
        return time.atZone(defaultZoneID).withZoneSameInstant(ESTzoneID);
    }

    /**
     * Converts a LocalDateTime to a ZonedDateTime in the UTC time zone.
     * This method was unnecessary due to MySQL Connector/J's automatic conversion of local time to UTC when inserting into the database.
     */
    protected ZonedDateTime convertLocalToUTC(LocalDateTime time) {
        return time.atZone(defaultZoneID).withZoneSameInstant(UTCzoneID);
    }
}
