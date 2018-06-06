package br.uefs.ecomp.av.util;

import br.uefs.ecomp.av.model.Atracao;
import br.uefs.ecomp.av.model.Hotel;
import br.uefs.ecomp.av.model.Local;
import br.uefs.ecomp.av.model.Restaurante;
import br.uefs.ecomp.av.model.enums.CategoriaAtracao;
import br.uefs.ecomp.av.model.enums.FaixaPreco;
import br.uefs.ecomp.av.model.enums.TipoCozinha;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe que implementa um teste para a tabela hash.
 *
 * @author
 */
public class MapaTest {

    private Atracao restaurante;
    private Atracao hotel = null;
    private Local local = null, local2 = null;
    private TabelaHash tabela;

    /**
     * Este método é executado antes de cada teste de unidade (testes a seguir),
     * e serve para inicializar objetos que são utilizados nos testes.
     */
    @Before
    public void setUp() {

        local = new Local("Feira", new Coordenadas(-12.266944, -38.966944), "Bahia", "Brasil");
        local2 = new Local("Salvador", new Coordenadas(-12.971111, -38.510833), "Bahia", "Brasil");
        hotel = new Hotel(local, "Barra", new Coordenadas(-12.199689, -38.971118), "Centro", CategoriaAtracao.Hotel, 5, true, true, true);
        restaurante = new Restaurante(local, "Vivas", new Coordenadas(-12.254778, -38.956394), "Kalilandia", CategoriaAtracao.RESTAURANTE, FaixaPreco.SOFISTICADO, TipoCozinha.INTERNACIONAL);
        tabela = new TabelaHash(10);
    }

    @Test
    public void testEstaVazia() {
        assertTrue(tabela.estaVazio());
        tabela.inserir("Feira", local);
        assertFalse(tabela.estaVazio());
        tabela.inserir("Salvador", local2);
        assertFalse(tabela.estaVazio());
        tabela.inserir("Vivas", restaurante);
        assertFalse(tabela.estaVazio());
        tabela.remover("Feira");
        assertFalse(tabela.estaVazio());
        tabela.remover("Salvador");
        assertFalse(tabela.estaVazio());
        tabela.remover("Vivas");
        assertTrue(tabela.estaVazio());
    }

    @Test
    public void testObterTamanho() {
        assertEquals(0, tabela.obterTamanho());
        tabela.inserir("Feira", local);
        assertEquals(1, tabela.obterTamanho());
        tabela.inserir("Salvador", local2);
        assertEquals(2, tabela.obterTamanho());
        tabela.inserir("Vivas", restaurante);
        assertEquals(3, tabela.obterTamanho());
        tabela.remover("Feira");
        assertEquals(2, tabela.obterTamanho());
        tabela.remover("Salvador");
        assertEquals(1, tabela.obterTamanho());
        tabela.remover("Vivas");
        assertEquals(0, tabela.obterTamanho());
    }

    @Test
    public void testInserirRemover() {
        assertNull(tabela.remover("Feira"));
        tabela.inserir("Feira", local);
        tabela.inserir("Salvador", local2);
        tabela.inserir("Barra", hotel);
        assertEquals(local, tabela.remover("Feira"));
        assertEquals(local2, tabela.remover("Salvador"));
        assertEquals(hotel, tabela.remover("Barra"));
        assertNull(tabela.remover("Salvador"));
    }

    @Test
    public void testInserirRecuperar() {
        assertTrue(tabela.estaVazio());
        tabela.inserir("Feira", local);
        tabela.inserir("Salvador", local2);
        tabela.inserir("Barra", hotel);
        assertEquals(local, tabela.recuperar("Feira"));
        tabela.remover("Feira");
        assertEquals(local2, tabela.recuperar("Salvador"));
        tabela.remover("Salvador");
        assertEquals(hotel, tabela.recuperar("Barra"));
        tabela.remover("Barra");
        assertTrue(tabela.estaVazio());
    }

    @Test
    public void testIterador() {
        Iterador it;
        Iterador it2;

        tabela.inserir("Feira", local);
        tabela.inserir("Salvador", local2);
        tabela.inserir("Barra", hotel);
        it = tabela.chaves();
        it2 = tabela.valores();
        assertTrue(it2.temProximo());
        assertEquals(local2, it2.obterProximo());
        assertTrue(it2.temProximo());
        assertEquals(local, it2.obterProximo());
        assertTrue(it2.temProximo());
        assertEquals(hotel, it2.obterProximo());
        assertFalse(it2.temProximo());
        assertNull(it2.obterProximo());
        assertTrue(it.temProximo());
        assertSame("Salvador", it.obterProximo());
        assertTrue(it.temProximo());
        assertSame("Feira", it.obterProximo());
        assertTrue(it.temProximo());
        assertSame("Barra", it.obterProximo());
        assertFalse(it.temProximo());
        assertFalse(it2.temProximo());
        assertNull(it.obterProximo());

        tabela.remover("Feira");
        it2 = tabela.valores();
        assertTrue(it2.temProximo());
        assertEquals(local2, it2.obterProximo());
        assertTrue(it2.temProximo());
        assertEquals(hotel, it2.obterProximo());
        assertFalse(it2.temProximo());
        assertNull(it2.obterProximo());
    }
}
