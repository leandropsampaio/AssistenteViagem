package br.uefs.ecomp.av.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import br.uefs.ecomp.av.util.Coordenadas;

public class LocalTest {

    Local local = null;

    @Before
    public void setUp() throws Exception {
        local = new Local("Feira", new Coordenadas(-12.266944, -38.966944), "Bahia", "Brasil");
    }

    @Test
    public void testBasic() {
        assertNotNull(local);
        assertEquals("Feira", local.getNome());
        assertEquals(new Coordenadas(-12.266944, -38.966944), local.getLocalizacao());
        assertEquals("Bahia", local.getEstado());
        assertEquals("Brasil", local.getPais());
    }
}
