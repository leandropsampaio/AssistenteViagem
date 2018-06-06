package br.uefs.ecomp.av.model;

import br.uefs.ecomp.av.model.enums.*;
import java.util.Date;

/**
 * Esta classe implementa as avaliações do sistema, contendo os atributos de uma
 * avaliação para o hotel.
 *
 */
public class AvaliacaoHotel extends Avaliacao {

    private final ObjetivoVisita objetivoVisita;
    private final int quartos;
    private final int qualidadeSono;

    /**
     * Construtor da classe, responsável pela criação de uma nova avaliação do
     * hotel.
     *
     * @param avaliador - Usuário que irá fazer a avaliação.
     * @param atracaoAvaliada - Atração que será avaliada pelo usuário.
     * @param pontos - Quantidade de pontos da atração.
     * @param titulo - Titulo da avaliação.
     * @param texto - Texto da avaliação.
     * @param data - Data da avaliação.
     * @param objetivoVisita - Objetivo de Visita do usuário.
     * @param atendimento - Nota pelo atendimento.
     * @param quartos - Quantidade de quartos.
     * @param qualidadeSono - Nota pela qualidade de sono.
     */
    public AvaliacaoHotel(Usuario avaliador, Atracao atracaoAvaliada, int pontos, String titulo, String texto, Date data,
            ObjetivoVisita objetivoVisita, int atendimento, int quartos, int qualidadeSono) {
        super(avaliador, atracaoAvaliada, pontos, titulo, texto, data, atendimento);
        this.objetivoVisita = objetivoVisita;
        this.quartos = quartos;
        this.qualidadeSono = qualidadeSono;
    }

    /**
     * Método responsável por retornar o conteúdo do atributo objetivoVisita.
     *
     * @return ObjetivoVisita - Objetivo de visita no hotel.
     */
    public ObjetivoVisita getObjetivoVisita() {
        return objetivoVisita;
    }

    /**
     * Método responsável por retornar o conteúdo do atributo quartos.
     *
     * @return int - Quantidade de quartos do hotel.
     */
    public int getQuartos() {
        return quartos;
    }

    /**
     * Método responsável por retornar o conteúdo do atributo qualidadeSono.
     *
     * @return int - Nota da qualidade do sono.
     */
    public int getQualidadeSono() {
        return qualidadeSono;
    }
}
