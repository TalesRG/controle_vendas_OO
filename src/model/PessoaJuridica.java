package model;

import controller.service.validator.DadosInvalidosException;
import controller.service.validator.Validador;

/**
 * Classe PessoaJuridica o qual possui os atributos que representa o cliente juridico e herda da classe Cliente.
 *
 * @author Ana Luiza Fernandes e Tales Rodrigues
 * @version 1.0
 * @see model.Cliente
 * @since 2022
 */
public class PessoaJuridica extends Cliente {
    private String cnpj;

    /**
     * Metodo construtor que retorna os dados de pessoa juridica.
     *
     * @param nome
     * @param telefone
     * @param email
     * @param endereco
     * @param cnpj
     */
    public PessoaJuridica(String nome, String telefone, String email, String endereco, String cnpj) {
        super(nome, telefone, email, endereco);
        this.cnpj = cnpj;
    }

    /**
     * Esta sobrescrevendo o metodo isPessoaFisica e altera o status para FALSE.
     *
     * @return boolean
     */
    @Override
    public boolean isPessoaFisica() {
        return false;
    }

    /**
     * Esta sobrescrevendo o metodo isPessoaJuridica e altera o status para TRUE.
     *
     * @return boolean
     */
    @Override
    public boolean isPessoaJuridica() {
        return true;
    }

    /**
     * Esta sobrescrevendo o metodo validarCliente e valida o cliente pessoa juridica.
     *
     * @return void
     * @throws DadosInvalidosException
     */
    @Override
    public void validarCliente() throws DadosInvalidosException {
        super.validarCliente();
        Validador.validarCampoPreenchidoComMascara(cnpj, Validador.CHAR_MASCARA_CNPJ, "O campo cnpj não está preenchido, verifique e tente novamente");
    }

    /**
     * Esta sobrescrevendo o metodo clone e retornando um objeto pessoaJuridica.
     *
     * @return PessoaJuridica
     */
    @Override
    public PessoaJuridica clone() {
        PessoaJuridica pessoaJuridica = new PessoaJuridica(getNome(), getTelefone(), getEmail(), getEndereco(), cnpj);
        return pessoaJuridica;
    }

    /**
     * Esta sobrescrevendo o metodo toString para retornar os dados da classe em formato de texto.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Nome: " + this.getNome() + "\n"
                + "Telefone: " + this.getTelefone() + "\n"
                + "Email: " + this.getEmail() + "\n"
                + "Endere�o: " + this.getEndereco() + "\n"
                + "CNPJ: " + this.cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
