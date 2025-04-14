/*module com.example.testjavafx {
    requires javafx.controls;
    requires javafx.fxml;



    opens com.example.testjavafx to javafx.fxml;
    exports com.example.testjavafx;
}*/

module com.example.testjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    opens com.example.testjavafx to javafx.fxml;
    exports com.example.testjavafx;
    exports com.example.testjavafx.donneetab;
    opens com.example.testjavafx.donneetab to javafx.fxml;
}
