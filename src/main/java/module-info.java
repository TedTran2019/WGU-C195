module ted.wguc195 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens ted.wguc195 to javafx.fxml;
    opens ted.wguc195.utils to javafx.fxml;
    opens ted.wguc195.controllers to javafx.fxml;
    opens ted.wguc195.models to javafx.base;

    exports ted.wguc195;
    exports ted.wguc195.controllers;
    exports ted.wguc195.utils;
}