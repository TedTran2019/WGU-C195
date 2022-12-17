module ted.wguc195 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens ted.wguc195 to javafx.fxml;
    exports ted.wguc195;
}