package sample;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Base64;

class ABC2 {

    public static void main(String args[]) throws IOException {
while (true) {
    ServerSocket Servidor = new ServerSocket(7071);
    Socket C2 = Servidor.accept();
    InputStream is = C2.getInputStream();
    InputStreamReader isr = new InputStreamReader(is, "UTF-8");
    BufferedReader br = new BufferedReader(isr);


    String item  = br.readLine();
    item = item.split("SEPARAPAL")[1];

    String linha = br.readLine();

    item += linha;
    while (linha != null) {
        item += linha;
        linha = br.readLine();

    }
    System.out.println("Message received from the client : " + item);


    String command = "python \"C:\\Users\\redes\\PycharmProjects\\PedidosReiceiver\\Reiceiver.py\" "+item;

    String s = null;

    try {

        Process p = Runtime.getRuntime().exec(command);

        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(p.getInputStream()));

        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(p.getErrorStream()));

        // read the output from the command
        System.out.println("Here is the standard output of the command:\n");
        while ((s = stdInput.readLine()) != null) {
            System.out.println(s);
        }

        // read any errors from the attempted command
        System.out.println("Here is the standard error of the command (if any):\n");
        while ((s = stdError.readLine()) != null) {
            System.out.println(s);
        }
    } catch (Exception e){
        e.printStackTrace();
    }





    Servidor.close();
}
    }



}