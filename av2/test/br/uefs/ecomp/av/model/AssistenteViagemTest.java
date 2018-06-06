package br.uefs.ecomp.av.model;

import br.uefs.ecomp.av.exception.EspacoEmBrancoException;
import br.uefs.ecomp.av.exception.UsuarioCadastradoException;
import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;

import br.uefs.ecomp.av.model.enums.*;
import br.uefs.ecomp.av.util.Coordenadas;
import br.uefs.ecomp.av.util.DadoDuplicadoException;
import br.uefs.ecomp.av.util.DadoNaoEncontradoException;
import br.uefs.ecomp.av.util.Iterador;

public class AssistenteViagemTest {

    private Local local1 = null, local2 = null;
    private Usuario usuario1 = null, usuario2 = null;
    private Atracao atracao1 = null, atracao2 = null, atracao3 = null, atracao4 = null;
    private AvaliacaoRestaurante avaliacao1 = null, avaliacao2 = null, avaliacao3 = null;
    private AvaliacaoHotel avaliacao4 = null, avaliacao5 = null, avaliacao6 = null;
    private AssistenteViagem assistente = null;

    private DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    @Before
    public void setUp() throws Exception {
        usuario1 = new Usuario("fulano@gmail.com", "qwerasdfzxcv", Sexo.MASCULINO, "Fulano de Tal");
        usuario2 = new Usuario("sicrana@gmail.com", "uiophjklvbnm", Sexo.FEMININO, "Sicrana de Qual");
        local1 = new Local("Feira", new Coordenadas(-12.266944, -38.966944), "Bahia", "Brasil");
        local2 = new Local("Salvador", new Coordenadas(-12.971111, -38.510833), "Bahia", "Brasil");
        atracao1 = new Hotel(local1, "Hotel1", new Coordenadas(-12.199689, -38.971118), "Centro", CategoriaAtracao.Hotel, 5, true, true, true);
        atracao2 = new Hotel(local2, "Hotel2", new Coordenadas(-12.254778, -38.956394), "Orla", CategoriaAtracao.Hotel, 4, false, false, false);
        atracao3 = new Restaurante(local1, "Restaurante1", new Coordenadas(-12.254778, -38.956394), "Kalilandia", CategoriaAtracao.RESTAURANTE, FaixaPreco.SOFISTICADO, TipoCozinha.INTERNACIONAL);
        atracao4 = new Restaurante(local2, "Restaurante2", new Coordenadas(-12.254778, -38.956394), "Zona Sul", CategoriaAtracao.RESTAURANTE, FaixaPreco.SOFISTICADO, TipoCozinha.INTERNACIONAL);
        avaliacao1 = new AvaliacaoRestaurante(usuario1, atracao1, 2, "Dureza!", "Parece mais o O do Borodogo!", format.parse("12/10/2014"), TipoVisita.passeio, TipoRefeicao.almoco, 4, 5, 3);
        avaliacao2 = new AvaliacaoRestaurante(usuario1, atracao2, 5, "Excelente restaurante!", "Bom servico, pratos bem saborosos, preco um pouco alto.", format.parse("12/10/2014"), TipoVisita.trabalho, TipoRefeicao.cafeDaManha, 5, 5, 4);
        avaliacao3 = new AvaliacaoRestaurante(usuario1, atracao3, 5, "Melhor que a Torre Eiffel!", "Eu, como legitimo morador do Tomba, me arrepio todos os dias ao ver esta obra.", format.parse("13/10/2014"), TipoVisita.passeio, TipoRefeicao.lanche, 3, 3, 4);
        avaliacao4 = new AvaliacaoHotel(usuario2, atracao1, 4, "Bom pra relaxar!", "Praca do Borogodo eh o que ha!", format.parse("13/10/2014"), ObjetivoVisita.viagem, 4, 3, 3);
        avaliacao5 = new AvaliacaoHotel(usuario2, atracao2, 4, "Otimo restaurante!", "Gostei, mas achei os gar�ons meio desatenciosos.", format.parse("14/10/2014"), ObjetivoVisita.viagem, 5, 3, 4);
        avaliacao6 = new AvaliacaoHotel(usuario2, atracao3, 5, "Melhor que o Elevador Lacerda!", "Nao tem coisa mais bela na Feira de Santana, ja dizia Feira da Depressao.", format.parse("15/10/2014"), ObjetivoVisita.viagem, 5, 4, 4);
    }

