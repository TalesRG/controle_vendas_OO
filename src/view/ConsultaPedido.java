package view;

import controller.service.PedidoService;
import model.Pedido;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Optional;

/**
 * Classe ConsultaPedido faz a consulta dos pedidos e herda de Componentes.
 *
 * @author Ana Luiza Fernandes e Tales Rodrigues
 * @version 1.0
 * @see Componentes
 * @since 2022
 */
public class ConsultaPedido extends Componentes {
    private static JLabel titulo;
    private static JButton buscarPedido;
    private static JButton excluiPedido;
    private static JButton editarPedido;
    private static JList<String> consultaPedido;
    private static List<Pedido> todosPedidos;
    private Optional<Integer> posicaoListaSelecionada = Optional.empty();
    private final PedidoService pedidoService;

    /**
     * Consulta o pedido.
     */
    public ConsultaPedido() {
        titulo = new JLabel("Pedidos");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBounds(125, 10, 300, 30);
        add(titulo);

        pedidoService = new PedidoService();

        consultaPedido = new JList<>();
        consultaPedido.setBounds(20, 50, 440, 120);
        consultaPedido.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        consultaPedido.setVisibleRowCount(10);
        consultaPedido.getSelectionModel()
                .addListSelectionListener(this::salvarPosicaoSelecinadaNaLista);
        add(consultaPedido);

        excluiPedido = new JButton("Excluir");
        excluiPedido.setBounds(30, 240, 180, 20);
        add(excluiPedido);
        excluiPedido.addActionListener(this::excluirPedido);

        //Buscar
        buscarPedido = new JButton("Buscar");
        buscarPedido.setBounds(30, 200, 180, 20);
        add(buscarPedido);
        buscarPedido.addActionListener(this::buscarPedido);

        editarPedido = new JButton("Editar");
        editarPedido.setBounds(250, 200, 180, 20);
        add(editarPedido);
        editarPedido.addActionListener(this::editarPedido);

        atualizarLista();

        setLayout(null);
        setVisible(true);
        setSize(500, 350);
    }

    /**
     * Busca pelo pedido.
     *
     * @param actionEvent
     * @return void
     */
    private void buscarPedido(ActionEvent actionEvent) {
        int idPedidoBusca = Integer.parseInt(JOptionPane.showInputDialog("Insira o id do cliente que deseja buscar"));
        var pedidoEncontrado = pedidoService.retornarPedidoPorId(idPedidoBusca);
        //Se não encotrar cliente mostrar mensagem de erro caso contrario mostrar o cliente
        if (Optional.empty().equals(pedidoEncontrado)) {
            JOptionPane.showMessageDialog(this, "Cliente não encontrado");
        } else {
            var buscaPedido = pedidoEncontrado
                    .stream()
                    .map(c -> c.toString())
                    .toArray(String[]::new);

            JOptionPane.showMessageDialog(this, buscaPedido);
        }
    }

    /**
     * Exclui o pedido.
     *
     * @param actionEvent
     * @return void
     */
    private void excluirPedido(ActionEvent actionEvent) {
        Pedido pedidoExcluir = retornarPedidoSelecionado(posicaoListaSelecionada.get());
        String idPedido = pedidoExcluir.getId();
        pedidoService.excluirPedido(idPedido);
        atualizarLista();
        JOptionPane.showMessageDialog(this, "Pedido Excluido com sucesso");
    }

    /**
     * Editar pedido.
     *
     * @param actionEvent
     * @return void
     */
    private void editarPedido(ActionEvent actionEvent) {
        Pedido pedido = retornarPedidoSelecionado(posicaoListaSelecionada.get());
        new FormularioPedido(pedido, () -> this.atualizarLista());
    }

    /**
     * Atualiza a lista de pedidos.
     *
     * @return void
     */
    public void atualizarLista() {
        String[] listaCliente;

        todosPedidos = pedidoService.retornarPedido();
        listaCliente = todosPedidos
                .stream()
                .map(c -> "Numero Pedido: " + c.getId() + "  " + c.getIdentificadorCliente() + " - " + c.getDtVenda())
                .toArray(String[]::new);

        consultaPedido.setListData(listaCliente);
        consultaPedido.updateUI();
        posicaoListaSelecionada = Optional.empty();
        desabilitarBotaoExcluir(excluiPedido);
        desabilitarBotaoEditar(editarPedido);
    }

    /**
     * Retorna todos os pedidos que foram selecionados.
     *
     * @param posicao
     * @return Pedido
     */
    private Pedido retornarPedidoSelecionado(int posicao) {
        return todosPedidos.get(posicao);
    }

    /**
     * Salva a posicao selecionada na lista.
     *
     * @param e
     * @return void
     */
    private void salvarPosicaoSelecinadaNaLista(ListSelectionEvent e) {
    	//Salva a posicao do elemento que o usuario clicar
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
        habilitarBotaoExcluir(excluiPedido);
        habilitarBotaoEditar(editarPedido);
    }

}
