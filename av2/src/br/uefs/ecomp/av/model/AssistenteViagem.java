/**
 * Componente Curricular: Módulo Integrado de Programação Autores: 
 * Leandro Pereira Sampaio. Lucas Anderson S. de Lima. Sampaio Data: 18/09/2015
 *
 * Declaro que este código foi elaborado por mim de forma individual e não
 * contém nenhum trecho de código de outro colega ou de outro autor, tais como
 * provindos de livros e apostilas, e páginas ou documentos eletrônicos da
 * Internet. Qualquer trecho de código de outra autoria que uma citação para o
 * não a minha está destacado com autor e a fonte do código, e estou ciente que
 * estes trechos não serão considerados para fins de avaliação. Alguns trechos
 * do código podem coincidir com de outros colegas pois estes foram discutidos
 * em sessões tutorias.
 */
package br.uefs.ecomp.av.model;

import br.uefs.ecomp.av.exception.EspacoEmBrancoException;
import br.uefs.ecomp.av.exception.UsuarioCadastradoException;
import br.uefs.ecomp.av.model.enums.*;
import br.uefs.ecomp.av.util.*;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Esta classe implementa o AssistenteViagem do sistema, ela contém as intâncias
 * dela mesmo e de listas encadeadas: lista de usuário, atrações, locais e
 * avaliações.
 *
 * @author Leandro Pereira Sampaio
 */
public class AssistenteViagem implements Serializable {

    private static AssistenteViagem assistente;
    private final ILista listaDeUsuario;
    private ILista listaDeAtracoes;
    private final ILista listaDeLocais;
    private final ILista listaDeAvaliacoes;
    private IMapa mapaLocal;
    private ArvoreAVL arvore;

    /**
     * Construtor do tipo AssistenteViagem, onde são inicializadas novas listas
     * encadeadas.
     */
    public AssistenteViagem() {
        listaDeAvaliacoes = new ListaEncadeada();
        listaDeUsuario = new ListaEncadeada();
        listaDeAtracoes = new ListaEncadeada();
        listaDeLocais = new ListaEncadeada();
        mapaLocal = new TabelaHash(10);
        arvore = new ArvoreAVL();
    }

    /**
     * Método que zera o assistente.
     */
    public static void zerarSingleton() {
        assistente = null;
    }

    /**
     * Método que cria uma nova instância, caso ela esteja zerada.
     *
     * @return AssistenteViagem - Caso esteja zerado irá criar um novo
     * assistente, caso exista irá retornar o mesmo assistente.
     */
    public static AssistenteViagem getInstance() {
        if (assistente == null) {
            assistente = new AssistenteViagem();
        }
        return assistente;
    }

    /**
     * Método para cadastrar o candidato, na lista inicial, isso é feito através
     * da chamada do método insereFinal.
     *
     * @param nome - String o qual adiciona o atributo nome ao objeto local.
     * @param localizacao - Coordenadas o qual adiciona o atributo localizacao
     * ao objeto local.
     * @param pais - String o qual adiciona o atributo pais ao objeto local.
     * @param estado - String o qual adiciona o atributo estado ao local.
     * @throws br.uefs.ecomp.av.exception.EspacoEmBrancoException
     * @throws br.uefs.ecomp.av.util.DadoDuplicadoException
     *
     */
    public void cadastrarLocal(String nome, Coordenadas localizacao, String estado, String pais) throws EspacoEmBrancoException, DadoDuplicadoException {
        if (nome.equals("") || localizacao.getLatitude() == 0 || localizacao.getLongitude() == 0 || estado.equals("") || pais.equals("")) {
            throw new EspacoEmBrancoException();
        }
        try {
            if (buscarLocal(nome) != null) {
                throw new DadoDuplicadoException();
            }
        } catch (DadoNaoEncontradoException ex) {
            Local local = new Local(nome, localizacao, estado, pais);
            listaDeLocais.inserirFinal(local);
            mapaLocal.inserir(nome, local);
        }
    }

