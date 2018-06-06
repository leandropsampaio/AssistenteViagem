package br.uefs.ecomp.av.util;

public class MyIteradorChave implements Iterador {

    private final DadoTabela[] objetos;
    int posicaoChave;

    public MyIteradorChave(DadoTabela[] objeto) {
        this.objetos = objeto;
        posicaoChave = 0;
    }

    /**
     * Método que retorna a chave desejada, após verificação de existência de um
     * próximo elemento, antes do retorno uma posição é avançada, e assim
     * sucessivamente, até não haver próxima chave para retornar.
     *
     * @return Comparable - Chave da tabela.
     */
    @Override
    public Comparable obterProximo() {

        if (temProximo()) {
            while (objetos[posicaoChave] == null) {
                posicaoChave++;
            }
            return (Comparable) ((DadoTabela) objetos[posicaoChave++]).getChave();
        }
        return null;
    }

    /**
     * Método que verifica se ainda há mais posições no vetor atual, retornando
     * false para não e true caso sim.
     *
     * @return boolean (true ou false)
     */
    @Override
    public boolean temProximo() {

        while (posicaoChave < objetos.length && objetos[posicaoChave] == null) {
            posicaoChave++;
        }
        if (posicaoChave < objetos.length) {
            return objetos[posicaoChave] != null;
        } else {
            return false;
        }
    }
}
