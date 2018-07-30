package sample;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sound.midi.Receiver;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.Properties;

public class Conexão {
    Boolean verifique = true;
    public static void main(String[] args) {
        while (true) {

            ReceiverTipo();

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
                ServerSocket Servidor2 = new ServerSocket(7070);
                Socket C2 = Servidor2.accept();
                PrintWriter writer = new PrintWriter(C2.getOutputStream());
                while (rs.next()) {
                    codigo = rs.getString("tipo");
                    writer.write(codigo + "\n");
                }
                ps.close();
                writer.flush();
                writer.close();


            } catch (Exception e) {
            }
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }

    }

    static void ReceiverTipo(){
        Thread StartReceiver = (new Thread() {
            @Override
            public void run() {

                while (true) {
                    try {

                            ServerSocket Servidor = new ServerSocket(7071);
                            Socket C2 = Servidor.accept();
                            InputStream is = C2.getInputStream();
                            InputStreamReader isr = new InputStreamReader(is);
                            BufferedReader br = new BufferedReader(isr);

                            String message = br.readLine();

                            System.out.println("Message received from the client : " + message);


                            String url = "jdbc:mysql://127.0.0.1:3306/Produtos?useTimezone=true&serverTimezone=UTC&useSSL=false";
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
                                String sql = "select nome,valor FROM SALGADOS where tipo = '" + message + "';";
                                PreparedStatement ps = conn.prepareStatement(sql);

                                ResultSet rs = ps.executeQuery();
                                String nome;
                                PrintWriter writer = new PrintWriter(C2.getOutputStream());
                                while (rs.next()) {
                                    nome = rs.getString("nome");
                                    String Valor = rs.getString("valor");
                                    System.out.println(nome + " " + Valor);

                                    writer.write(nome + "\n");
                                }
                                ps.close();
                                writer.flush();
                                writer.close();
                                Servidor.close();
                            } catch (Exception e) {
                                e.printStackTrace();

                            }




                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        StartReceiver.start();
    }

    }
