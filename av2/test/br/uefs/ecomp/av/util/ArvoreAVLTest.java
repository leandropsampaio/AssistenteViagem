package br.uefs.ecomp.av.util;

import static org.junit.Assert.*;
import org.junit.Test;

public class ArvoreAVLTest {

    private final ArvoreAVL arvore;

    public ArvoreAVLTest() {
        arvore = new ArvoreAVL();
    }

    private void insere() throws DadoDuplicadoException {
        assertEquals(-1, arvore.getAltura());
        assertNull(arvore.getRoot());
        arvore.inserir(6);
        arvore.inserir(21);
        arvore.inserir(56);
        arvore.inserir(34);
        arvore.inserir(2);
        arvore.inserir(7);
        arvore.inserir(3);
        arvore.inserir(9);
        arvore.inserir(4);
        arvore.inserir(10);
        arvore.inserir(13);
        arvore.inserir(14);
        arvore.inserir(11);
        arvore.inserir(19);
        arvore.inserir(15);
        arvore.inserir(17);
        assertNotNull(arvore.getRoot());
    }

    /**
     * Teste do metódo buscar, da classe ArvoreAVL.
     *
     * @throws java.lang.Exception
     */
    @Test(expected = DadoNaoEncontradoException.class)
    public void testBuscar() throws Exception {
        insere();
        assertEquals(10, arvore.buscar(10));
        assertEquals(4, arvore.buscar(4));
        assertEquals(11, arvore.buscar(11));
        assertEquals(19, arvore.buscar(19));
        assertEquals(17, arvore.buscar(17));
        assertEquals(100, arvore.buscar(100));
    }

    /**
     * Teste do método remover, da classe ArvoreAVL.
     *
     * @throws java.lang.Exception
     */
    @Test(expected = DadoNaoEncontradoException.class)
    public void testRemover() throws Exception {
        insere();
        arvore.remover(6);
        arvore.remover(21);
        arvore.remover(56);
        arvore.remover(34);
        arvore.remover(2);
        arvore.remover(7);
        arvore.remover(3);
        arvore.remover(9);
        arvore.remover(4);
        arvore.remover(10);
        arvore.remover(13);
        arvore.remover(14);
        arvore.remover(11);
        arvore.remover(19);
        arvore.remover(15);
        arvore.remover(17);
        arvore.remover(10000);
        assertNull(arvore.getRoot());
    }

    /**
     * Esse método garante que a árvore está balanceada, se não houver diferença
     * de dois níveis ela está balanceada.
     *
     * @throws br.uefs.ecomp.av.util.DadoDuplicadoException
     */
    @Test
    public void testAltura() throws DadoDuplicadoException {
        insere();
        arvore.inserir(100);
        arvore.inserir(101);
        arvore.inserir(102);
        arvore.inserir(103);
        arvore.inserir(104);
        arvore.inserir(105);
        arvore.inserir(106);
        arvore.inserir(107);
        arvore.inserir(108);
        arvore.inserir(109);
        arvore.inserir(110);
        arvore.inserir(120);
        arvore.inserir(130);
        arvore.inserir(140);
        arvore.inserir(150);
        arvore.inserir(160);
    }

    /**
     * Caso a Arvore contenha erro irá printar na tela de testes
     *
     * @throws br.uefs.ecomp.av.util.DadoDuplicadoException
     */
    @Test
    public void testProcurarErro() throws DadoDuplicadoException {
        insere();
        arvore.ProcurarErro();
    }

    @Test
    public void testIterator() throws DadoDuplicadoException {
        arvore.inserir(1);
        arvore.inserir(2);
        arvore.inserir(3);
        arvore.inserir(4);
        arvore.inserir(5);
        Iterador it = arvore.iterador();
        assertTrue(it.temProximo());
        assertEquals(1, it.obterProximo());
        assertEquals(2, it.obterProximo());
        assertEquals(3, it.obterProximo());
        assertEquals(4, it.obterProximo());
        assertEquals(5, it.obterProximo());
        assertFalse(it.temProximo());
    }
}
