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
import java.io.IOException;
import java.util.ResourceBundle;

public abstract class BaseController {
    protected Stage stage;
    protected Parent root;

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
}
