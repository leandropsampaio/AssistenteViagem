package br.uefs.ecomp.av.model;

import br.uefs.ecomp.av.util.*;
import java.io.Serializable;

/**
 * Esta classe implementa as locais do sistema, contendo os atributos de um
 * local.
 *
 * @author Leandro Pereira Sampaio.
 */
public class Local implements Comparable<Local>, Serializable {

    private ArvoreAVL arvore;
    private ILista atracoes;
    private final String nome;
    private final Coordenadas localizacao;
    private final String estado;
    private final String pais;

    /**
     * Construtor da classe, responsável pela criação um novo local.
     *
     * @param nome - Nome do local.
     * @param localizacao - Localização(latitude e longitude) do local.
     * @param estado - Estado que se localiza o local.
     * @param pais - Pais que se localiza o local.
     */
    public Local(String nome, Coordenadas localizacao, String estado, String pais) {
        atracoes = new ListaEncadeada();
        this.nome = nome;
        this.localizacao = localizacao;
        this.estado = estado;
        this.pais = pais;
        arvore = new ArvoreAVL();
    }

    /**
     * Método responsável por enviar o iterador da lista de atrações ordenados
     * por ordem alfabética.
     *
     * @return Iterador - Iterador da lista de atrações, ordenados pela ordem
     * alfabética.
     */
    public Iterador listarAtracoesOrdemAlfabetica() {
       return arvore.iterador();
    }

    /**
     * Método responsável por enviar o iterador da lista de avaliações.
     *
     * @return Iterador - Iterador da lista de atrações, ordenados pela
     * pontuação média.
     */
    public Iterador listarAtracoesOrdemPontuacaoMedia() {
        atracoes.ordenar();
        return atracoes.iterador();
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
     * @return Coordenadas - Localização do local.
     */
    public Coordenadas getLocalizacao() {
        return localizacao;
    }

    /**
     * Método responsável por retornar o conteúdo do atributo estado.
     *
     * @return String - Nome do estado do local.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Método responsável por retornar o conteúdo do atributo pais.
     *
     * @return String - Nome do pais do local.
     */
    public String getPais() {
        return pais;
    }

    /**
     * Método responsável por retornar a lista de atrações do local.
     *
     * @return ILista - Lista de atrações.
     */
    public ILista getAtracoes() {
        return atracoes;
    }
    
    public IArvore getArvore(){
        return arvore;
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
        if (objeto instanceof Local) {
            Local local = (Local) objeto;
            if (local.getNome().equals(nome) && local.getLocalizacao().equals(localizacao)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método responsável pela realizão da comparação de dois locais através do
     * nome.
     *
     * @param local
     * @return int - Resultado da comparação.
     */
    @Override
    public int compareTo(Local local) {
        Local outroLocal = (Local) local;
        return (nome.compareTo(outroLocal.getNome()));
    }
}
