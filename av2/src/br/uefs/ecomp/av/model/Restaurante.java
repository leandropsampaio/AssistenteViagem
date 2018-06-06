package br.uefs.ecomp.av.model;

import br.uefs.ecomp.av.model.enums.CategoriaAtracao;
import br.uefs.ecomp.av.model.enums.FaixaPreco;
import br.uefs.ecomp.av.model.enums.TipoCozinha;
import br.uefs.ecomp.av.util.Coordenadas;

/**
 * Esta classe é uma extensão de atração e implementa os restaurantes do
 * sistema, contendo os atributos de um restaurante.
 *
 * @author Leandro Pereira Sampaio.
 */
public class Restaurante extends Atracao {

    private final FaixaPreco preco;
    private final TipoCozinha cozinha;

    /**
     * Construtor da classe, responsável pela criação um novo restaurante.
     *
     * @param local - Local que se localiza o restaurante.
     * @param nome - Nome do restaurante.
     * @param localizacao - Localização(latitude e longitude) do restaurante.
     * @param categoria - Categoria do restaurante.
     * @param preco - Faixa de preço do restaurante.
     * @param cozinha - Tipo de cozinha do restaurante.
     * @param bairro - Bairro que se localiza o restaurante.
     */
    public Restaurante(Local local, String nome, Coordenadas localizacao, String bairro,
            CategoriaAtracao categoria, FaixaPreco preco, TipoCozinha cozinha) {
        super(local, nome, localizacao, bairro, categoria);
        this.preco = preco;
        this.cozinha = cozinha;
    }

    /**
     * Método responsável por retornar a faixa de preço.
     *
     * @return FaixaPreco - Faixa de preço do restaurante.
     */
    public FaixaPreco getPreco() {
        return preco;
    }

    /**
     * Método responsável por retornar o tipo de cozinha do restaurante.
     *
     * @return TipoCozinha - Tipo de cozinha do restaurante.
     */
    public TipoCozinha getCozinha() {
        return cozinha;
    }
}
