package br.uefs.ecomp.av.util;

import java.io.Serializable;

/**
 * A classe ArvoreAVL é uma classe criada para atender ao requesito pedido no
 * problema, que seria uma velocidade de log de n na base dois, ela garante essa
 * velocidade tanto na busca, remoção e inserção.
 * 
 */
public class ArvoreAVL implements IArvore, Serializable {

    private Node raiz;

    /**
     * O construtor da arvore inicializa a raiz vazia.
     */
    public ArvoreAVL() {
        raiz = null;
    }

    /**
     * O método buscar, é responsável por busca o elemento em qualquer posição
     * da árvore e retorná-lo se encontrado.
     *
     * @param c elemento a buscar
     * @return objeto
     * @throws DadoNaoEncontradoException "dado não encontado"
     */
    @Override
    public Object buscar(Comparable c) throws DadoNaoEncontradoException {
        Node corrente = raiz;
        while (corrente != null) {
            if (corrente.getElemento().toString().compareTo(c.toString()) == 0) {
                return corrente.getElemento();
            }
            if (corrente.getElemento().toString().compareTo(c.toString()) < 0) {
                corrente = corrente.getDireita();
            } else {
                corrente = corrente.getEsquerda();
            }
        }
        throw new DadoNaoEncontradoException("Não foi encontrado nenhum dado!");
    }

    /**
     * O método inserir é o método que insere o elemento na árvore, para isso
     * ela chama um método auxiliar add, que insere recursivamente na posição
     * correta na árvore. Quando a árvore ficar desbalanceada ele
     * altomaticamente balancea.
     *
     * @param c elemento a ser inserido
     * @throws DadoDuplicadoException "dado não encontado"
     */
    @Override
    public void inserir(Comparable c) throws DadoDuplicadoException {
        add(raiz, c, null);
    }

    /**
     * O método remover, remove o elemento da arvore, para isso usa um método
     * auxiliar removerRecursivo.
     *
     * @param c elemento a ser removido
     * @throws DadoNaoEncontradoException "dado não encontado"
     */
    @Override
    public void remover(Comparable c) throws DadoNaoEncontradoException {
        if (buscar(c) != null) {
            removerRecurssao(c, raiz, null);
            calcularAltura(raiz, null);
        }
    }

    /**
     * Método auxiliar que faz a inserção de forma balanceada no sistema.
     *
     * @param corrente inicialmente é raiz
     * @param c inicialmente é o novo elemento
     * @param pai inicialmente é nulo
     * @throws DadoDuplicadoException "dado não encontado"
     */
    private void add(Node corrente, Comparable c, Node pai) throws DadoDuplicadoException {
        if (raiz == null) {
            raiz = new Node(c);
        } else if (corrente.getElemento().toString().compareTo(c.toString()) == 0 && corrente.getDireita() == null) {
            throw new DadoDuplicadoException("Esse elemento já existe!");
        } else if (corrente.getElemento().toString().compareTo(c.toString()) == 0 && corrente.getEsquerda() == null) {
            throw new DadoDuplicadoException("Esse elemento já existe!");
        } else if (corrente.getElemento().toString().compareTo(c.toString()) < 0 && corrente.getDireita() == null) {
            corrente.setDireita(new Node(c));
            calculaAlturaLadoDireito(corrente);
        } else if (corrente.getElemento().toString().compareTo(c.toString()) > 0 && corrente.getEsquerda() == null) {
            corrente.setEsquerda(new Node(c));
            calcularAlturaLadoEsquerdo(corrente);
        } else if (corrente.getElemento().toString().compareTo(c.toString()) < 0) {
            add(corrente.getDireita(), c, corrente);
            calculaAlturaLadoDireito(corrente);
            balanceamento(corrente, pai);
        } else {
            add(corrente.getEsquerda(), c, corrente);
            calcularAlturaLadoEsquerdo(corrente);
            balanceamento(corrente, pai);
        }
    }

