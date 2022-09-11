package controller.respository;

import model.Produto;

import java.util.List;
import java.util.Optional;

/**
 * Interface ProdutoRepository serve para definir os metodos e esconder a implementacao.
 *
 * @author Ana Luiza Fernandes e Tales Rodrigues
 * @version 1.0
 * @since 2022
 */
public interface ProdutoRepository {
    /**
     * Salva o produto.
     *
     * @param produto
     * @return void
     */
    void salvar(Produto produto);

    /**
     * Deleta o produto.
     *
     * @param id
     * @return void
     */
    void deletar(String id);

    /**
     * Edita o produto.
     *
     * @param produto
     * @return void
     */
    void editar(Produto produto);

    /**
     * Retorna todos os produtos.
     *
     * @return List<Produto>
     */
    List<Produto> retornarTodosProdutos();

    /**
     * Pesquisa os produtos por nome.
     *
     * @param nomeProduto
     * @return Optional<Produto>
     */
    Optional<Produto> pesquisarPorNome(String nomeProduto);


}