    @Test
    public void testBasic() {
        AssistenteViagem.zerarSingleton();
        assistente = AssistenteViagem.getInstance();

        assertNotNull(assistente);
    }

    @Test
    public void testCadastrarListarLocais() throws EspacoEmBrancoException, DadoDuplicadoException {
        AssistenteViagem.zerarSingleton();
        assistente = AssistenteViagem.getInstance();

        Iterador it = assistente.listarLocais();
        assertFalse(it.temProximo());

        assistente.cadastrarLocal("Salvador", new Coordenadas(-12.971111, -38.510833), "Bahia", "Brasil");
        it = assistente.listarLocais();
        assertTrue(it.temProximo());
        assertEquals(local2, it.obterProximo());
        assertFalse(it.temProximo());

        assistente.cadastrarLocal("Feira", new Coordenadas(-12.266944, -38.966944), "Bahia", "Brasil");
        it = assistente.listarLocais();

        // os locais devem ser listados em ordem alfabetica
        assertTrue(it.temProximo());
        assertEquals(local1, it.obterProximo());
        assertTrue(it.temProximo());
        assertEquals(local2, it.obterProximo());
        assertFalse(it.temProximo());
    }

    @Test
    public void testCadastrarLogarUsuario() throws EspacoEmBrancoException, UsuarioCadastradoException {
        AssistenteViagem.zerarSingleton();
        assistente = AssistenteViagem.getInstance();

        assistente.cadastrarUsuario("fulano@gmail.com", "qwerasdfzxcv", Sexo.MASCULINO, "Fulano de Tal");
        assistente.cadastrarUsuario("sicrana@gmail.com", "uiophjklvbnm", Sexo.FEMININO, "Sicrana de Qual");
        //assertNull(assistente.fazerLogin("fulano@gmail.com", "123456"));
        //assertNull(assistente.fazerLogin("sicrana@gmail.com", "qwerasdfzxcv"));
        assertEquals(usuario1, assistente.fazerLogin("fulano@gmail.com", "qwerasdfzxcv"));
        assertEquals(usuario2, assistente.fazerLogin("sicrana@gmail.com", "uiophjklvbnm"));
    }

    @Test
    public void cadastrarBuscarAtracaoOuRestaurante() throws EspacoEmBrancoException, DadoDuplicadoException, UsuarioCadastradoException, DadoNaoEncontradoException {
        AssistenteViagem.zerarSingleton();
        assistente = AssistenteViagem.getInstance();

        assistente.cadastrarLocal("Feira", new Coordenadas(-12.266944, -38.966944), "Bahia", "Brasil");

        assistente.cadastrarUsuario("fulano@gmail.com", "qwerasdfzxcv", Sexo.MASCULINO, "Fulano de Tal");
        assistente.fazerLogin("fulano@gmail.com", "qwerasdfzxcv");

        //assertNull(assistente.buscarAtracao("Praca do Borogodo"));
        assistente.cadastrarHotel(local1, "Hotel1", new Coordenadas(-12.199689, -38.971118), "Centro", CategoriaAtracao.Hotel, 5, true, true, true);
        assertEquals(atracao1, assistente.buscarAtracao("Hotel1"));

        assistente.cadastrarRestaurante(local1, "Restaurante1", new Coordenadas(-12.254778, -38.956394), "Kalilandia", CategoriaAtracao.RESTAURANTE, FaixaPreco.SOFISTICADO, TipoCozinha.INTERNACIONAL);

        assistente.cadastrarHotel(local2, "Hotel2", new Coordenadas(-12.254778, -38.956394), "Orla", CategoriaAtracao.Hotel, 4, false, false, false);

        assertEquals(atracao2, assistente.buscarAtracao("Hotel2"));
        assertEquals(atracao3, assistente.buscarAtracao("Restaurante1"));
        assertEquals(atracao1, assistente.buscarAtracao("Hotel1"));
        //assertNull(assistente.buscarAtracao("Atracao nao cadastrada"));
    }

