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
     * Criando pessoas fisicas.
     *
     * @return void
     */
    private static void criarPessoasFisica() {
        ClienteService clienteService = new ClienteService();
        var clienteTales = new PessoaFisica("Tales", "(61)98638-6912", "tales@gmail.com", "3 Av bl 1000", "123.456.789-11");
        var clienteAna = new PessoaFisica("Ana Luiza Fernandes", "(61)98633-3982", "ana@gmail.com", "1 Av bl 95", "690.456.789-11");
        var clienteMonica = new PessoaFisica("Monica", "(61)99999-3999", "monica@gmail.com", "2 Av bl 1000", "888.888.888-88");
        var clienteLevi = new PessoaFisica("Levi", "(61)93333-3333", "levi@gmail.com", "3 Av bl 3995", "999.999.999-99");
        var clienteSara = new PessoaFisica("Sarah", "(61)97777-7777", "sara@gmail.com", "4 Av bl A", "222.222.222-22");
        var clienteJulia = new PessoaFisica("Julia", "(61)92222-2222", "julia@gmail.com", "Av Central bl C", "000.000.000-11");
        
        try {
            clienteService.cadastrarPessoaFisica(clienteTales);
            clienteService.cadastrarPessoaFisica(clienteAna);
            clienteService.cadastrarPessoaFisica(clienteMonica);
            clienteService.cadastrarPessoaFisica(clienteLevi);
            clienteService.cadastrarPessoaFisica(clienteSara);
            clienteService.cadastrarPessoaFisica(clienteJulia);
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
        var clienteTales = new PessoaJuridica("Google", "(61)3333-0000", "google@gmail.com", "3 Av bl 1000", "06.990.590/0001-23");
        var clienteAna = new PessoaJuridica("Microsoft", "(61)2330-3982", "microsoft@gmail.com", "3 Av bl 995", "60.316.817/0001-03");
        var clienteMonica = new PessoaJuridica("Banco do Brasil", "(61)5555-0000", "BB@gmail.com", "Av central bl 95", "99.318.417/0001-73");
        var clienteLevi = new PessoaJuridica("Nivea", "(61)3232-1282", "nivea@gmail.com", "2 AV bl 95", "89.748.227/0001-22");
        var clienteSara =  new PessoaJuridica("Apple", "(61)3214-3772", "apple1@gmail.com", "2 Av bl D", "77.201.557/0001-99");
        var clienteJulia =  new PessoaJuridica("Tupperware", "(61)7555-7887", "tupperware@gmail.com", "Av central bl 95", "99.318.417/0001-73");
        try {
            clienteService.cadastrarPessoaJuridica(clienteTales);
            clienteService.cadastrarPessoaJuridica(clienteAna);
            clienteService.cadastrarPessoaJuridica(clienteMonica);
            clienteService.cadastrarPessoaJuridica(clienteLevi);
            clienteService.cadastrarPessoaJuridica(clienteSara);
            clienteService.cadastrarPessoaJuridica(clienteJulia);
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
        var produto1 = new Produto("Nootbook", "Samsung", 3000.00, 2500.00);
        var produto2 = new Produto("Celular", "Samsung", 2500.00, 1800.00);
        var produto3 = new Produto("Copo Americano", "Marinex", 1.30, 1.50);
        var produto4 = new Produto("Creme corporal", "Nivea", 15.50, 16.50);
        var produto5 = new Produto("Estojo","Basic", 10.00, 12.00);
        var produto6 = new Produto("Fone de ouvido", "Xiaomi", 25.00,30.00);

        try {
            produtoService.cadastrar(produto1);
            produtoService.cadastrar(produto2);
            produtoService.cadastrar(produto3);
            produtoService.cadastrar(produto4);
            produtoService.cadastrar(produto5);
            produtoService.cadastrar(produto6);
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

        var produto1 = new Produto("Nootbook", "Samsung", 3000.00, 2500.00);
        var produto2 = new Produto("Celular", "Samsung", 2500, 1800);
        var produto3 = new Produto("Copo Americano", "Marinex", 1.30, 1.50);
        var produto4 = new Produto("Creme corporal", "Nivea", 15.50, 16.50);
        var produto5 = new Produto("Estojo","Basic", 10.00, 12.00);
        var produto6 = new Produto("Fone de ouvido", "Xiaomi", 25.00,30.00);
        
        var clienteTales = new PessoaFisica("Tales", "(61)98638-6912", "tales@gmail.com", "3 Av bl 1000", "123.456.789-11");
        var clienteAna = new PessoaFisica("Ana Luiza Fernandes", "(61)98633-3982", "ana@gmail.com", "1 Av bl 95", "690.456.789-11");
        var clienteMonica = new PessoaFisica("Monica", "(61)99999-3999", "monica@gmail.com", "2 Av bl 1000", "888.888.888-88");
        var clienteLevi = new PessoaFisica("Levi", "(61)93333-3333", "levi@gmail.com", "3 Av bl 3995", "999.999.999-99");
        var clienteSara = new PessoaFisica("Sarah", "(61)97777-7777", "sara@gmail.com", "4 Av bl A", "222.222.222-22");
        var clienteJulia = new PessoaFisica("Julia", "(61)92222-2222", "julia@gmail.com", "Av Central bl C", "000.000.000-11");

        var pedido1 = new Pedido("01/09/2022", clienteAna.getNome(), produto1.getNome(), 2);
        var pedido2 = new Pedido("02/09/2022", clienteTales.getNome(), produto2.getNome(), 2);
        var pedido3 = new Pedido("03/09/2022", clienteMonica.getNome(), produto3.getNome(),10);
        var pedido4 = new Pedido("04/09/2022", clienteLevi.getNome(), produto4.getNome(),15);
        var pedido5 = new Pedido("05/09/2022", clienteSara.getNome(), produto5.getNome(),2);
        var pedido6 = new Pedido("10/09/2022", clienteJulia.getNome(), produto6.getNome(),20);

        try {
            pedidoService.cadastrarPedido(pedido1);
            pedidoService.cadastrarPedido(pedido2);
            pedidoService.cadastrarPedido(pedido3);
            pedidoService.cadastrarPedido(pedido4);
            pedidoService.cadastrarPedido(pedido5);
            pedidoService.cadastrarPedido(pedido6);
        } catch (DadosInvalidosException e) {
            e.printStackTrace();
        }
    }
}

