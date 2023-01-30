package ted.wguc195.daos;

import javafx.collections.ObservableList;
import ted.wguc195.models.Appointment;

import java.sql.SQLException;

public interface AppointmentDao {
    public ObservableList<Appointment> getAllAppointments() throws SQLException;
    public Appointment getAppointment(int appointmentID) throws SQLException;
    public void updateAppointment(Appointment appointment) throws SQLException;
    public void deleteAppointment(int appointmentID) throws SQLException;
    public void addAppointment(Appointment appointment) throws SQLException;
}
