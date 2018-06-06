package br.uefs.ecomp.av.util;

import java.io.Serializable;

public class DadoTabela implements Comparable, Serializable {

    private final Object chave;
    private final Object valor;

    /**
     * Construtor responsável por inicializar os atributos da classe DadoTabela
     *
     * @param chave - Pode ser usado para calcular o indice da tabela.
     * @param valor - Objeto inserido na tabela.
     */
    public DadoTabela(Object chave, Object valor) {
        this.chave = chave;
        this.valor = valor;
    }

    /**
     * Método responsável por retornar o conteúdo do atributo texto.
     *
     * @param chave
     */
   // public DadoTabela(Object chave) {
    //     this.chave = chave;
    //}
    
    /**
     * Método responsável por retornar o conteúdo do atributo chave.
     *
     * @return Object - Chave que pode ser utilizada para o indice da tabela.
     */
    public Object getChave() {
        return chave;
    }

    /**
     * Método responsável por retornar o conteúdo do atributo valor.
     *
     * @return Object - Objeto que será inserido na tabela.
     */
    public Object getValor() {
        return valor;
    }

    /**
     * Reescrita do método compareTo, utilizado para verificar a precedência do
     * objeto passado por parâmetro.
     *
     * @param t objeto a ser comparado o grau de precedência.
     * @return 1, se o objeto que t representa tiver ordem de precedencia maior,
     * -1 ordem menor ou 0 se for igual.
     */
    @Override
    public int compareTo(Object t) {
        return ((Comparable) chave).compareTo(((DadoTabela) t).getChave());
    }
}