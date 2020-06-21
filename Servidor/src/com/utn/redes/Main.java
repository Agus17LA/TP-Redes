package com.utn.redes;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {

        Integer puerto = 6000;
        Integer idSession = 0;
        System.out.println("Iniciando servidor...\n");
        try {
            //Create a serverSocket listening to the port
            ServerSocket servidorSocket = new ServerSocket(puerto);
            System.out.println("Servidor iniciado.\n");
            System.out.println("Escribe un mensaje: \n");
            while (true) {
                //Accept the connection with the client and create the correspondent socket
                Socket clienteSocket = servidorSocket.accept();
                System.out.println("Nueva conexión entrante.");
                // increase the session number by 1
                idSession++;
                //Create a thread to handle the communication with the client
                new ClienteManager(clienteSocket, idSession);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
