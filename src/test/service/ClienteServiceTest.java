package test.service;

import controller.service.ClienteService;
import controller.service.validator.DadosInvalidosException;
import model.PessoaFisica;
import model.PessoaJuridica;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class ClienteServiceTest {

    @Test
    public void deveRetornarMesmoCpf() throws DadosInvalidosException {
        ClienteService clienteService = new ClienteService();
        PessoaFisica pessoaFisica = new PessoaFisica("Cliente", "(61)4002-8922", "email@gmail", "end", "111.111.111-59");
        clienteService.cadastrarPessoaFisica(pessoaFisica);

        Optional<PessoaFisica> pessoaEncontrada = clienteService.retornarPorCpf(pessoaFisica.getCpf());

        Assertions.assertEquals(pessoaFisica.getCpf(), pessoaEncontrada.get().getCpf());

    }


    @Test
    public void deveRetornarVazioQuandoPassarCpfNaoExistente() {
        ClienteService clienteService = new ClienteService();
        String cpfAleatorio = "999.999.999-99";

        Optional<PessoaFisica> pessoaEncontrada = clienteService.retornarPorCpf(cpfAleatorio);

        Assertions.assertTrue(pessoaEncontrada.isEmpty());

    }

    @Test
    public void deveRetornarMesmoCnpj() throws DadosInvalidosException {
        ClienteService clienteService = new ClienteService();
        PessoaJuridica pessoaJuridica = new PessoaJuridica("Cliente", "(61)4002-8922", "email@gmail", "end", "06.990.590/0001-23");

        clienteService.cadastrarPessoaJuridica(pessoaJuridica);

        Optional<PessoaJuridica> pessoaEncontrada = clienteService.retornarPorCnpj(pessoaJuridica.getCnpj());

        Assertions.assertEquals(pessoaJuridica.getCnpj(), pessoaEncontrada.get().getCnpj());
    }


    @Test
    public void deveRetornarVazioQuandoPassarCnpjNaoExistente() {
        ClienteService clienteService = new ClienteService();
        String cnpjAleatorio = "00.000.000/0000-00";

        Optional<PessoaJuridica> pessoaEncontrada = clienteService.retornarPorCnpj(cnpjAleatorio);

        Assertions.assertTrue(pessoaEncontrada.isEmpty());

    }

}