    @Test
    public void testCadastrarBuscarLocal() throws EspacoEmBrancoException, DadoNaoEncontradoException, DadoDuplicadoException {
        AssistenteViagem.zerarSingleton();
        assistente = AssistenteViagem.getInstance();

        assistente.cadastrarLocal("Feira", new Coordenadas(-12.266944, -38.966944), "Bahia", "Brasil");
        assertEquals(local1, assistente.buscarLocal("Feira"));
        //assertNull(assistente.buscarLocal("Salvador"));

        assistente.cadastrarLocal("Salvador", new Coordenadas(-12.971111, -38.510833), "Bahia", "Brasil");
        assertEquals(local1, assistente.buscarLocal("Feira"));
        assertEquals(local2, assistente.buscarLocal("Salvador"));
    }

    @Test
    public void testCadastrarListarAtracoesOrdemAlfabetica() throws EspacoEmBrancoException, DadoDuplicadoException, UsuarioCadastradoException, DadoNaoEncontradoException {
        AssistenteViagem.zerarSingleton();
        AssistenteViagem assistente = AssistenteViagem.getInstance();

        assistente.cadastrarLocal("Feira", new Coordenadas(-12.266944, -38.966944), "Bahia", "Brasil");
        Local local = assistente.buscarLocal("Feira");

        Iterador it = local.listarAtracoesOrdemAlfabetica();
        assertFalse(it.temProximo());

        assistente.cadastrarUsuario("fulano@gmail.com", "qwerasdfzxcv", Sexo.MASCULINO, "Fulano de Tal");
        assistente.fazerLogin("fulano@gmail.com", "qwerasdfzxcv");
        assistente.cadastrarRestaurante(local, "Restaurante1", new Coordenadas(-12.254778, -38.956394), "Kalilandia", CategoriaAtracao.RESTAURANTE, FaixaPreco.SOFISTICADO, TipoCozinha.INTERNACIONAL);
        it = local.listarAtracoesOrdemAlfabetica();
        assertTrue(it.temProximo());
        assertEquals(atracao3, it.obterProximo());
        assertFalse(it.temProximo());

        assistente.cadastrarHotel(local, "Hotel1", new Coordenadas(-12.199689, -38.971118), "Centro", CategoriaAtracao.Hotel, 5, true, true, true);

        it = local.listarAtracoesOrdemAlfabetica();
        assertTrue(it.temProximo());
        assertEquals(atracao1, it.obterProximo());
        assertTrue(it.temProximo());
        assertEquals(atracao3, it.obterProximo());
        assertFalse(it.temProximo());
    }

    @Test
    public void testProcessarNovasAvaliacoes() throws ParseException, EspacoEmBrancoException, DadoDuplicadoException, UsuarioCadastradoException, DadoNaoEncontradoException {
        AssistenteViagem.zerarSingleton();
        assistente = AssistenteViagem.getInstance();

        assistente.cadastrarLocal("Feira", new Coordenadas(-12.266944, -38.966944), "Bahia", "Brasil");
        Local local = assistente.buscarLocal("Feira");
        assistente.cadastrarUsuario("fulano@gmail.com", "qwerasdfzxcv", Sexo.MASCULINO, "Fulano de Tal");
        assistente.cadastrarUsuario("sicrana@gmail.com", "uiophjklvbnm", Sexo.FEMININO, "Sicrana de Qual");

        Usuario usuario1 = assistente.fazerLogin("fulano@gmail.com", "qwerasdfzxcv");
        assistente.cadastrarHotel(local2, "Hotel2", new Coordenadas(-12.254778, -38.956394), "Orla", CategoriaAtracao.Hotel, 4, false, false, false);
        assistente.cadastrarRestaurante(local1, "Restaurante1", new Coordenadas(-12.254778, -38.956394), "Kalilandia", CategoriaAtracao.RESTAURANTE, FaixaPreco.SOFISTICADO, TipoCozinha.INTERNACIONAL);
        assistente.cadastrarHotel(local1, "Hotel1", new Coordenadas(-12.199689, -38.971118), "Centro", CategoriaAtracao.Hotel, 5, true, true, true);
        Atracao atracaoAvaliada1 = assistente.buscarAtracao("Hotel2");
        Atracao atracaoAvaliada2 = assistente.buscarAtracao("Restaurante1");

        usuario1.avaliarAtracao(atracaoAvaliada1, 2, "Dureza!", "Parece mais o O do Borodogo!", format.parse("12/10/2014"), TipoVisita.passeio, TipoRefeicao.almoco, 4, 5, 3);
        usuario1.avaliarAtracao(atracaoAvaliada2, 5, "Excelente restaurante!", "Bom servico, pratos bem saborosos, preco um pouco alto.", format.parse("12/10/2014"), TipoVisita.trabalho, TipoRefeicao.cafeDaManha, 5, 5, 4);

        Usuario usuario2 = assistente.fazerLogin("sicrana@gmail.com", "uiophjklvbnm");
        usuario2.avaliarAtracao(atracaoAvaliada2, 4, "Otimo restaurante!", "Gostei, mas achei os gar�ons meio desatenciosos.", format.parse("14/10/2014"), ObjetivoVisita.viagem, 5, 3, 4);

        Avaliacao avaliacaoAtual = assistente.processarProximaAvaliacao();
        assertEquals(avaliacao1, avaliacaoAtual);
        avaliacaoAtual = assistente.processarProximaAvaliacao();
        assertEquals(avaliacao2, avaliacaoAtual);
        avaliacaoAtual = assistente.processarProximaAvaliacao();
        assertEquals(avaliacao5, avaliacaoAtual);
        //assertNull(assistente.processarProximaAvaliacao());
    }

