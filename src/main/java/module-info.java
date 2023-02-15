module com.example.snowpark {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.snowpark to javafx.fxml;
    exports com.example.snowpark;
    exports com.example.snowpark.Utils;
    exports com.example.snowpark.Models;
}