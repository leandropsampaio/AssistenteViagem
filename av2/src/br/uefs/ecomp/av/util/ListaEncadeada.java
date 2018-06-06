/**
 * Componente Curricular: Módulo Integrado de Programação 
 * Autor: Leandro Pereira Sampaio.
 *
 * Declaro que este código foi elaborado por mim de forma individual e não
 * contém nenhum trecho de código de outro colega ou de outro autor, tais como
 * provindos de livros e apostilas, e páginas ou documentos eletrônicos da
 * Internet. Qualquer trecho de código de outra autoria que uma citação para o
 * não a minha está destacado com autor e a fonte do código, e estou ciente que
 * estes trechos não serão considerados para fins de avaliação. Alguns trechos
 * do código podem coincidir com de outros colegas pois estes foram discutidos
 * em sessões tutorias.
 */
package br.uefs.ecomp.av.util;

import java.io.Serializable;

/**
 * Esta classe implementa a lista encadeada do sistema Aula, de acordo com as
 * pré definições feitas na interface Ilista. Ela contém o atributo primeiro e
 * os metodos que ja estão declarados na interface Ilista.
 *
 * @author Leandro Pereira Sampaio.
 */
public class ListaEncadeada implements ILista, Serializable {

    private Celula primeiro;
    private Celula ult;
    private Comparable[] vetorComparable;

    /**
     * Construtor do tipo ListaEncadeada, onde ao atributo primeiro é atribuido
     * o valor null.
     *
     */
    public ListaEncadeada() {
        primeiro = null;
    }

