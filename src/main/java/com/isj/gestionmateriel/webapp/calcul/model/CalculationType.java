package com.isj.gestionmateriel.webapp.calcul.model;

public enum CalculationType {
    ADDITION,
    MULTIPLICATION,
    DIVISION,
    SUBTRACTION,
    CONVERSION;

    public static CalculationType fromSymbol(String operation) {
        switch (operation) {
            case "+":
                return ADDITION;
            case "-":
                return SUBTRACTION;
            case "/":
                return DIVISION;
            case "*":
                return MULTIPLICATION;
            case "x":
                return MULTIPLICATION;
            default:
                throw new UnsupportedOperationException("Not implemented yet");
        }
    }
}