    @Test
    public void testCadastrarAceitarListarAvaliacoes() throws ParseException, EspacoEmBrancoException, DadoDuplicadoException, UsuarioCadastradoException, DadoNaoEncontradoException {
        AssistenteViagem.zerarSingleton();
        assistente = AssistenteViagem.getInstance();

        assistente.cadastrarLocal("Feira", new Coordenadas(-12.266944, -38.966944), "Bahia", "Brasil");
        Local local = assistente.buscarLocal("Feira");
        assistente.cadastrarUsuario("fulano@gmail.com", "qwerasdfzxcv", Sexo.MASCULINO, "Fulano de Tal");
        assistente.cadastrarUsuario("sicrana@gmail.com", "uiophjklvbnm", Sexo.FEMININO, "Sicrana de Qual");

        Usuario usuario1 = assistente.fazerLogin("fulano@gmail.com", "qwerasdfzxcv");
        assistente.cadastrarHotel(local1, "Hotel1", new Coordenadas(-12.199689, -38.971118), "Centro", CategoriaAtracao.Hotel, 5, true, true, true);
        assistente.cadastrarRestaurante(local2, "Restaurante2", new Coordenadas(-12.254778, -38.956394), "Zona Sul", CategoriaAtracao.RESTAURANTE, FaixaPreco.SOFISTICADO, TipoCozinha.INTERNACIONAL);
        assistente.cadastrarHotel(local2, "Hotel2", new Coordenadas(-12.254778, -38.956394), "Orla", CategoriaAtracao.Hotel, 4, false, false, false);
        Atracao atracaoAvaliada1 = assistente.buscarAtracao("Hotel1");
        Atracao atracaoAvaliada2 = assistente.buscarAtracao("Restaurante2");
        Atracao atracaoAvaliada3 = assistente.buscarAtracao("Hotel2");

        usuario1.avaliarAtracao(atracaoAvaliada1, 4, "Bom pra relaxar!", "Praca do Borogodo eh o que ha!", format.parse("11/10/2014"), ObjetivoVisita.viagem, 4, 3, 3);
        usuario1.avaliarAtracao(atracaoAvaliada2, 5, "Excelente restaurante!", "Bom servico, pratos bem saborosos, preco um pouco alto.", format.parse("12/10/2014"), TipoVisita.trabalho, TipoRefeicao.cafeDaManha, 5, 5, 4);
        usuario1.avaliarAtracao(atracaoAvaliada3, 4, "Otimo restaurante!", "Gostei, mas achei os gar�ons meio desatenciosos.", format.parse("13/10/2014"), ObjetivoVisita.viagem, 5, 3, 4);

        Usuario usuario2 = assistente.fazerLogin("sicrana@gmail.com", "uiophjklvbnm");
        usuario2.avaliarAtracao(atracaoAvaliada1, 5, "Melhor que o Elevador Lacerda!", "Nao tem coisa mais bela na Feira de Santana, ja dizia Feira da Depressao.", format.parse("12/10/2014"), ObjetivoVisita.viagem, 5, 4, 4);
        usuario2.avaliarAtracao(atracaoAvaliada2, 5, "Excelente restaurante!", "Bom servico, pratos bem saborosos, preco um pouco alto.", format.parse("13/10/2014"), TipoVisita.trabalho, TipoRefeicao.cafeDaManha, 5, 5, 4);
        usuario2.avaliarAtracao(atracaoAvaliada3, 4, "Bom pra relaxar!", "Praca do Borogodo eh o que ha!", format.parse("15/10/2014"), ObjetivoVisita.viagem, 4, 3, 3);

        assistente.processarProximaAvaliacao().aceitar();
        assistente.processarProximaAvaliacao().aceitar();
        assistente.processarProximaAvaliacao();	// rejeitar
        assistente.processarProximaAvaliacao().aceitar();
        assistente.processarProximaAvaliacao();	// rejeitar
        assistente.processarProximaAvaliacao();	// rejeitar

        // listar avaliacoes da mais recente para a mais antiga
        Iterador it = atracaoAvaliada1.listarAvaliacoes();
        assertTrue(it.temProximo());
        assertEquals(avaliacao4, it.obterProximo());
        assertTrue(it.temProximo());
        assertEquals(avaliacao6, it.obterProximo());
        assertFalse(it.temProximo());

        it = atracaoAvaliada2.listarAvaliacoes();
        assertTrue(it.temProximo());
        assertEquals(avaliacao2, it.obterProximo());
        assertFalse(it.temProximo());

        it = atracaoAvaliada3.listarAvaliacoes();
        assertFalse(it.temProximo());
    }

