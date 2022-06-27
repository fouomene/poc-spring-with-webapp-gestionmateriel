package com.isj.gestionmateriel.webapp.calcul;

import com.isj.gestionmateriel.webapp.aop.Supervision;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public int sub(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public int divide(int a, int b) {
        return a / b;
    }

    public double add(double a, double b) {
        return a + b;
    }

    public double sub(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
        return a / b;
    }


    public void longCalculation() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public Set<Integer> digitsSet(int number) {

        Set<Integer> integers = new HashSet<Integer>();
        String numberString = String.valueOf(number);

        for (int i = 0; i < numberString.length(); i++) {
            if (numberString.charAt(i) != '-') {
                integers.add(Integer.parseInt(numberString.substring(i, i + 1)));
            }
        }
        return integers;
    }

    public int fact(int a) {
        if (a < 0 || a > 12) {
            throw new IllegalArgumentException("Doit être compris entre 0 et 12.");
        }
        if (a <= 1) {
            return a;
        }
        return a * fact(a - 1);
    }

}
