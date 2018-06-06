package br.uefs.ecomp.av.util;

public interface IMapa {

    public boolean estaVazio();

    public int obterTamanho();

    public void inserir(Object chave, Object valor);

    public Object recuperar(Object chave);

    public Object remover(Object chave);

    public boolean contemChave(Object chave);

    public Iterador chaves();

    public Iterador valores();
}