package br.uefs.ecomp.av.util;

/**
 * Classe Fila, implementada a partir dos métodos da interface IFila.
 *
 */
public class Fila implements IFila {

    private ILista fila;   //Declara um objeto do tipo ILista, para usar métodos da lista.

    /*Construtor da classe, instancia o objeto tipo lista "fila", que aponta para nulo.
     */
    public Fila() {
        fila = new ListaEncadeada();
    }

    /**
     * Método que verifica se a fila está vazia.
     *
     * @return true ou false
     */
    @Override
    public boolean estaVazia() {
        return fila.estaVazia();
    }

    /**
     * Método que verifica o tamanho da lista em tempo de execução
     *
     * @return int
     */
    @Override
    public int obterTamanho() {
        return fila.obterTamanho();
    }

    /**
     * Método que recebe um objeto para inserir no final da fila.
     *
     * @param o
     */
    @Override
    public void inserirFinal(Comparable o) {
        fila.inserirFinal(o);
    }

    /**
     * Método que remove o primeiro elemento da fila.
     *
     * @return Object
     */
    @Override
    public Comparable removerInicio() {
        return fila.removerInicio();
    }

    /**
     * Método que recupera o primeiro elemento da pilha.
     *
     * @return Object
     */
    @Override
    public Comparable recuperarInicio() {
        return fila.recuperar(0);
    }

    /**
     * Método responsavel por retornar o iterador da fila.
     *
     * @return Iterador - O iterador da fila.
     */
    @Override
    public Iterador iterador() {
        return fila.iterador();
    }
}