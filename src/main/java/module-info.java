module ted.wguc195 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens ted.wguc195 to javafx.fxml;
    exports ted.wguc195;
    exports ted.wguc195.controllers;
    opens ted.wguc195.controllers to javafx.fxml;
    exports ted.wguc195.utils;
    opens ted.wguc195.utils to javafx.fxml;
}