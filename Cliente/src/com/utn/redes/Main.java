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

            //Se deben ingresar tanto la IP como el puerto para
            // establecer conexión con el servidor
            System.out.println("Ingrese la IP del servidor: ");
            ip = scan.nextLine();
            System.out.println("Ingrese el puerto: ");
            puerto = scan.nextInt();

            System.out.println("Intetando conectarse al servidor " + ip + " en el puerto: " + puerto + "\n");

            try {
                //Se crea un cliente con el socket apuntando hacia el servidor
                // utilizando los datos que ingresó el cliente previamente
                Cliente cliente = new Cliente(new Socket(ip, puerto));
                //Se establece conexión con el servidor
                cliente.startClient();
            } catch (IOException e) {
                System.out.println("Error al conectarse con el servidor.");
            }
        }
    }
}
