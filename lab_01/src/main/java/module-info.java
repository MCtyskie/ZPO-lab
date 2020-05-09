module com.mycompany.mavenproject10 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.mycompany.mavenproject10 to javafx.fxml;
    exports com.mycompany.mavenproject10;
}