package ted.wguc195.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ted.wguc195.utils.FileIO;

import java.time.LocalDateTime;

public class UserLog {
    private String userName;
    private LocalDateTime attemptDate;
    private boolean loginSuccess;
    private String failureReason;

    /**
     * Returns all user logs from the login_activity.txt file.
     * @return ObservableList of UserLog objects
     */
    public static ObservableList<UserLog> getAll() {
        ObservableList<UserLog> userLogs = FXCollections.observableArrayList();
        FileIO.readFromFile("login_activity.txt").lines().forEach(line -> {
            String[] fields = line.split(",");
            userLogs.add(new UserLog(fields[0], LocalDateTime.parse(fields[1]), Boolean.parseBoolean(fields[2]), fields[3]));
        });
        return userLogs;
    }

    /**
     * Returns all user logs for a given user from the login_activity.txt file.
     * @param userName The username to search for
     * @return ObservableList of UserLog objects
     */
    public static ObservableList<UserLog> getAllByUser(String userName) {
        ObservableList<UserLog> userLogs = FXCollections.observableArrayList();
        FileIO.readFromFile("login_activity.txt").lines().forEach(line -> {
            String[] fields = line.split(",");
            if (fields[0].equals(userName)) {
                userLogs.add(new UserLog(fields[0], LocalDateTime.parse(fields[1]), Boolean.parseBoolean(fields[2]), fields[3]));
            }
        });
        return userLogs;
    }

    /**
     * Constructor for UserLog
     * @param userName
     * @param attemptDate
     * @param loginSuccess
     * @param failureReason
     */
    public UserLog(String userName, LocalDateTime attemptDate, boolean loginSuccess, String failureReason) {
        this.userName = userName;
        this.attemptDate = attemptDate;
        this.loginSuccess = loginSuccess;
        this.failureReason = failureReason;
    }

    /**
     * @return the username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the username to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the attemptDate
     */
    public LocalDateTime getAttemptDate() {
        return attemptDate;
    }

    /**
     * @param attemptDate the attemptDate to set
     */
    public void setAttemptDate(LocalDateTime attemptDate) {
        this.attemptDate = attemptDate;
    }

    /**
     * @return the loginSuccess
     */
    public boolean isLoginSuccess() {
        return loginSuccess;
    }

    /**
     * @param loginSuccess the loginSuccess to set
     */
    public void setLoginSuccess(boolean loginSuccess) {
        this.loginSuccess = loginSuccess;
    }

    /**
     * @return the failureReason
     */
    public String getFailureReason() {
        return failureReason;
    }

    /**
     * @param failureReason the failureReason to set
     */
    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }
}
