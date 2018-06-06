package br.uefs.ecomp.av.view;

import br.uefs.ecomp.av.controller.ControllerOperador;
import br.uefs.ecomp.av.controller.ControllerUsuario;
import br.uefs.ecomp.av.exception.EspacoEmBrancoException;
import br.uefs.ecomp.av.model.Atracao;
import br.uefs.ecomp.av.model.Local;
import br.uefs.ecomp.av.util.DadoNaoEncontradoException;
import br.uefs.ecomp.av.util.Iterador;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Esta classe tem como função conter as ações e opções para as janelas da
 * interface gráfica já desenvolvidas para a listagem de informações das
 * atrações.
 */
public class ListarAtracoes extends JFrame {

    private ControllerUsuario controllerUsuario;
    private ControllerOperador controllerOperador;
    private JTextField nomeField;
    private JPanel painelCentro;
    private DefaultListModel listaModel;
    private final JList lista;

    /**
     * Construtor do tipo ListarAtracoes, onde são inicializadas os controllers
     * de operações e é inicializado uma janela/formulario.
     *
     * @param controllerUsuario - Controller de Usuário.
     * @param controllerOperador - Controller do Operador.
     */
    public ListarAtracoes(ControllerUsuario controllerUsuario, ControllerOperador controllerOperador) {
        super("Assistente De Viagem");
        this.controllerUsuario = controllerUsuario;
        this.controllerOperador = controllerOperador;
        lista = new JList();
        criarFormulario();
    }

    /**
     * Método responsável por criar um formulário de modo a ser utilizado na
     * tela da interface gráfica.
     */
    private void criarFormulario() {
        CadastrarAction cadastrarAction = new CadastrarAction();
        VoltarAction voltarAction = new VoltarAction();

        setLayout(new BorderLayout());

        JPanel painelTitulo = new JPanel();
        painelTitulo.setLayout(new FlowLayout());

        JLabel titulo = new JLabel("Listar Atrações");
        //titulo.setForeground(Color.BLACK);
        titulo.setFont(new Font("Verdana", Font.BOLD, 16));

        painelTitulo.add(titulo);

        painelCentro = new JPanel();
        painelCentro.setLayout(new FlowLayout());

        JLabel nomeLabel = new JLabel("Nome do Local");
        nomeField = new JTextField(40); // Mudar parâmetro.

        listaModel = new DefaultListModel();

        painelCentro.add(nomeLabel);
        painelCentro.add(nomeField);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout());

        Icon icone = new ImageIcon(getClass().getResource("/voltar.png"));
        JButton butaoVoltar = new JButton("Voltar", icone);
        butaoVoltar.addActionListener(voltarAction);

        icone = new ImageIcon(getClass().getResource("/buscar.png"));
        JButton butaoCadastrar = new JButton("Buscar", icone);
        butaoCadastrar.addActionListener(cadastrarAction);

        painelBotoes.add(butaoVoltar);
        painelBotoes.add(butaoCadastrar);

        add(painelTitulo, BorderLayout.NORTH);
        add(painelCentro, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    /**
     * Método responsável por criar uma tela de ação para o cadastro das
     * informações.
     */
    private class CadastrarAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Icon icone = new ImageIcon(getClass().getResource("/perigo.png"));
            try {
                Local local = controllerUsuario.buscarLocal(nomeField.getText().toLowerCase());
                Iterador iterador = local.listarAtracoesOrdemAlfabetica();
                Atracao atracao;
                listaModel.clear();
                while (iterador.temProximo()) {
                    atracao = (Atracao) iterador.obterProximo();
                    listaModel.addElement("Local: " + local.getNome() + " | Nome: " + atracao.getNome() + " | Bairro: " + atracao.getBairro()
                            + " | Categoria: " + atracao.getCategoria());
                }
                lista.setModel(listaModel);
                painelCentro.add(lista);
                add(painelCentro, BorderLayout.CENTER);
                setVisible(rootPaneCheckingEnabled);
            } catch (EspacoEmBrancoException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Assistente De Viagem", JOptionPane.PLAIN_MESSAGE, icone);
            } catch (DadoNaoEncontradoException ex) {
                JOptionPane.showMessageDialog(null, "Local não Encontrado, Tente Novamente...", "Assistente De Viagem", JOptionPane.PLAIN_MESSAGE, icone);
            }
        }
    }

    /**
     * Método responsável por ações de retorno/volta em interface gráfica.
     */
    private class VoltarAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            FrameEscolhaUsuario frameEscolha = new FrameEscolhaUsuario(controllerUsuario, controllerOperador);
            CriarJFrame jFrame = new CriarJFrame(frameEscolha, controllerOperador, 530, 260);
            setVisible(false);
            jFrame.iniciar();
        }
    }
}
