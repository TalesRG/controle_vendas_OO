package model;

import controller.service.validator.DadosInvalidosException;
import controller.service.validator.Validador;

/**
 * Classe Produto que possui os atributos que representa o produto.
 *
 * @author Ana Luiza Fernandes e Tales Rodrigues
 * @version 1.0
 * @since 2022
 */
public class Produto {
    private String nome;
    private String fabricante;
    private double precoVenda;
    private double precoCusto;

    /**
     * Metodo construtor que possui os atributos que representa o produto.
     *
     * @param nome
     * @param fabricante
     * @param precoVenda
     * @param precoCusto
     */
    public Produto(String nome, String fabricante, double precoVenda, double precoCusto) {
        this.nome = nome;
        this.fabricante = fabricante;
        this.precoVenda = precoVenda;
        this.precoCusto = precoCusto;
    }

    /**
     * Valida o produto.
     *
     * @return void
     * @throws DadosInvalidosException
     */
    public void validarProduto() throws DadosInvalidosException {
        Validador.validarCampoPreenchido(nome, "Prencha o nome");
        Validador.validarCampoPreenchido(fabricante, "Preencha o fabricante");
        
        //se o preco custo ou preco venda for menor que zero, muda seus valores para zero
        if (precoCusto < 0 || precoVenda < 0) {
            precoCusto = 0.0;
            precoVenda = 0.0;
        }
    }

    /**
     * Esta sobrescrevendo o metodo do toString para retornar os dados da classe em formato de texto.
     *
     * @return String
     */
    public String toString() {
        return "Nome: " + this.nome + "\n"
                + "Fabricante: " + this.fabricante + "\n"
                + "Preço de custo: " + this.precoCusto + "\n"
                + "Preço de venda: " + this.precoVenda;
    }

    /**
     * Esta sobrescrevendo o metodo clone e retornando um objeto produto.
     *
     * @return Produto
     */
    @Override
    public Produto clone() {
        Produto produto = new Produto(getNome(), getFabricante(), getPrecoVenda(), getPrecoCusto());
        return produto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public double getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(double precoCusto) {
        this.precoCusto = precoCusto;
    }
}