    /**
     * Método responsável por enviar o iterador da lista de locais.
     *
     * @return Iterador - Iterador da lista de locais, ordenados pela ordem
     * alfabética.
     */
    public Iterador listarLocais() {
        listaDeLocais.ordenar();
        return listaDeLocais.iterador();
    }

    /**
     * Método responsável por remover e retornar a próxima avaliação existente.
     *
     * @return Avaliacao - Próxima avaliação da lista de avaliações.
     */
    public Avaliacao processarProximaAvaliacao() {
        return ((Avaliacao) listaDeAvaliacoes.removerInicio());
    }

    /**
     * Batch para recalcular os rankings de todas as atrações, em ordem
     * decrescente de média de pontuação.
     */
    public void recalcularTodosRankingsAtracoes() {
        Atracao[] vetorAtracoes = new Atracao[listaDeAtracoes.obterTamanho()];

        for (int i = 0; i < vetorAtracoes.length; i++) {
            vetorAtracoes[i] = (Atracao) listaDeAtracoes.recuperar(i);
        }
        int ini = 0, fim = vetorAtracoes.length - 1;

        //Se o vetor for nulo ou tiver tamanho igual a 0:
        //Uma posição "meio" é escolhida.
        int meio = ini + (fim - ini) / 2, i = ini, j = fim;
        double pivo = vetorAtracoes[meio].pontuacaoMedia();   //Um número pivor é escolhido com base nessa posição do meio.

        while (i <= j) {

            //As médias são comparadas:
            while (vetorAtracoes[i].pontuacaoMedia() > pivo) {
                i++;   //Se vier depois, desloca para a posição à direita.
            }

            while (vetorAtracoes[j].pontuacaoMedia() < pivo) {
                j--;   //Se vier antes, desloca para a posição à esqueda.
            }

            if (i <= j) {   //Troca:
                Atracao temp = vetorAtracoes[i];
                vetorAtracoes[i] = vetorAtracoes[j];
                vetorAtracoes[j] = temp;
                i++;
                j--;
            }
        }

        //Caso ainda não esteja ordenado:
        if (ini < j) {
            this.recalcularTodosRankingsAtracoes();   //Método recursivo.
        }

        if (fim > i) {
            this.recalcularTodosRankingsAtracoes();   //Método recursivo.
        }

        listaDeAtracoes = new ListaEncadeada();   //A lista é reiniciada.

        //O vetor de avaliações de uma atração ordenado é passado para uma lista de mesmo tamanho:
        for (i = 0; i < vetorAtracoes.length; i++) {
            vetorAtracoes[i].setOrdem(i + 1);
            listaDeAtracoes.inserirFinal(vetorAtracoes[i]);
        }
    }

    /**
     * Método para cadastrar um usuário, na lista de usuários, isso é feito
     * através da chamada do método insereFinal.
     *
     * @param emailLogin - String o qual adiciona o atributo emailLogin ao
     * objeto usuario.
     * @param hashSenha - String o qual adiciona o atributo hashSenha ao objeto
     * usuario.
     * @param sexo - String o qual adiciona o atributo sexo ao objeto usuario.
     * @param nomeCompleto - String o qual adiciona o atributo nomeCompleto ao
     * usuario.
     * @throws br.uefs.ecomp.av.exception.EspacoEmBrancoException
     * @throws br.uefs.ecomp.av.exception.UsuarioCadastradoException
     *
     */
    public void cadastrarUsuario(String emailLogin, String hashSenha, Sexo sexo, String nomeCompleto) throws EspacoEmBrancoException, UsuarioCadastradoException {
        if ("".equals(emailLogin) || "".equals(hashSenha) || sexo == null || "".equals(nomeCompleto)) {
            throw new EspacoEmBrancoException();
        }
        Iterador it = listaDeUsuario.iterador();
        while (it.temProximo()) {
            Usuario usuario = (Usuario) it.obterProximo();
            if (emailLogin.equals(usuario.getEmailLogin())) {
                throw new UsuarioCadastradoException();
            }
        }
        Usuario usuario = new Usuario(emailLogin, hashSenha, sexo, nomeCompleto);
        listaDeUsuario.inserirFinal(usuario);
    }

