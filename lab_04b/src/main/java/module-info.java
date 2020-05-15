module com.mycompany.mavenproject14 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.mavenproject14 to javafx.fxml;
    exports com.mycompany.mavenproject14;
}