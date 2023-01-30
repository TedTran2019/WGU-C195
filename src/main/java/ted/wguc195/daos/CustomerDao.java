package ted.wguc195.daos;

import javafx.collections.ObservableList;
import ted.wguc195.models.Customer;

import java.sql.SQLException;

public interface CustomerDao {
    public ObservableList<Customer> getAllCustomers() throws SQLException;
    public Customer getCustomer(int customerID) throws SQLException;
    public void updateCustomer(Customer customer) throws SQLException;
    public void deleteCustomer(int customerID) throws SQLException;
    public void addCustomer(Customer customer) throws SQLException;
}
