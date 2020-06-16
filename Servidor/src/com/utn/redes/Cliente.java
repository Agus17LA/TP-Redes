package com.utn.redes;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente implements Runnable {

    Socket clienteSocket;
    Integer idSession;


    public Cliente(Socket clienteSocket, Integer idSession) {
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
            //Initializing the input and output
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clienteSocket.getOutputStream()));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));

            //Send a message to the client indicating that the connection was established
            bufferedWriter.write("Se ha establecido conexión.");
            bufferedWriter.newLine();
            bufferedWriter.flush();
            //Printing the message from the server indicating that the connection was established
            mensaje = bufferedReader.readLine();
            this.printMensaje(mensaje);
            //True if the socket connected and is not closed
            while ((clienteSocket.isConnected()) && (!clienteSocket.isClosed())) {
                //Read the message from the user
                mensaje = bufferedReader.readLine();

                //If the message sent by the user is x the server disconnects if not print the message and input a response
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
                    //If the response is not x the response is send if not send a notification that the server finish the connection and disconnect the socket
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
