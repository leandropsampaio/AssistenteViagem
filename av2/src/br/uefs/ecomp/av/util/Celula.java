package br.uefs.ecomp.av.util;

import java.io.Serializable;

/**
 * Esta classe implementa o comportamento de uma celula na lista encadeada. Ela
 * contém os atributos de uma celula, que são prox e objeto.
 *
 * @author Leandro Pereira Sampaio.
 */
public class Celula implements Serializable {

    private Comparable comparable;
    private Celula Prox;
    private int index;

    /**
     * Construtor do tipo celula, onde ao atributo é atribuido um valor de
     * acordo com o parâmetro recebido.
     *
     *
     * @param comparable - Objeto do tipo objeto para adicionar o atributo
     * objeto a celula.
     */
    public Celula(Comparable comparable) {
        this.comparable = comparable;
    }
    
    public Celula(int index, Comparable comparable) {
        this.index = index;
        this.comparable = comparable;
    }

    /**
     * Método responsável por retornar o conteudo do atributo objeto.
     *
     * @return Object - Objeto da celula.
     */
    public Comparable getObjeto() {
        return comparable;
    }

    /**
     * Método responsável por dar um novo valor ao atributo objeto, de acordo
     * com objeto recebido.
     *
     * @param comparable - Objeto para adicionar o atributo objeto a celula.
     */
    public void setObject(Comparable comparable) {
        this.comparable = comparable;
    }

    /**
     * Método responsável por retornar o conteúdo do atributo prox.
     *
     * @return Celula - referência para a próxima celula da lista encadeada.
     */
    public Celula getProx() {
        return Prox;
    }

    /**
     * Método responsável por dar um novo valor ao atributo prox, de acordo com
     * prox recebido.
     *
     * @param Prox - No para adicionar o atributo prox ao No.
     */
    public void setProx(Celula Prox) {
        this.Prox = Prox;
    }

    /**
     * Método responsavel por criar e retornar o iterador da lista.
     *
     * @return Iterador - O iterador da lista.
     */
    public Comparable getComparable() {
        return comparable;
    }

    /**
     * Método responsavel por criar e retornar o iterador da lista.
     *
     * @param comparable
     */
    public void setComparable(Comparable comparable) {
        this.comparable = comparable;
    }
    
    /**
     * Método responsavel por criar e retornar o iterador da lista.
     *
     * @return Iterador - O iterador da lista.
     */
    public int getIndex() {
        return index;
    }
    
    /**
     * Método responsavel por criar e retornar o iterador da lista.
     *
     * @param index
     */
    public void setIndex(int index) {
        this.index = index;
    }
}