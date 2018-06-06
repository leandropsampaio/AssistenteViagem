package br.uefs.ecomp.av.util;

public class DadoNaoEncontradoException extends Exception {

    public DadoNaoEncontradoException() {
        super();
    }

    public DadoNaoEncontradoException(String msg) {
        super(msg);
    }

    public DadoNaoEncontradoException(Throwable t) {
        super(t);
    }
}
