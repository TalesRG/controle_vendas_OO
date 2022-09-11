package controller.respository;

import model.Pedido;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Classe PedidoListRepository faz o controle da base de dados e implementa a interface PedidoRepository.
 *
 * @author Ana Luiza Fernandes e Tales Rodrigues
 * @version 1.0
 * @see controller.respository.PedidoRepository
 * @since 2022
 */
public class PedidoListRepository implements PedidoRepository {
    private static List<Pedido> storage = new ArrayList<>();
    private static int idAtual = 1;

    /**
     * Esta sobrescrevendo o metodo da interface PedidoRepository e salva um pedido na base de dados.
     *
     * @param pedido
     * @return void
     */
    @Override
    public void salvar(Pedido pedido) {
        pedido.setId(String.valueOf(idAtual));
        storage.add(pedido);
        idAtual++;
    }

    /**
     * Esta sobrescrevendo o metodo da interface PedidoRepository e retorna todos os pedidos na base de dados.
     *
     * @return List<Pedido>
     */
    @Override
    public List<Pedido> retornarTodosPedidos() {
        return storage;
    }

    /**
     * Esta sobrescrevendo o metodo da interface PedidoRepository e pesquisa por ID do pedido na base de dados.
     *
     * @param idPesquisado
     * @return Optional<Pedido>
     */
    @Override
    public Optional<Pedido> pesquisarPorId(String idPesquisado) {
        return storage
                .stream()
                .filter(pedidoArmazenado -> pedidoArmazenado.getId().equals(idPesquisado))
                .map(p -> p.clone())
                .findFirst();
    }

    /**
     * Esta sobrescrevendo o metodo da interface PedidoRepository e deleta o pedido na base de dados.
     *
     * @param idDeletar
     * @return void
     */
    @Override
    public void deletar(String idDeletar) {
        storage
                .removeIf(clienteArmazenado -> clienteArmazenado.getId().equals(idDeletar));
    }

    /**
     * Esta sobrescrevendo o metodo da interface PedidoRepository e edita o pedido na base de dados.
     *
     * @param pedido
     * @return void
     */
    @Override
    public void editar(Pedido pedido) {
        int posicaoNaLista = procurarPosicaoPedido(pedido.getId());
        // se encontrar o pedido edita ele
        if (posicaoNaLista != -1) {
            storage.set(posicaoNaLista, pedido.clone());
        }


    }

    /**
     * Procura Posicao do pedido na base de dados.
     *
     * @param id
     * @return int
     */
    private int procurarPosicaoPedido(String id) {
    	/* 
   	 Percorre a lista de pedidos se encontrar um pedido a posicao 
   	 dele na lista 
   	 */
        int contador = -1;
        for (Pedido pedido : storage) {
            contador = 1 + contador;
            if (pedido.getId().equals(id)) {
                return contador;
            }
        }
        return -1;
    }
}
