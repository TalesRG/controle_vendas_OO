package controller.service.validator;

/**
 * Classe Validador valida os campos de formulario.
 *
 * @author Ana Luiza Fernandes e Tales Rodrigues
 * @version 1.0
 * @since 2022
 */
public class Validador {

    public static String CHAR_MASCARA_CPF = "[.-]";
    public static String CHAR_MASCARA_TELEFONE = "()-";
    public static String CHAR_MASCARA_CNPJ = "./-";
    public static String CHAR_MASCARA_DATA = "/";

    /**
     * Valida o campo que esta preenchido com a mascara.
     *
     * @param campo
     * @param caracteresRemover
     * @param mensagemErro
     * @return void
     * @throws DadosInvalidosException
     */
    public static void validarCampoPreenchidoComMascara(String campo, String caracteresRemover, String mensagemErro) throws DadosInvalidosException {
        //valida se o campo com mascara inserido é nulo
    	if (campo == null) {
            throw new DadosInvalidosException(mensagemErro);
        }
        var regexRemover = String.format("[%s]", caracteresRemover);
        var campoSemMascara = campo.replaceAll(regexRemover, "");
        validarCampoPreenchido(campoSemMascara, mensagemErro);
    }

    /**
     * Valida campo preenchido.
     *
     * @param campo
     * @param mensagemErro
     * @return void
     * @throws DadosInvalidosException
     */
    public static void validarCampoPreenchido(String campo, String mensagemErro) throws DadosInvalidosException {
       //valida se o campo preenchido é nulo
    	if (campo == null || campo.trim().equals("")) {
            throw new DadosInvalidosException(mensagemErro);
        }
    }

}
