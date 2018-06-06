package br.uefs.ecomp.av.util;

public interface ILista {

    public boolean estaVazia();

    public int obterTamanho();

    public void inserir(int index, Comparable o);

    public void inserirInicio(Comparable o);

    public void inserirFinal(Comparable o);

    public Comparable remover(int index);

    public Comparable removerInicio();

    public Comparable removerFinal();

    public Comparable recuperar(int index);

    public Iterador iterador();
    
    public void ordenar();
}
