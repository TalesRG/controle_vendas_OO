package view;

import controller.service.ClienteService;
import controller.service.PedidoService;
import controller.service.ProdutoService;
import controller.service.validator.DadosInvalidosException;
import model.Pedido;
import model.PessoaFisica;
import model.PessoaJuridica;
import model.Produto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Classe Menu cria a tela de Menu e herda da classe JFrame.
 *
 * @author Ana Luiza Fernandes e Tales Rodrigues
 * @see javax.swing.JFrame
 * @since 2022
 */
public class Menu extends JFrame {
    private static JLabel titulo = new JLabel("Menu");

    private static JButton consultar_Cliente = new JButton("Clientes");

    private static JButton consultar_Produto = new JButton("Produtos");

    private static JButton consultar_Pedido = new JButton("Pedidos");


    /**
     * Metodo construtor que gera tela de Menu.
     */
    public Menu() {
        super("Menu");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBounds(200, 10, 200, 30);
        add(titulo);

        consultar_Cliente.setBounds(140, 50, 180, 20);
        add(consultar_Cliente);
        consultar_Cliente.addActionListener(this::gerarTelas);

        consultar_Produto.setBounds(140, 80, 180, 20);
        add(consultar_Produto);
        consultar_Produto.addActionListener(this::gerarTelas);

        consultar_Pedido.setBounds(140, 110, 180, 20);
        add(consultar_Pedido);
        consultar_Pedido.addActionListener(this::gerarTelas);

        setLayout(null);
        setVisible(true);
        setSize(500, 190);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Gera Telas do SubMenu.
     *
     * @param actionEvent
     * @return void
     */

    private void gerarTelas(ActionEvent actionEvent) {
        Object src = actionEvent.getSource();

        if (consultar_Cliente.equals(src)) {
            new SubMenu(1);
        }
        if (consultar_Produto.equals(src)) {
            new SubMenu(2);
        }
        if (consultar_Pedido.equals(src)) {
            new SubMenu(3);
        }
    }

    /**
     * Roda a aplicacao.
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {

        new Menu();

        criarPessoasFisica();
        criarPessoasJuridica();
        criarProduto();
        criarPedido();

    }

    /**
     * Criando pessoas fisicas.
     *
     * @return void
     */
    private static void criarPessoasFisica() {
        ClienteService clienteService = new ClienteService();
        var clienteTales = new PessoaFisica("Tales", "(61)98638-6912", "tales@gmail.com", "3 Av bl 1000", "123.456.789-11");
        var clienteAna = new PessoaFisica("Ana Luíza Fernandes", "(61)98633-3982", "ana@gmail.com", "3 Av bl 995", "690.456.789-11");

        try {
            clienteService.cadastrarPessoaFisica(clienteTales);
            clienteService.cadastrarPessoaFisica(clienteAna);
        } catch (DadosInvalidosException e) {
            e.printStackTrace();
        }
    }

    /**
     * Criando pessoas juridicas.
     *
     * @return void
     */
    private static void criarPessoasJuridica() {
        ClienteService clienteService = new ClienteService();
        var clienteTales = new PessoaJuridica("Google", "(61)98638-6912", "tales@gmail.com", "3 Av bl 1000", "06.990.590/0001-23");
        var clienteAna = new PessoaJuridica("Microsoft", "(61)98638-3982", "ana@gmail.com", "3 Av bl 995", "60.316.817/0001-03");

        try {
            clienteService.cadastrarPessoaJuridica(clienteTales);
            clienteService.cadastrarPessoaJuridica(clienteAna);
        } catch (DadosInvalidosException e) {
            e.printStackTrace();
        }
    }

    /**
     * Criando Produto.
     *
     * @return void
     */
    private static void criarProduto() {
        ProdutoService produtoService = new ProdutoService();
        var produto1 = new Produto("NootBook", "samsung", 3000.00, 2500.00);
        var produto2 = new Produto("celular", "samsung", 2500, 1800);

        try {
            produtoService.cadastrar(produto1);
            produtoService.cadastrar(produto2);
        } catch (DadosInvalidosException e) {
            e.printStackTrace();
        }

    }

    /**
     * Criando Pedidos.
     *
     * @return void
     */
    private static void criarPedido() {
        PedidoService pedidoService = new PedidoService();

        var produto1 = new Produto("NootBook", "samsung", 3000.00, 2500.00);
        var produto2 = new Produto("celular", "samsung", 2500, 1800);
        var clienteTales = new PessoaFisica("Tales", "(61)98638-6912", "tales@gmail.com", "3 Av bl 1000", "123.456.789-11");
        var clienteAna = new PessoaFisica("Ana Luíza Fernandes", "(61)98633-3982", "ana@gmail.com", "3 Av bl 995", "690.456.789-11");

        var pedido1 = new Pedido("11/09/2022", clienteAna.getNome(), produto1.getNome(), 2);
        var pedido2 = new Pedido("12/09/2022", clienteTales.getNome(), produto2.getNome(), 2);

        try {
            pedidoService.cadastrarPedido(pedido1);
            pedidoService.cadastrarPedido(pedido2);
        } catch (DadosInvalidosException e) {
            e.printStackTrace();
        }
    }
}

