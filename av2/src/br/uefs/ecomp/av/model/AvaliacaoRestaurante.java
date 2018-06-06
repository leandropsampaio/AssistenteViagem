/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.av.model;

import br.uefs.ecomp.av.model.enums.*;
import java.util.Date;

/**
 * Esta classe implementa as avaliações do sistema, contendo os atributos de uma
 * avaliação do restaurante.
 */
public class AvaliacaoRestaurante extends Avaliacao {

    private final TipoVisita tipoVisita;
    private final TipoRefeicao tipoRefeicao;
    private final int comida;
    private final int custoBeneficio;

    /**
     * Construtor da classe, responsável pela criação de uma nova avaliação do
     * restaurante.
     *
     * @param avaliador - Usuário que irá fazer a avaliação.
     * @param atracaoAvaliada - Atração que será avaliada pelo usuário.
     * @param pontos - Quantidade de pontos da atração.
     * @param titulo - Titulo da avaliação.
     * @param texto - Texto da avaliação.
     * @param data - Data da avaliação.
     * @param tipoVisita - Tipo de Visita no restaurante.
     * @param tipoRefeicao - Tipo de Refeição do restaurante.
     * @param atendimento - Nota pelo atendimento do restaurante.
     * @param custoBeneficio - Custo Beneficio da atração.
     * @param comida - Nota pela comida do restaurante.
     */
    public AvaliacaoRestaurante(Usuario avaliador, Atracao atracaoAvaliada, int pontos, String titulo, String texto, Date data,
            TipoVisita tipoVisita, TipoRefeicao tipoRefeicao, int atendimento, int comida, int custoBeneficio) {
        super(avaliador, atracaoAvaliada, pontos, titulo, texto, data, atendimento);
        this.tipoVisita = tipoVisita;
        this.tipoRefeicao = tipoRefeicao;
        this.comida = comida;
        this.custoBeneficio = custoBeneficio;
    }

    /**
     * Método responsável por retornar o conteúdo do atributo tipoVisita.
     *
     * @return TipoVisita - Tipo de visita.
     */
    public TipoVisita getTipoVisita() {
        return tipoVisita;
    }

    /**
     * Método responsável por retornar o conteúdo do atributo tipoRefeicao.
     *
     * @return TipoRefeicao - Tipo de refeicao do restaurante.
     */
    public TipoRefeicao getTipoRefeicao() {
        return tipoRefeicao;
    }

    /**
     * Método responsável por retornar o conteúdo do atributo comida.
     *
     * @return int - Nota da comida do restaurante.
     */
    public int getComida() {
        return comida;
    }

    /**
     * Método responsável por retornar o conteúdo do atributo custoBeneficio.
     *
     * @return int - Nota custo benefício do restaurante.
     */
    public int getCustoBeneficio() {
        return custoBeneficio;
    }
}
