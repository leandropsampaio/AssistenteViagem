package br.uefs.ecomp.av.util;

/**
 * Esta classe implementa o iterador do sistema Aula, de acordo com as pré
 * definições feitas na interface Iterador. Ela contém o atributo list e os
 * métodos temProximo e Proximo, que já estão declarados na interface Iterador.
 *
 * @author Leandro Pereira Sampaio.
 */
public class MyIterador implements Iterador {

    Celula list;

    /**
     * Construtor do tipo Iterator, onde ao atributo é atribuido um valor de
     * acordo com o parâmetro recebido.
     *
     *
     * @param Primeiro - Objeto do tipo Celula para adicionar o atributo list.
     */
    public MyIterador(Celula Primeiro) {
        list = Primeiro;
    }

    /**
     * Método responsável por verificar se uma lista encadeada tem ou não um
     * proximo elemento, isso é feito através do uso de uma variavel auxiliar,
     * chamada list.
     *
     * @return boolean - Se list for igual a null, significa que não há um
     * próximo elemento na lista, e então será retornado false, caso contrário,
     * será retornado true.
     */
    @Override
    public boolean temProximo() {
        if (list != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método responsável por retornar o próximo objeto da lista.
     *
     * @return Object - próximo objeto da lista encadeada.
     */
    @Override
    public Comparable obterProximo() {
        if (temProximo()) {
            Object aux = list.getObjeto();
            list = list.getProx();

            return (Comparable) aux;
        } else {
            return null;
        }
    }
}
