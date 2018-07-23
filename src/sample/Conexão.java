package sample;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;
import java.util.Properties;

public class Conexão {

    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3306/Produtos?useTimezone=true&serverTimezone=UTC&useSSL=false";
        String username = "root";
        String password = "root";



        System.out.println("Connecting database...");
        System.out.println("Loading driver...");

        Connection conn = null;
        try {

            conn = DriverManager.getConnection(url, username, password);
        } catch(Exception e){
            e.printStackTrace();
        }
        try {
            assert conn != null;
            Statement stmt = conn.createStatement();
            String sql = "SELECT * from salgados;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String codigo = rs.getString("nome");
                System.out.println(codigo);
            }


        }catch (Exception e){
            e.printStackTrace();
        }


    }

    }
