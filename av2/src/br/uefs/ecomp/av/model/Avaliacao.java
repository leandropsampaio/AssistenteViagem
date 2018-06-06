package br.uefs.ecomp.av.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Esta classe implementa as avaliações do sistema, contendo os atributos de uma
 * avaliação.
 * 
 */
public abstract class Avaliacao implements Comparable<Avaliacao>, Serializable {

    private final Usuario avaliador;
    private final Atracao atracaoAvaliada;
    private final int pontos;
    private final String titulo;
    private final String texto;
    private final Date data;
    private final int atendimento;

    /**
     * Construtor da classe, responsável pela criação de uma nova avaliação.
     *
     * @param avaliador - Usuario que irá avaliar a atração.
     * @param atracaoAvaliada - Atracao que será avaliada.
     * @param pontos - Quantidade de pontos da atração.
     * @param titulo - Titulo da avaliação.
     * @param texto - Texto da avaliação.
     * @param data - Data que foi feita a avaliação.
     * @param atendimento - Nota pelo atendimento.
     */
    public Avaliacao(Usuario avaliador, Atracao atracaoAvaliada, int pontos, String titulo, String texto, Date data, int atendimento) {
        this.avaliador = avaliador;
        this.atracaoAvaliada = atracaoAvaliada;
        this.pontos = pontos;
        this.titulo = titulo;
        this.texto = texto;
        this.data = data;
        this.atendimento = atendimento;
    }

    /**
     * Método responsável por aceitar a avaliação feita pelo usuário,
     * cadastrando assim, na lista de avaliações da atração.
     */
    public void aceitar() {
        this.atracaoAvaliada.getAvaliacoes().inserirFinal((Comparable) this);
    }

    /**
     * Método responsável por retornar o objeto do usuário.
     *
     * @return Usuario - usuário que fez a avaliação.
     */
    public Usuario getAvaliador() {
        return avaliador;
    }

    /**
     * Método responsável por retornar a atração avaliada.
     *
     * @return Atracao - tração que foi avaliada.
     */
    public Atracao getAtracaoAvaliada() {
        return atracaoAvaliada;
    }

    /**
     * Método responsável por retornar o conteúdo do atributo pontos.
     *
     * @return Int - pontos da atração.
     */
    public int getPontos() {
        return pontos;
    }

    /**
     * Método responsável por retornar o conteúdo do atributo titulo.
     *
     * @return String - titulo da avaliação.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Método responsável por retornar o conteúdo do atributo texto.
     *
     * @return String - coméntario da atração.
     */
    public String getTexto() {
        return texto;
    }

    /**
     * Método responsável por retornar o conteúdo do atributo data.
     *
     * @return Date - data da avaliação.
     */
    public Date getData() {
        return data;
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
        if (objeto instanceof Avaliacao) {
            Avaliacao avaliacao = (Avaliacao) objeto;

            if (titulo.equals(avaliacao.getTitulo()) && texto.equals(avaliacao.getTexto())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método responsável pela realizão da comparação de duas avaliações pela
     * data.
     *
     * @param avaliacao
     * @return int - Resultado da comparação.
     */
    @Override
    public int compareTo(Avaliacao avaliacao) {
        Avaliacao outraAvaliacao = (Avaliacao) avaliacao;
        return this.data.compareTo(outraAvaliacao.getData());
    }

    /**
     * Método responsável por retornar o conteúdo do atributo atendimento.
     *
     * @return int - Nota do atendimento.
     */
    public int getAtendimento() {
        return atendimento;
    }
}
