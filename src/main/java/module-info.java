module com.example.esalaf {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.esalaf to javafx.fxml;
    exports com.example.esalaf;
}