    /**
     * Método para verificar se o usuário está cadastrado e seu email está de
     * acordo com sua senha.
     *
     * @param emailLogin - String o qual adiciona o atributo emailLogin ao
     * objeto usuario.
     * @param hashSenha - String o qual adiciona o atributo hashSenha ao objeto
     * usuario.
     * @return Usuario - O usuário correspondente com a ao email e senha.
     * @throws br.uefs.ecomp.av.exception.EspacoEmBrancoException
     *
     */
    public Usuario fazerLogin(String emailLogin, String hashSenha) throws EspacoEmBrancoException {
        if ("".equals(emailLogin) || "".equals(hashSenha)) {
            throw new EspacoEmBrancoException();
        }
        Iterador it = listaDeUsuario.iterador();
        while (it.temProximo()) {
            Usuario usuario = (Usuario) it.obterProximo();
            if (emailLogin.equals(usuario.getEmailLogin()) && hashSenha.equals(usuario.getHashSenha())) {
                return usuario;
            }
        }
        return null;
    }

    /**
     * Método para cadastrar uma atração, na lista de atracões, isso é feito
     * através da chamada do método insereFinal.
     *
     * @param local - Local o qual adiciona o objeto recebido local ao objeto
     * criado de atracao.
     * @param nome - String o qual adiciona o atributo nome ao objeto de
     * atracao.
     * @param localizacao - Coordenadas o qual adiciona o atributo localizacao
     * ao objeto de atracao.
     * @param bairro - String o qual adiciona o atributo bairro na atracao.
     * @param categoria - Enum o qual adiciona o atributo categoria na atracao.
     * @param estrelas - int o qual adiciona o atributo estrelas na atracao.
     * @param arCondicionado - Boolean o qual adiciona o arCondicionado na
     * atracao.
     * @param tv - Boolean o qual adiciona o atributo tv na atracao.
     * @param servicoQuarto - Boolean o qual adiciona o atributo servicoQuarto
     * na atracao.
     * @throws br.uefs.ecomp.av.exception.EspacoEmBrancoException
     * @throws br.uefs.ecomp.av.util.DadoDuplicadoException
     *
     */
    public void cadastrarHotel(Local local, String nome, Coordenadas localizacao, String bairro,
            CategoriaAtracao categoria, int estrelas, boolean arCondicionado, boolean tv, boolean servicoQuarto)
            throws EspacoEmBrancoException, DadoDuplicadoException {
        if (nome.equals("") || localizacao.getLatitude() == 0 || localizacao.getLongitude() == 0
                || bairro.equals("") || categoria == null || estrelas == 0) {
            throw new EspacoEmBrancoException();
        }
        Hotel hotel = new Hotel(local, nome, localizacao, bairro, categoria, estrelas, arCondicionado, tv, servicoQuarto);
        arvore.inserir(hotel);
        local.getArvore().inserir(hotel);
        listaDeAtracoes.inserirFinal(hotel);
        local.getAtracoes().inserirFinal(hotel);
    }

