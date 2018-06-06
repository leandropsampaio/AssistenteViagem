package br.uefs.ecomp.av.model;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import br.uefs.ecomp.av.model.enums.*;
import br.uefs.ecomp.av.util.Coordenadas;

public class AvaliacaoRestaurantelTest {

    private Local local = null;
    private Usuario usuario = null;
    private Restaurante restaurante = null;
    private AvaliacaoRestaurante avaliacao = null;
    DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    @Before
    public void setUp() throws Exception {
        Date data = format.parse("12/10/2014");
        usuario = new Usuario("fulano@gmail.com", "qwerasdfzxcv", Sexo.MASCULINO, "Fulano de Tal");
        local = new Local("Feira", new Coordenadas(-12.266944, -38.966944), "Bahia", "Brasil");
        restaurante = new Restaurante(local, "Vivas", new Coordenadas(-12.254778, -38.956394), "Kalilandia", CategoriaAtracao.RESTAURANTE, FaixaPreco.SOFISTICADO, TipoCozinha.INTERNACIONAL);
        avaliacao = new AvaliacaoRestaurante(usuario, restaurante, 5, "Otimo restaurante!", "Bom servico, pratos bem saborosos, preco um pouco alto.", data, TipoVisita.passeio, TipoRefeicao.almoco, 4, 5, 3);
    }

    @Test
    public void testBasic() throws ParseException {
        assertNotNull(avaliacao);
        assertSame(usuario, avaliacao.getAvaliador());
        assertSame(restaurante, avaliacao.getAtracaoAvaliada());
        assertEquals(5, avaliacao.getPontos());
        assertEquals("Otimo restaurante!", avaliacao.getTitulo());
        assertEquals("Bom servico, pratos bem saborosos, preco um pouco alto.", avaliacao.getTexto());
        assertEquals(format.parse("12/10/2014"), avaliacao.getData());
        assertEquals(TipoVisita.passeio, avaliacao.getTipoVisita());
        assertEquals(TipoRefeicao.almoco, avaliacao.getTipoRefeicao());
        assertEquals(4, avaliacao.getAtendimento());
        assertEquals(5, avaliacao.getComida());
        assertEquals(3, avaliacao.getCustoBeneficio());
    }
}
