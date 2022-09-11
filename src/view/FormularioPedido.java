package view;

import controller.service.ClienteService;
import controller.service.PedidoService;
import controller.service.ProdutoService;
import controller.service.validator.DadosInvalidosException;
import model.Pedido;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static view.MascaraUtil.*;

/**
 * Classe FormularioPedido gera um formulario para cadastrar o pedido e herda de Componentes.
 *
 * @author Ana Luiza Fernandes e Tales Rodrigues
 * @version 1.0
 * @see Componentes
 * @since 2022
 */
public class FormularioPedido extends Componentes {
    private JTextField dt_venda;
    private static JComboBox produto_pedido;
    private static JComboBox cliente_pedido;
    private static JTextField quantidade_Produto;
    private static JLabel titulo;
    private static JButton cadastrarEditarPedido;

    private static Pedido pedidoEditar;

    private PedidoService pedidoService;
    private ClienteService clienteService;
    private ProdutoService produtoService;
    private Runnable callBackAtualizarLista;

    /**
     * Cria a tela de cadastro de pedido quando isCadastrar for TRUE
     */
    public FormularioPedido() {
        this(true);
    }


    /**
     * Quando isCadastrar for FALSE gera formulario de editar.
     *
     * @param pedido
     * @param callBackAtualizarLista
     */
    public FormularioPedido(Pedido pedido, Runnable callBackAtualizarLista) {
        this(false);
        this.callBackAtualizarLista = callBackAtualizarLista;
        pedidoEditar = pedido;
        pedidoEditar.setId(pedido.getId());
        dt_venda.setText(pedido.getDt_venda());
        cliente_pedido.setSelectedItem(pedido.getIdentificadorCliente());
        produto_pedido.setSelectedItem(pedido.getIndentificadorProduto());
        quantidade_Produto.setText(String.valueOf(pedido.getQt_produto()));
    }

    /**
     * Gera telas de cadastro e editar conforme o status de isCadastrar.
     *
     * @param isCadastrar
     */
    private FormularioPedido(boolean isCadastrar) {
        pedidoService = new PedidoService();
        clienteService = new ClienteService();
        produtoService = new ProdutoService();

        titulo = new JLabel("Cadastro pedido");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBounds(160, 10, 200, 30);
        add(titulo);

        criarLabel("Data venda: ", 50, 60, 200, 20);
        dt_venda = new JFormattedTextField(mascaraData());
        dt_venda.setBounds(50, 80, 200, 20);
        add(dt_venda);

        //Listando Clientes
        criarLabel("Cliente: ", 50, 100, 200, 20);
        var todosClientes = clienteService.retornarTodosClientes();
        var listarClientes = todosClientes
                .stream()
                .map(c -> c.getNome())
                .toArray(String[]::new);
        cliente_pedido = new JComboBox<>(listarClientes);
        cliente_pedido.setBounds(50, 120, 200, 20);
        add(cliente_pedido);

        //Listando produtos
        criarLabel("Produto: ", 50, 140, 200, 20);
        var todosProdutos = produtoService.retornarProdutos();
        var listarProdutos = todosProdutos
                .stream()
                .map(c -> c.getNome())
                .toArray(String[]::new);

        produto_pedido = new JComboBox<>(listarProdutos);
        produto_pedido.setBounds(50, 160, 200, 20);
        add(produto_pedido);

        criarLabel("Quantidade do produto: ", 50, 180, 200, 20);
        quantidade_Produto = new JTextField();
        quantidade_Produto.setBounds(50, 200, 200, 20);
        add(quantidade_Produto);

        cadastrarEditarPedido = new JButton("Salvar");
        criarBotao(cadastrarEditarPedido, 50, 240, 100, 20);
        configurarAcaoBotaoSalvar(isCadastrar);

        setLayout(null);
        setVisible(true);
        setSize(500, 380);
    }

    /**
     * Cadastra o pedido.
     *
     * @param actionEvent
     * @return void
     */
    private void cadastrarPedido(ActionEvent actionEvent) {
        try {
            Pedido pedido = new Pedido(
                    dt_venda.getText()
                    , (String) cliente_pedido.getSelectedItem(),
                    (String) produto_pedido.getSelectedItem(),
                    Integer.parseInt(quantidade_Produto.getText())
            );
            pedidoService.cadastrarPedido(pedido);
            JOptionPane.showMessageDialog(this, "Pedido cadastrado com sucesso");
            dispose();
        } catch (DadosInvalidosException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Verifique os dados", JOptionPane.ERROR_MESSAGE);
        }


    }

    /**
     * Configura a acao do botao salvar.
     *
     * @param cadastro
     * @return void
     */
    private void configurarAcaoBotaoSalvar(boolean cadastro) {
        ActionListener acao;
        acao = cadastro ? this::cadastrarPedido : this::editarPedido;
        cadastrarEditarPedido.addActionListener(acao);
    }

    /**
     * Preenche o formulario com os dados do pedido para ser editado.
     *
     * @return void
     */
    private void preencherDadosPedidoEditar() {
        pedidoEditar.setDt_venda(dt_venda.getText());
        pedidoEditar.setIdentificadorCliente(String.valueOf(cliente_pedido.getSelectedItem()));
        pedidoEditar.setIndentificadorProduto(String.valueOf((produto_pedido.getSelectedItem())));
        pedidoEditar.setQt_produto(Integer.parseInt(quantidade_Produto.getText()));
    }

    /**
     * Editar dados do pedido
     *
     * @param actionEvent
     * @return void
     */
    private void editarPedido(ActionEvent actionEvent) {
        preencherDadosPedidoEditar();
        pedidoService.editar(pedidoEditar);
        edicaoConcluidaComSucesso();
    }

    /**
     * Quando existe edicao de pedido, aparece a mensagem de "Pedido atualizado com sucesso".
     *
     * @return void
     */
    private void edicaoConcluidaComSucesso() {
        callBackAtualizarLista.run();
        JOptionPane.showMessageDialog(this, "Pedido atualizado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
}
