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
     * - Times are stored in UTC, displayed in local time, and checked against EST business hours
     * For MySqlConnector 8, times are stored as UTC.
     * When times are entered into the database, they're converted to UTC OR server time depending on what time_zone is set.
     * However, when times are taken out of the database, they're converted to local time.
     * Does that mean the connector already did the conversion, so there's no need to do it again w/ LocalDateTime?
     * It simply doesn't matter. All calculations can be done in LocalDateTime, and the database will handle the conversion.
     *
     *  - Implement input validation/logical error checks as specified in part D of the rubric
     *  - Implement alert when there's an appointment within 15 mins of a user's login (15 mins within local time)
     *
     *  Provide reports on the following:
     *  - total # of customer appointments by type and month
     *  - schedule for each contact in organization (sort by earliest to latest)
     *  - Additional report based on user login date and time stamp from login attempts
     *
     *  - Two lambdas in code
     *  - Track user activity by recording all login attempts (along w/ date and timestamps + success) to a text file
     *  - Javadocs
     *  - README.txt (not md?) with a bunch of information
     * */
    public static void main(String[] args) {
        Locale french = new Locale("fr", "FR");
        Locale.setDefault(french);
        JDBC.openConnection();
        launch();
        JDBC.closeConnection();
    }
}
