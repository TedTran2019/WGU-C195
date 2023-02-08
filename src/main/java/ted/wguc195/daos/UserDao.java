package ted.wguc195.daos;

import javafx.collections.ObservableList;
import ted.wguc195.models.User;

import java.sql.SQLException;

public interface UserDao {
    public ObservableList<User> getAllUsers() throws SQLException;
    public User getUser(int userID) throws SQLException;
    public User getUser(String userName) throws SQLException;
    public void updateUser(User user) throws SQLException;
    public void deleteUser(int userID) throws SQLException;
    public void addUser(User user) throws SQLException;
    public ObservableList<String> getAllUserNames() throws SQLException;
}
