package br.uefs.ecomp.av.util;

public interface IFila {
  
    public boolean estaVazia();

    public int obterTamanho();

    public void inserirFinal(Comparable o);

    public Comparable removerInicio();

    public Comparable recuperarInicio();   
    
    public Iterador iterador();

}
