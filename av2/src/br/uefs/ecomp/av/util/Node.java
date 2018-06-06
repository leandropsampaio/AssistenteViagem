package br.uefs.ecomp.av.util;

import java.io.Serializable;

/**
 * Classe Node, responsável pela criação de cada elemento da Árvore, recebendo
 * um objeto para criação individual de casa elemento.
 *
 */
public class Node implements Serializable{

    private Comparable elemento;
    private Node direita = null;
    private Node esquerda = null;
    private int hE, hD;

    /**
     * Construtor da Classe responsável pela criação do novo node, onde recebe
     * um comparable e cria uma node para aquele determinado objeto.
     *
     * @param elemento elemento a ser criado.
     */
    public Node(Comparable elemento) {
        this.elemento = elemento;
    }

    /**
     * @return - O elemento.
     */
    public Comparable getElemento() {
        return elemento;
    }

    /**
     * @return - O elemento da direita
     */
    public Node getDireita() {
        return direita;
    }

    /**
     * Muda o elemento da direita.
     *
     * @param direita - novo elemento a substituir o direito.
     */
    public void setDireita(Node direita) {
        this.direita = direita;
    }

    /**
     * @return - O elemento esquerdo.
     */
    public Node getEsquerda() {
        return esquerda;
    }

    /**
     * Muda o elemento da esquerda.
     *
     * @param esquerda - novo elemento a substituir o esquerdo.
     */
    public void setEsquerda(Node esquerda) {
        this.esquerda = esquerda;
    }

    /**
     * @return Altura da esquerda
     */
    public int gethE() {
        return hE;
    }

    /**
     * Muda a altura da esquerda.
     *
     * @param hE - altura da esquerda.
     */
    public void sethE(int hE) {
        this.hE = hE;
    }

    /**
     * @return - Altura da direita.
     */
    public int gethD() {
        return hD;
    }

    /**
     * Muda a altura da direita.
     *
     * @param hD - altura da direita.
     */
    public void sethD(int hD) {
        this.hD = hD;
    }
}