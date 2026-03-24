package bloque_1;

import java.util.Random;

public class Sensor {
    private final int id;
    private float temp;
    private final Random rand;

    public Sensor(int id) {
        this.id = id;
        this.rand = new Random();
        this.temp = rand.nextFloat() * 110;
    }

    public int getId() {
        return id;
    }
    public float getTemp() {
        return temp;
    }
    public boolean verificar() {
        return temp < 100;
    }
    public void actualizarTemp() {
        this.temp = rand.nextFloat() * 110;
    }
}