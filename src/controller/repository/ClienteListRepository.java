package controller.repository;

import model.Cliente;
import model.PessoaFisica;
import model.PessoaJuridica;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Classe ClienteListRepository faz o controle da base de dados e implementa a interface ClienteRepository.
 *
 * @author Ana Luiza Fernandes e Tales Rodrigues
 * @version 1.0
 * @see controller.repository.ClienteRepository
 * @since 2022
 */
public class ClienteListRepository implements ClienteRepository {

    private static List<Cliente> storage = new ArrayList<>();

    /**
     * Esta sobrescrevendo o metodo da interface ClienteRepository e salva um cliente na base de dados.
     *
     * @param cliente
     * @return void
     */
    //Salva o cliente numa lista de clientes.
    @Override
    public void salvar(Cliente cliente) {
        storage.add(cliente);
    }

    /**
     * Esta sobrescrevendo o metodo da interface ClienteRepository e edita um cliente na base de dados.
     *
     * @param cliente
     * @return void
     */
    @Override
    public void editar(Cliente cliente) {
        /*Se o cliente for pessoa fisica procura a posicao na do cliente na lista pelo cpf 
         se encontrar edita o cliente anterior
         caso contrario faça a mesma coisa porem com pessoa juridica
    	*/
        if (cliente.isPessoaFisica()) {
            PessoaFisica pessoaFisica = (PessoaFisica) cliente;
            int posicaoNaLista = procurarPosicaoPessaoFisica(pessoaFisica.getCpf());
            if (posicaoNaLista != -1) {
                storage.set(posicaoNaLista, pessoaFisica.clone());
            }
        } else {
            PessoaJuridica pessoaJuridica = (PessoaJuridica) cliente;
            int posicaoNaLista = procurarPosicaoPessaoJuridica(pessoaJuridica.getCnpj());
            if (posicaoNaLista != -1) {
                storage.set(posicaoNaLista, pessoaJuridica.clone());
            }
        }
    }

    /**
     * Esta sobrescrevendo o metodo da interface ClienteRepository e percorre a Lista e filtra os clientes com o CPF.
     *
     * @param cpf
     * @return Optional<PessoaFisica>
     */
    @Override
    //Percorre a Lista e filtra os clientes com o cpf passado no parametro
    public Optional<PessoaFisica> pesquisarPorCpf(String cpf) {
        return pesquisarPessoasFisicas()
                .stream()
                .filter(clienteArmazenado -> clienteArmazenado.getCpf().equals(cpf))
                .map(p -> p.clone())
                .findFirst();
    }


    /**
     * Esta sobrescrevendo o metodo da interface ClienteRepository e percorre a Lista e filtra os clientes com o CNPJ.
     *
     * @param cnpj
     * @return Optional<PessoaJuridica>
     */
    @Override
    //Percorre a Lista e filtra os clientes com o cnpj passado no parametro
    public Optional<PessoaJuridica> pesquisarPorCnpj(String cnpj) {
        return pesquisarPessoasJuridicas()
                .stream()
                .filter(clienteArmazenado -> clienteArmazenado.getCnpj().equals(cnpj))
                .map(p -> p.clone())
                .findFirst();
    }

    /**
     * Esta sobrescrevendo o metodo da interface ClienteRepository e retorna somente os clientes pessoa fisica.
     *
     * @return List<PessoaFisica>
     */
    @Override
    public List<PessoaFisica> pesquisarPessoasFisicas() {
        return storage
                .stream()
                .filter(clienteArmazenado -> clienteArmazenado.isPessoaFisica())
                .map(clienteFiltrado -> (PessoaFisica) clienteFiltrado)
                .map(p -> p.clone())
                .collect(Collectors.toList());
    }

    /**
     * Esta sobrescrevendo o metodo da interface ClienteRepository e retorna somente os clientes pessoa juridica.
     *
     * @return List<PessoaJuridica>
     */
    //
    @Override
    public List<PessoaJuridica> pesquisarPessoasJuridicas() {
        return storage
                .stream()
                .filter(clienteArmazenado -> clienteArmazenado.isPessoaJuridica())
                .map(clienteFiltrado -> (PessoaJuridica) clienteFiltrado)
                .map(p -> p.clone())
                .collect(Collectors.toList());
    }

    /**
     * Esta sobrescrevendo o metodo da interface ClienteRepository e retorna todos os clientes.
     *
     * @return List<Cliente>
     */
    @Override
    public List<Cliente> retornarTodosClientes() {
        return storage;
    }

    /**
     * Esta sobrescrevendo o metodo da interface ClienteRepository e deleta o cliente quando pessoa fisica.
     *
     * @param cpf
     * @return void
     */
    //
    @Override
    public void deletarPessoaFisica(String cpf) {
        storage
                .removeIf(clienteArmazenado -> clienteArmazenado.isPessoaFisica()
                        && ((PessoaFisica) clienteArmazenado).getCpf().equals(cpf));
    }

    /**
     * Esta sobrescrevendo o metodo da interface ClienteRepository e deleta o cliente quando pessoa juridica.
     *
     * @param cnpj
     * @return void
     */
    @Override
    //deleta o cliente quando é Pessoa fisica
    public void deletarPessoaJuridica(String cnpj) {
        storage
                .removeIf(clienteArmazenado ->
                        clienteArmazenado.isPessoaJuridica() &&
                                ((PessoaJuridica) clienteArmazenado).getCnpj().equals(cnpj)
                );
    }

    /**
     * Percorre a lista e procura a posicao da pessoa fisica.
     *
     * @param cpf
     * @return int
     */
    private int procurarPosicaoPessaoFisica(String cpf) { 
    	/* 
    	 Percorre a lista de clientes se encontrar um cliente pessoa fisica retorna a posicao 
    	 dele na lista 
    	 */
        int contador = -1; // 2
        for (Cliente cliente : storage) {
            contador = 1 + contador;
            if (cliente.isPessoaFisica()) {
                PessoaFisica pessoaFisica = (PessoaFisica) cliente;
                if (pessoaFisica.getCpf().equals(cpf)) {
                    return contador;
                }
            }
        }
        return -1;
    }

    /**
     * Percorre a lista e procura a posicao da pessoa juridica.
     *
     * @param cnpj
     * @return int
     */
    private int procurarPosicaoPessaoJuridica(String cnpj) {
    	/* 
   	 Percorre a lista de clientes se encontrar um cliente pessoa juriica retorna a posicao 
   	 dele na lista 
   	 */
        int contador = -1;
        for (Cliente cliente : storage) {
            contador = 1 + contador;
            if (cliente.isPessoaJuridica()) {
                PessoaJuridica pessoaJuridica = (PessoaJuridica) cliente;
                if (pessoaJuridica.getCnpj().equals(cnpj)) {
                    return contador;
                }
            }
        }
        return -1;
    }
}
