package sample;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.*;

class ABC2 {

    public static void main(String args[]) throws IOException, InterruptedException {
        while (true) {
            String url = "jdbc:mysql://127.0.0.1:3306/Produtos?useTimezone=true&serverTimezone=UTC";
            String username = "root";
            String password = "root";

            Connection conn = null;
            try {

                conn = DriverManager.getConnection(url, username, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                assert conn != null;
                Statement stmt = conn.createStatement();
                String sql = "select nome FROM pedidos;";
                PreparedStatement ps = conn.prepareStatement(sql);

                ResultSet rs = ps.executeQuery();
                String codigo;
                HashMap<String, List<String>> Produtos = new HashMap<String, List<String>>();
                List<String> select = new ArrayList<String>();
                while (rs.next()) {
                    codigo = rs.getString("nome");
                    select.add(codigo);

                }
                ps.close();
                rs.close();
                System.out.println(select);
                String json = new Gson().toJson(select);

                conn.close();
                ServerSocket Servidor2 = new ServerSocket(7072);
                System.out.println("Aguardando cliente");
                Socket C2 = Servidor2.accept();
                PrintWriter writer = new PrintWriter(C2.getOutputStream());
                writer.write(json);
                System.out.println("Enviado");
                writer.flush();
                writer.close();

                C2.close();
                Servidor2.close();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}