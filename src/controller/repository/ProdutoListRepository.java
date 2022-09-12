package controller.repository;

import model.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * Classe ProdutoListRepository faz o controle da base de dados e implementa a interface ProdutoRepository.
 *
 * @author Ana Luiza Fernandes e Tales Rodrigues
 * @version 1.0
 * @see controller.repository.ProdutoRepository
 * @since 2022
 */
public class ProdutoListRepository implements ProdutoRepository {
    private static List<Produto> storage = new ArrayList<>();

    /**
     * Esta sobrescrevendo o metodo da interface ProdutoRepository e salva um produto na base de dados.
     *
     * @param produto
     * @return void
     */
    public void salvar(Produto produto) {
        storage.add(produto);
    }

    /**
     * Esta sobrescrevendo o metodo da interface ProdutoRepository e retorna todos os produtos na base de dados.
     *
     * @return List<Produto>
     */
    @Override
    public List<Produto> retornarTodosProdutos() {
        return storage;
    }

    /**
     * Esta sobrescrevendo o metodo da interface ProdutoRepository e deleta o produto da base de dados.
     *
     * @param nome
     * @return void
     */
    @Override
    public void deletar(String nome) {
        storage
                .removeIf(produtoArmazenado -> produtoArmazenado.getNome().equals(nome));

    }

    /**
     * Esta sobrescrevendo o metodo da interface ProdutoRepository e pesquisa por nome do produto na base de dados.
     *
     * @param nomeProduto
     * @return Optional<Produto>
     */
    @Override
    public Optional<Produto> pesquisarPorNome(String nomeProduto) {
        return storage
                .stream()
                .filter(clienteArmazenado -> clienteArmazenado.getNome().equals(nomeProduto))
                .map(p -> p.clone())
                .findFirst();
    }

    /**
     * Esta sobrescrevendo o metodo da interface ProdutoRepository e edita o produto na base de dados.
     *
     * @param produto
     * @return void
     */
    @Override
    public void editar(Produto produto) {
    	// se encontar posicao do produto na lista edita ele
        int posicaoNaLista = procurarPosicaoProduto(produto.getNome());
        if (posicaoNaLista != -1) {
            storage.set(posicaoNaLista, produto.clone());
        }

    }

    /**
     * Procura posicao do produto na base de dados.
     *
     * @param nomeProduto
     * @return int
     */
    private int procurarPosicaoProduto(String nomeProduto) {
    	/* 
   	 Percorre a lista de produtos se encontrar um produto retorna a posicao 
   	 dele na lista 
   	 */
        int contador = -1;
        for (Produto produto : storage) {
            contador = 1 + contador;
            if (produto.getNome().equals(nomeProduto)) {
                return contador;
            }
        }
        return -1;
    }
}


