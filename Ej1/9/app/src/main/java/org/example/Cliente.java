package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Ingrese \"Archivo.txt\" para solicitar el archivo al servidor:");
            String mensaje = reader.readLine();
            writer.println(mensaje);


            BufferedReader responseReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while ((line = responseReader.readLine()) != null) {
                System.out.println(line);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}