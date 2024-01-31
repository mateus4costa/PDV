/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class DBConnection {
    
    
    private static final String DRIVER = "org.sqlite.JDBC";
    private static final String URL = "jdbc:sqlite:BASE_DE_DADOS.s3db";  
    
    public static Connection Conectar(){
       try{
           Class.forName(DRIVER);
           return DriverManager.getConnection(URL);
           //
       }catch (ClassNotFoundException | SQLException ex) {
           throw new RuntimeException("Erro na conexão!", ex);
       } 
    }
    public static void closeConnection(Connection conn){
        if(conn != null){
            try{
                conn.close();
            } catch (SQLException ex) {
               Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void closeConnection(Connection conn, PreparedStatement stat){
        closeConnection(conn);
        if(stat != null){
        try{
            stat.close();
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    }
    public static void closeConnection(Connection conn, PreparedStatement stat, ResultSet rs){
        closeConnection(conn,stat);
        if(rs != null){
            try{
                rs.close();
            } catch (SQLException ex) {
               Logger.getLogger(Connection.class.getName()).log(Level.SEVERE,null, ex);  
            }
        }
    }


}

