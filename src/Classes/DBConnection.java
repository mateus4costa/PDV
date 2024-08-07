package Classes;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBConnection {
    
    public static boolean verificarLogin(String usuario, String senha, String endereco, String base) {
        String url = "jdbc:postgresql://" + endereco + ":5432/" + base;
        
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url, usuario, senha);
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static Connection conectarDB(String usuario, String senha, String endereco, String base) {
        Connection conn = null;

        try {
            String url = "jdbc:postgresql://" + endereco + "/" + base;
            conn = DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar ao banco de dados: " + ex.getMessage());
        }

        return conn;
    }
    
    public static void fecharConexao(Connection conn) {
    try {
        if (conn != null) {
            conn.close();
        }
    } catch (SQLException ex) {
        Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    
}
   


    
