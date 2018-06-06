package br.uefs.ecomp.av.model;

import br.uefs.ecomp.av.model.enums.CategoriaAtracao;
import br.uefs.ecomp.av.util.Coordenadas;

/**
 * Esta classe implementa as avaliações do sistema, contendo os atributos de um
 * hotel.
 *
 */
public class Hotel extends Atracao {

    private final int estrelas;
    private final boolean arCondicionado;
    private final boolean tv;
    private final boolean servicoQuarto;

    /**
     * Construtor da classe, responsável pela criação de um novo hotel.
     *
     * @param local - Local do hotel.
     * @param nome - Nome do hotel.
     * @param localizacao - Localização do hotel.
     * @param bairro - Bairro do hotel.
     * @param categoria - Categoria da atração.
     * @param estrelas - Quantidade de estrelas do hotel.
     * @param arCondicionado - Se o hotel possui ar-Condicionado.
     * @param tv - Se o hotel possui televisão.
     * @param servicoQuarto - Se o hotel possui serviço de quarto.
     */
    public Hotel(Local local, String nome, Coordenadas localizacao, String bairro, CategoriaAtracao categoria,
            int estrelas, boolean arCondicionado, boolean tv, boolean servicoQuarto) {
        super(local, nome, localizacao, bairro, categoria);
        this.estrelas = estrelas;
        this.arCondicionado = arCondicionado;
        this.tv = tv;
        this.servicoQuarto = servicoQuarto;
    }

    /**
     * Método responsável por retornar o conteúdo do atributo estrelas.
     *
     * @return int - Quantidade de estrelas do hotel.   
     */
    public int getEstrelas() {
        return estrelas;
    }

    /**
     * Método responsável por retornar o conteúdo do atributo arCondicionado.
     *
     * @return boolean - Se o hotel possui ar-condicionado.
     */
    public boolean getArCondicionado() {
        return arCondicionado;
    }

    /**
     * Método responsável por retornar o conteúdo do atributo tv.
     *
     * @return boolean - Se o hotel possui televisão.
     */
    public boolean getTv() {
        return tv;
    }

    /**
     * Método responsável por retornar o conteúdo do atributo servicoQuarto.
     *
     * @return boolean - Se possui serviço de quarto.
     */
    public boolean getServicoQuarto() {
        return servicoQuarto;
    }

    /**
     * Método responsável pela realizão da comparação de dois hotéis através de
     * seu nome.
     *
     * @param hotel
     * @return int - Resultado da comparação.
     */
    public int compareTo(Hotel hotel) {
        Hotel outroHotel = (Hotel) hotel;
        return (getNome().compareTo(outroHotel.getNome()));
    }
}