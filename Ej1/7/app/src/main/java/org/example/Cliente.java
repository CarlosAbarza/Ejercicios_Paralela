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
            BufferedReader serverReader = new BufferedReader(new java.io.InputStreamReader(socket.getInputStream()));
            BufferedReader consoleReader = new BufferedReader(new java.io.InputStreamReader(System.in));

            String mensaje;
            System.out.println("Conectado al servidor. Escribe mensajes para enviar (escribe 'salir' para terminar):");
            
        while ((mensaje = consoleReader.readLine()) != null) {
                writer.println(mensaje);
                String respuesta = serverReader.readLine();
                System.out.println("Respuesta del servidor: " + respuesta);
                if (respuesta.equalsIgnoreCase("Conexión cerrada. ¡Adiós!")) {
                    break;
                }
                System.out.println("Escribe otro mensaje (o 'salir' para terminar):");
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}