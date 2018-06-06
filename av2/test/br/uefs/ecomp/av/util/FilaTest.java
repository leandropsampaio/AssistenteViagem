package br.uefs.ecomp.av.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FilaTest {

    private Fila fila;
    String s1 = "Feira", s2 = "Salvador", s3 = "Fortaleza";

    @Before
    public void setUp() throws Exception {
        this.fila = new Fila();
    }

    @Test
    public void testEstaVazia() {
        assertTrue(fila.estaVazia());
        fila.inserirFinal(s1);
        assertFalse(fila.estaVazia());
        fila.inserirFinal(s2);
        assertFalse(fila.estaVazia());
        fila.inserirFinal(s3);
        assertFalse(fila.estaVazia());
        fila.removerInicio();
        assertFalse(fila.estaVazia());
        fila.removerInicio();
        assertFalse(fila.estaVazia());
        fila.removerInicio();
        assertTrue(fila.estaVazia());
    }

    @Test
    public void testObterTamanho() {
        assertEquals(0, fila.obterTamanho());
        fila.inserirFinal(s1);
        assertEquals(1, fila.obterTamanho());
        fila.inserirFinal(s2);
        assertEquals(2, fila.obterTamanho());
        fila.inserirFinal(s3);
        assertEquals(3, fila.obterTamanho());
        fila.removerInicio();
        assertEquals(2, fila.obterTamanho());
        fila.removerInicio();
        assertEquals(1, fila.obterTamanho());
        fila.removerInicio();
        assertEquals(0, fila.obterTamanho());
    }

    @Test
    public void testInserirRemover() {
        assertNull(fila.removerInicio());
        fila.inserirFinal(s1);
        fila.inserirFinal(s2);
        fila.inserirFinal(s3);
        assertEquals(s1, fila.removerInicio());
        assertEquals(s2, fila.removerInicio());
        assertEquals(s3, fila.removerInicio());
        assertNull(fila.removerInicio());
    }

    @Test
    public void testInserirRecuperar() {
        assertNull(fila.recuperarInicio());
        fila.inserirFinal(s1);
        fila.inserirFinal(s2);
        fila.inserirFinal(s3);
        assertEquals(s1, fila.recuperarInicio());
        fila.removerInicio();
        assertEquals(s2, fila.recuperarInicio());
        fila.removerInicio();
        assertEquals(s3, fila.recuperarInicio());
        fila.removerInicio();
        assertNull(fila.recuperarInicio());
    }

    @Test
    public void testIterador() {
        Iterador it = fila.iterador();
        assertFalse(it.temProximo());
        assertNull(it.obterProximo());

        fila.inserirFinal(s1);
        fila.inserirFinal(s2);
        fila.inserirFinal(s3);
        it = fila.iterador();
        assertTrue(it.temProximo());
        assertSame(s1, it.obterProximo());
        assertTrue(it.temProximo());
        assertSame(s2, it.obterProximo());
        assertTrue(it.temProximo());
        assertSame(s3, it.obterProximo());
        assertFalse(it.temProximo());
        assertNull(it.obterProximo());

        fila.removerInicio();
        it = fila.iterador();
        assertTrue(it.temProximo());
        assertSame(s2, it.obterProximo());
        assertTrue(it.temProximo());
        assertSame(s3, it.obterProximo());
        assertFalse(it.temProximo());
        assertNull(it.obterProximo());
    }
}
