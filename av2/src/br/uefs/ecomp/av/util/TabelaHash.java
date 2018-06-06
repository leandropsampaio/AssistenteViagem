package br.uefs.ecomp.av.util;

import java.io.Serializable;

/**
 * Classe responsável por armazenar um conjunto de pares (chave, valor). Para
 * cada chave, existe apenas um valor correspondente. Lembrando que para evitar
 * colisoes foi utilizado lista encadeda como forma de contornar este problema.
 *
 * @author Leandro Pereira Sampaio.
 */
public class TabelaHash implements IMapa, Serializable {

    private DadoTabela[] tabela; // Vetor que compoe a tabela hah.
    private int tam = 0;
    private int tamanho = 0;

    /**
     * Construtor responsável por inicializr os atributos da classe TabelaHash
     *
     * @param tam
     */
    public TabelaHash(int tam) {
        this.tam = tam;
        tabela = new DadoTabela[tam]; // Instanciando o vetor da tabela hash.
    }

    /**
     * Armazena um mapeamento (chave, valor). Lembrando que para calcular o
     * indice do vetor, foi utilizado como m�trica a primeira letra de um
     * determinado objeto.
     *
     * @param chave - A chave do mapeamento.
     * @param valor - O valor associado a chave.
     */
    @Override
    public void inserir(Object chave, Object valor) {
        int i = 0;
        DadoTabela dados = new DadoTabela(chave, valor);
        while (i < tam
                && tabela[Math.abs((int) chave.hashCode() + i) % tam] != null) {
            i = i + 1;
        }
        if (i < tam) {
            tamanho++;
            tabela[Math.abs((int) chave.hashCode() + i) % tam] = dados;
        } else {
            redimensionaTabela();
            inserir(chave, valor);
        }
    }

    /**
     * Retorna o valor correspondente a chave dada.
     *
     * @param chave a chave
     * @return o valor associado a chave
     */
    @Override
    public Object recuperar(Object chave) {
        int i = 0;
        while (i < tam && tabela[Math.abs((int) chave.hashCode() + i) % tam] != null
                && !tabela[Math.abs((int) chave.hashCode() + i) % tam].getChave().equals(chave)) //(pos + i) % tam!!!!!!!
        {
            i = i + 1;
        }
        if (tabela[Math.abs((int) chave.hashCode() + i) % tam] != null
                && tabela[Math.abs((int) chave.hashCode() + i) % tam].getChave().equals(chave)) { //(pos + i) % tam!!!!!!
            return tabela[Math.abs((int) chave.hashCode() + i) % tam].getValor();
        } else {
            return null;
        }
    }

    /**
     * Remove um mapeamento da estrutura.
     *
     * @param chave a chave
     * @return
     */
    @Override
    public Object remover(Object chave) {
        int i = 0;

        while (i < tam && tabela[((int) chave.hashCode() + i) % tam] != null
                && tabela[((int) chave.hashCode() + i) % tam].getChave() != chave) //(pos + i) % tam!!!!!!!
        {
            i = i + 1;
        }
        if (tabela[Math.abs((int) chave.hashCode() + i) % tam] != null
                && tabela[Math.abs((int) chave.hashCode() + i) % tam].getChave().equals(chave)) { //(pos + i) % tam!!!!!!!
            Object objeto = tabela[Math.abs((int) chave.hashCode() + i) % tam].getValor();
            tabela[Math.abs((int) chave.hashCode() + i) % tam] = null;
            tamanho--;
            return objeto;
        } else {
            return null;
        }
    }

    /**
     * Método responsável por verificar se a tabela hash está vazia.
     *
     * @return true, caso n�o tenha nenhum par armazenado na estrutura. False,
     * caso contr�rio.
     */
    @Override
    public boolean estaVazio() {
        for (int i = 0; i < tabela.length; i++) {
            if (tabela[i] != null) {
                return false;
            }
        }
        return true;
    }

    /*
     * Metodo responsável por obter o tamanho da tabela hash.
     *
     *@return tamanho - Tamnho do vetor do Hash.
     */
    @Override
    public int obterTamanho() {
        return this.tamanho;
    }

    /* 
     * Metodo responsável por verificar se a tabela já possui o valor da chave.
     *
     *@return true - Caso não possua a chave recebida.
     */
    @Override
    public boolean contemChave(Object chave) {
        return recuperar(chave) != null;
    }

    /**
     * Método responsável por percorrer a tabela através da chave.
     *
     * @return iterador - Iterador das chaves.
     */
    @Override
    public Iterador chaves() {
        MyIteradorChave it = new MyIteradorChave(tabela);
        return it;
    }

    /**
     * Método responsável por percorrer a tabela através dos valores.
     *
     * @return iterador - Iterador dos valores.
     */
    @Override
    public Iterador valores() {
        MyIteradorValor it = new MyIteradorValor(tabela);
        return it;
    }

    /**
     * Método responsável por redimensionar a tabela.
     *
     */
    private void redimensionaTabela() {
        int novaCapacidade = (int) (tam + (tam * 0.7));
        DadoTabela[] tabelaAux = new DadoTabela[novaCapacidade];
        tam = novaCapacidade;
        for (int i = 0; i < tabela.length; i++) {
            if (tabela[i] != null) {
                DadoTabela dados = new DadoTabela(tabela[i].getChave(), tabela[i].getValor());
                tabelaAux[i] = dados;
            }
        }
        tabela = new DadoTabela[novaCapacidade];

        for (int i = 0; i < tabela.length; i++) {
            if (tabelaAux[i] != null) {
                DadoTabela dados = new DadoTabela(tabelaAux[i].getChave(), tabelaAux[i].getValor());
                tabela[i] = dados;
            }
        }
    }
}
