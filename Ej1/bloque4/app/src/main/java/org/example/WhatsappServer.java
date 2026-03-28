package org.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;

// Servidor de Mensajería de Grupo
public class WhatsappServer {
    // Lista para gestionar a los miembros del grupo
    private static List<PrintWriter> clientes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket servidor = new ServerSocket(5000);
        System.out.println("Servidor de grupo iniciado...");
        while (true) {
            Socket socket = servidor.accept();
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true); 
            clientes.add(out); // Agregar nuevo miembro al grupo
            
            Thread thread = new Thread(new ManejadorCliente(socket, out));
            thread.start();
        }
    }

    static class ManejadorCliente implements Runnable {
        private Socket socket;
        private PrintWriter out;

        public ManejadorCliente(Socket socket, PrintWriter out) {
            this.socket = socket;
            this.out = out;
        }

        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while (true) {
                    String mensaje = in.readLine();
                    if (mensaje != null) {
                        String nombre = mensaje.split(":")[0];
                        String texto = mensaje.split(":")[1];
                        for (PrintWriter cliente : clientes) {
                            if (!cliente.equals(out)) { 
                                cliente.println(nombre + ":" + texto);
                            } else {
                                cliente.println("Tú: " + texto);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}