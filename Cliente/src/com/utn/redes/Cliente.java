package com.utn.redes;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    private Socket socket;

    public Cliente(Socket socket){
        this.socket = socket;
    }


    public void startClient(){
        Scanner scan = new Scanner(System.in);
        String mensaje;
        try {
            //Initializing the input and output
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

            System.out.println("El cliente se inició de manera exitosa.");
            //Printing the message from the server indicating that the connection was established
            mensaje = bufferedReader.readLine();
            this.printMensaje(mensaje);
            //Send a message to the server indicating that the connection was established
            bufferedWriter.write("Conexión establecida.");
            bufferedWriter.newLine();
            bufferedWriter.flush();
            //True if the socket connected and is not closed
            while((socket.isConnected()) && (!socket.isClosed())){
                System.out.println("Escribe un mensaje: ");
                mensaje = scan.nextLine();
                bufferedWriter.write(mensaje);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                //If the message is not x print the message from the server, and if it's x break the while
                if(!mensaje.equalsIgnoreCase("X")){
                    mensaje = bufferedReader.readLine();
                    if(!mensaje.equalsIgnoreCase("Conexión finalizada por el servidor.")){
                        printMensaje(mensaje);
                    }else{
                        this.disconnect();
                        break;
                    }
                }else{
                    this.disconnect();
                    break;
                }
            }
            this.disconnect();

        } catch (IOException e) {
            System.out.println("El servidor se ha desconectado.");
        }


    }

    public void printMensaje(String text){
        System.out.println("[Servidor]: " + text + "\n");
    }

    public void disconnect() {
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Ha surgido un error al tratar de desconectarse.\n");
        }
    }
}
