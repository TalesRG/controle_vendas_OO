package model;

import controller.service.validator.DadosInvalidosException;
import controller.service.validator.Validador;

import static controller.service.validator.Validador.*;

/**
 * Classe abstrata Cliente a qual possui os atributos que representa o cliente.
 *
 * @author Ana Luiza Fernandes e Tales Rodrigues
 * @version 1.0
 * @since 2022
 */
public abstract class Cliente {
    private String nome;
    private String telefone;
    private String email;
    private String endereco;

    /**
     * Metodo construtor que retorna os dados de cliente.
     *
     * @param nome
     * @param telefone
     * @param email
     * @param endereco
     */
    public Cliente(String nome, String telefone, String email, String endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }

    /**
     * Retorna o status de isPessoaFisica. TRUE se isPessoaFisica e FALSE se isPessoaJuridica.
     *
     * @return boolean
     */
    public abstract boolean isPessoaFisica();

    /**
     * Retorna o status de isPessoaJuridica.
     *
     * @return boolean
     */
    public abstract boolean isPessoaJuridica();

    /**
     * Valida os dados do clientes.
     *
     * @return void
     * @throws DadosInvalidosException
     */
    public void validarCliente() throws DadosInvalidosException {
        validarCampoPreenchido(nome, "O campo nome não está preenchido, verifique e tente novamente");
        validarCampoPreenchidoComMascara(telefone, CHAR_MASCARA_TELEFONE, "O campo telefone não está preenchido, verifique e tente novamente");
        validarCampoPreenchido(email, "O campo email não está preenchido, verifique e tente novamente");
        validarCampoPreenchido(endereco, "O campo endereço não está preenchido, verifique e tente novamente");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }


}
