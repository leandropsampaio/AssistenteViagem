package br.uefs.ecomp.av.controller;

import br.uefs.ecomp.av.exception.EspacoEmBrancoException;
import br.uefs.ecomp.av.exception.UsuarioCadastradoException;
import java.util.Date;
import br.uefs.ecomp.av.model.AssistenteViagem;
import br.uefs.ecomp.av.model.Atracao;
import br.uefs.ecomp.av.model.Local;
import br.uefs.ecomp.av.model.Usuario;
import br.uefs.ecomp.av.model.enums.*;
import br.uefs.ecomp.av.util.Coordenadas;
import br.uefs.ecomp.av.util.DadoDuplicadoException;
import br.uefs.ecomp.av.util.DadoNaoEncontradoException;
import br.uefs.ecomp.av.util.Iterador;

public class ControllerUsuario {

    private AssistenteViagem assistente = null;

    private Usuario usuarioAtual = null;

    private Local localAtual = null;

    private Atracao atracaoAtual = null;

    public ControllerUsuario() {
        this.assistente = AssistenteViagem.getInstance();
    }

    public void cadastrarUsuario(String emailLogin, String hashSenha, Sexo sexo, String nomeCompleto) throws EspacoEmBrancoException, UsuarioCadastradoException {
        this.assistente.cadastrarUsuario(emailLogin, hashSenha, sexo, nomeCompleto);
    }

    public Usuario fazerLogin(String emailLogin, String hashSenha) throws EspacoEmBrancoException {
        return this.usuarioAtual = this.assistente.fazerLogin(emailLogin, hashSenha);
    }

//	public void cadastrarAtracao(Local local, String nome, Coordenadas localizacao, Enum categoria) {
//		this.assistente.cadastrarAtracao(local, nome, localizacao, categoria);
//	}
    public void cadastrarRestaurante(Local local, String nome, Coordenadas localizacao, String bairro,
            CategoriaAtracao categoria, FaixaPreco preco, TipoCozinha cozinha) throws EspacoEmBrancoException, DadoDuplicadoException {
        this.assistente.cadastrarRestaurante(local, nome, localizacao, bairro, categoria, preco, cozinha);
    }

    public void cadastrarHotel(Local local, String nome, Coordenadas localizacao, String bairro,
            CategoriaAtracao categoria, int estrelas, boolean ar, boolean tv, boolean servicoQuarto) throws EspacoEmBrancoException, DadoDuplicadoException {
        this.assistente.cadastrarHotel(local, nome, localizacao, bairro, categoria, estrelas, ar, tv, servicoQuarto);
    }

    public Local buscarLocal(String nome) throws EspacoEmBrancoException, DadoNaoEncontradoException {
        return this.localAtual = this.assistente.buscarLocal(nome);
    }

    public Iterador listarAtracoesOrdemAlfabetica() throws DadoNaoEncontradoException {
        return this.localAtual.listarAtracoesOrdemAlfabetica();
    }

    public Iterador listarAtracoesOrdemPontuacaoMedia() {
        return this.localAtual.listarAtracoesOrdemPontuacaoMedia();
    }

    public Atracao buscarAtracao(String nome) throws EspacoEmBrancoException, DadoNaoEncontradoException {
        return this.atracaoAtual = this.assistente.buscarAtracao(nome);
    }

//	public void avaliarAtracao(int pontos, String titulo, String texto, Date data) {
//		this.usuarioAtual.avaliarAtracao(atracaoAtual, pontos, titulo, texto, data);
//	}
    public void avaliarRestaurante(int pontos, String titulo, String texto, Date data,
            TipoVisita tipoVisita, TipoRefeicao tipoRefeicao, int atendimento, int comida, int custoBeneficio) {
        this.usuarioAtual.avaliarAtracao(atracaoAtual, pontos, titulo, texto, data,
                tipoVisita, tipoRefeicao, atendimento, comida, custoBeneficio);
    }

    public void avaliarHotel(int pontos, String titulo, String texto, Date data,
            ObjetivoVisita objetivoVisita, int atendimento, int quartos, int qualidadeSono) {
        this.usuarioAtual.avaliarAtracao(atracaoAtual, pontos, titulo, texto, data,
                objetivoVisita, atendimento, quartos, qualidadeSono);
    }

    public Iterador listarAvaliacoesPorAtracao() {
        return this.atracaoAtual.listarAvaliacoes();
    }
}
