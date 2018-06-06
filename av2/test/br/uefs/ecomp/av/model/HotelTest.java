package br.uefs.ecomp.av.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.uefs.ecomp.av.model.enums.CategoriaAtracao;
import br.uefs.ecomp.av.util.Coordenadas;

public class HotelTest {

    private Hotel hotel = null;
    private Local local = null;

    @Before
    public void setUp() throws Exception {
        local = new Local("Feira", new Coordenadas(-12.266944, -38.966944), "Bahia", "Brasil");
        hotel = new Hotel(local, "Praca do Borogodo", new Coordenadas(-12.199689, -38.971118), "Centro", CategoriaAtracao.Hotel, 5, true, true, true);
    }

    @Test
    public void testBasic() {
        assertNotNull(hotel);
        assertSame(local, hotel.getLocal());
        assertEquals("Praca do Borogodo", hotel.getNome());
        assertEquals(new Coordenadas(-12.199689, -38.971118), hotel.getLocalizacao());
        assertEquals(CategoriaAtracao.Hotel, hotel.getCategoria());
        assertEquals(5, hotel.getEstrelas());
        assertEquals(true, hotel.getArCondicionado());
        assertEquals(true, hotel.getTv());
        assertEquals(true, hotel.getServicoQuarto());
    }
}
