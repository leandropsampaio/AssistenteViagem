package br.uefs.ecomp.av.controller;

import br.uefs.ecomp.av.exception.EspacoEmBrancoException;
import br.uefs.ecomp.av.model.AssistenteViagem;
import br.uefs.ecomp.av.model.Avaliacao;
import br.uefs.ecomp.av.util.Coordenadas;
import br.uefs.ecomp.av.util.DadoDuplicadoException;
import br.uefs.ecomp.av.util.Iterador;

public class ControllerOperador {

    private AssistenteViagem assistente = null;

    private Avaliacao avaliacaoAtual = null;

    public ControllerOperador() {
        this.assistente = AssistenteViagem.getInstance();
    }

    public void cadastrarLocal(String nome, Coordenadas localizacao, String estado, String pais) throws EspacoEmBrancoException, DadoDuplicadoException {
        this.assistente.cadastrarLocal(nome, localizacao, estado, pais);
    }

    public Iterador listarLocais() {
        return this.assistente.listarLocais();
    }

    public Avaliacao processarProximaAvaliacao() {
        this.avaliacaoAtual = this.assistente.processarProximaAvaliacao();
        return avaliacaoAtual;
    }

    public void rejeitarAvaliacao() {
        this.avaliacaoAtual = null;
    }

    public void aceitarAvaliacao() {
        this.avaliacaoAtual.aceitar();
        this.avaliacaoAtual = null;
    }

    public void recalcularRankingsAtracoes() {
        this.assistente.recalcularTodosRankingsAtracoes();
    }

    public void salvarEstadoSistema() throws Exception {
        this.assistente.salvarEstadoSistema();
    }

    public void zerarSistema() {
        AssistenteViagem.zerarSingleton();
        this.assistente = AssistenteViagem.getInstance();
    }

    public void carregarEstadoSistema() throws Exception {
        this.assistente.carregarEstadoSistema();
    }
}
