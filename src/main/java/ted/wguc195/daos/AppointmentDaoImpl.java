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
import java.time.ZonedDateTime;

public class AppointmentDaoImpl implements AppointmentDao{
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

    @Override
    public void deleteAppointment(int appointmentID) throws SQLException {
        String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, appointmentID);
        ps.executeUpdate();
    }

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

    @Override
    public void deleteAppointmentsByCustomerID(int customerID) throws SQLException {
        String sql = "DELETE FROM appointments WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, customerID);
        ps.executeUpdate();
    }

    @Override
    public ObservableList<Appointment> getOverlappingAppointments(Timestamp startUTC, Timestamp endUTC) throws SQLException {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointments WHERE (? >= Start AND ? < End) OR (? > Start AND ? <= End) OR (? <= Start AND ? >= End)";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setTimestamp(1, startUTC);
        ps.setTimestamp(2, startUTC);
        ps.setTimestamp(3, endUTC);
        ps.setTimestamp(4, endUTC);
        ps.setTimestamp(5, startUTC);
        ps.setTimestamp(6, endUTC);
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
