package view;

import controller.service.ProdutoService;
import controller.service.validator.DadosInvalidosException;
import model.Produto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe FormularioProduto gera um formulario para cadastrar o produto e herda de Componentes.
 *
 * @author Ana Luiza Fernandes e Tales Rodrigues
 * @version 1.0
 * @see Componentes
 * @since 2022
 */
public class FormularioProduto extends Componentes {
    private JTextField nomeProduto;
    private JTextField fabricante;
    private JTextField precoVenda;
    private JTextField precoCusto;
    private JLabel titulo;
    private JButton cadastrarEditarProduto;
    private Produto produtoEditar;

    private ProdutoService produtoService;

    private Runnable callBackAtualizarLista;

    /**
     * Cria a tela de cadastro de pedido quando isCadastrar for TRUE
     */
    public FormularioProduto() {
        this(true);
    }

    /**
     * Quando isCadastrar for FALSE gera formulario de editar.
     *
     * @param produto
     * @param callBackAtualizarLista
     */
    public FormularioProduto(Produto produto, Runnable callBackAtualizarLista) {
        this(false);
        this.callBackAtualizarLista = callBackAtualizarLista;
        produtoEditar = produto;
        nomeProduto.setText(produto.getNome());
        nomeProduto.enable(false);
        fabricante.setText(produto.getFabricante());
        precoCusto.setText(String.valueOf(produto.getPrecoCusto()));
        precoVenda.setText(String.valueOf(produto.getPrecoVenda()));
    }

    /**
     * Gera telas de cadastro e editar conforme o status de isCadastrar.
     *
     * @param isCadastrar
     */
    private FormularioProduto(boolean isCadastrar) {
        produtoService = new ProdutoService();

        titulo = new JLabel("Cadastro produto");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBounds(160, 10, 200, 30);
        add(titulo);

        //Nome do Produto
        criarLabel("Nome: ", 50, 60, 200, 20);
        nomeProduto = new JTextField();
        nomeProduto.setBounds(50, 80, 200, 20);
        add(nomeProduto);
        //Fabricante
        criarLabel("Fabricante: ", 50, 100, 200, 20);
        fabricante = new JTextField();
        fabricante.setBounds(50, 120, 200, 20);
        add(fabricante);
        //Preço de venda
        criarLabel("R$", 30, 165, 30, 10);
        criarLabel("Preço de venda: ", 50, 140, 200, 10);
        precoVenda = new JTextField();
        precoVenda.setBounds(50, 160, 200, 20);
        add(precoVenda);
        //Preço de custo
        criarLabel("Preço de custo: ", 50, 180, 200, 10);
        criarLabel("R$", 30, 205, 30, 10);
        precoCusto = new JTextField();
        precoCusto.setBounds(50, 200, 200, 20);
        add(precoCusto);
        //Botao cadastrar produto
        cadastrarEditarProduto = new JButton("Salvar");
        criarBotao(cadastrarEditarProduto, 50, 240, 100, 20);
        configurarAcaoBotaoSalvar(isCadastrar);

        setLayout(null);
        setVisible(true);
        setSize(500, 380);
    }

    /**
     * Cadastra o produto.
     *
     * @param actionEvent
     */
    private void cadastrarProduto(ActionEvent actionEvent) {
        try {
            Produto produtoValidado = new Produto(
                    nomeProduto.getText(),
                    fabricante.getText(),
                    Double.parseDouble(precoVenda.getText()),
                    Double.parseDouble(precoCusto.getText())
            );
            produtoService.cadastrar(produtoValidado);
            JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso");
            dispose();
        } catch (DadosInvalidosException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Verifique os dados", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Configura a acao do botao salvar.
     *
     * @param cadastro
     */
    private void configurarAcaoBotaoSalvar(boolean cadastro) {
        ActionListener acao;
        acao = cadastro ? this::cadastrarProduto : this::editarProduto;
        cadastrarEditarProduto.addActionListener(acao);
    }

    /**
     * Preenche o formulario com os dados do produto para ser editado.
     *
     * @return void
     */
    private void preencherDadosProdutoEditar() {
        produtoEditar.setNome(nomeProduto.getText());
        produtoEditar.setFabricante(fabricante.getText());
        produtoEditar.setPrecoVenda(Double.parseDouble(precoVenda.getText()));
        produtoEditar.setPrecoCusto(Double.parseDouble(precoCusto.getText()));
    }

    /**
     * Edita produto.
     *
     * @param actionEvent
     * @return void
     */
    private void editarProduto(ActionEvent actionEvent) {
        preencherDadosProdutoEditar();
        produtoService.editar(produtoEditar);
        edicaoConcluidaComSucesso();
    }

    /**
     * Quando existe edicao de pedido, aparece a mensagem de "Pedido atualizado com sucesso".
     *
     * @return void
     */
    private void edicaoConcluidaComSucesso() {
        callBackAtualizarLista.run();
        JOptionPane.showMessageDialog(this, "Produto atualizado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
}
