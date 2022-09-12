package view;

import controller.service.ProdutoService;
import model.Produto;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Optional;

/**
 * Classe ConsultaProduto faz a consulta dos produtos e herda de Componentes.
 *
 * @author Ana Luiza Fernandes e Tales Rodrigues
 * @version 1.0
 * @see Componentes
 * @since 2022
 */
public class ConsultaProduto extends Componentes {

    private static JLabel titulo;
    private JList<String> consultaProduto;
    private static JButton buscarProduto;
    private static JButton excluiProduto;
    private static JButton editarProduto;
    private List<Produto> todosProdutos;
    private Optional<Integer> posicaoListaSelecionada = Optional.empty();
    private final ProdutoService produtoService;


    /**
     * Consulta o produto.
     */
    public ConsultaProduto() {
        produtoService = new ProdutoService();

        titulo = new JLabel("Produtos");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBounds(160, 10, 200, 30);
        add(titulo);
        //Listar produtos
        consultaProduto = new JList<>();
        consultaProduto.setBounds(20, 50, 440, 120);
        consultaProduto.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        consultaProduto.setVisibleRowCount(10);
        consultaProduto.getSelectionModel()
                .addListSelectionListener(this::salvarPosicaoSelecinadaNaLista);
        add(consultaProduto);
        //Exclui
        excluiProduto = new JButton("Excluir produto");
        excluiProduto.setBounds(250, 300, 180, 20);

        excluiProduto = new JButton("Excluir");
        excluiProduto.setBounds(30, 240, 180, 20);
        add(excluiProduto);
        excluiProduto.addActionListener(this::excluirProduto);

        //Buscar
        buscarProduto = new JButton("Buscar");
        buscarProduto.setBounds(30, 200, 180, 20);
        add(buscarProduto);
        buscarProduto.addActionListener(this::buscarProduto);
        //Editar
        editarProduto = new JButton("Editar");
        editarProduto.setBounds(250, 200, 180, 20);
        add(editarProduto);
        editarProduto.addActionListener(this::editarProduto);

        atualizarLista();

        setLayout(null);
        setVisible(true);
        setSize(500, 350);
    }

    /**
     * Busca pelos produtos.
     *
     * @param actionEvent
     * @return void
     */
    private void buscarProduto(ActionEvent actionEvent) {
        String nomeProduto = JOptionPane.showInputDialog("Insira o nome do produto que deseja buscar");
        var produtoEncontrado = produtoService.retornarProdutoPorNome(nomeProduto);

        if (Optional.empty().equals(produtoEncontrado)) {
            JOptionPane.showMessageDialog(this, "Produto não encontrado");
        } else {
            var buscaProduto = produtoEncontrado
                    .stream()
                    .map(c -> c.toString())
                    .toArray(String[]::new);

            JOptionPane.showMessageDialog(this, buscaProduto);
        }
    }

    /**
     * Exclui produtos.
     *
     * @param actionEvent
     * @return void
     */
    private void excluirProduto(ActionEvent actionEvent) {
        Produto produtoExcluir = retornarProdutoSelecionado(posicaoListaSelecionada.get());
        String nomeProduto1 = produtoExcluir.getNome();
        produtoService.excluirProduto(nomeProduto1);
        atualizarLista();
        JOptionPane.showMessageDialog(this, "Produto excluido com sucesso");
    }

    /**
     * Edita os produtos.
     *
     * @param actionEvent
     * @return void
     */
    private void editarProduto(ActionEvent actionEvent) {
        Produto produto = retornarProdutoSelecionado(posicaoListaSelecionada.get());
        new FormularioProduto(produto, () -> this.atualizarLista());
    }


    /**
     * Atualiza a lista de produto.
     *
     * @return void
     */
    public void atualizarLista() {
        String[] listaCliente;

        todosProdutos = produtoService.retornarProdutos();
        listaCliente = todosProdutos
                .stream()
                .map(c -> c.getNome())
                .toArray(String[]::new);

        consultaProduto.setListData(listaCliente);
        consultaProduto.updateUI();
        posicaoListaSelecionada = Optional.empty();
        desabilitarBotaoExcluir(excluiProduto);
        desabilitarBotaoEditar(editarProduto);
    }

    /**
     * Retorna produtos selecionados.
     *
     * @param posicao
     * @return Produto
     */
    private Produto retornarProdutoSelecionado(int posicao) {
        return todosProdutos.get(posicao);
    }

    /**
     * Salva posicao selecionada na lista.
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
        habilitarBotaoExcluir(excluiProduto);
        habilitarBotaoEditar(editarProduto);
    }


}

