package org.example;
import java.util.Random;

public class Auto {
    private int tiempo;
    private static Random rand = new Random();

    public Auto() {
        tiempo = rand.nextInt(5) + 1;
    }

    public void avanzar() {
        tiempo -= 1;
    }

    public boolean llegoALaMeta() {
        return tiempo == 0;
    }
}