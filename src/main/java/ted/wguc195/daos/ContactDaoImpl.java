package ted.wguc195.daos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ted.wguc195.models.Contact;
import ted.wguc195.utils.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Implements the ContactDao interface.
 */
public class ContactDaoImpl implements ContactDao{
    /**
     * Gets all contacts from the database.
     * @return An ObservableList of all contacts.
     * @throws SQLException
     */
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

    /**
     * Gets a contact from the database.
     * @param contactID The ID of the contact to get.
     * @return The contact.
     * @throws SQLException
     */
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

    /**
     * Updates a contact in the database.
     * @param contact The contact to update.
     * @throws SQLException
     */
    @Override
    public void updateContact(Contact contact) throws SQLException {
        String sql = "UPDATE contacts SET Contact_Name = ?, Email = ? WHERE Contact_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, contact.getContactName());
        ps.setString(2, contact.getEmail());
        ps.setInt(3, contact.getContactID());
        ps.executeUpdate();
    }

    /**
     * Deletes a contact from the database.
     * @param contactID The ID of the contact to delete.
     * @throws SQLException
     */
    @Override
    public void deleteContact(int contactID) throws SQLException {
        String sql = "DELETE FROM contacts WHERE Contact_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, contactID);
        ps.executeUpdate();
    }

    /**
     * Adds a contact to the database.
     * @param contact The contact to add.
     * @throws SQLException
     */
    @Override
    public void addContact(Contact contact) throws SQLException {
        String sql = "INSERT INTO contacts (Contact_Name, Email) VALUES (?, ?)";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, contact.getContactName());
        ps.setString(2, contact.getEmail());
        ps.executeUpdate();
    }
}
