package view;

import controller.service.ClienteService;
import controller.service.PedidoService;
import controller.service.ProdutoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Classe SubMenu gera tela de submenu e herda da classe Componentes.
 *
 * @author Ana Luiza Fernandes e Tales Rodrigues
 * @version 1.0
 * @see Componentes
 * @since 2022
 */
public class SubMenu extends Componentes {
    private static JButton cadastraPessoaFisica = new JButton("Cadastrar Pessoa Fisica");
    private static JButton cadastraPessoaJuridica = new JButton("Cadastrar Pessoa Juridica");
    private static JButton cadastraProduto = new JButton("Cadastrar produto");
    private static JButton cadastraPedido = new JButton("Cadastrar pedido");

    private static JButton vizualizaPessoaFisica = new JButton("Listar Pessoa Fisica");
    private static JButton vizualizarClientePJ = new JButton("Listar Pessoa Juridica");
    private static JButton vizualizarProduto = new JButton("Listar Produtos");
    private static JButton vizualizarPedido = new JButton("Listar Pedidos");
    private JLabel titulo = new JLabel();
    private final ClienteService clienteService;
    private final PedidoService pedidoService;
    private final ProdutoService produtoService;

    /**
     * Metodo construtor que instancia as classes ClienteService, ProdutoService e PedidoService.
     *
     * @param opcao
     */
    public SubMenu(int opcao) {
        clienteService = new ClienteService();
        produtoService = new ProdutoService();
        pedidoService = new PedidoService();

        switch (opcao) {
            case 1:
                //Botoes cliente
                titulo = new JLabel("Clientes");
                titulo.setFont(new Font("Arial", Font.BOLD, 20));
                titulo.setBounds(200, 10, 200, 30);
                add(titulo);

                //vizualizar ClientesPF
                vizualizaPessoaFisica.setBounds(30, 80, 180, 20);
                add(vizualizaPessoaFisica);
                vizualizaPessoaFisica.addActionListener(this::vizualizar);

                //vizualizar ClientesPJ
                vizualizarClientePJ.setBounds(250, 80, 180, 20);
                add(vizualizarClientePJ);
                vizualizarClientePJ.addActionListener(this::vizualizar);

                //cadastrar cliente
                cadastraPessoaFisica.setBounds(30, 50, 180, 20);
                add(cadastraPessoaFisica);
                cadastraPessoaFisica.addActionListener(this::cadastrar);

                //cadastrar cliente Pessoa Juridica
                cadastraPessoaJuridica.setBounds(250, 50, 180, 20);
                add(cadastraPessoaJuridica);
                cadastraPessoaJuridica.addActionListener(this::cadastrar);
                break;
            case 2:
                titulo = new JLabel("Produtos");
                titulo.setFont(new Font("Arial", Font.BOLD, 20));
                titulo.setBounds(200, 10, 200, 30);
                add(titulo);

                vizualizarProduto.setBounds(260, 80, 180, 20);
                add(vizualizarProduto);
                vizualizarProduto.addActionListener(this::vizualizar);
                cadastraProduto.setBounds(40, 80, 180, 20);
                add(cadastraProduto);
                cadastraProduto.addActionListener(this::cadastrar);
                break;
            case 3:
                titulo = new JLabel("Pedidos");
                titulo.setFont(new Font("Arial", Font.BOLD, 20));
                titulo.setBounds(200, 10, 200, 30);
                add(titulo);

                vizualizarPedido.setBounds(260, 80, 180, 20);
                add(vizualizarPedido);
                vizualizarPedido.addActionListener(this::vizualizar);

                cadastraPedido.setBounds(40, 80, 180, 20);
                add(cadastraPedido);
                cadastraPedido.addActionListener(this::cadastrar);
                break;
        }

        setLayout(null);
        setVisible(true);
        setSize(500, 190);
    }

    /**
     * Cadastra de acordo com o selecionado seja ele pessoa fisica, pessoa juridica, pedido ou produto.
     *
     * @param actionEvent
     * @return void
     */
    private void cadastrar(ActionEvent actionEvent) {
        Object cadastroSelecionado = actionEvent.getSource();

        if (cadastraPessoaFisica == cadastroSelecionado) {
            new FormularioCliente(1);
        }
        if (cadastraPessoaJuridica == cadastroSelecionado) {
            new FormularioCliente(2);
        }
        if (cadastraPedido == cadastroSelecionado) {
            new FormularioPedido();
        }
        if (cadastraProduto == cadastroSelecionado) {
            new FormularioProduto();
        }

    }

    /**
     * Consulta de acordo com o selecionado seja ele pessoa fisica, pessoa juridica, pedido ou produto.
     *
     * @param actionEvent
     * @return void
     */
    private void vizualizar(ActionEvent actionEvent) {
        Object vizualizaSelecionado = actionEvent.getSource();
        //Cria Telas de Listar
        if (vizualizaPessoaFisica == vizualizaSelecionado) {
            new ConsultaCliente(1);
        }
        if (vizualizarClientePJ == vizualizaSelecionado) {
            new ConsultaCliente(2);
        }
        if (vizualizarProduto == vizualizaSelecionado) {
            new ConsultaProduto();
        }
        if (vizualizarPedido == vizualizaSelecionado) {
            new ConsultaPedido();
        }
    }
}
