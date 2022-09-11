package model;

import controller.service.validator.DadosInvalidosException;
import controller.service.validator.Validador;

/**
 * Classe Pedido que possui os atributos que representa o pedido.
 */
public class Pedido {
    private String id;
    private String dt_venda;
    private String identificadorCliente;

    private String indentificadorProduto;

    private int qt_produto;

    /**
     * Metodo construtor que retorna os dados do pedido com ID.
     *
     * @param id
     * @param dt_venda
     * @param cliente
     * @param produto
     * @param qt_produto
     */
    public Pedido(String id, String dt_venda, String cliente, String produto, int qt_produto) {
        this.id = id;
        this.dt_venda = dt_venda;
        this.identificadorCliente = cliente;
        this.indentificadorProduto = produto;
        this.qt_produto = qt_produto;
    }

    /**
     * Metodo construtor que retorna os dados do pedido.
     *
     * @param dt_venda
     * @param cliente
     * @param produto
     * @param qt_produto
     */
    public Pedido(String dt_venda, String cliente, String produto, int qt_produto) {
        this.dt_venda = dt_venda;
        this.identificadorCliente = cliente;
        this.indentificadorProduto = produto;
        this.qt_produto = qt_produto;
    }

    /**
     * Esta sobrescrevendo o metodo do toString para retornar os dados da classe em formato de texto.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Numero Pedido: " + this.id + "\n"
                + "DATA DE VENDA: " + this.dt_venda + "\n"
                + "Cliente: " + this.identificadorCliente + "\n"
                + "Produto: " + this.indentificadorProduto + "\n"
                + "Quantidade " + this.qt_produto;
    }

    /**
     * Valida o pedido.
     *
     * @return void
     * @throws DadosInvalidosException
     */
    public void validarPedido() throws DadosInvalidosException {
        Validador.validarCampoPreenchidoComMascara(dt_venda, Validador.CHAR_MASCARA_DATA, "preencha a data");
        Validador.validarCampoPreenchido(indentificadorProduto, "Escolha um produto");
        Validador.validarCampoPreenchido(identificadorCliente, "Escolha um cliente");
    }

    /**
     * Esta sobrescrevendo o metodo clone e retornando um objeto pedido.
     *
     * @return pedido
     */
    @Override
    public Pedido clone() {
        Pedido pedido = new Pedido(getId(), getDt_venda(), getIdentificadorCliente(), getIndentificadorProduto(), getQt_produto());
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

    public int getQt_produto() {
        return qt_produto;
    }

    public void setQt_produto(int qt_produto) {
        this.qt_produto = qt_produto;
    }

    public String getDt_venda() {
        return dt_venda;
    }

    public void setDt_venda(String dt_venda) {
        this.dt_venda = dt_venda;
    }

}
