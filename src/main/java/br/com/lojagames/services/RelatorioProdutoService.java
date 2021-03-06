package br.com.lojagames.services;

import java.text.DecimalFormat;

/**
 *
 * @author victor
 */
public class RelatorioProdutoService implements IRelatorioServices {
    
    private String nome;
    private int quantidade;
    private double valor;
    
    public RelatorioProdutoService() {}
    
    public RelatorioProdutoService(String nome, int quantidade, double valor) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public double getValor() {
        return this.valor;
    }
    
    public void setValor(double valor) {
        this.valor = valor;
    }
    
    @Override
    public String getPorcentagem(double totalVenda) {
        DecimalFormat df = new DecimalFormat("0.00");
        double porcentagem = (valor * quantidade) * 100 / totalVenda; 
        
        return df.format(porcentagem) + "%";
    }
}


