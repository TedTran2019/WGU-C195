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

    public static ObservableList<UserLog> getAll() {
        ObservableList<UserLog> userLogs = FXCollections.observableArrayList();
        FileIO.readFromFile("login_activity.txt").lines().forEach(line -> {
            String[] fields = line.split(",");
            userLogs.add(new UserLog(fields[0], LocalDateTime.parse(fields[1]), Boolean.parseBoolean(fields[2]), fields[3]));
        });
        return userLogs;
    }

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

    public UserLog(String userName, LocalDateTime attemptDate, boolean loginSuccess, String failureReason) {
        this.userName = userName;
        this.attemptDate = attemptDate;
        this.loginSuccess = loginSuccess;
        this.failureReason = failureReason;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDateTime getAttemptDate() {
        return attemptDate;
    }

    public void setAttemptDate(LocalDateTime attemptDate) {
        this.attemptDate = attemptDate;
    }

    public boolean isLoginSuccess() {
        return loginSuccess;
    }

    public void setLoginSuccess(boolean loginSuccess) {
        this.loginSuccess = loginSuccess;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }
}
