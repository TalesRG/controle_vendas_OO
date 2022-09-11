package controller.service;

import controller.service.validator.DadosInvalidosException;
import controller.service.validator.Validador;
import model.Pedido;
import controller.respository.PedidoListRepository;
import controller.respository.PedidoRepository;

import java.util.List;
import java.util.Optional;

/**
 * Classe PedidoService cria as regras de negocio.
 *
 * @author Ana Luiza Fernandes e Tales Rodrigues
 * @version 1.0
 * @since 2022
 */
public class PedidoService {
    private final PedidoRepository pedidoRepository;

    /**
     * Metodo construtor que atribui a variavel pedidoRepository uma nova instancia da classe PedidoListRepository.
     */
    public PedidoService() {
        pedidoRepository = new PedidoListRepository();
    }

    /**
     * Valida os dados do pedido e em seguida cadastra.
     *
     * @param pedido
     * @return Pedido
     * @throws DadosInvalidosException
     */
    public Pedido cadastrarPedido(Pedido pedido) throws DadosInvalidosException {
        pedido.validarPedido();
        pedidoRepository.salvar(pedido);
        return pedido;
    }

    /**
     * Retorna o pedido.
     *
     * @return List<Pedido>
     */
    public List<Pedido> retornarPedido() {
        return pedidoRepository.retornarTodosPedidos();
    }

    /**
     * Retorna o pedido por ID.
     *
     * @param id
     * @return Optional<Pedido>
     */
    public Optional<Pedido> retornarPedidoPorId(int id) {
        return pedidoRepository.pesquisarPorId(String.valueOf(id));
    }

    /**
     * Exclui o pedido.
     *
     * @param id
     * @return void
     */
    public void excluirPedido(String id) {
        pedidoRepository.deletar(id);
    }

    /**
     * Edita o pedido.
     *
     * @param pedido
     * @return void
     */
    public void editar(Pedido pedido) {
        pedidoRepository.editar(pedido);
    }
}
