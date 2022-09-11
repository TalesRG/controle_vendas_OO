package view;

import controller.service.ClienteService;
import controller.service.validator.DadosInvalidosException;
import model.Cliente;
import model.PessoaFisica;
import model.PessoaJuridica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static view.MascaraUtil.*;

/**
 * Classe FormularioCliente gera um formulario para cadastrar o cliente e herda de Componentes.
 *
 * @author Ana Luiza Fernandes e Tales Rodrigues
 * @version 1.0
 * @see Componentes
 * @since 2022
 */
public class FormularioCliente extends Componentes {
    //Cliente
    private JTextField nomeCliente;
    private JFormattedTextField telefone;
    private JFormattedTextField cpf;
    private JFormattedTextField cnpj;
    private JTextField endereco;
    private JTextField email;
    private JLabel titulo;
    private JButton botaoCadastrarEditar;
    private ClienteService clienteService;
    private Cliente clienteEditar;
    private Runnable callBackAtualizarLista;

    /**
     * Cria a tela de cadastro de cliente quando isCadastrar for TRUE
     *
     * @param opcao
     */
    public FormularioCliente(int opcao) {
        this(opcao, true);
    }

    /**
     * Quando isCadastrar for FALSE gera formulario de editar.
     *
     * @param opcao
     * @param cliente
     * @param callBackAtualizarLista
     */
    public FormularioCliente(int opcao, Cliente cliente, Runnable callBackAtualizarLista) {
        this(opcao, false);
        this.callBackAtualizarLista = callBackAtualizarLista;
        if (opcao == 1) {
            PessoaFisica pessoaFisica = (PessoaFisica) cliente;
            cpf.setText(pessoaFisica.getCpf());
            cpf.enable(false);
            titulo.setText("Editar Pessoa Fisica");
        } else {
            PessoaJuridica pessoaJuridica = (PessoaJuridica) cliente;
            cnpj.setText(pessoaJuridica.getCnpj());
            cnpj.enable(false);
            titulo.setText("Editar Pesoa Juridica");
        }
        clienteEditar = cliente;
        nomeCliente.setText(clienteEditar.getNome());
        telefone.setText(clienteEditar.getTelefone());
        email.setText(clienteEditar.getEmail());
        endereco.setText(clienteEditar.getEndereco());
    }

    /**
     * Gera telas de cadastro e editar conforme o status de isCadastrar.
     *
     * @param opcao
     * @param isCadastrar
     */
    private FormularioCliente(int opcao, boolean isCadastrar) {
        clienteService = new ClienteService();

        //Nome
        criarLabel("Nome:", 50, 60, 200, 20);
        nomeCliente = new JTextField();
        nomeCliente.setBounds(50, 80, 200, 20);
        add(nomeCliente);

        //Telefone
        criarLabel("Telefone", 50, 100, 200, 20);
        telefone = new JFormattedTextField(mascaraTelefone());
        telefone.setBounds(50, 120, 200, 20);
        add(telefone);

        //Email
        criarLabel("E-mail:", 50, 180, 200, 20);
        email = new JTextField();
        email.setBounds(50, 200, 200, 20);
        add(email);
        //Endereço
        criarLabel("Endereço: ", 50, 220, 200, 20);
        endereco = new JTextField();
        endereco.setBounds(50, 240, 200, 20);
        add(endereco);

        //Botão Cadastrar
        botaoCadastrarEditar = new JButton("Salvar");
        criarBotao(botaoCadastrarEditar, 50, 280, 100, 20);

        String tituloTela = "";

        configurarAcaoBotaoSalvar(opcao, isCadastrar);

        switch (opcao) {
            case 1:
                tituloTela = "Cadastro Pessoa Fisica";
                //CPF
                criarLabel("CPF: ", 50, 140, 200, 20);
                cpf = new JFormattedTextField(mascaraCpf());
                cpf.setBounds(50, 160, 200, 20);
                add(cpf);
                break;
            case 2:
                tituloTela = "Cadastro Pessoa Juridica";
                //CNPJ
                criarLabel("CNPJ: ", 50, 140, 200, 20);
                cnpj = new JFormattedTextField(mascaraCnpj());
                cnpj.setBounds(50, 160, 200, 20);
                add(cnpj);
                break;

        }

        titulo = new JLabel(tituloTela);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBounds(125, 10, 300, 30);
        add(titulo);

        setLayout(null);
        setVisible(true);

        setSize(500, 380);
    }

    /**
     * Gera o formulario para se cadastrar o cliente pessoa juridica.
     *
     * @param actionEvent
     * @return void
     */
    private void cadastrarClientePessoaJuridica(ActionEvent actionEvent) {
        try {
            PessoaJuridica commandInserir = new PessoaJuridica(
                    nomeCliente.getText(),
                    telefone.getText(),
                    email.getText(),
                    endereco.getText(),
                    cnpj.getText()
            );
            clienteService.cadastrarPessoaJuridica(commandInserir);
            JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso");
            dispose();
        } catch (DadosInvalidosException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Verifique os dados", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Configura a acao do botao salvar.
     *
     * @param opcao
     * @param cadastro
     * @return void
     */
    private void configurarAcaoBotaoSalvar(int opcao, boolean cadastro) {
        ActionListener acao;
        if (opcao == 1) {
            acao = cadastro ? this::cadastrarClientePessoaFisica : this::editarPessoaFisica;
        } else {
            acao = cadastro ? this::cadastrarClientePessoaJuridica : this::editarPessoaJuridica;
        }
        botaoCadastrarEditar.addActionListener(acao);
    }

    /**
     * Cadastra por pessoa fisica.
     *
     * @param actionEvent
     * @return void
     */
    private void cadastrarClientePessoaFisica(ActionEvent actionEvent) {
        try {
            PessoaFisica commandInserir = new PessoaFisica(
                    nomeCliente.getText(),
                    telefone.getText(),
                    email.getText(),
                    endereco.getText(),
                    cpf.getText()
            );
            clienteService.cadastrarPessoaFisica(commandInserir);
            JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso");
            dispose();
        } catch (DadosInvalidosException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Verifique os dados", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Preenche o formulario com os dados do cliente para ser editado.
     *
     * @return void
     */
    private void preencherDadosClienteEditar() {
        clienteEditar.setNome(nomeCliente.getText());
        clienteEditar.setTelefone(telefone.getText());
        clienteEditar.setEmail(email.getText());
        clienteEditar.setEndereco(endereco.getText());
    }

    /**
     * Edita dados de pessoa fisica.
     *
     * @param actionEvent
     * @return void
     */
    private void editarPessoaFisica(ActionEvent actionEvent) {
        preencherDadosClienteEditar();
        ((PessoaFisica) clienteEditar).setCpf(cpf.getText());
        clienteService.editar(clienteEditar);
        edicaoConcluidaComSucesso();
    }

    /**
     * Edita pessoa juridica.
     *
     * @param actionEvent
     * @return void
     */
    private void editarPessoaJuridica(ActionEvent actionEvent) {
        preencherDadosClienteEditar();
        ((PessoaJuridica) clienteEditar).setCnpj(cnpj.getText());
        clienteService.editar(clienteEditar);
        edicaoConcluidaComSucesso();
    }

    /**
     * Quando existe edicao de cliente, aparece a mensagem de "Cliente atualizado com sucesso".
     *
     * @return void
     */
    private void edicaoConcluidaComSucesso() {
        callBackAtualizarLista.run();
        JOptionPane.showMessageDialog(this, "Cliente atualizado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }

}
