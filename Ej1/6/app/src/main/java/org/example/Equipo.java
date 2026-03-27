package org.example;

public class Equipo {
    private final String nombre;
    private boolean libre;
    private int ocupante;

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.libre = true;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isLibre() {
        return libre;
    }

    public void ocupar(int id) {
        this.libre = false;
        this.ocupante = id;
    }
    public void liberar() {
        this.libre = true;
        this.ocupante = -1; 
    }
}