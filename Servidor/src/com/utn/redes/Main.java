package com.utn.redes;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {

        Integer puerto = 3000;
        Integer idSession = 0;
        System.out.println("Iniciando servidor...\n");
        try {
            //Se crea un servidor socket escuchando al puerto indicado
            ServerSocket servidorSocket = new ServerSocket(puerto);
            System.out.println("Servidor iniciado.\n");
            System.out.println("Escribe un mensaje: \n");
            while (true) {
                //Acepta la conexión con el cliente y
                // crea el socket correspondiente
                Socket clienteSocket = servidorSocket.accept();
                System.out.println("Nueva conexión entrante.");
                //Aumenta una unidad el número de la sesión
                idSession++;
                //Se crea un hilo para manejar la comunicación
                // con el cliente
                new ClienteManager(clienteSocket, idSession);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
