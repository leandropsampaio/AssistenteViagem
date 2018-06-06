package br.uefs.ecomp.av.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.uefs.ecomp.av.model.enums.Sexo;

public class UsuarioTest {

    private Usuario usuario = null;

    @Before
    public void setUp() throws Exception {
        usuario = new Usuario("fulano@gmail.com", "qwerasdfzxcv", Sexo.MASCULINO, "Fulano de Tal");
    }

    @Test
    public void testBasic() {
        assertNotNull(usuario);
        assertEquals("fulano@gmail.com", usuario.getEmailLogin());
        assertEquals("qwerasdfzxcv", usuario.getHashSenha());
        assertEquals(Sexo.MASCULINO, usuario.getSexo());
        assertEquals("Fulano de Tal", usuario.getNomeCompleto());
    }
}
