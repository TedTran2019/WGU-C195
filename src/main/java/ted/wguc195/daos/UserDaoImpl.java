package ted.wguc195.daos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ted.wguc195.models.User;
import ted.wguc195.utils.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class UserDaoImpl implements UserDao{
    @Override
    public ObservableList<User> getAllUsers() throws SQLException {
        ObservableList<User> users = FXCollections.observableArrayList();

        String sql = "SELECT * FROM users";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int userID = rs.getInt("User_ID");
            String userName = rs.getString("User_Name");
            String password = rs.getString("Password");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");

            users.add(new User(userID, userName, password, createDate.toLocalDateTime(), createdBy, lastUpdate.toLocalDateTime(), lastUpdatedBy));
        }
        return users;
    }

    @Override
    public User getUser(int userID) throws SQLException {
        String sql = "SELECT * FROM users WHERE User_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, userID);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String userName = rs.getString("User_Name");
            String password = rs.getString("Password");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");

            return new User(userID, userName, password, createDate.toLocalDateTime(), createdBy, lastUpdate.toLocalDateTime(), lastUpdatedBy);
        }
        return null;
    }

    @Override
    public User getUser(String userName) throws SQLException {
        String sql = "SELECT * FROM users WHERE User_Name = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, userName);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int userID = rs.getInt("User_ID");
            String password = rs.getString("Password");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");

            return new User(userID, userName, password, createDate.toLocalDateTime(), createdBy, lastUpdate.toLocalDateTime(), lastUpdatedBy);
        }
        return null;
    }

    @Override
    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE users SET User_Name = ?, Password = ?, Last_Update = ?, Last_Updated_By = ? WHERE User_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, user.getUserName());
        ps.setString(2, user.getPassword());
        ps.setTimestamp(3, Timestamp.valueOf(user.getLastUpdate()));
        ps.setString(4, user.getLastUpdatedBy());
        ps.setInt(5, user.getUserID());
        ps.executeUpdate();
    }

    @Override
    public void deleteUser(int userID) throws SQLException {
        String sql = "DELETE FROM users WHERE User_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, userID);
        ps.executeUpdate();
    }

    @Override
    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO users (User_Name, Password, Create_Date, Created_By, Last_Update, Last_Updated_By) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, user.getUserName());
        ps.setString(2, user.getPassword());
        ps.setTimestamp(3, Timestamp.valueOf(user.getCreateDate()));
        ps.setString(4, user.getCreatedBy());
        ps.setTimestamp(5, Timestamp.valueOf(user.getLastUpdate()));
        ps.setString(6, user.getLastUpdatedBy());
        ps.executeUpdate();
    }
}
