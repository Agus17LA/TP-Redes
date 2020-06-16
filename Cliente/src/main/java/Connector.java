import java.net.*;
import java.io.*;

public class Connector {

    //ServerSocket server;
    //Socket socket;
    Socket cliente;
    String ip = "127.0.0.1";
    int port = 9000;
    //DataOutputStream salida;
    BufferedReader entrada, teclado;
    PrintStream salida;


    public void iniciar()
    {
        try {
            /*
            server = new ServerSocket(port);
            socket = new Socket();
            socket = server.accept();

            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String mensaje = entrada.readLine();
            System.out.println(mensaje);
            salida = new DataOutputStream(socket.getOutputStream());
            salida.writeUTF("Adios mundo");
            socket.close();
             */

            cliente = new Socket(ip, port);
            entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            teclado = new BufferedReader(new InputStreamReader(System.in));
            String tec = teclado.readLine();
            salida = new PrintStream(cliente.getOutputStream());
            salida.println(tec);
            String msg = entrada.readLine();
            System.out.println(msg);

            entrada.close();
            salida.close();
            teclado.close();
            cliente.close();

        }catch(Exception e){};
    }
}
