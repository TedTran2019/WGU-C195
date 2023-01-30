package ted.wguc195.daos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ted.wguc195.models.Customer;
import ted.wguc195.utils.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class CustomerDaoImpl implements CustomerDao{
    @Override
    public ObservableList<Customer> getAllCustomers() throws SQLException {
        ObservableList<Customer> customers = FXCollections.observableArrayList();

        String sql = "SELECT * FROM customers";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int customerID = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int divisionID = rs.getInt("Division_ID");

            customers.add(new Customer(customerID, customerName, address, postalCode, phone, createDate.toLocalDateTime(),
                    createdBy, lastUpdate.toLocalDateTime(), lastUpdatedBy, divisionID));
        }
        return customers;
    }

    @Override
    public Customer getCustomer(int customerID) throws SQLException {
        String sql = "SELECT * FROM customers WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, customerID);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String customerName = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int divisionID = rs.getInt("Division_ID");

            return new Customer(customerID, customerName, address, postalCode, phone, createDate.toLocalDateTime(),
                    createdBy, lastUpdate.toLocalDateTime(), lastUpdatedBy, divisionID);
        }
        return null;
    }

    @Override
    public void updateCustomer(Customer customer) throws SQLException {
        String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Update = ?, " +
                "Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, customer.getCustomerName());
        ps.setString(2, customer.getAddress());
        ps.setString(3, customer.getPostalCode());
        ps.setString(4, customer.getPhone());
        ps.setTimestamp(5, Timestamp.valueOf(customer.getLastUpdate()));
        ps.setString(6, customer.getLastUpdatedBy());
        ps.setInt(7, customer.getDivisionID());
        ps.setInt(8, customer.getCustomerID());
        ps.executeUpdate();
    }

    @Override
    public void deleteCustomer(int customerID) throws SQLException {
        String sql = "DELETE FROM customers WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, customerID);
        ps.executeUpdate();
    }

    @Override
    public void addCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, " +
                "Last_Update, Last_Updated_By, Division_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, customer.getCustomerName());
        ps.setString(2, customer.getAddress());
        ps.setString(3, customer.getPostalCode());
        ps.setString(4, customer.getPhone());
        ps.setTimestamp(5, Timestamp.valueOf(customer.getCreateDate()));
        ps.setString(6, customer.getCreatedBy());
        ps.setTimestamp(7, Timestamp.valueOf(customer.getLastUpdate()));
        ps.setString(8, customer.getLastUpdatedBy());
        ps.setInt(9, customer.getDivisionID());
        ps.executeUpdate();
    }
}
