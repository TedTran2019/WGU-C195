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
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SchedulingApplication.class.getResource("/views/LoginForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Scheduling Application");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Login Form: username, password, displays lang and zoneID.
     * Let's do a drop down or combo box for the language for easy testing + practice
     * Main form: Customers and Appointments (Can be added, updated, deleted) (Table views)
     * - All customer's appointments must be deleted b4 customer can be deleted
     * - Text fields collect: name, address, postal code, phone #
     * - IDs are auto-generated, states/provinces and country data are collected using combo boxes
     * - Country and state/provinces are prepopulated combo boxes, with first list filtering the second
     *
     * - Drop down menu OR combo box for contact name for appointment
     * - ID autogen, title, description, location, contact, type, start-date and time, end-date and time
     *
     * - User can view appointment schedules by month/week using tableview (tabs or radio buttons to pick month/week to filter by)
     * - Some way to enter date? (Calendar? Text field?)
     *
     * - Allow users to adjust appointment times.
     * - Times are stored in UTC, displayed in local time, and checked against EST business hours
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
        try {
            ResourceBundle rb = ResourceBundle.getBundle("bundles/lang", Locale.getDefault());
            if (Locale.getDefault().getLanguage().equals("fr")) {
                System.out.println(rb.getString("hello"));
            }
        } catch (Exception e) {
            System.out.println("Resource bundle not found, continuing with default English.");
        }
        JDBC.openConnection();
        launch();
        JDBC.closeConnection();
    }

    private static void testCountry() {
        CountryDaoImpl countryDao = new CountryDaoImpl();
        try {
            ObservableList<Country> countries = countryDao.getAllCountries();
            for (Country country : countries) {
                System.out.println(country.getCountry());
            }
            Country country = countryDao.getCountry(1);
            System.out.println(country.getCountry());
            country.setCountry("United States of America");
            countryDao.updateCountry(country);
            country = countryDao.getCountry(1);
            System.out.println(country.getCountry());
            country.setCountry("USA");
            countryDao.updateCountry(country);
            // Adding Japan
            // country.setCountry("Japan");
            // countryDao.addCountry(country);
            // Deleting Japan
            // countryDao.deleteCountry(4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
