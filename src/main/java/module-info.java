module com.example.esalaf {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.esalaf to javafx.fxml;

    opens Models to javafx.base;
    exports com.example.esalaf;
    exports Models;
}