    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import Classes.DBConnection;
import javax.swing.table.DefaultTableModel;

import java.awt.Dimension;
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
/**
 *
 * @author 06003438
 */
public class VendaCRUD {
    
    
    
    public static void create(String qtd_vendida, String pagamento, String codigo){
        Connection conn = new DBConnection().conectarDB("usuario", "senha", "endereco", "base");
        PreparedStatement sql = null;
        ResultSet resultSet = null;
        
        try {
            sql = conn.prepareStatement("SELECT preco FROM produto WHERE codigo = '"+codigo+"'");
            resultSet = sql.executeQuery();
            float preco = resultSet.getFloat("preco");
            
            sql = conn.prepareStatement("INSERT INTO vendas(qtd_vendida, pagamento, codigo, preco, precoTotal) VALUES ('"+qtd_vendida+"', '"+pagamento+"','"+codigo+"','"+preco+"', '"+preco+"' * '"+qtd_vendida+"')");
    
            sql.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Vendido com sucesso!");
            
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
        sql = conn.prepareStatement("SELECT codigo, pagamento, precoTotal, qtd_vendida, preco FROM vendas");
        resultSet = sql.executeQuery();

        DefaultTableModel tableModel = new DefaultTableModel();

        tableModel.addColumn("Código");
        tableModel.addColumn("Pagamento");
        tableModel.addColumn("Preço Total");
        tableModel.addColumn("Quantidade Vendida");

        while (resultSet.next()) {
            String codigo = resultSet.getString("codigo");
            String pagamento = resultSet.getString("pagamento");
            float precoTotal = resultSet.getFloat("precoTotal");
            String qtd_vendida = resultSet.getString("qtd_vendida");
            float preco = resultSet.getFloat("preco");

            Vector<String> rowData = new Vector<>();
            rowData.add(codigo);
            rowData.add(pagamento);
            rowData.add(Float.toString(precoTotal));
            rowData.add(qtd_vendida);
            rowData.add(Float.toString(preco));
            tableModel.addRow(rowData);
        }

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setPreferredScrollableViewportSize(new Dimension(800, 400));

        JOptionPane.showMessageDialog(null, scrollPane, "Informações de Vendas", JOptionPane.INFORMATION_MESSAGE);

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
}
