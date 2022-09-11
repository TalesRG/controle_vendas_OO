package view;

import javax.swing.*;

/**
 * Classe abstrata componentes cria as posicoes e botoes e herda da classe JFrame.
 *
 * @author Ana Luiza Fernandes e Tales Rodrigues
 * @version 1.0
 * @see javax.swing.JFrame
 * @since 2022
 */
public abstract class Componentes extends JFrame {

    /**
     * Metodo construtor de Componentes
     */
    public Componentes() {

    }

    /**
     * Cria label, com as posicoes, largura e altura.
     *
     * @param label
     * @param posicaoX
     * @param posicaoY
     * @param largura
     * @param altura
     * @return void
     */
    public void criarLabel(String label, int posicaoX, int posicaoY, int largura, int altura) {
        JLabel etiqueta = new JLabel(label);
        etiqueta.setBounds(posicaoX, posicaoY, largura, altura);
        add(etiqueta);

    }

    /**
     * Cria botoes com posicoes, largura e altura.
     *
     * @param button
     * @param posicaoX
     * @param posicaoY
     * @param largura
     * @param altura
     * @return void
     */
    public void criarBotao(JButton button, int posicaoX, int posicaoY, int largura, int altura) {
        button.setBounds(posicaoX, posicaoY, largura, altura);
        add(button);
    }

    /**
     * Desabilita o botao excluir.
     *
     * @param excluir
     * @return void
     */
    public void desabilitarBotaoExcluir(JButton excluir) {
        excluir.setEnabled(false);
    }

    /**
     * Habilita o botao excluir
     *
     * @param excluir
     * @return void
     */
    public void habilitarBotaoExcluir(JButton excluir) {
        excluir.setEnabled(true);
    }

    /**
     * Desabilita o botao editar.
     *
     * @param editar
     * @return void
     */
    public void desabilitarBotaoEditar(JButton editar) {
        editar.setEnabled(false);
    }

    /**
     * Habilita o botao editar.
     *
     * @param editar
     * @return void
     */
    public void habilitarBotaoEditar(JButton editar) {
        editar.setEnabled(true);
    }

}