    public void testRecalcularRankings() throws ParseException, EspacoEmBrancoException, DadoDuplicadoException, UsuarioCadastradoException, DadoNaoEncontradoException {
        AssistenteViagem.zerarSingleton();
        assistente = AssistenteViagem.getInstance();

        assistente.cadastrarLocal("Feira", new Coordenadas(-12.266944, -38.966944), "Bahia", "Brasil");
        Local local = assistente.buscarLocal("Feira");
        assistente.cadastrarUsuario("fulano@gmail.com", "qwerasdfzxcv", Sexo.MASCULINO, "Fulano de Tal");
        assistente.cadastrarUsuario("sicrana@gmail.com", "uiophjklvbnm", Sexo.FEMININO, "Sicrana de Qual");

        Usuario usuario1 = assistente.fazerLogin("fulano@gmail.com", "qwerasdfzxcv");
        assistente.cadastrarHotel(local1, "Hotel1", new Coordenadas(-12.199689, -38.971118), "Centro", CategoriaAtracao.Hotel, 5, true, true, true);
        assistente.cadastrarRestaurante(local2, "Restaurante2", new Coordenadas(-12.254778, -38.956394), "Zona Sul", CategoriaAtracao.RESTAURANTE, FaixaPreco.SOFISTICADO, TipoCozinha.INTERNACIONAL);
        assistente.cadastrarHotel(local2, "Hotel2", new Coordenadas(-12.254778, -38.956394), "Orla", CategoriaAtracao.Hotel, 4, false, false, false);
        Atracao atracaoAvaliada1 = assistente.buscarAtracao("Hotel1");
        Atracao atracaoAvaliada2 = assistente.buscarAtracao("Restaurante2");
        Atracao atracaoAvaliada3 = assistente.buscarAtracao("Hotel2");

        usuario1.avaliarAtracao(atracaoAvaliada1, 4, "Bom pra relaxar!", "Praca do Borogodo eh o que ha!", format.parse("13/10/2014"), ObjetivoVisita.viagem, 4, 3, 3);
        usuario1.avaliarAtracao(atracaoAvaliada2, 5, "Excelente restaurante!", "Bom servico, pratos bem saborosos, preco um pouco alto.", format.parse("12/10/2014"), TipoVisita.trabalho, TipoRefeicao.cafeDaManha, 5, 5, 4);
        usuario1.avaliarAtracao(atracaoAvaliada3, 4, "Otimo restaurante!", "Gostei, mas achei os gar�ons meio desatenciosos.", format.parse("14/10/2014"), ObjetivoVisita.viagem, 5, 3, 4);

        Usuario usuario2 = assistente.fazerLogin("sicrana@gmail.com", "uiophjklvbnm");
        usuario2.avaliarAtracao(atracaoAvaliada1, 5, "Melhor que o Elevador Lacerda!", "Nao tem coisa mais bela na Feira de Santana, ja dizia Feira da Depressao.", format.parse("15/10/2014"), ObjetivoVisita.viagem, 5, 4, 4);
        usuario2.avaliarAtracao(atracaoAvaliada2, 5, "Excelente restaurante!", "Bom servico, pratos bem saborosos, preco um pouco alto.", format.parse("12/10/2014"), TipoVisita.trabalho, TipoRefeicao.cafeDaManha, 5, 5, 4);
        usuario2.avaliarAtracao(atracaoAvaliada3, 4, "Bom pra relaxar!", "Praca do Borogodo eh o que ha!", format.parse("13/10/2014"), ObjetivoVisita.viagem, 4, 3, 3);

        assistente.processarProximaAvaliacao().aceitar();
        assistente.processarProximaAvaliacao().aceitar();
        assistente.processarProximaAvaliacao().aceitar();
        assistente.processarProximaAvaliacao().aceitar();
        assistente.processarProximaAvaliacao().aceitar();
        assistente.processarProximaAvaliacao().aceitar();

        assistente.recalcularTodosRankingsAtracoes();

        assertEquals(5.0, atracaoAvaliada3.pontuacaoMedia(), 0.001);
        assertEquals(1, atracaoAvaliada3.getOrdem());
        assertEquals(4.5, atracaoAvaliada2.pontuacaoMedia(), 0.001);
        assertEquals(2, atracaoAvaliada2.getOrdem());
        assertEquals(3.0, atracaoAvaliada1.pontuacaoMedia(), 0.001);
        assertEquals(3, atracaoAvaliada1.getOrdem());
    }

