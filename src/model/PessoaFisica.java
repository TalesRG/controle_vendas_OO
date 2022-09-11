package model;

import controller.service.validator.DadosInvalidosException;

import static controller.service.validator.Validador.*;

/**
 * Classe PessoaFisica a qual possui os atributos que representa o cliente fisico, herda da classe Cliente e implementa
 * a interface Cloneable.
 *
 * @author Ana Luiza Fernandes e Tales Rodrigues
 * @version 1.0
 * @see model.Cliente
 * @see java.lang.Cloneable
 * @since 2022
 */
public class PessoaFisica extends Cliente implements Cloneable {
    private String cpf;

    /**
     * Metodo construtor que retrona os dados do cliente pessoa fisica.
     *
     * @param nome
     * @param telefone
     * @param email
     * @param endereco
     * @param cpf
     */
    public PessoaFisica(String nome, String telefone, String email, String endereco, String cpf) {
        super(nome, telefone, email, endereco);
        this.cpf = cpf;
    }

    /**
     * Esta sobrescrevendo o metodo validarCliente e valida o cliente pessoa fisica.
     *
     * @return void
     * @throws DadosInvalidosException
     */
    @Override
    public void validarCliente() throws DadosInvalidosException {
        super.validarCliente();
        validarCampoPreenchidoComMascara(cpf, CHAR_MASCARA_CPF, "A algum campo não preenchido, " +
                "verifique e tente novamente");
    }

    /**
     * Esta sobrescrevendo o metodo do toString para retornar os dados da classe em formato de texto.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Nome: " + this.getNome() + "\n"
                + "Telefone: " + this.getTelefone() + "\n"
                + "Email: " + this.getEmail() + "\n"
                + "Endereço: " + this.getEndereco() + "\n"
                + "CPF: " + this.cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Esta sobrescrevendo o metodo isPessoaFisica e altera o status para TRUE.
     *
     * @return boolean
     */
    @Override
    public boolean isPessoaFisica() {
        return true;
    }

    /**
     * Esta sobrescrevendo o metodo isPessoaJuridica e altera o status para FALSE.
     *
     * @return boolean
     */
    @Override
    public boolean isPessoaJuridica() {
        return false;
    }

    /**
     * Esta sobrescrevendo o metodo clone e retornando um objeto pessoaFisica.
     *
     * @return PessoaFisica
     */
    @Override
    public PessoaFisica clone() {
        PessoaFisica pessoaFisica = new PessoaFisica(getNome(), getTelefone(), getEmail(), getEndereco(), cpf);
        return pessoaFisica;
    }
}
