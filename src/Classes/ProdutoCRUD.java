/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import Classes.DBConnection;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.lang.String;

public class ProdutoCRUD {
   
    public void create(String codigo, String nome, int unidade, float preco, int qtd_estoque) {
    Connection conn = DBConnection.conectarDB("usuario", "senha", "endereco", "base");
    PreparedStatement sql = null;
    
    try {
        
        sql = conn.prepareStatement("INSERT INTO produto(codigo, nome, unidade, preco, qtd_estoque) VALUES (?,?,?,?,?)");
        sql.setString(1, codigo);
        sql.setString(2, nome);
        sql.setInt(3, unidade);
        sql.setFloat(4, preco);
        sql.setInt(5, qtd_estoque);
        
        int executeQuery = sql.executeUpdate();
        
        if (executeQuery > 0) {
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar o produto.");
        }
    } catch (SQLException ex) {
        Logger.getLogger(ProdutoCRUD.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, "Erro ao cadastrar o produto. Verifique os dados e tente novamente.");
    } finally {
        if (sql != null) {
            try {
            sql.close();
         } catch (SQLException ex) {
            Logger.getLogger(ProdutoCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        if (conn != null) {
        DBConnection.fecharConexao(conn);
    }
    }
    
}

    public static void update(String codigo, String nome, int unidade, float preco, int qtd_estoque){
    Connection conn = new DBConnection().conectarDB("usuario", "senha", "endereco", "base");
    PreparedStatement sql;
    
    try {
        sql = conn.prepareStatement("UPDATE pdv.produto SET codigo = ?, nome = ?, unidade = ?, preco = ?, qtd_estoque = ? WHERE codigo = ?");
        sql.setString(1, codigo);
        sql.setString(2, nome);
        sql.setInt(3, unidade);
        sql.setFloat(4, preco);
        sql.setInt(5, qtd_estoque);
        sql.setString(6, codigo);
        
        int rowsUpdated = sql.executeUpdate();
        
        if (rowsUpdated > 0) {
            JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum produto atualizado.");
        }
        
    } catch (SQLException ex) {
        Logger.getLogger(ProdutoCRUD.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        DBConnection.fecharConexao(conn);
    }  
}
    public static void delete(String codigo){
        Connection conn = new DBConnection().conectarDB("usuario", "senha", "endereco", "base");
        PreparedStatement sql;
        
        try {
            sql = conn.prepareStatement("DELETE FROM produto WHERE codigo = ?");
            sql.setString(1, codigo);
            
            int rowsDeleted = sql.executeUpdate();
            
         if (rowsDeleted > 0) {
            JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum produto foi excluído. Código não encontrado.");
        }
        } catch (SQLException ex) {
        Logger.getLogger(ProdutoCRUD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        DBConnection.fecharConexao(conn);
        }
        
}
    public static void select() {
        Connection conn = new DBConnection().conectarDB("usuario", "senha", "endereco", "base");
        PreparedStatement sql = null;
        ResultSet resultSet = null;

        try {
            sql = conn.prepareStatement("SELECT codigo, nome, unidade, preco, qtd_estoque, data_ultima FROM produto");
            resultSet = sql.executeQuery();

            DefaultTableModel tableModel = new DefaultTableModel();

            tableModel.addColumn("Código");
            tableModel.addColumn("Nome");
            tableModel.addColumn("Unidade");
            tableModel.addColumn("Preço");
            tableModel.addColumn("Quantidade em Estoque");
            tableModel.addColumn("Data Última Atualização");

            while (resultSet.next()) {
                String codigo = resultSet.getString("codigo");
                String nome = resultSet.getString("nome");
                int unidade = resultSet.getInt("unidade");
                float preco = resultSet.getFloat("preco");
                int qtd_estoque = resultSet.getInt("qtd_estoque");
                String data_ultima = resultSet.getString("data_ultima");

                Vector<String> rowData = new Vector<>();
                rowData.add(codigo);
                rowData.add(nome);
                rowData.add(Integer.toString(unidade));
                rowData.add(Float.toString(preco));
                rowData.add(Integer.toString(qtd_estoque));
                rowData.add(data_ultima);
                tableModel.addRow(rowData);
            }

            JTable table = new JTable(tableModel);

            JOptionPane.showMessageDialog(null, new JScrollPane(table), "Informações de Produtos", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoCRUD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (sql != null) sql.close();
                DBConnection.fecharConexao(conn);
            } catch (SQLException ex) {
                Logger.getLogger(ProdutoCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void consult(String codigo){
        Connection conn = new DBConnection().conectarDB("usuario", "senha", "endereco", "base");
        PreparedStatement sql = null;
        ResultSet resultSet = null;

    try {
        sql = conn.prepareStatement("SELECT codigo, nome, unidade, preco, qtd_estoque, data_ultima FROM produto WHERE codigo =?");
        sql.setString(1, codigo);
        resultSet = sql.executeQuery();

        if (resultSet.next()) {
            do {
                codigo = resultSet.getString("codigo");
                String nome = resultSet.getString("nome");
                int unidade = resultSet.getInt("unidade");
                float preco = resultSet.getFloat("preco");
                int qtd_estoque = resultSet.getInt("qtd_estoque");
                LocalDateTime data_ultima = resultSet.getTimestamp("data_ultima").toLocalDateTime();

                String mensagem = "Código: " + codigo + "\n" +
                        "Nome: " + nome + "\n" +
                        "Unidade: " + unidade + "\n" +
                        "Preço: " + preco + "\n" +
                        "Quantidade em Estoque: " + qtd_estoque + "\n" +
                        "Data Última Atualização: " + data_ultima;

                JOptionPane.showMessageDialog(null, mensagem, "Informações do Produto", JOptionPane.INFORMATION_MESSAGE);
            } while (resultSet.next());
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum produto selecionado.");
        }
        } catch (SQLException ex) {
        Logger.getLogger(ProdutoCRUD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        DBConnection.fecharConexao(conn);
        }
        
}
 
}