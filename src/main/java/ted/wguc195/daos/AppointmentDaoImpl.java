package ted.wguc195.daos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ted.wguc195.models.Appointment;
import ted.wguc195.utils.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 * Implements the AppointmentDao interface.
 */
public class AppointmentDaoImpl implements AppointmentDao{
    /**
     * Gets all appointments from the database.
     * @return An ObservableList of all appointments.
     * @throws SQLException
     */
    @Override
    public ObservableList<Appointment> getAllAppointments() throws SQLException {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointments";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int appointmentID = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            Timestamp start = rs.getTimestamp("Start");
            Timestamp end = rs.getTimestamp("End");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");

            appointments.add(new Appointment(appointmentID, title, description, location, type, start.toLocalDateTime(),
                    end.toLocalDateTime(), createDate.toLocalDateTime(), createdBy, lastUpdate.toLocalDateTime(),
                    lastUpdatedBy, customerID, userID, contactID));
        }
        return appointments;
    }

    /**
     * Gets an appointment from the database.
     * @param appointmentID The ID of the appointment to get.
     * @return The appointment.
     * @throws SQLException
     */
    @Override
    public Appointment getAppointment(int appointmentID) throws SQLException {
        String sql = "SELECT * FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, appointmentID);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            Timestamp start = rs.getTimestamp("Start");
            Timestamp end = rs.getTimestamp("End");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");

            return new Appointment(appointmentID, title, description, location, type, start.toLocalDateTime(),
                    end.toLocalDateTime(), createDate.toLocalDateTime(), createdBy, lastUpdate.toLocalDateTime(),
                    lastUpdatedBy, customerID, userID, contactID);
        }
        return null;
    }

    /**
     * Updates an appointment in the database.
     * @param appointment The appointment to update.
     * @throws SQLException
     */
    @Override
    public void updateAppointment(Appointment appointment) throws SQLException {
        String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, " +
                "Last_Update = ?, Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, appointment.getTitle());
        ps.setString(2, appointment.getDescription());
        ps.setString(3, appointment.getLocation());
        ps.setString(4, appointment.getType());
        ps.setTimestamp(5, Timestamp.valueOf(appointment.getStart()));
        ps.setTimestamp(6, Timestamp.valueOf(appointment.getEnd()));
        ps.setTimestamp(7, Timestamp.valueOf(appointment.getLastUpdate()));
        ps.setString(8, appointment.getLastUpdatedBy());
        ps.setInt(9, appointment.getCustomerID());
        ps.setInt(10, appointment.getUserID());
        ps.setInt(11, appointment.getContactID());
        ps.setInt(12, appointment.getAppointmentID());
        ps.executeUpdate();
    }

    /**
     * Deletes an appointment from the database.
     * @param appointmentID The ID of the appointment to delete.
     * @throws SQLException
     */
    @Override
    public void deleteAppointment(int appointmentID) throws SQLException {
        String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, appointmentID);
        ps.executeUpdate();
    }

    /**
     * Adds an appointment to the database.
     * @param appointment The appointment to add.
     * @throws SQLException
     */
    @Override
    public void addAppointment(Appointment appointment) throws SQLException {
        String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Create_Date, " +
                "Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, appointment.getTitle());
        ps.setString(2, appointment.getDescription());
        ps.setString(3, appointment.getLocation());
        ps.setString(4, appointment.getType());
        ps.setTimestamp(5, Timestamp.valueOf(appointment.getStart()));
        ps.setTimestamp(6, Timestamp.valueOf(appointment.getEnd()));
        ps.setTimestamp(7, Timestamp.valueOf(appointment.getCreateDate()));
        ps.setString(8, appointment.getCreatedBy());
        ps.setTimestamp(9, Timestamp.valueOf(appointment.getLastUpdate()));
        ps.setString(10, appointment.getLastUpdatedBy());
        ps.setInt(11, appointment.getCustomerID());
        ps.setInt(12, appointment.getUserID());
        ps.setInt(13, appointment.getContactID());
        ps.executeUpdate();
    }

    /**
     * Gets all appointments from the database for a given month.
     * @param date The date to filter by.
     * @return A list of all appointments for a given month.
     * @throws SQLException
     */
    @Override
    public ObservableList<Appointment> getAppointmentsByMonth(LocalDate date) throws SQLException {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        Timestamp filterDate = Timestamp.valueOf(date.atStartOfDay());

        String sql = "SELECT * FROM appointments WHERE MONTH(Start) = MONTH(?) AND YEAR(Start) = YEAR(?)";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setTimestamp(1, filterDate);
        ps.setTimestamp(2, filterDate);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int appointmentID = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            Timestamp start = rs.getTimestamp("Start");
            Timestamp end = rs.getTimestamp("End");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");

            appointments.add(new Appointment(appointmentID, title, description, location, type, start.toLocalDateTime(),
                    end.toLocalDateTime(), createDate.toLocalDateTime(), createdBy, lastUpdate.toLocalDateTime(),
                    lastUpdatedBy, customerID, userID, contactID));
        }
        return appointments;
    }

    /**
     * Gets all appointments from the database for a given week.
     * @param date The date to filter by.
     * @return A list of all appointments for a given week.
     * @throws SQLException
     */
    @Override
    public ObservableList<Appointment> getAppointmentsByWeek(LocalDate date) throws SQLException {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        Timestamp filterDate = Timestamp.valueOf(date.atStartOfDay());

        String sql = "SELECT * FROM appointments WHERE WEEK(Start) = WEEK(?) AND YEAR(Start) = YEAR(?)";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setTimestamp(1, filterDate);
        ps.setTimestamp(2, filterDate);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int appointmentID = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            Timestamp start = rs.getTimestamp("Start");
            Timestamp end = rs.getTimestamp("End");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");

            appointments.add(new Appointment(appointmentID, title, description, location, type, start.toLocalDateTime(),
                    end.toLocalDateTime(), createDate.toLocalDateTime(), createdBy, lastUpdate.toLocalDateTime(),
                    lastUpdatedBy, customerID, userID, contactID));
        }
        return appointments;
    }

    /**
     * Deletes all appointments for a given customer.
     * @param customerID The customer ID to filter by.
     * @throws SQLException
     */
    @Override
    public void deleteAppointmentsByCustomerID(int customerID) throws SQLException {
        String sql = "DELETE FROM appointments WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, customerID);
        ps.executeUpdate();
    }

    /**
     * Gets all overlapping appointments
     * @param startUTC The start time to filter by.
     * @param endUTC The end time to filter by.
     * @param custID The customer ID to filter by.
     * @return An ObservableList of all overlapping appointments.
     * @throws SQLException
     */
    @Override
    public ObservableList<Appointment> getOverlappingAppointments(LocalDateTime startUTC, LocalDateTime endUTC, int custID) throws SQLException {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        String sql = "SELECT * FROM (SELECT * FROM appointments WHERE Customer_ID = ?) AS minus " +
                "WHERE (? >= Start AND ? < End) OR (? > Start AND ? <= End) OR (? <= Start AND ? >= End)";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, custID);
        ps.setTimestamp(2, Timestamp.valueOf(startUTC));
        ps.setTimestamp(3, Timestamp.valueOf(startUTC));
        ps.setTimestamp(4, Timestamp.valueOf(endUTC));
        ps.setTimestamp(5, Timestamp.valueOf(endUTC));
        ps.setTimestamp(6, Timestamp.valueOf(startUTC));
        ps.setTimestamp(7, Timestamp.valueOf(endUTC));
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int appointmentID = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            Timestamp start = rs.getTimestamp("Start");
            Timestamp end = rs.getTimestamp("End");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");

            appointments.add(new Appointment(appointmentID, title, description, location, type, start.toLocalDateTime(),
                    end.toLocalDateTime(), createDate.toLocalDateTime(), createdBy, lastUpdate.toLocalDateTime(),
                    lastUpdatedBy, customerID, userID, contactID));
        }
        return appointments;
    }

    /**
     * Gets all overlapping appointments minus the appointment with the given ID.
     * @param startUTC The start time to filter by.
     * @param endUTC The end time to filter by.
     * @param ID The appointment ID to filter by.
     * @param custID The customer ID to filter by.
     * @return An ObservableList of all overlapping appointments minus the appointment with the given ID.
     * @throws SQLException
     */
    @Override
    public ObservableList<Appointment> getOverlappingAppointmentsMinusSelf(LocalDateTime startUTC, LocalDateTime endUTC, int ID, int custID) throws SQLException {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        String sql = "SELECT * FROM (SELECT * FROM appointments WHERE Appointment_ID != ? AND Customer_ID = ?) AS minus " +
                "WHERE (? >= Start AND ? < End) OR (? > Start AND ? <= End) OR (? <= Start AND ? >= End)";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, ID);
        ps.setInt(2, custID);
        ps.setTimestamp(3, Timestamp.valueOf(startUTC));
        ps.setTimestamp(4, Timestamp.valueOf(startUTC));
        ps.setTimestamp(5, Timestamp.valueOf(endUTC));
        ps.setTimestamp(6, Timestamp.valueOf(endUTC));
        ps.setTimestamp(7, Timestamp.valueOf(startUTC));
        ps.setTimestamp(8, Timestamp.valueOf(endUTC));
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int appointmentID = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            Timestamp start = rs.getTimestamp("Start");
            Timestamp end = rs.getTimestamp("End");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");

            appointments.add(new Appointment(appointmentID, title, description, location, type, start.toLocalDateTime(),
                    end.toLocalDateTime(), createDate.toLocalDateTime(), createdBy, lastUpdate.toLocalDateTime(),
                    lastUpdatedBy, customerID, userID, contactID));
        }
        return appointments;
    }

    /**
     * Gets all appointments within 15 minutes of the given login time.
     * @param loginTime The login time to filter by.
     * @return An ObservableList of all appointments within 15 minutes of the given login time.
     * @throws SQLException
     */
    @Override
    public ObservableList<Appointment> getAppointmentsWithin15Minutes(LocalDateTime loginTime) throws SQLException {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointments WHERE Start BETWEEN ? AND (? + INTERVAL 15 MINUTE)";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setTimestamp(1, Timestamp.valueOf(loginTime));
        ps.setTimestamp(2, Timestamp.valueOf(loginTime));
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int appointmentID = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            Timestamp start = rs.getTimestamp("Start");
            Timestamp end = rs.getTimestamp("End");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");

            appointments.add(new Appointment(appointmentID, title, description, location, type, start.toLocalDateTime(),
                    end.toLocalDateTime(), createDate.toLocalDateTime(), createdBy, lastUpdate.toLocalDateTime(),
                    lastUpdatedBy, customerID, userID, contactID));
        }
        return appointments;
    }

    /**
     * Gets all appointments sorted by date
     * @return An ObservableList of all appointments sorted by date
     * @throws SQLException
     */
    @Override
    public ObservableList<Appointment> getAllAppointmentsSortedByDate() throws SQLException {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointments ORDER BY Start";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int appointmentID = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            Timestamp start = rs.getTimestamp("Start");
            Timestamp end = rs.getTimestamp("End");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");

            appointments.add(new Appointment(appointmentID, title, description, location, type, start.toLocalDateTime(),
                    end.toLocalDateTime(), createDate.toLocalDateTime(), createdBy, lastUpdate.toLocalDateTime(),
                    lastUpdatedBy, customerID, userID, contactID));
        }
        return appointments;
    }

    /**
     * Gets all appointments by contact ID
     * @param contactID The contact ID to filter by.
     * @return An ObservableList of all appointments by contact ID
     * @throws SQLException
     */
    @Override
    public ObservableList<Appointment> getAllAppointmentsByContactID(int contactID) throws SQLException {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointments WHERE Contact_ID = ? ORDER BY Start";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, contactID);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int appointmentID = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            Timestamp start = rs.getTimestamp("Start");
            Timestamp end = rs.getTimestamp("End");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");

            appointments.add(new Appointment(appointmentID, title, description, location, type, start.toLocalDateTime(),
                    end.toLocalDateTime(), createDate.toLocalDateTime(), createdBy, lastUpdate.toLocalDateTime(),
                    lastUpdatedBy, customerID, userID, contactID));
        }
        return appointments;
    }

    /**
     * Gets all appointment types
     * @return An ObservableList of all appointment types
     * @throws SQLException
     */
    @Override
    public ObservableList<String> getAllAppointmentTypes() throws SQLException {
        ObservableList<String> types = FXCollections.observableArrayList();

        String sql = "SELECT DISTINCT Type FROM appointments";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            types.add(rs.getString("Type"));
        }
        return types;
    }

    /**
     * Gets all appointments of a given type
     * @param type The type to filter by.
     * @return An ObservableList of all appointments of a given type
     * @throws SQLException
     */
    @Override
    public ObservableList<Appointment> getAllAppointmentsByType(String type) throws SQLException {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointments WHERE Type = ? ORDER BY Start";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, type);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int appointmentID = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            Timestamp start = rs.getTimestamp("Start");
            Timestamp end = rs.getTimestamp("End");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");

            appointments.add(new Appointment(appointmentID, title, description, location, type, start.toLocalDateTime(),
                    end.toLocalDateTime(), createDate.toLocalDateTime(), createdBy, lastUpdate.toLocalDateTime(),
                    lastUpdatedBy, customerID, userID, contactID));
        }
        return appointments;
    }

    /**
     * Gets all appointments of a given month
     * @param month The month to filter by.
     * @return An ObservableList of all appointments of a given month
     * @throws SQLException
     */
    @Override
    public ObservableList<Appointment> getAllAppointmentsByMonth(int month) throws SQLException {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointments WHERE MONTH(Start) = ? ORDER BY Start";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, month);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int appointmentID = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            Timestamp start = rs.getTimestamp("Start");
            Timestamp end = rs.getTimestamp("End");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");

            appointments.add(new Appointment(appointmentID, title, description, location, type, start.toLocalDateTime(),
                    end.toLocalDateTime(), createDate.toLocalDateTime(), createdBy, lastUpdate.toLocalDateTime(),
                    lastUpdatedBy, customerID, userID, contactID));
        }
        return appointments;
    }
}
