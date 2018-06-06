package br.uefs.ecomp.av.util;

import java.io.Serializable;

/**
 * Classe Coordenadas, responsável pela criação de coordenadas, no cadastro de
 * um local.
 *
 * @author Leandro
 */
public class Coordenadas implements Serializable {

    private final double latitude;
    private final double longitude;

    /**
     * Construtor da classe, responsável pela criação das coordenadas.
     *
     * @param latitude
     * @param longitude
     */
    public Coordenadas(double latitude, double longitude) {

        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Método responsável por retornar o conteúdo do atributo latitude.
     *
     * @return double - Latitude da coordenada.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Método responsável por retornar o conteúdo do atributo longitude.
     *
     * @return double - Longitude da coordenada.
     */
    public double getLongitude() {
        return longitude;
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
        if (objeto instanceof Coordenadas) {
            Coordenadas coordenadas = (Coordenadas) objeto;
            if (coordenadas.getLatitude() == latitude && coordenadas.getLongitude() == longitude) {
                return true;
            }
        }
        return false;
    }
}
