package ted.wguc195;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
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
        stage.show();
    }

    /**
     *  Provide reports on the following:
     *  - Additional report based on user login date and time stamp from login attempts
     *
     *  - Two lambdas in code
     *  - Track user activity by recording all login attempts (along w/ date and timestamps + success) to a text file
     *  - Javadocs
     *  - README.txt (not md?) with a bunch of information
     * */
    public static void main(String[] args) {
        JDBC.openConnection();
        launch();
        JDBC.closeConnection();
    }
}
