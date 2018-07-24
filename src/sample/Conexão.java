package sample;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.Properties;

public class Conexão {

    public static void main(String[] args) {
        while (true) {
            String url = "jdbc:mysql://127.0.0.1:3306/Produtos?useTimezone=true&serverTimezone=UTC&useSSL=false";
            String username = "root";
            String password = "root";


            System.out.println("Connecting database...");
            System.out.println("Loading driver...");

            Connection conn = null;
            try {

                conn = DriverManager.getConnection(url, username, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                assert conn != null;
                Statement stmt = conn.createStatement();
                String sql = "SELECT * from salgados;";
                PreparedStatement ps = conn.prepareStatement(sql);

                ResultSet rs = ps.executeQuery();
                String codigo;
                ServerSocket Servidor2 = new ServerSocket(7070);
                Socket C2 = Servidor2.accept();
                PrintWriter writer = new PrintWriter(C2.getOutputStream());
                while (rs.next()) {
                    codigo = rs.getString("nome");
                    writer.write(codigo + "\n");

                    System.out.println(codigo);
                }
                ps.close();
                writer.flush();
                writer.close();


            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    }