    /**
     * Método responsável por cadastrar um restaurante, na lista de atracaoes,
     * isso é feito através da chamada do método insereFinal.
     *
     * @param local - Local o qual adiciona o atributo emailLogin ao objeto
     * usuario.
     * @param nome - String o qual adiciona o atributo hashSenha ao objeto
     * usuario.
     * @param localizacao - String o qual adiciona o atributo sexo ao objeto
     * usuario.
     * @param categoria - String o qual adiciona o atributo nomeCompleto ao
     * usuario.
     * @param preco - Enum o qual adiciona o atributo sexo ao objeto usuario.
     * @param cozinha - Enum o qual adiciona o atributo sexo ao objeto usuario.
     * @param bairro - String o qual adiciona o atributo sexo ao objeto usuario.
     * @throws br.uefs.ecomp.av.exception.EspacoEmBrancoException
     * @throws br.uefs.ecomp.av.util.DadoDuplicadoException
     *
     */
    public void cadastrarRestaurante(Local local, String nome, Coordenadas localizacao, String bairro,
            CategoriaAtracao categoria, FaixaPreco preco, TipoCozinha cozinha) throws EspacoEmBrancoException, DadoDuplicadoException {
        if (nome.equals("") || localizacao.getLatitude() == 0 || localizacao.getLongitude() == 0
                || bairro.equals("") || categoria == null || preco == null || cozinha == null) {
            throw new EspacoEmBrancoException();
        }
        Restaurante restaurante = new Restaurante(local, nome, localizacao, bairro, categoria, preco, cozinha);
        arvore.inserir(restaurante);
        local.getArvore().inserir(restaurante);
        listaDeAtracoes.inserirFinal(restaurante);
        local.getAtracoes().inserirFinal(restaurante);
    }

    /**
     * Método para obter um especifico local da listaDeLocais, isso é feito
     * através da busca na lista pelo nome correspondente ao local.
     *
     * @param nome - String o qual, serve como identificação do local.
     *
     * @return Local - O local com o nome correspondente ao que foi solicitado,
     * caso não encontre o método irá retornar nulo.
     * @throws br.uefs.ecomp.av.exception.EspacoEmBrancoException
     * @throws br.uefs.ecomp.av.util.DadoNaoEncontradoException
     */
    public Local buscarLocal(String nome) throws EspacoEmBrancoException, DadoNaoEncontradoException {
        if (nome.equals("")) {
            throw new EspacoEmBrancoException();
        }
        Local local = (Local) mapaLocal.recuperar(nome);
        if (local == null) {
            throw new DadoNaoEncontradoException();
        }
        return local;
    }

    /**
     * Método para obter uma especifico atração na listaDeAtracoes, isso é feito
     * através da busca na lista pelo nome correspondente a atração.
     *
     * @param nome - String o qual, serve como identificação da atração.
     *
     * @return Atracao - A atração com o nome correspondente ao que foi
     * solicitado, caso não encontre, o método irá retornar nulo.
     * @throws br.uefs.ecomp.av.exception.EspacoEmBrancoException
     * @throws br.uefs.ecomp.av.util.DadoNaoEncontradoException
     */
    public Atracao buscarAtracao(String nome) throws EspacoEmBrancoException, DadoNaoEncontradoException {
        if (nome.equals("")) {
            throw new EspacoEmBrancoException();
        }
        Atracao atracao = (Atracao) arvore.buscar(nome);
        return atracao;
    }

    /**
     * Método responsável por retornar a lista de avaliações.
     *
     * @return ILista - Lista de avaliações.
     */
    public ILista getListaDeAvaliacoes() {
        return listaDeAvaliacoes;
    }

    /**
     * Método responsável por retornar a lista de atrações.
     *
     * @return listaDeAtracoes - Lista de atrações.
     */
    public ILista getListaDeAtracoes() {
        return listaDeAtracoes;
    }

    /**
     * Método responsável por retornar a lista de locais.
     *
     * @return listaDeLocais - Lista de locais.
     */
    public ILista getListaDeLocais() {
        return listaDeLocais;
    }

    /**
     * Método responsável por retornar o mapa de locais.
     *
     * @return mapaLocal - Mapa de locais.
     */
    public IMapa getMapaDeLocais() {
        return mapaLocal;
    }

    /**
     * Método responsável por retornar a arvore de atrações.
     *
     * @return arvore - Árvore de atrações.
     */
    public IArvore getArvore() {
        return arvore;
    }
    
    /**
     * Método responsável por retornar a lista de usuarios.
     *
     * @return listaDeUsuario - Lista de usuarios
     */
    public ILista getListaDeUsuario() {
        return listaDeUsuario;
    }

