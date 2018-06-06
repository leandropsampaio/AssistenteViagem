package br.uefs.ecomp.av.util;

public interface IArvore {

    public Object buscar(Comparable item) throws DadoNaoEncontradoException;

    public void inserir(Comparable item) throws DadoDuplicadoException;

    public void remover(Comparable item) throws DadoNaoEncontradoException;

    public Iterador iterador();
}
