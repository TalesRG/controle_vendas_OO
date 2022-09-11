package view;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;

/**
 * Classe MascaraUtil cria mascaras para serem utilizadas nas telas de cadastrar.
 *
 * @author Ana Luiza Fernandes e Tales Rodrigues
 * @version 1.0
 * @since 2022
 */
public class MascaraUtil {

    /**
     * Cria uma mascara para o telefone.
     *
     * @return mascaraTelefone
     */
    public static MaskFormatter mascaraTelefone() {
        try {
            return new MaskFormatter("(##)#####-####");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Cria mascara para o CPF.
     *
     * @return mascaraCpf
     */
    public static MaskFormatter mascaraCpf() {
        try {
            return new MaskFormatter("###.###.###-##");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Cria uma mascara para o CNPJ.
     *
     * @return mascaraCnpj
     */
    public static MaskFormatter mascaraCnpj() {
        try {
            return new MaskFormatter("##.###.###/####-##");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Cria uma mascara para a data.
     *
     * @return mascaraData
     */
    public static MaskFormatter mascaraData() {
        try {
            return new MaskFormatter("##/##/####");
        } catch (ParseException e) {
            throw new RuntimeException("Erro na formatação: " + e.getMessage());
        }
    }

}