    /**
     * O método calculaAlturaLado Esquerdo, cacula a altura da árvore tendendo
     * para o lado esquerdo.
     *
     * @param aux elemento que auxiliar a contar o lado esquerdo
     */
    private void calcularAlturaLadoEsquerdo(Node aux) {
        if (aux.getEsquerda() == null) {
            aux.sethE(0);
        } else {
            if (aux.getEsquerda().gethD() > aux.getEsquerda().gethE()) {
                aux.sethE(aux.getEsquerda().gethD() + 1);
            } else {
                aux.sethE(aux.getEsquerda().gethE() + 1);
            }
        }
    }

    /**
     * O método calculaAlturaLado Direito, cacula a altura da árvore tendendo
     * para o lado direito.
     *
     * @param aux elemento que auxiliar a contar o lado direito
     */
    private void calculaAlturaLadoDireito(Node aux) {
        if (aux.getDireita() == null) {
            aux.sethD(0);
        } else {
            if (aux.getDireita().gethD() > aux.getDireita().gethE()) {
                aux.sethD(aux.getDireita().gethD() + 1);
            } else {
                aux.sethD(aux.getDireita().gethE() + 1);
            }
        }
    }

    /**
     * O método de balanceamento é responsábel por identificar o fator de
     * balanceamento apartir do cálculo de sua altura, para fazer a rotação
     * simples ou dupla, para isso o método tem dois métodos auxiliares que
     * fazem a rotação tanto simples quanto dupla.
     *
     * @param corrente recursivo
     * @param pai recursivo
     */
    private void balanceamento(Node corrente, Node pai) {
        int fatorBalanceamento, fatorProximo;
        fatorBalanceamento = corrente.gethD() - corrente.gethE();
        if (fatorBalanceamento == 2) {
            fatorProximo = corrente.getDireita().gethD() - corrente.getDireita().gethE();
            if (fatorProximo >= 0) {
                rotacaoEsquerda(corrente, pai);
            } else {
                rotacaoDireita(corrente.getDireita(), corrente);
                rotacaoEsquerda(corrente, pai);
            }
        } else if (fatorBalanceamento == -2) {
            fatorProximo = corrente.getEsquerda().gethD() - corrente.getEsquerda().gethE();
            if (fatorProximo <= 0) {
                rotacaoDireita(corrente, pai);
            } else {
                rotacaoEsquerda(corrente.getEsquerda(), corrente);
                rotacaoDireita(corrente, pai);
            }
        }
    }

    /**
     * O método rotacaoEsquerda, balancea a arvore rotacionando ela para a
     * esquerda, deixando-a balanceada
     *
     * @param corrente recursivo
     * @param father recursivo
     */
    private void rotacaoEsquerda(Node corrente, Node father) {

        Node a1 = corrente.getDireita();
        Node a2 = a1.getEsquerda();
        corrente.setDireita(a2);
        a1.setEsquerda(corrente);
        if (corrente == raiz) {
            raiz = a1;
        } else {
            if (father.getEsquerda() == corrente) {
                father.setEsquerda(a1);
            } else {
                father.setDireita(a1);
            }
        }
        calculaAlturaLadoDireito(corrente);
        calcularAlturaLadoEsquerdo(corrente);
        calculaAlturaLadoDireito(a1);
        calcularAlturaLadoEsquerdo(a1);
    }

    /**
     * O método rotacaoDireita, balancea a arvore rotacionando ela para a
     * direita, deixando-a balanceada
     *
     * @param corrente recursivo
     * @param father recursivo
     */
    private void rotacaoDireita(Node corrente, Node father) {

        Node a1 = corrente.getEsquerda();
        Node a2 = a1.getDireita();
        corrente.setEsquerda(a2);
        a1.setDireita(corrente);
        if (corrente == raiz) {
            raiz = a1;
        } else {
            if (father.getDireita() == corrente) {
                father.setDireita(a1);
            } else {
                father.setEsquerda(a1);
            }
        }
        calculaAlturaLadoDireito(corrente);
        calcularAlturaLadoEsquerdo(corrente);
        calculaAlturaLadoDireito(a1);
        calcularAlturaLadoEsquerdo(a1);
    }

