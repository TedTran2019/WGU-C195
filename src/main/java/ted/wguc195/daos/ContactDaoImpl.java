package ted.wguc195.daos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ted.wguc195.models.Contact;
import ted.wguc195.utils.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ContactDaoImpl implements ContactDao{
    @Override
    public ObservableList<Contact> getAllContacts() throws SQLException {
        ObservableList<Contact> contacts = FXCollections.observableArrayList();

        String sql = "SELECT * FROM contacts";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int contactID = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            String email = rs.getString("Email");

            contacts.add(new Contact(contactID, contactName, email));
        }
        return contacts;
    }

    @Override
    public Contact getContact(int contactID) throws SQLException {
        String sql = "SELECT * FROM contacts WHERE Contact_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, contactID);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String contactName = rs.getString("Contact_Name");
            String email = rs.getString("Email");

            return new Contact(contactID, contactName, email);
        }
        return null;
    }

    @Override
    public void updateContact(Contact contact) throws SQLException {
        String sql = "UPDATE contacts SET Contact_Name = ?, Email = ? WHERE Contact_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, contact.getContactName());
        ps.setString(2, contact.getEmail());
        ps.setInt(3, contact.getContactID());
        ps.executeUpdate();
    }

    @Override
    public void deleteContact(int contactID) throws SQLException {
        String sql = "DELETE FROM contacts WHERE Contact_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, contactID);
        ps.executeUpdate();
    }

    @Override
    public void addContact(Contact contact) throws SQLException {
        String sql = "INSERT INTO contacts (Contact_Name, Email) VALUES (?, ?)";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, contact.getContactName());
        ps.setString(2, contact.getEmail());
        ps.executeUpdate();
    }
}
