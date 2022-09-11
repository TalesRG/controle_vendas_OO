package controller.service.validator;

/**
 * Classe DadosInvalidosException cria excessoes e herda de Exception.
 *
 * @author Ana Luiza Fernandes e Tales Rodrigues
 * @version 1.0
 * @see java.lang.Exception
 * @since 2022
 */
public class DadosInvalidosException extends Exception {

    /**
     * Metodo construtor mostra uma mensagem de erro.
     *
     * @param message
     */
    public DadosInvalidosException(String message) {
        super(message);
    }
}
