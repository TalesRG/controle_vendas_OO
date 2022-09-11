package controller.service;

import controller.service.validator.DadosInvalidosException;
import model.Cliente;
import model.PessoaFisica;
import model.PessoaJuridica;
import controller.respository.ClienteRepository;
import controller.respository.ClienteListRepository;

import java.awt.*;
import java.util.List;
import java.util.Optional;

/**
 * Classe ClienteService cria as regras de negocio.
 *
 * @author Ana Luiza Fernandes e Tales Rodrigues
 * @version 1.0
 * @since 2022
 */
public class ClienteService {
    private final ClienteRepository clienteRepository;

    /**
     * Metodo construtor que atribui a variavel clienteRepository uma nova instancia da classe ClienteListRepository.
     */
    public ClienteService() {
        clienteRepository = new ClienteListRepository();
    }

    /**
     * Valida os dados do cliente pessoa fisica e em seguida cadastra.
     *
     * @param cliente
     * @return PessoaFisica
     * @throws DadosInvalidosException
     */
    public PessoaFisica cadastrarPessoaFisica(PessoaFisica cliente) throws DadosInvalidosException {
        cliente.validarCliente();
        clienteRepository.salvar(cliente);
        return cliente;
    }

    /**
     * Valida os dados do cliente pessoa juridica e em seguida cadastra.
     *
     * @param cliente
     * @return PessoaJuridica
     * @throws DadosInvalidosException
     */
    public PessoaJuridica cadastrarPessoaJuridica(PessoaJuridica cliente) throws DadosInvalidosException {
        cliente.validarCliente();
        clienteRepository.salvar(cliente);
        return cliente;
    }

    /**
     * Edita o cliente.
     *
     * @param cliente
     * @return void
     */
    public void editar(Cliente cliente) {
        clienteRepository.editar(cliente);
    }

    /**
     * Retorna todas as pessoas fisicas.
     *
     * @return List<PessoaFisica>
     */
    public List<PessoaFisica> retornarTodasPessoasFisicas() {
        return clienteRepository.pesquisarPessoasFisicas();
    }

    /**
     * Retorna todas as pessoas juridicas.
     *
     * @return List<PessoaJuridica>
     */
    public List<PessoaJuridica> retornarTodasPessoasJuridicas() {
        return clienteRepository.pesquisarPessoasJuridicas();
    }

    /**
     * Retorna todos os clientes.
     *
     * @return List<Cliente>
     */
    public List<Cliente> retornarTodosClientes() {
        return clienteRepository.retornarTodosClientes();
    }

    /**
     * Retorna os clientes fisicos por CPF.
     *
     * @param cpf
     * @return Optional<PessoaFisica>
     */
    public Optional<PessoaFisica> retornarPorCpf(String cpf) {
        return clienteRepository.pesquisarPorCpf(cpf);
    }

    /**
     * Retorna os clientes juridicos por CNPJ.
     *
     * @param cnpj
     * @return Optional<PessoaJuridica>
     */
    public Optional<PessoaJuridica> retornarPorCnpj(String cnpj) {
        return clienteRepository.pesquisarPorCnpj(cnpj);
    }

    /**
     * Exclui os clientes de pessoa fisica.
     *
     * @param cpf
     * @return void
     */
    public void excluirClientePessoaFisica(String cpf) {
        clienteRepository.deletarPessoaFisica(cpf);
    }

    /**
     * Exclui os clientes de pessoa juridica.
     *
     * @param cnpj
     * @return void
     */
    public void excluirClientePessoaJuridica(String cnpj) {
        clienteRepository.deletarPessoaJuridica(cnpj);
    }

}