    /**
     * Método para realizar o salvamento dos objetos das classes.
     *
     * @throws java.lang.Exception
     */
    public void salvarEstadoSistema() throws Exception {
        try {
            FileOutputStream file = new FileOutputStream("atracoes.txt");
            ObjectOutputStream output = new ObjectOutputStream(file);

            int tam = listaDeAtracoes.obterTamanho();
            while (tam != 0) {
                Atracao atracao = (Atracao) assistente.listaDeAtracoes.removerInicio();
                output.writeObject(atracao);
                tam--;
            }

            file = new FileOutputStream("avaliacoes.txt");
            output = new ObjectOutputStream(file);
            tam = assistente.listaDeAvaliacoes.obterTamanho();
            while (tam != 0) {
                Avaliacao avaliacao = (Avaliacao) assistente.listaDeAvaliacoes.removerInicio();
                output.writeObject(avaliacao);
                tam--;
            }

            file = new FileOutputStream("locais.txt");
            output = new ObjectOutputStream(file);
            tam = assistente.listaDeLocais.obterTamanho();
            while (tam != 0) {
                Local local = (Local) assistente.listaDeLocais.removerInicio();
                output.writeObject(local);
                tam--;
            }

            file = new FileOutputStream("usuarios.txt");
            output = new ObjectOutputStream(file);
            tam = assistente.listaDeUsuario.obterTamanho();
            while (tam != 0) {
                Usuario usuario = (Usuario) assistente.listaDeUsuario.removerInicio();
                output.writeObject(usuario);
                tam--;
            }

            file.flush();
            file.close();
            output.close();

        } catch (Exception ex) {
        }
    }

    /**
     * Método para realizar o carregamento dos objetos das classes.
     *
     * @throws java.lang.Exception
     */
    public void carregarEstadoSistema() throws Exception {

        try {
            FileInputStream file = new FileInputStream("atracoes.txt");
            ObjectInputStream input = new ObjectInputStream(file);
            boolean x = false;
            while (x == false) {
                Object objeto = null;
                try {
                    objeto = input.readObject();
                } catch (EOFException ex) {
                }
                if (objeto != null) {
                    Atracao atracao = (Atracao) objeto;
                    assistente.arvore.inserir(atracao);
                    assistente.listaDeAtracoes.inserirFinal(atracao);
                } else {
                    x = true;
                }
            }

            file = new FileInputStream("avaliacoes.txt");
            input = new ObjectInputStream(file);
            x = false;
            while (x == false) {
                Object objeto = null;
                try {
                    objeto = input.readObject();
                } catch (EOFException ex) {
                }
                if (objeto != null) {
                    Avaliacao avaliacao = (Avaliacao) objeto;
                    assistente.listaDeAvaliacoes.inserirFinal(avaliacao);
                } else {
                    x = true;
                }
            }

            file = new FileInputStream("locais.txt");
            input = new ObjectInputStream(file);
            x = false;
            while (x == false) {
                Object objeto = null;
                try {
                    objeto = input.readObject();
                } catch (EOFException ex) {
                }
                if (objeto != null) {
                    Local local = (Local) objeto;
                    assistente.mapaLocal.inserir(local.getNome(), local);
                    assistente.listaDeLocais.inserirFinal(local);
                } else {
                    x = true;
                }
            }

            file = new FileInputStream("usuarios.txt");
            input = new ObjectInputStream(file);
            x = false;
            while (x == false) {
                Object objeto = null;
                try {
                    objeto = input.readObject();
                } catch (EOFException ex) {
                }
                if (objeto != null) {
                    Usuario usuario = (Usuario) objeto;
                    assistente.listaDeUsuario.inserirFinal(usuario);
                } else {
                    x = true;
                }
            }

            file.close();
            input.close();

        } catch (EOFException e) {
            e.printStackTrace(System.err);
        } catch (IOException ex) {
        }
    }
}
