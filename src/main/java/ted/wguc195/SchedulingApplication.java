package ted.wguc195;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ted.wguc195.utils.JDBC;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class SchedulingApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SchedulingApplication.class.getResource("/views/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Locale french = new Locale("fr", "FR");
        Locale.setDefault(french);
        try {
            ResourceBundle rb = ResourceBundle.getBundle("bundles/lang", Locale.getDefault());
            if (Locale.getDefault().getLanguage().equals("en")) {
                System.out.println(rb.getString("hello"));
            }
        } catch (Exception e) {
            System.out.println("Resource bundle not found, continuing with default English.");
        }
        JDBC.openConnection();
        launch();
        JDBC.closeConnection();
    }
}