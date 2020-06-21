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
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

            System.out.println("El cliente se inició de manera exitosa.");
            //Se indica desde el servidor que la conexión se ha establecido correctamente
            mensaje = bufferedReader.readLine();
            this.printMensaje(mensaje);
            //Se envía un mensaje al servidor indicando que la conexión
            // se ha establecido correctamente
            bufferedWriter.write("Conexión establecida.");
            bufferedWriter.newLine();
            bufferedWriter.flush();
            while((socket.isConnected()) && (!socket.isClosed())){
                //Devuelve verdadero si es que el socket está conectado
                // y no fue cerrado
                System.out.println("Escribe un mensaje: ");
                mensaje = scan.nextLine();
                bufferedWriter.write(mensaje);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                //Cuando se ingresa X en el chat la conexión automáticamente se finaliza
                // rompiendo el while
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
