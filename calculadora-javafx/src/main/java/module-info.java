module com.calculadora.calculadorajavafx {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.calculadora.calculadorajavafx to javafx.fxml;
    exports com.calculadora.calculadorajavafx;

    opens com.calculadora.calculadorajavafx.controller to javafx.fxml;
    exports com.calculadora.calculadorajavafx.controller;
}