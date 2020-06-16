package com.utn.redes;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String ip, mensaje;
        Integer puerto;

        while (true) {
            Scanner scan = new Scanner(System.in);

            //Enter the ip and port of the server to connect
            System.out.println("Ingrese la IP del servidor: ");
            ip = scan.nextLine();
            System.out.println("Ingrese el puerto: ");
            puerto = scan.nextInt();

            System.out.println("Intetando conectarse al servidor " + ip + " en el puerto: " + puerto + "\n");

            try {
                //create a new client with the socket pointing to the server
                Cliente cliente = new Cliente(new Socket(ip, puerto));
                //start the interactions with the server
                cliente.startClient();
            } catch (IOException e) {
                System.out.println("Error al conectarse con el servidor.");
            }
        }
    }
}
