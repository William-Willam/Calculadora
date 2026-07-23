package com.calculadora.calculadorajavafx;

import com.calculadora.calculadorajavafx.controller.CalculadoraController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CalculadoraApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("calculadora-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root,320 ,420);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        CalculadoraController controller = fxmlLoader.getController();
        scene.setOnKeyPressed(controller :: handleKeyPress);

        stage.setTitle("Calculadora");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        root.requestFocus();
    }

    public static void main(String[] args) {
        launch();
    }
}
