package ted.wguc195.daos;

import javafx.collections.ObservableList;
import ted.wguc195.models.Appointment;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public interface AppointmentDao {
    public ObservableList<Appointment> getAllAppointments() throws SQLException;
    public Appointment getAppointment(int appointmentID) throws SQLException;
    public void updateAppointment(Appointment appointment) throws SQLException;
    public void deleteAppointment(int appointmentID) throws SQLException;
    public void addAppointment(Appointment appointment) throws SQLException;
    public ObservableList<Appointment> getAppointmentsByMonth(LocalDate date) throws SQLException;
    public ObservableList<Appointment> getAppointmentsByWeek(LocalDate date) throws SQLException;
    public void deleteAppointmentsByCustomerID(int customerID) throws SQLException;
    public ObservableList<Appointment> getOverlappingAppointments(LocalDateTime start, LocalDateTime end, int custID) throws SQLException;
    public ObservableList<Appointment> getOverlappingAppointmentsMinusSelf(LocalDateTime start, LocalDateTime end, int ID, int custID) throws SQLException;
    public ObservableList<Appointment> getAppointmentsWithin15Minutes(LocalDateTime loginTime) throws SQLException;
    public ObservableList<Appointment> getAllAppointmentsSortedByDate() throws SQLException;
    public ObservableList<Appointment> getAllAppointmentsByContactID(int contactID) throws SQLException;
}
