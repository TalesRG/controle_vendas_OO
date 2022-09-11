package controller.respository;

import model.Cliente;
import model.PessoaFisica;
import model.PessoaJuridica;

import java.util.List;
import java.util.Optional;

/**
 * Interface ClienteRepository serve para definir os metodos e esconder a implementacao.
 *
 * @author Ana Luiza Fernandes e Tales Rodrigues
 * @version 1.0
 * @since 2022
 */
public interface ClienteRepository {

    /**
     * Salva o cliente.
     *
     * @param cliente
     * @return void
     */
    void salvar(Cliente cliente);

    /**
     * Edita o cliente.
     *
     * @param cliente
     * @return void
     */
    void editar(Cliente cliente);

    /**
     * Deleta pessoa fisica.
     *
     * @param cpf
     * @return void
     */
    void deletarPessoaFisica(String cpf);

    /**
     * Deleta pessoa juridica.
     *
     * @param cnpj
     * @return void
     */
    void deletarPessoaJuridica(String cnpj);

    /**
     * Retorna todos os clientes.
     *
     * @return List<Cliente>
     */
    List<Cliente> retornarTodosClientes();

    /**
     * Pesquisa por CPF.
     *
     * @param cpf
     * @return Optional<PessoaFisica>
     */
    Optional<PessoaFisica> pesquisarPorCpf(String cpf);

    /**
     * Pesquisa por CNPJ.
     *
     * @param cnpj
     * @return Optional<PessoaJuridica>
     */
    Optional<PessoaJuridica> pesquisarPorCnpj(String cnpj);

    /**
     * Pesquisa por pessoa fisica.
     *
     * @return List<PessoaFisica>
     */
    List<PessoaFisica> pesquisarPessoasFisicas();

    /**
     * Pesquisa por pessoa juridica
     *
     * @return List<PessoaJuridica>
     */
    List<PessoaJuridica> pesquisarPessoasJuridicas();
}
