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

class abc {

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
                String sql = "select tipo FROM SALGADOS group by tipo";
                PreparedStatement ps = conn.prepareStatement(sql);

                ResultSet rs = ps.executeQuery();
                String codigo;
                HashMap<String, List<String>> Produtos = new HashMap<String, List<String>>();
                List<String> select = new ArrayList<String>();
                while (rs.next()) {
                    codigo = rs.getString("tipo");
                    // select.add();


                    PreparedStatement ps2 = conn.prepareStatement("select nome,valor FROM SALGADOS where tipo = '" + codigo + "';");

                    ResultSet rs2 = ps2.executeQuery();
                    List<String> results = new ArrayList<String>();
                    while (rs2.next()) {
                        String Nome = rs2.getString("nome");
                        String Valor = rs2.getString("valor");

                        results.add(Nome + " - " + Valor);
                    }

                    Produtos.put(codigo, results);
                    ps2.close();
                    rs2.close();


                    //
                }
                ps.close();
                rs.close();


                Gson gson = new Gson();
                String json = gson.toJson(Produtos);

                Type mapType = new TypeToken<Map<String, List<String>>>() {
                }.getType();
                Map<String, List<String>> son = new Gson().fromJson(json, mapType);

                //System.out.println(son.get("Doce").toArray()[0]);


                conn.close();

                ServerSocket Servidor2 = new ServerSocket(7070);
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