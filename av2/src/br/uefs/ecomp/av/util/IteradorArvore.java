package br.uefs.ecomp.av.util;

import java.io.Serializable;

/**
 * O iteradorArvore tem a finalizade de iterar sobre uma arvore, enquanto haver
 * elementos a serem iterados.
 */
public class IteradorArvore implements Iterador, Serializable {

    private final Node raiz;
    private final ILista listaDados;
    private final Iterador iterador;

    /**
     * O construtor inicializa um nó da Arvore ou seja sua raíz, em uma lista
     * encadeada, chamada listaDados por ter o mesmo comportamento, e o current
     * é inicalizado para a esquerda.
     *
     * @param raiz
     */
    public IteradorArvore(Node raiz) {
        listaDados = new ListaEncadeada();
        this.raiz = raiz;
        inOrder(raiz);
        iterador = listaDados.iterador();
    }

    /**
     * O método hasNext verifica se a lista está vazia, enquanto a lista não
     * estiver vazia vai sempre haver próximo na árvore.
     *
     * @return true or flase
     */
    @Override
    public boolean temProximo() {
        return iterador.temProximo();
    }

    /**
     * O método next retorna o primeiro elemento da lista que no caso é o mesmo
     * do próximo da Àrvore.
     *
     * @return o proximo objeto da lista até o final.
     */
    @Override
    public Comparable obterProximo() {
        return iterador.obterProximo();
    }

    /**
     * Método recursivo para percorrer a árvore inteiramente, e inserir
     * posteriormente na lista encadeda contendo os dados gerais.
     *
     * @param noAtual
     */
    private void inOrder(Node noAtual) {
        if (noAtual != null) {
            inOrder(noAtual.getEsquerda());
            listaDados.inserirFinal((Comparable) noAtual.getElemento());
            inOrder(noAtual.getDireita());
        }
    }
}
