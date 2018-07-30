package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

class abc {

    public static void main(String args[]) throws IOException, InterruptedException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        String IP = inetAddress.getHostAddress();
        System.out.println(IP);
        String[] a = IP.split("[.]");
        System.out.println(a[0]);

        String ipSub= a[0]+"."+a[1]+"."+a[2];
        int timeout=5;
        ArrayList<String> item = new ArrayList<String>();;
        for (int i=1;i<255;i++){
            String hostIP=ipSub + "." + i;
            System.out.println(hostIP);

            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(hostIP, 7070), 100);

                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);

                String message = br.readLine();
                System.out.println("Message received from the server : " + message);


                item.add(message);

                while (message != null){
                    message = br.readLine();
                    if (message != null) {
                        System.out.println("Message received from the server : " + message);
                        item.add(message);
                    }

                }
                break;
            } catch (Exception e){

            }







           // if (InetAddress.getByName(hostIP).isReachable(timeout)){
               // System.out.println(hostIP);
           // }
        }
    }
}