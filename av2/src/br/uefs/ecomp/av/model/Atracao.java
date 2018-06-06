package br.uefs.ecomp.av.model;

import br.uefs.ecomp.av.model.enums.CategoriaAtracao;
import br.uefs.ecomp.av.util.Coordenadas;
import br.uefs.ecomp.av.util.*;
import java.io.Serializable;

/**
 * Esta classe implementa os atrações do sistema, ela contém os atributos de um
 * atrações, que são: local, nome, localização, ordem, categoria e uma lista
 * encadeada de avaliações.
 *
 */
public abstract class Atracao implements Comparable<Atracao>, Serializable {

    private final Local local;
    private final String nome;
    private final Coordenadas localizacao;
    private int ordem;
    private final CategoriaAtracao categoria;
    private final ILista avaliacoes;
    private final String bairro;

    /**
     * Construtor do tipo Atracao, onde aos atributos são atribuidos valores de
     * acordo com os parâmetros recebidos.
     *
     * @param local - Local da atração.
     * @param nome - Nome da atração.
     * @param localizacao - Localização da atração.
     * @param bairro - Bairro da atração.
     * @param categoria - Categoria da atração.
     */
    public Atracao(Local local, String nome, Coordenadas localizacao, String bairro, CategoriaAtracao categoria) {

        avaliacoes = new ListaEncadeada();
        this.local = local;
        this.nome = nome;
        this.localizacao = localizacao;
        this.categoria = categoria;
        this.bairro = bairro;
    }

    /**
     * Método responsável por enviar o iterador da lista de avaliações.
     *
     * @return Iterador - Iterador da lista de avaliações, ordenados pela ordem
     * da data.
     */
    public Iterador listarAvaliacoes() {
        avaliacoes.ordenar();
        return avaliacoes.iterador();
    }

    /**
     * Método responsável por enviar a pontuação média da atração.
     *
     * @return double - Pontuação média das avaliações da atração.
     */
    public double pontuacaoMedia() {
        Iterador it = avaliacoes.iterador();
        double pontuacao = 0;
        int x = 0;
        Avaliacao avaliacao;

        while (it.temProximo()) {
            avaliacao = (Avaliacao) it.obterProximo();
            pontuacao = pontuacao + avaliacao.getPontos();
            x++;
        }
        return pontuacao / x;
    }

    /**
     * Método responsável por retornar o conteúdo do objeto local.
     *
     * @return Local - Local da atracao.
     */
    public Local getLocal() {
        return local;
    }

    /**
     * Método responsável por retornar o conteúdo do atributo nome.
     *
     * @return String - Nome da atração.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método responsável por retornar o conteúdo do atributo localização.
     *
     * @return Coordenadas - Localização da atração.
     */
    public Coordenadas getLocalizacao() {
        return localizacao;
    }

    /**
     * Método responsável por retornar o conteúdo do atributo categoria.
     *
     * @return CategoriaAtracao - Tipo da atração.
     */
    public CategoriaAtracao getCategoria() {
        return categoria;
    }

    /**
     * Método responsável por retornar a lista de avaliações da atração.
     *
     * @return ILista - Lista de avaliações.
     */
    public ILista getAvaliacoes() {
        return avaliacoes;
    }

    /**
     * Método responsável por checar se o objeto recebido é uma instância da
     * classe, através da comparação dos atributos da classe com os atributos do
     * objeto recebido.
     *
     * @param objeto - objeto que será comparado pelo presente método
     *
     * @return Boolean - se o resultado da comparação estiver correto, será
     * retornado true, caso contrário, será retornado false.
     */
    @Override
    public boolean equals(Object objeto) {
        if (objeto instanceof Atracao) {
            Atracao atracao = (Atracao) objeto;
            if (atracao.getNome().equals(nome)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método responsável por retornar o conteúdo do atributo ordem.
     *
     * @return int - Ordem da atração.
     */
    public int getOrdem() {
        return ordem;
    }

    /**
     * Método responsável por dar um novo valor ao atributo ordem, de acordo com
     * a ordem recebida.
     *
     * @param ordem - Inteiro para adicionar o atributo ordem.
     */
    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    /**
     * Método responsável pela realizão da comparação das atrações pela
     * pontuação média.
     *
     * @param atracao
     * @return int - Valor da comparação.
     */
    @Override
    public int compareTo(Atracao atracao) {
        Atracao outraAtracao = (Atracao) atracao;
        int comparacao = Double.compare(this.pontuacaoMedia(), outraAtracao.pontuacaoMedia());
        System.out.println(comparacao);
        return comparacao;
    }

    /**
     * Método responsável por retornar o conteúdo do atributo bairro.
     *
     * @return String - Nome do bairro.
     */
    public String getBairro() {
        return bairro;
    }

    @Override
    public String toString(){
        return nome;
    }
}
