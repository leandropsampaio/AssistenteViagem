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

public class AvaliacaoHotelTest {

    private Local local = null;
    private Usuario usuario = null;
    private Hotel hotel = null;
    private AvaliacaoHotel avaliacao = null;
    DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    @Before
    public void setUp() throws Exception {
        Date data = format.parse("12/10/2014");
        usuario = new Usuario("fulano@gmail.com", "qwerasdfzxcv", Sexo.MASCULINO, "Fulano de Tal");
        local = new Local("Feira", new Coordenadas(-12.266944, -38.966944), "Bahia", "Brasil");
        hotel = new Hotel(local, "Vivas", new Coordenadas(-12.254778, -38.956394), "Kalilandia", CategoriaAtracao.Hotel, 5, true, true, false);
        avaliacao = new AvaliacaoHotel(usuario, hotel, 5, "Otimo restaurante!", "Bom servico, pratos bem saborosos, preco um pouco alto.", data, ObjetivoVisita.viagem, 5, 3, 4);
    }

    @Test
    public void testBasic() throws ParseException {
        assertNotNull(avaliacao);
        assertSame(usuario, avaliacao.getAvaliador());
        assertSame(hotel, avaliacao.getAtracaoAvaliada());
        assertEquals(5, avaliacao.getPontos());
        assertEquals("Otimo restaurante!", avaliacao.getTitulo());
        assertEquals("Bom servico, pratos bem saborosos, preco um pouco alto.", avaliacao.getTexto());
        assertEquals(format.parse("12/10/2014"), avaliacao.getData());
        assertEquals(ObjetivoVisita.viagem, avaliacao.getObjetivoVisita());
        assertEquals(5, avaliacao.getAtendimento());
        assertEquals(3, avaliacao.getQuartos());
        assertEquals(4, avaliacao.getQualidadeSono());
    }
}
