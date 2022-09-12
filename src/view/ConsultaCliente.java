package view;

import controller.service.ClienteService;
import model.Cliente;
import model.PessoaFisica;
import model.PessoaJuridica;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.util.Optional;


/**
 * Classe ConsultaCliente faz a consulta dos clientes e herda de Componentes.
 *
 * @author Ana Luiza Fernandes e Tales Rodrigues
 * @version 1.0
 * @see Componentes
 * @since 2022
 */
public class ConsultaCliente extends Componentes {
    private static JLabel titulo;
    private static JList<String> consultaCliente;
    private static JButton excluiCliente;
    private static JButton buscarCliente;
    private static JButton editarCliente;

    private ClienteService clienteService;

    private List<PessoaFisica> todosClientesPessoasFisicas;
    private List<PessoaJuridica> todosClientesPessoasJuridica;
    private Optional<Integer> posicaoListaSelecionada = Optional.empty();
    private final int tipoCliente;

    /**
     * Consulta o cliente.
     *
     * @param opcao
     */
    public ConsultaCliente(int opcao) {
        tipoCliente = opcao;
        clienteService = new ClienteService();

        String tituloTela = isTipoTelaPessoaFisica() ? "Consulta Pessoa Fisica" : "Consulta Pessoa Juridica";
        titulo = new JLabel(tituloTela);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBounds(125, 10, 300, 30);
        add(titulo);

        consultaCliente = new JList<>();
        consultaCliente.setBounds(20, 50, 440, 120);
        consultaCliente.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        consultaCliente.setVisibleRowCount(10);
        consultaCliente
                .getSelectionModel()
                .addListSelectionListener(this::salvarPosicaoSelecinadaNaLista);
        add(consultaCliente);

        //excluir
        excluiCliente = new JButton("Excluir");
        excluiCliente.setBounds(30, 240, 180, 20);
        add(excluiCliente);
        excluiCliente.addActionListener(this::excluirCliente);

        editarCliente = new JButton("Editar");
        editarCliente.setBounds(250, 200, 180, 20);

        add(editarCliente);
        editarCliente.addActionListener(this::editarCliente);

        ActionListener escolhaBotao = isTipoTelaPessoaFisica() ? this::buscarClientePessoaFisica : this::buscarClientePessoaJuridica;
        buscarCliente = new JButton("Buscar");
        buscarCliente.setBounds(30, 200, 180, 20);
        add(buscarCliente);
        buscarCliente.addActionListener(escolhaBotao);

        atualizarLista();

        setLayout(null);
        setVisible(true);
        setSize(500, 350);
    }

    /**
     * Edita o cliente.
     *
     * @param actionEvent
     * @return void
     */
    private void editarCliente(ActionEvent actionEvent) {
        Cliente cliente = retornarClienteSelecionado(posicaoListaSelecionada.get());
        new FormularioCliente(tipoCliente, cliente, () -> this.atualizarLista());
    }

    /**
     * Atualiza a lista de clientes fisicos e juridicos.
     *
     * @return void
     */
    public void atualizarLista() {
        String[] listaCliente;
        if (isTipoTelaPessoaFisica()) {
            todosClientesPessoasFisicas = clienteService.retornarTodasPessoasFisicas();
            listaCliente = todosClientesPessoasFisicas
                    .stream()
                    .map(c -> c.getNome() + " - " + c.getCpf())
                    .toArray(String[]::new);
        } else {
            todosClientesPessoasJuridica = clienteService.retornarTodasPessoasJuridicas();
            listaCliente = todosClientesPessoasJuridica
                    .stream()
                    .map(c -> c.getNome() + " - " + c.getCnpj())
                    .toArray(String[]::new);
        }
        consultaCliente.setListData(listaCliente);
        consultaCliente.updateUI();
        posicaoListaSelecionada = Optional.empty();
        desabilitarBotaoExcluir(excluiCliente);
        desabilitarBotaoEditar(editarCliente);
    }

    /**
     * Busca por cliente pessoa fisica.
     *
     * @param actionEvent
     * @return void
     */
    private void buscarClientePessoaFisica(ActionEvent actionEvent) {
        String idClienteBusca = JOptionPane.showInputDialog("Insira o CPF do cliente que deseja buscar");
        var clienteEncontrado = clienteService.retornarPorCpf(idClienteBusca);

        if (clienteEncontrado.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Cliente não encontrado");
        } else {
            var buscaCliente = clienteEncontrado
                    .stream()
                    .map(c -> c.toString())
                    .toArray(String[]::new);

            JOptionPane.showMessageDialog(this, buscaCliente);
        }
    }

    /**
     * Busca por cliente pessoa juridica.
     *
     * @param actionEvent
     * @return void
     */
    private void buscarClientePessoaJuridica(ActionEvent actionEvent) {
        String idClienteBusca = JOptionPane.showInputDialog("Insira o CNPJ do cliente que deseja buscar");
        var clienteEncontrado = clienteService.retornarPorCnpj(idClienteBusca);

        if (clienteEncontrado.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Cliente não encontrado");
        } else {
            var buscaCliente = clienteEncontrado
                    .stream()
                    .map(c -> c.toString())
                    .toArray(String[]::new);

            JOptionPane.showMessageDialog(this, buscaCliente);
        }
    }

    /**
     * Exclui cliente.
     *
     * @param actionEvent
     * @return void
     */
    private void excluirCliente(ActionEvent actionEvent) {
        Cliente clienteExcluir = retornarClienteSelecionado(posicaoListaSelecionada.get());

        if (isTipoTelaPessoaFisica()) {
            String cpf = ((PessoaFisica) clienteExcluir).getCpf();
            clienteService.excluirClientePessoaFisica(cpf);
        } else {
            String cpnj = ((PessoaJuridica) clienteExcluir).getCnpj();
            clienteService.excluirClientePessoaJuridica(cpnj);
        }
        atualizarLista();
        JOptionPane.showMessageDialog(this, "Cliente excluido com sucesso");
    }

    /**
     * Salva por posicao na lista.
     *
     * @param e
     * @return void
     */
    private void salvarPosicaoSelecinadaNaLista(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return;
        }
        ListSelectionModel lsm = (ListSelectionModel) e.getSource();
        if (lsm.isSelectionEmpty()) {
            posicaoListaSelecionada = Optional.empty();
        } else {
            int minIndex = lsm.getMinSelectionIndex();
            int maxIndex = lsm.getMaxSelectionIndex();
            for (int i = minIndex; i <= maxIndex; i++) {
                if (lsm.isSelectedIndex(i)) {
                    posicaoListaSelecionada = Optional.of(i);
                }
            }
        }
        habilitarBotaoExcluir(excluiCliente);
        habilitarBotaoEditar(editarCliente);
    }

    /**
     * Retorna o cliente que foi selecionado.
     *
     * @param posicao
     * @return Cliente
     */
    private Cliente retornarClienteSelecionado(int posicao) {
        if (isTipoTelaPessoaFisica()) {
            return todosClientesPessoasFisicas.get(posicao);
        } else {
            return todosClientesPessoasJuridica.get(posicao);
        }
    }

    /**
     * Retorna o status do isTipoTelaPessoaFisica.
     *
     * @return boolean
     */
    private boolean isTipoTelaPessoaFisica() {
        return tipoCliente == 1;
    }


}