    /**
     * O método removerRecurssao é responsável por auxiliar o metodo de remover
     * a retirar um elemento da lista.
     *
     * @param c novo elemento
     * @param aux raíz
     * @param pai nulo
     */
    private void removerRecurssao(Comparable c, Node aux, Node pai) {

        if (raiz.getElemento().toString().compareTo(c.toString()) == 0) {
            deletarRoot();
        } else if (aux.getElemento().toString().compareTo(c.toString()) == 0 && aux.getDireita() != null && aux.getEsquerda() != null) {
            Node folha = buscarFolha(aux);
            Node paiFolha = buscarPaiFolha(aux);
            if (pai.getDireita() == aux) {
                pai.setDireita(folha);
            } else {
                pai.setEsquerda(folha);
            }
            folha.setEsquerda(aux.getEsquerda());
            if (aux != paiFolha) {
                folha.setDireita(aux.getDireita());
                if (paiFolha.getDireita() == folha) {
                    paiFolha.setDireita(null);
                } else {
                    paiFolha.setEsquerda(null);
                }
            }
        } else if (aux.getElemento().toString().compareTo(c.toString()) == 0 && aux.getDireita() == null && aux.getEsquerda() == null) {
            if (pai.getDireita() == aux) {
                pai.setDireita(null);
            } else {
                pai.setEsquerda(null);
            }
        } else if (aux.getElemento().toString().compareTo(c.toString()) == 0 && (aux.getDireita() != null || aux.getEsquerda() != null)) {
            if (aux.getDireita() != null) {
                if (pai.getDireita() == aux) {
                    pai.setDireita(aux.getDireita());
                } else {
                    pai.setEsquerda(aux.getDireita());
                }
            } else {
                if (pai.getDireita() == aux) {
                    pai.setDireita(aux.getEsquerda());
                } else {
                    pai.setEsquerda(aux.getEsquerda());
                }
            }
        } else if (aux.getElemento().toString().compareTo(c.toString()) < 0) {
            if (aux.getDireita() != null) {
                pai = aux;
                removerRecurssao(c, aux.getDireita(), pai);
            }
        } else {
            if (aux.getEsquerda() != null) {
                pai = aux;
                removerRecurssao(c, aux.getEsquerda(), pai);
            }
        }
    }

    /**
     * O método deletarRoot deleta a raiz da arvore.
     */
    public void deletarRoot() {

        if (raiz.getDireita() == null && raiz.getEsquerda() == null) {
            raiz = null;
        } else if (raiz.getDireita() != null && raiz.getEsquerda() != null) {
            Node folha = buscarFolha(raiz);
            folha.setEsquerda(raiz.getEsquerda());
            raiz = raiz.getDireita();
        } else if (raiz.getDireita() != null) {
            raiz = raiz.getDireita();
        } else {
            raiz = raiz.getEsquerda();
        }
    }

    /**
     * O método buscarFolha, auxilia o deletarRoot, buscando o final da árvore
     * para deletar a árvore completamente.
     *
     * @param atual elemento inicial para buscar a sua folha
     * @return a folha do atual.
     */
    private Node buscarFolha(Node atual) {

        Node folha;
        Node corrente = atual;
        corrente = corrente.getDireita();
        folha = corrente;
        while (corrente != null) {
            folha = corrente;
            corrente = corrente.getEsquerda();
        }
        return folha;
    }

    /**
     * O método buscarPaiFolha, é responsável por buscar o pai do último
     * elemento da árvore e retorná-lo.
     *
     * @param atual elemento inicial para buscar o pai da folha
     * @return o pai da folha.
     */
    private Node buscarPaiFolha(Node atual) {

        Node corrente = atual;
        Node pai = corrente;
        corrente = corrente.getDireita();
        while (corrente != null) {
            if (corrente.getEsquerda() != null) {
                pai = corrente;
            }
            corrente = corrente.getEsquerda();
        }
        return pai;
    }

