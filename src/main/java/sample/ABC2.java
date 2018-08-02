package sample;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.*;

class ABC2 {

    public static void main(String args[]) throws IOException, InterruptedException {

        ServerSocket Servidor = new ServerSocket(7071);
        Socket C2 = Servidor.accept();
        InputStream is = C2.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        String message = br.readLine();

        System.out.println("Message received from the client : " + message);
        String image = message.split(";")[3];
        byte[] decoded = DatatypeConverter.parseBase64Binary("aGVsbG8gd29ybGQ=");
        System.out.println(new String(decoded, "UTF-8"));


    }


}