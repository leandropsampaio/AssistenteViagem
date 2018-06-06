package br.uefs.ecomp.av.view;

import br.uefs.ecomp.av.controller.ControllerOperador;
import br.uefs.ecomp.av.controller.ControllerUsuario;
import br.uefs.ecomp.av.exception.EspacoEmBrancoException;
import br.uefs.ecomp.av.model.Local;
import br.uefs.ecomp.av.util.Coordenadas;
import br.uefs.ecomp.av.util.DadoNaoEncontradoException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Esta classe tem como função: conter, criar, organizar as informações de
 * interface gráfica das buscas de locais.
 *
 */
public class BuscarLocal extends JFrame {

    private ControllerUsuario controllerUsuario;
    private ControllerOperador controllerOperador;
    private JTextField nomeField;
    private JPanel painelCentro;
    private JTextArea informacaoArea;

    /**
     * Construtor do tipo BucarLocal, onde são inicializadas os controllers e é
     * inicializado um formulário.
     *
     * @param controllerUsuario - Controller de Usuário.
     * @param controllerOperador - Controller do Operador.
     */
    public BuscarLocal(ControllerUsuario controllerUsuario, ControllerOperador controllerOperador) {
        super("Assistente De Viagem");
        this.controllerUsuario = controllerUsuario;
        this.controllerOperador = controllerOperador;
        criarFormulario();
    }

    /**
     * Método responsável por criar um formulário de modo a ser utilizado na
     * interface grafica.
     */
    private void criarFormulario() {

        BuscarAction buscarAction = new BuscarAction();
        VoltarAction voltarAction = new VoltarAction();

        setLayout(new BorderLayout());

        JPanel painelTitulo = new JPanel();
        painelTitulo.setLayout(new FlowLayout());

        JLabel titulo = new JLabel("Buscar Local");
        //titulo.setForeground(Color.BLACK);
        titulo.setFont(new Font("Verdana", Font.BOLD, 16));

        painelTitulo.add(titulo);

        painelCentro = new JPanel();
        painelCentro.setLayout(new FlowLayout());

        JLabel nomeLabel = new JLabel("Nome do Local");
        nomeField = new JTextField(40); // Mudar parâmetro.

        informacaoArea = new JTextArea("");
        informacaoArea.setEditable(false);

        painelCentro.add(nomeLabel);
        painelCentro.add(nomeField);
        painelCentro.add(informacaoArea);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout());

        Icon icone = new ImageIcon(getClass().getResource("/voltar.png"));
        JButton butaoVoltar = new JButton("Voltar", icone);
        butaoVoltar.addActionListener(voltarAction);

        icone = new ImageIcon(getClass().getResource("/buscar.png"));
        JButton butaoBuscar = new JButton("Buscar", icone);
        butaoBuscar.addActionListener(buscarAction);

        painelBotoes.add(butaoVoltar);
        painelBotoes.add(butaoBuscar);

        add(painelTitulo, BorderLayout.NORTH);
        add(painelCentro, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    /**
     * Método responsável por ações e exibições de informações em tela onde são
     * apresentadas informações de busca das ações informadas anteriormente pelo
     * usuário.
     */
    private class BuscarAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Icon icone = new ImageIcon(getClass().getResource("/perigo.png"));
            try {
                Local local = controllerUsuario.buscarLocal(nomeField.getText().toLowerCase());
                Coordenadas coordenadas = local.getLocalizacao();
                informacaoArea.setText("Nome: " + local.getNome() + "\nPais: " + local.getPais()
                        + "\nEstado: " + local.getEstado() + "\nLatitude: " + coordenadas.getLatitude()
                        + "\nLongitude: "
                        + coordenadas.getLongitude());
                informacaoArea.setForeground(Color.red);
                informacaoArea.setFont(new Font("Verdana", Font.PLAIN, 20));
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
