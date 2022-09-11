package controller.respository;

import model.Pedido;

import java.util.List;
import java.util.Optional;

/**
 * Interface PedidoRepository serve para definir os metodos e esconder a implementacao.
 *
 * @author Ana Luiza Fernandes e Tales Rodrigues
 * @version 1.0
 * @since 2022
 */
public interface PedidoRepository {

    /**
     * Salva o pedido.
     *
     * @param pedido
     * @return void
     */
    void salvar(Pedido pedido);

    /**
     * Deleta o pedido.
     *
     * @param id
     * @return void
     */
    void deletar(String id);

    /**
     * Edita o pedido.
     *
     * @param pedido
     * @return void
     */
    void editar(Pedido pedido);

    /**
     * Retorna todos os pedidos.
     *
     * @return List<Pedido>
     */
    List<Pedido> retornarTodosPedidos();

    /**
     * Pesquisa por ID.
     *
     * @param id
     * @return Optional<Pedido>
     */
    Optional<Pedido> pesquisarPorId(String id);

}
