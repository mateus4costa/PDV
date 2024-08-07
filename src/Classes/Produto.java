/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;
import java.time.LocalDateTime;
/**
 *
 * @author convidado
 */
public class Produto {
    private String codigo;
    private String nome;
    private String unidade;
    private float preco;
    private int qtd_estoque;
    private LocalDateTime data_ultima;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(int String) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getQtd_estoque() {
        return qtd_estoque;
    }

    public void setQtd_estoque(int qtd_estoque) {
        this.qtd_estoque = qtd_estoque;
    }

    public LocalDateTime getData_ultima() {
        return data_ultima;
    }

    public void setData_ultima(LocalDateTime data_ultima) {
        this.data_ultima = data_ultima;
    }

    
}