    /**
     * Método responsável por verificar se a lista encadeada está ou não vazia.
     *
     * @return boolean - se primeiro for igual a null, significa que a lista
     * está vazia, e então será retornado true, caso contrário, será retornado
     * false.
     */
    @Override
    public boolean estaVazia() {
        if (primeiro == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metódo responsável para retornar o tamanho da lista encadeada, ele
     * percorrerá a lista a partir do primeiro até o ultimo elemento.
     *
     * @return
     */
    @Override
    public int obterTamanho() {

        int tamanho = 0;
        Celula aux = primeiro;
        while (aux != null) {
            tamanho++;
            aux = aux.getProx();
        }
        return tamanho;
    }

    /**
     * Método responsavel por inserir uma nova celula no inicio da lista.
     *
     * @param objeto - Objeto que será conteudo do novo nó criado no presente
     * método.
     */
    @Override
    public void inserirInicio(Comparable objeto) {

        Celula nova = new Celula(objeto);
        nova.setProx(primeiro);
        primeiro = nova;
    }

    /**
     * Método responsavel por inserir um novo nó no final da lista.
     *
     * @param objeto - Objeto que será conteudo do novo nó criado no presente
     * método.
     */
    @Override
    public void inserirFinal(Comparable objeto) {
        Celula nova = new Celula(objeto);
        Celula aux = primeiro;
        if (obterTamanho() == 0) {
            inserirInicio(objeto);
        } else {
            while (aux.getProx() != null) {
                aux = aux.getProx();
            }
            aux.setProx(nova);
        }
    }

    /**
     * Método responsavel por remover o primeiro elemento da lista encadeada.
     *
     * @return Object - o objeto que foi removido da lista encadeada.
     */
    @Override
    public Comparable removerInicio() {

        if (primeiro == null) {
            return null;
        }
        Celula aux = primeiro;
        primeiro = primeiro.getProx();
        return (Comparable) aux.getObjeto();
    }

    /**
     * Método responsável por remover o ultimo elemento da lista encadeada.
     *
     * @return Object - o objeto que foi removido da lista encadeada.
     */
    @Override
    public Comparable removerFinal() {
        Celula aux = primeiro;
        Celula aux1 = primeiro;
        if (obterTamanho() == 0) {
            return null;
        } else if (aux.getProx() == null) {
            primeiro = null;
            return (Comparable) aux.getObjeto();
        } else {
            while (aux.getProx() != null) {
                aux1 = aux;
                aux = aux.getProx();
            }
            aux1.setProx(null);
            return (Comparable) aux.getObjeto();
        }
    }

    /**
     * Método responsavel por retornar o Objeto contido na posição especificada.
     *
     * @param index - Número inteiro que representa a posição onde está o objeto
     * a ser retornado.
     *
     * @return Object - o objeto que está na posição correspondente a do numero
     * recebido.
     */
    @Override
    public Comparable recuperar(int index) {

        if (index >= 0 && index < obterTamanho()) {
            Celula aux = primeiro;
            for (int i = 0; i < index; i++) {
                aux = aux.getProx();
            }
            return (Comparable) aux.getObjeto();
        }
        return null;
    }

    /**
     * Método responsavel por criar e retornar o iterador da lista.
     *
     * @return Iterador - O iterador da lista.
     */
    @Override
    public Iterador iterador() {
        MyIterador it = new MyIterador(primeiro);
        return it;
    }

    /**
     * Método responsável por inserir ordenado os elementos na lista.
     *
     * @param objeto
     * @param index
     */
    @Override
    public void inserir(int index, Comparable objeto) {
        Celula aux = primeiro;
        Celula aux1 = null;
        if (estaVazia()) {
            Celula celula = new Celula(index, objeto);
            primeiro = celula;
        } else {
            for (int i = 0; i < index; i++) {
                aux1 = aux;
                aux = aux.getProx();
            }
            Celula novo = new Celula(objeto);
            aux1.setProx(novo);
            novo.setProx(aux);
        }
    }

    /**
     * Método responsável por remover os elementos na lista.
     *
     * @param index
     *
     * @return comparable - Objeto removido.
     */
    @Override
    public Comparable remover(int index) {

        Celula atual = this.primeiro;
        Celula ant = null;
        if (estaVazia()) {
            return null;
        } else {
            while ((atual != null) && (atual.getObjeto() != recuperar(index))) {
                ant = atual;
                atual = atual.getProx();
            }
            if (atual == null) {
                return null;
            } else {
                if (atual == this.primeiro) {//igual ao primeiro
                    if (this.primeiro == this.ult) {//se for único
                        this.ult = null;
                    }
                    this.primeiro = this.primeiro.getProx();
                } else {
                    if (atual == this.ult) {//se for último
                        this.ult = ant;
                    }
                    ant.setProx(atual.getProx());//se for no meio
                }
                return (Comparable) atual.getObjeto();
            }
        }
    }

    /**
     * Método responsável por ordenar os elementos na lista.
     *
     */
     @Override
    public void ordenar() {
        if (this.estaVazia() == false) {
            vetorComparable = new Comparable[this.obterTamanho()];

            for (int i = 0; i < vetorComparable.length; i++) {
                vetorComparable[i] = this.removerInicio();
            }
            int ini = 0, fim = vetorComparable.length - 1;
            this.quickSort(vetorComparable, ini, fim);

            for (Comparable vetorComparable1 : vetorComparable) {
                this.inserirFinal(vetorComparable1);
            }
        }
    }
    
    public void quickSort(Comparable[] vetorComparable, int ini, int fim) {
        
        if (ini >= fim) {   //Se o vetor já estiver ordenado:
            return;   //Retorna ele mesmo.
        }
        //Uma posição "meio" é escolhida.
        int meio = ini + (fim - ini) / 2, i = ini, j = fim;
        Comparable pivo = vetorComparable[meio];   //Um objeto pivor é escolhido com base nessa posição do meio.
        
        while (i <= j) {
            
            //As strings são comparadas:
            while (vetorComparable[i].compareTo(pivo) < 0) {
                i++;   //Se vier depois, desloca para a posição à direita.
            }
                       
            while (vetorComparable[j].compareTo(pivo) > 0) {
                j--;   //Se vier antes, desloca para a posição à esquerda.
            }
            
            if (i <= j) {  //Troca:
                Comparable temp = vetorComparable[i];
                vetorComparable[i] = vetorComparable[j];
                vetorComparable[j] = temp;
                i++;
                j--;
            }
        }
        
        //Caso ainda não esteja ordenado:
        if (ini < j) {
            quickSort(vetorComparable, ini, j);   //Método recursivo.
        }
        
        if (fim > i) {
            quickSort(vetorComparable, i, fim);   //Método recursivo.
        }
    }
    

    /**
     * Método responsável por buscar os elementos na lista.
     *
     * @param obj - Objeto que será buscado.
     *
     * @return object - Objeto que foi buscado.
     */
    public Object buscar(Object obj) {
        Celula corrente = primeiro;
        while (corrente != null) {
            int a = corrente.getIndex();
            if (a == (int) obj) {
                return corrente.getObjeto();
            }
            corrente = corrente.getProx();
        }
        return null;
    }
}
