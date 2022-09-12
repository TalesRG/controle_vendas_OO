package controller.service;

import controller.repository.ProdutoListRepository;
import controller.repository.ProdutoRepository;
import controller.service.validator.DadosInvalidosException;
import model.Cliente;
import model.Produto;

import java.util.List;
import java.util.Optional;

/**
 * Classe ProdutoService cria as regras de negocio.
 *
 * @author Ana Luiza Fernandes e Tales Rodrigues
 * @version 1.0
 * @since 2022
 */
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    /**
     * Metodo construtor que atribui a variavel produtoRepository uma nova instancia da classe ProdutoListRepository.
     */
    public ProdutoService() {
        produtoRepository = new ProdutoListRepository();
    }

    /**
     * Valida os dados do produto e em seguida cadastra.
     *
     * @param produtoValido
     * @return Produto
     * @throws DadosInvalidosException
     */
    public Produto cadastrar(Produto produtoValido) throws DadosInvalidosException {
        produtoValido.validarProduto();
        produtoRepository.salvar(produtoValido);
        return produtoValido;
    }

    /**
     * Exclui o produto.
     *
     * @param nome
     * @return void
     */
    public void excluirProduto(String nome) {
        produtoRepository.deletar(nome);
    }

    /**
     * Retorna os produtos.
     *
     * @return List<Produto>
     */
    public List<Produto> retornarProdutos() {
        return produtoRepository.retornarTodosProdutos();
    }

    /**
     * Retorna produto por nome.
     *
     * @param nome
     * @return Optional<Produto>
     */
    public Optional<Produto> retornarProdutoPorNome(String nome) {
        return produtoRepository.pesquisarPorNome(String.valueOf(nome));
    }

    /**
     * Edita o produto.
     *
     * @param produto
     * @return void
     */
    public void editar(Produto produto) {
        produtoRepository.editar(produto);
    }

}
