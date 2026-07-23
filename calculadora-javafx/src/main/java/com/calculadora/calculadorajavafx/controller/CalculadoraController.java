package com.calculadora.calculadorajavafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculadoraController {

    @FXML
    private TextField display;

    private double valorAnterior = 0;
    private String operador = "";
    private boolean comecarNovoNumero = true;

    @FXML
    private void handleButtonClick(ActionEvent event) {
        Button botao = (Button) event.getSource();
        String texto = botao.getText();

        switch (texto) {
            case "C" -> limpar();
            case "⌫" -> apagarUltimoDigito();
            case "%" -> aplicarPorcentagem();
            case "÷", "X", "-", "+" -> definirOperador(texto);
            case "." -> adicionarPontoDecimal();
            default -> adicionarDigito(texto);
        }
    }

    @FXML
    private void handleIgual(ActionEvent event) {
        if (operador.isEmpty()) return;

        double valorAtual = Double.parseDouble(display.getText());
        double resultado = calcular(valorAnterior, valorAtual, operador);

        display.setText(formatarResultado(resultado));
        operador = "";
        comecarNovoNumero = true;
    }

    private void adicionarDigito(String digito) {
        if (comecarNovoNumero) {
            display.setText(digito);
            comecarNovoNumero = false;
        } else {
            display.setText(display.getText() + digito);
        }
    }

    private void adicionarPontoDecimal() {
        if (comecarNovoNumero) {
            display.setText("0.");
            comecarNovoNumero = false;
        } else if (!display.getText().contains(".")) {
            display.setText(display.getText() + ".");
        }
    }

    private void definirOperador(String novoOperador) {
        if (!operador.isEmpty() && !comecarNovoNumero) {
            handleIgual(null);
        }
        valorAnterior = Double.parseDouble(display.getText());
        operador = novoOperador;
        comecarNovoNumero = true;
    }

    private double calcular(double a, double b, String op) {
        return switch (op) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "X" -> a * b;
            case "÷" -> b != 0 ? a / b : 0;
            default -> b;
        };
    }

    private void aplicarPorcentagem() {
        double valor = Double.parseDouble(display.getText());
        display.setText(formatarResultado(valor / 100));
        comecarNovoNumero = true;
    }

    private void apagarUltimoDigito() {
        String textoAtual = display.getText();
        if (textoAtual.length() > 1) {
            display.setText(textoAtual.substring(0, textoAtual.length() - 1));
        } else {
            display.setText("0");
            comecarNovoNumero = true;
        }
    }

    private void limpar() {
        display.setText("0");
        valorAnterior = 0;
        operador = "";
        comecarNovoNumero = true;
    }

    private String formatarResultado(double valor) {
        if (valor == (long) valor) {
            return String.valueOf((long) valor);
        }
        return String.valueOf(valor);
    }
}