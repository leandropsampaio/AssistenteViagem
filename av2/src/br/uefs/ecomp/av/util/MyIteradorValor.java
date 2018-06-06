package br.uefs.ecomp.av.util;

public class MyIteradorValor implements Iterador {

    private final DadoTabela[] objetos;
    int posicaoValor;

    public MyIteradorValor(DadoTabela[] objeto) {
        this.objetos = objeto;
        posicaoValor = 0;
    }

    /**
     * Método que retorna o valor desejado, após verificação de existência de um
     * próximo elemento, antes do retorno uma posição é avançada, e assim
     * sucessivamente, até não haver próximo valor para retornar.
     *
     * @return Comparable - Valor armazenado no hash.
     */
    @Override
    public Comparable obterProximo() {

        if (temProximo()) {

            while (objetos[posicaoValor] == null) {
                posicaoValor++;
            }
            return ((Comparable) ((DadoTabela) objetos[posicaoValor++]).getValor());
        }
        return null;
    }

    /**
     * Método que verifica se ainda há mais posições no vetor atual, retornando
     * false para não e true caso possua.
     *
     * @return boolean (true ou false)
     */
    @Override
    public boolean temProximo() {
        while (posicaoValor < objetos.length && objetos[posicaoValor] == null) {
            posicaoValor++;
        }
        if (posicaoValor < objetos.length) {
            return objetos[posicaoValor] != null;
        } else {
            return false;
        }
    }
}
