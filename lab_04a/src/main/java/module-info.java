module com.mycompany.mavenproject13 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.mavenproject13 to javafx.fxml;
    exports com.mycompany.mavenproject13;
}