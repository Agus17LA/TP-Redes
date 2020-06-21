package com.utn.redes;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClienteManager implements Runnable {

    Socket clienteSocket;
    Integer idSession;


    public ClienteManager(Socket clienteSocket, Integer idSession) {
        this.clienteSocket = clienteSocket;
        this.idSession = idSession;
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {

        Scanner scan = new Scanner(System.in);
        String mensaje;
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clienteSocket.getOutputStream()));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));

            //Se indica desde el servidor que la conexión se ha establecido correctamente
            bufferedWriter.write("Se ha establecido conexión.");
            bufferedWriter.newLine();
            bufferedWriter.flush();
            //Se envía un mensaje al servidor indicando que la conexión
            // se ha establecido correctamente
            mensaje = bufferedReader.readLine();
            this.printMensaje(mensaje);
            while ((clienteSocket.isConnected()) && (!clienteSocket.isClosed())) {
                //Devuelve verdadero si es que el socket está conectado
                // y no fue cerrado
                //Se lee el mensaje recibido del cliente
                mensaje = bufferedReader.readLine();

                //Cuando se ingresa X en el chat desde el lado del cliente
                // la conexión automáticamente se finaliza rompiendo el while
                if ("X".equalsIgnoreCase(mensaje)) {
                    bufferedWriter.write("La conexión ha sido finalizada por el usuario.");
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                    disconnect();
                    System.out.println("La conexión ha sido finalizada por el usuario.");
                    break;
                } else {
                    printMensaje(mensaje);
                    System.out.println("Ingrese una respuesta: ");
                    mensaje = scan.nextLine();
                    //Si la respuesta por parte del servidor no es X se envía un mensaje escrito
                    // desde el servidor, sino se envía un mensaje indicando que la conexión
                    // se ha finalizado
                    if (!mensaje.equalsIgnoreCase("X")) {
                        bufferedWriter.write(mensaje);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    } else {
                        bufferedWriter.write("La conexión ha sido finalizada por el servidor.");
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                        disconnect();
                        System.out.println("Conexión finalizada.");
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Usuario desconectado.");
            this.disconnect();
        }

    }

    public void printMensaje(String text) {
        System.out.println("[Cliente " + idSession + "]: " + text + "\n");
    }

    public void disconnect() {
        try {
            clienteSocket.close();
        } catch (IOException e) {
            System.out.println("Surgió un error al tratar de cerrar el servidor.\n");
        }
    }
}
