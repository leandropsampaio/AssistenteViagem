package br.uefs.ecomp.av.model;

import br.uefs.ecomp.av.model.enums.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Esta classe implementa os usuários do sistema, contendo os atributos de um
 * usuário.
 *
 * @author Leandro Pereira Sampaio.
 */
public class Usuario implements Comparable<Usuario>, Serializable {

    private final String emailLogin;
    private final String hashSenha;
    private final Sexo sexo;
    private final String nomeCompleto;
    private AssistenteViagem assistente;

    /**
     * Construtor da classe, responsável pela criação um novo usuário.
     *
     * @param emailLogin - Email do usuário.
     * @param hashSenha - Senha do usuário.
     * @param sexo - Sexo(masculino ou feminino) do usuário.
     * @param nomeCompleto - Nome completo do usuário.
     */
    public Usuario(String emailLogin, String hashSenha, Sexo sexo, String nomeCompleto) {
        this.emailLogin = emailLogin;
        this.hashSenha = hashSenha;
        this.nomeCompleto = nomeCompleto;
        this.sexo = sexo;
    }

    /**
     * Método responsável por criar uma nova avaliação da atração.
     *
     * @param atracao
     * @param pontos
     * @param titulo
     * @param texto
     * @param data
     * @param atendimento
     * @param quartos
     * @param objetivoVisita
     * @param qualidadeSono
     */
    public void avaliarAtracao(Atracao atracao, int pontos, String titulo, String texto,
            Date data, ObjetivoVisita objetivoVisita, int atendimento, int quartos, int qualidadeSono) {
        assistente = AssistenteViagem.getInstance();
        AvaliacaoHotel avaliacao = new AvaliacaoHotel(this, atracao, pontos, titulo, texto, data, objetivoVisita,
                atendimento, quartos, qualidadeSono);
        assistente.getListaDeAvaliacoes().inserirFinal((Comparable) avaliacao);
    }

    /**
     * Método responsável por retornar o conteúdo do atributo emailLogin.
     *
     * @return String - Email do usuário que serve como login.
     */
    public String getEmailLogin() {
        return emailLogin;
    }

    /**
     * Método responsável por retornar o conteúdo do atributo hashSenha.
     *
     * @return String - Senha do usúario.
     */
    public String getHashSenha() {
        return hashSenha;
    }

    /**
     * Método responsável por retornar o conteúdo do atributo sexo.
     *
     * @return Sexo - Sexo do usuário.
     */
    public Sexo getSexo() {
        return sexo;
    }

    /**
     * Método responsável por retornar o conteúdo do atributo nomeCompleto.
     *
     * @return String - Nome completo do usuário.
     */
    public String getNomeCompleto() {
        return nomeCompleto;
    }

    /**
     * Método responsável por checar se o objeto recebido é uma instância da
     * classe, através da comparação dos atributos da classe com os atributos do
     * objeto recebido.
     *
     * @param objeto - objeto que será comparado pelo presente método
     *
     * @return Boolean - se o resultado da comparação estiver correto, será
     * retornado true, caso contrário, será retornado false.
     */
    @Override
    public boolean equals(Object objeto) {
        if (objeto instanceof Usuario) {
            Usuario usuario = (Usuario) objeto;
            if (usuario.getEmailLogin().equals(emailLogin) && usuario.getHashSenha().equals(hashSenha)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método responsável pela realizão da comparação de dois usuários através
     * de seu nome.
     *
     * @param usuario
     * @return int - Resultado da comparação.
     */
    @Override
    public int compareTo(Usuario usuario) {
        Usuario outroUsuario = (Usuario) usuario;
        return (nomeCompleto.compareTo(outroUsuario.getNomeCompleto()));
    }

    /**
     * Método responsável por criar uma nova avaliação da atração.
     *
     * @param atracao
     * @param pontos
     * @param titulo
     * @param texto
     * @param data
     * @param tipoVisita
     * @param tipoRefeicao
     * @param atendimento
     * @param comida
     * @param custoBeneficio
     */
    public void avaliarAtracao(Atracao atracao, int pontos, String titulo, String texto, Date data,
            TipoVisita tipoVisita, TipoRefeicao tipoRefeicao, int atendimento, int comida, int custoBeneficio) {
        assistente = AssistenteViagem.getInstance();
        AvaliacaoRestaurante avaliacao = new AvaliacaoRestaurante(this, atracao, pontos, titulo,
                texto, data, tipoVisita, tipoRefeicao, atendimento, comida, custoBeneficio);
        assistente.getListaDeAvaliacoes().inserirFinal((Comparable) avaliacao);
    }
}