    public void testCadastrarListarAtracoesOrdemPontuacaoMedia() throws ParseException, EspacoEmBrancoException, DadoDuplicadoException, UsuarioCadastradoException, DadoNaoEncontradoException {
        AssistenteViagem.zerarSingleton();
        assistente = AssistenteViagem.getInstance();

        assistente.cadastrarLocal("Feira", new Coordenadas(-12.266944, -38.966944), "Bahia", "Brasil");
        Local local = assistente.buscarLocal("Feira");
        assistente.cadastrarUsuario("fulano@gmail.com", "qwerasdfzxcv", Sexo.MASCULINO, "Fulano de Tal");
        assistente.cadastrarUsuario("sicrana@gmail.com", "uiophjklvbnm", Sexo.FEMININO, "Sicrana de Qual");

        Usuario usuario1 = assistente.fazerLogin("fulano@gmail.com", "qwerasdfzxcv");
        assistente.cadastrarHotel(local1, "Hotel1", new Coordenadas(-12.199689, -38.971118), "Centro", CategoriaAtracao.Hotel, 5, true, true, true);
        assistente.cadastrarRestaurante(local2, "Restaurante2", new Coordenadas(-12.254778, -38.956394), "Zona Sul", CategoriaAtracao.RESTAURANTE, FaixaPreco.SOFISTICADO, TipoCozinha.INTERNACIONAL);
        assistente.cadastrarHotel(local2, "Hotel2", new Coordenadas(-12.254778, -38.956394), "Orla", CategoriaAtracao.Hotel, 4, false, false, false);
        Atracao atracaoAvaliada1 = assistente.buscarAtracao("Hotel1");
        Atracao atracaoAvaliada2 = assistente.buscarAtracao("Restaurante2");
        Atracao atracaoAvaliada3 = assistente.buscarAtracao("Hotel2");

        usuario1.avaliarAtracao(atracaoAvaliada1, 4, "Bom pra relaxar!", "Praca do Borogodo eh o que ha!", format.parse("13/10/2014"), ObjetivoVisita.viagem, 4, 3, 3);
        usuario1.avaliarAtracao(atracaoAvaliada2, 5, "Excelente restaurante!", "Bom servico, pratos bem saborosos, preco um pouco alto.", format.parse("12/10/2014"), TipoVisita.trabalho, TipoRefeicao.cafeDaManha, 5, 5, 4);
        usuario1.avaliarAtracao(atracaoAvaliada3, 4, "Otimo restaurante!", "Gostei, mas achei os gar�ons meio desatenciosos.", format.parse("14/10/2014"), ObjetivoVisita.viagem, 5, 3, 4);

        Usuario usuario2 = assistente.fazerLogin("sicrana@gmail.com", "uiophjklvbnm");
        usuario2.avaliarAtracao(atracaoAvaliada1, 5, "Melhor que o Elevador Lacerda!", "Nao tem coisa mais bela na Feira de Santana, ja dizia Feira da Depressao.", format.parse("15/10/2014"), ObjetivoVisita.viagem, 5, 4, 4);
        usuario2.avaliarAtracao(atracaoAvaliada2, 5, "Excelente restaurante!", "Bom servico, pratos bem saborosos, preco um pouco alto.", format.parse("12/10/2014"), TipoVisita.trabalho, TipoRefeicao.cafeDaManha, 5, 5, 4);
        usuario2.avaliarAtracao(atracaoAvaliada3, 4, "Bom pra relaxar!", "Praca do Borogodo eh o que ha!", format.parse("13/10/2014"), ObjetivoVisita.viagem, 4, 3, 3);

        assistente.processarProximaAvaliacao().aceitar();
        assistente.processarProximaAvaliacao().aceitar();
        assistente.processarProximaAvaliacao().aceitar();
        assistente.processarProximaAvaliacao().aceitar();
        assistente.processarProximaAvaliacao().aceitar();
        assistente.processarProximaAvaliacao().aceitar();

        assistente.recalcularTodosRankingsAtracoes();

        Iterador it = local.listarAtracoesOrdemPontuacaoMedia();
        assertTrue(it.temProximo());
        assertEquals(atracao3, it.obterProximo());
        assertTrue(it.temProximo());
        assertEquals(atracao2, it.obterProximo());
        assertTrue(it.temProximo());
        assertEquals(atracao1, it.obterProximo());
        assertFalse(it.temProximo());
    }

