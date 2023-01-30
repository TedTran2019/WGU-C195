package ted.wguc195.daos;

import javafx.collections.ObservableList;
import ted.wguc195.models.Contact;

import java.sql.SQLException;

public interface ContactDao {
    public ObservableList<Contact> getAllContacts() throws SQLException;
    public Contact getContact(int contactID) throws SQLException;
    public void updateContact(Contact contact) throws SQLException;
    public void deleteContact(int contactID) throws SQLException;
    public void addContact(Contact contact) throws SQLException;
}
