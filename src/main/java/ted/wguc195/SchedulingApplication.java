package ted.wguc195;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ted.wguc195.daos.CountryDaoImpl;
import ted.wguc195.daos.UserDaoImpl;
import ted.wguc195.models.Country;
import ted.wguc195.models.User;
import ted.wguc195.utils.JDBC;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
public class SchedulingApplication extends Application {
    public static String user = null;
    public static String location = null;

    public static String getLocation() {
        return location;
    }

    public static void setLocation(String location) {
        SchedulingApplication.location = location;
    }

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        SchedulingApplication.user = user;
    }

    @Override
    public void start(Stage stage) throws IOException {
        Locale userLocale = Locale.getDefault();
        FXMLLoader fxmlLoader = new FXMLLoader(SchedulingApplication.class.getResource("/views/LoginForm.fxml"), ResourceBundle.getBundle("bundles/lang", userLocale));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Scheduling Application");
        stage.setScene(scene);
        setLocation("/views/LoginForm.fxml");
        setXHandler(stage);
        stage.show();
    }

    private void setXHandler(Stage stage) {
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Parent root = null;
                try {
                    String targetLocation = null;
                    if (getLocation().equals("/views/LoginForm.fxml")) {
                        System.exit(0);
                    } else if (getLocation().equals("/views/Main.fxml")) {
                        targetLocation = "/views/LoginForm.fxml";
                        setUser(null);
                    } else {
                        targetLocation = "/views/Main.fxml";
                    }
                    event.consume();
                    root = FXMLLoader.load(getClass().getResource(targetLocation), ResourceBundle.getBundle("bundles/lang", Locale.getDefault()));
                    Scene newScene = new Scene(root);
                    setLocation(targetLocation);
                    stage.setScene(newScene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        JDBC.openConnection();
        launch();
        JDBC.closeConnection();
    }
}