    @Test
    public void testCarregarSalvarEstadoSistema() throws ParseException, Exception {
        AssistenteViagem.zerarSingleton();
        assistente = AssistenteViagem.getInstance();

        assistente.cadastrarLocal("Feira", new Coordenadas(-12.266944, -38.966944), "Bahia", "Brasil");

        assistente.cadastrarUsuario("fulano@gmail.com", "qwerasdfzxcv", Sexo.MASCULINO, "Fulano de Tal");
        assistente.fazerLogin("fulano@gmail.com", "qwerasdfzxcv");

        //assertNull(assistente.buscarAtracao("Praca do Borogodo"));
        assistente.cadastrarHotel(local1, "Hotel1", new Coordenadas(-12.199689, -38.971118), "Centro", CategoriaAtracao.Hotel, 5, true, true, true);
        assertEquals(atracao1, assistente.buscarAtracao("Hotel1"));

        assistente.cadastrarRestaurante(local1, "Restaurante1", new Coordenadas(-12.254778, -38.956394), "Kalilandia", CategoriaAtracao.RESTAURANTE, FaixaPreco.SOFISTICADO, TipoCozinha.INTERNACIONAL);
        
        assertEquals(local1, assistente.buscarLocal("Feira"));
        assertEquals(atracao3, assistente.buscarAtracao("Restaurante1"));
        assertEquals(atracao1, assistente.buscarAtracao("Hotel1"));
        //assertNull(assistente.buscarAtracao("Atracao nao cadastrada"));
        
        assistente.salvarEstadoSistema();

        AssistenteViagem.zerarSingleton();
        assistente = AssistenteViagem.getInstance(); 

        assistente.carregarEstadoSistema();
        
        assertEquals(local1, assistente.buscarLocal("Feira"));
        assertEquals(atracao3, assistente.buscarAtracao("Restaurante1"));
        assertEquals(atracao1, assistente.buscarAtracao("Hotel1"));
        // assertNull(assistente.buscarAtracao("Atracao nao cadastrada"));
    }
}
