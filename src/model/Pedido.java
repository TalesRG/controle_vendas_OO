package model;

import controller.service.validator.DadosInvalidosException;
import controller.service.validator.Validador;

/**
 * Classe Pedido que possui os atributos que representa o pedido.
 */
public class Pedido {
    private String id;
    private String dtVenda;
    private String identificadorCliente;

    private String indentificadorProduto;

    private int qtProduto;

    /**
     * Metodo construtor que retorna os dados do pedido com ID.
     *
     * @param id
     * @param dtVenda
     * @param cliente
     * @param produto
     * @param qtProduto
     */
    public Pedido(String id, String dtVenda, String cliente, String produto, int qtProduto) {
        this.id = id;
        this.dtVenda = dtVenda;
        this.identificadorCliente = cliente;
        this.indentificadorProduto = produto;
        this.qtProduto = qtProduto;
    }

    /**
     * Metodo construtor que retorna os dados do pedido.
     *
     * @param dtVenda
     * @param cliente
     * @param produto
     * @param qtProduto
     */
    public Pedido(String dtVenda, String cliente, String produto, int qtProduto) {
        this.dtVenda = dtVenda;
        this.identificadorCliente = cliente;
        this.indentificadorProduto = produto;
        this.qtProduto = qtProduto;
    }

    /**
     * Esta sobrescrevendo o metodo do toString para retornar os dados da classe em formato de texto.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Numero Pedido: " + this.id + "\n"
                + "DATA DE VENDA: " + this.dtVenda + "\n"
                + "Cliente: " + this.identificadorCliente + "\n"
                + "Produto: " + this.indentificadorProduto + "\n"
                + "Quantidade " + this.qtProduto;
    }

    /**
     * Valida o pedido.
     *
     * @return void
     * @throws DadosInvalidosException
     */
    public void validarPedido() throws DadosInvalidosException {
        Validador.validarCampoPreenchidoComMascara(dtVenda, Validador.CHAR_MASCARA_DATA, "preencha a data");
        Validador.validarCampoPreenchido(indentificadorProduto, "Escolha um produto");
        Validador.validarCampoPreenchido(identificadorCliente, "Escolha um cliente");
    }

    /**
     * Esta sobrescrevendo o metodo clone e retornando um objeto pedido.
     *
     * @return Pedido
     */
    @Override
    public Pedido clone() {
        Pedido pedido = new Pedido(getId(), getDtVenda(), getIdentificadorCliente(), getIndentificadorProduto(), getQtProduto());
        return pedido;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdentificadorCliente() {
        return identificadorCliente;
    }

    public void setIdentificadorCliente(String identificadorCliente) {
        this.identificadorCliente = identificadorCliente;
    }

    public String getIndentificadorProduto() {
        return indentificadorProduto;
    }

    public void setIndentificadorProduto(String indentificadorProduto) {
        this.indentificadorProduto = indentificadorProduto;
    }

    public int getQtProduto() {
        return qtProduto;
    }

    public void setQtProduto(int qtProduto) {
        this.qtProduto = qtProduto;
    }

    public String getDtVenda() {
        return dtVenda;
    }

    public void setDtVenda(String dtVenda) {
        this.dtVenda = dtVenda;
    }

}