    /**
     * Calcula a altura referenciando cada pai, para lado esquerdo ou direito
     * para verificar que tipo de rotação será feita, para isso tem a ajuda dos
     * métodos que calcula altura esquerda e direita, exemplo como a
     * balanleamento é feito de baixo para cima, ele acha os ultimos elementos
     * de cada lado, calculando sua altura se der 2 ou -2 o método de
     * balanceamento irá balanceá-lo.
     *
     * @param aux recursivo
     * @param pai recursivo
     */
    private void calcularAltura(Node aux, Node pai) {

        if (aux != null) {
            calcularAltura(aux.getEsquerda(), aux);
            calcularAltura(aux.getDireita(), aux);
            calculaAlturaLadoDireito(aux);
            calcularAlturaLadoEsquerdo(aux);
            balanceamento(aux, pai);
        }
    }

    /**
     * O método getRoot retorna o início da árvore.
     *
     * @return a raíz
     */
    public Node getRoot() {
        return raiz;
    }

    /**
     * O método get altura retorna a altura da árvore.
     *
     * @return altura
     */
    public int getAltura() {
        return auxAltura(raiz);
    }

    /**
     * O método auxilia a encontrar a altura até as folhas maiores esquerdas e
     * direitas.
     *
     * @param aux recursivo
     * @return a altura
     */
    private int auxAltura(Node aux) {

        if (aux == null) {
            return -1;
        } else {
            int e = auxAltura(aux.getEsquerda());
            int d = auxAltura(aux.getDireita());
            if (d > e) {
                return d + 1;
            } else {
                return e + 1;
            }
        }
    }

    /**
     * O método precura auxProcuraErro é bem peculiar, ele varre a arvore
     * olhando se tem algum fator desbalanceado.
     */
    public void ProcurarErro() {
        auxProcuraErro(raiz);
    }

    private void auxProcuraErro(Node aux) {

        if (aux != null) {
            auxProcuraErro(aux.getEsquerda());
            if (aux.gethD() - aux.gethE() >= 2) {
                System.out.println("Esse ponto não ta balanceado");
            }
            auxProcuraErro(aux.getEsquerda());
        }
    }

    /**
     * O método imprimirOrdemRSV, impreme os dados em ordem crescente.
     */
    public void imprimirOrdem() {
        auxImprimirOrdem(raiz);
    }

    /**
     * auxiliar do método imprimirOrdem.
     *
     * @param aux recursivo
     */
    private void auxImprimirOrdem(Node aux) {
        if (aux != null) {
            auxImprimirOrdem(aux.getEsquerda());
            System.out.println(aux.getElemento());
            auxImprimirOrdem(aux.getDireita());
        }
    }

    /**
     * O método imprimirPreOrdemRSV, impreme os dados tendendo para a esquerda
     * começando pela sua raiz.
     */
    public void imprimirPreOrdem() {
        auxImprimirPreOrdem(raiz);
    }

    /**
     * auxiliar do método imprimirPreOrdem.
     *
     * @param aux recursivo
     */
    private void auxImprimirPreOrdem(Node aux) {

        if (aux != null) {

            System.out.println(aux.getElemento());
            auxImprimirPreOrdem(aux.getEsquerda());
            auxImprimirPreOrdem(aux.getDireita());
        }
    }

    /**
     * O método imprimirPosOrdem, impreme os dados de forma que vai para
     * esquerda e printa da folha crescentemente, depois vai para esquerda acha
     * a folha e retorna printando o último é o Raiz.
     */
    public void imprimirPosOrdem() {
        auxImprimirPosOrdem(raiz);
    }

    /**
     * auxiliar do método imprimirPosOrdem.
     *
     * @param aux recursivo
     */
    private void auxImprimirPosOrdem(Node aux) {

        if (aux != null) {

            auxImprimirPosOrdem(aux.getEsquerda());
            auxImprimirPosOrdem(aux.getDireita());
            System.out.println(aux.getElemento());

        }

    }

    @Override
    public Iterador iterador() {
        Iterador it = new IteradorArvore(raiz);
        return it;
    }
}
