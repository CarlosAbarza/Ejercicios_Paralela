package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


public class Cliente {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8080);
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(socket.getInputStream()));

            String mensaje;
            
        while ((mensaje = reader.readLine()) != null) {
                System.out.println("Respuesta del servidor: " + mensaje);
                if (mensaje.equalsIgnoreCase("Conexión cerrada. ¡Adiós!")) {
                    break;
                }
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}