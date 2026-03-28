package out.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5000);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Ingrese su nombre: ");
        String nombre = teclado.readLine();

        Thread enviar = new Thread(new Lector(out, nombre));
        enviar.start();

        while (true) {
            String respuesta = in.readLine();
            if (respuesta != null) {
                String remitente = respuesta.split(":")[0];
                String mensaje = respuesta.split(":")[1];
                System.out.println(remitente + ":" + mensaje);
            }
        }

    }

    static class Lector implements Runnable {
        private PrintWriter out;
        private String nombre;

        public Lector(PrintWriter out, String nombre) {
            this.out = out;
            this.nombre = nombre;
        }

        @Override
        public void run() {
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                try {
                    String mensaje = teclado.readLine();
                    if (mensaje != null) {
                        out.println(nombre + ":" + mensaje);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}