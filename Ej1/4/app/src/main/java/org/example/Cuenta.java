package org.example;

public class Cuenta {
    private double saldo;

    public Cuenta(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public void retirar(double cantidad) {
        if (cantidad <= saldo) {
            // Tuve que agregar este sleep para aumentar la probabilidad de que ocurra una condición de carrera
            try {
                Thread.sleep(10);
            } catch (Exception e) {
            }
            saldo -= cantidad;
        } else {
            System.out.println("Saldo insuficiente");
        }
    }

    public double getSaldo() {
        return saldo;
    }
    
    public synchronized void retirarSync(double cantidad) {
        if (cantidad <= saldo) {
            // Tuve que agregar este sleep para aumentar la probabilidad de que ocurra una condición de carrera
            try {
                Thread.sleep(10);
            } catch (Exception e) {
            }
            saldo -= cantidad;
        } else {
            System.out.println("Saldo insuficiente");
        }
    }
}