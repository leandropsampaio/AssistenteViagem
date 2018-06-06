package br.uefs.ecomp.av.view;

import br.uefs.ecomp.av.controller.ControllerOperador;
import br.uefs.ecomp.av.controller.ControllerUsuario;
import br.uefs.ecomp.av.exception.EspacoEmBrancoException;
import br.uefs.ecomp.av.util.Coordenadas;
import br.uefs.ecomp.av.util.DadoDuplicadoException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Esta classe tem como função: conter, criar, organizar as informações de
 * interface gráfica da tela de cadastro dos locais.
 */
public class CadastrarLocal extends JFrame {

    private ControllerUsuario controllerUsuario;
    private ControllerOperador controllerOperador;
    private JTextField nomeField;
    private JTextField paisField;
    private JTextField estadoField;
    private JTextField longitudeField;
    private JTextField latitudeField;

    /**
     * Construtor do tipo CadastrarLocal, onde são inicializadas os controllers
     * e é inicializado um formulário.
     *
     * @param controllerUsuario - Controller de Usuário.
     * @param controllerOperador - Controller do Operador.
     */
    public CadastrarLocal(ControllerUsuario controllerUsuario, ControllerOperador controllerOperador) {
        super("Assistente De Viagem");
        this.controllerUsuario = controllerUsuario;
        this.controllerOperador = controllerOperador;
        criarFormulario();
    }

    /**
     * Método responsável por criar um formulario de modo a ser utilizado na
     * tela da interface gráfica.
     */
    private void criarFormulario() {

        CadastrarAction cadastrarAction = new CadastrarAction();
        VoltarAction voltarAction = new VoltarAction();

        setLayout(new BorderLayout());

        JPanel painelTitulo = new JPanel();
        painelTitulo.setLayout(new FlowLayout());

        JLabel titulo = new JLabel("Cadastro do Local");
        //titulo.setForeground(Color.red);
        titulo.setFont(new Font("Verdana", Font.BOLD, 16));

        painelTitulo.add(titulo);

        JPanel painelCentro = new JPanel();
        painelCentro.setLayout(new FlowLayout());

        JLabel nomeLabel = new JLabel("Nome ");
        nomeField = new JTextField(40); // Mudar parâmetro.

        JLabel paisLabel = new JLabel("Pais  ");
        paisField = new JTextField(40);

        JLabel estadoLabel = new JLabel("Estado");
        estadoField = new JTextField(40);

        JLabel longitudeLabel = new JLabel("Longitude");
        longitudeField = new JTextField(10);

        JLabel latitudeLabel = new JLabel("Latitude");
        latitudeField = new JTextField(10);

        painelCentro.add(nomeLabel);
        painelCentro.add(nomeField);
        painelCentro.add(paisLabel);
        painelCentro.add(paisField);
        painelCentro.add(estadoLabel);
        painelCentro.add(estadoField);
        painelCentro.add(longitudeLabel);
        painelCentro.add(longitudeField);
        painelCentro.add(latitudeLabel);
        painelCentro.add(latitudeField);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout());

        Icon icone = new ImageIcon(getClass().getResource("/voltar.png"));
        JButton butaoVoltar = new JButton("Voltar", icone);
        butaoVoltar.addActionListener(voltarAction);

        icone = new ImageIcon(getClass().getResource("/livro.png"));
        JButton butaoCadastrar = new JButton("Cadastrar", icone);
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
                double latitude = Double.parseDouble(latitudeField.getText());
                double longitude = Double.parseDouble(longitudeField.getText());
                //nome - localização - estado - pais.
                controllerOperador.cadastrarLocal(nomeField.getText().toLowerCase(),
                        new Coordenadas(latitude, longitude),
                        estadoField.getText(), paisField.getText());
                JOptionPane.showMessageDialog(null, "Local Cadastrado Com Sucesso!", "Assistente De Viagem", JOptionPane.PLAIN_MESSAGE);
                VoltarAction voltar = new VoltarAction();
                voltar.actionPerformed(e);
            } catch (EspacoEmBrancoException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Assistente De Viagem", JOptionPane.PLAIN_MESSAGE, icone);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Digite um Número Correto em Latitude e Longitude...", "Assistente De Viagem", JOptionPane.PLAIN_MESSAGE, icone);
            } catch (DadoDuplicadoException ex) {
                JOptionPane.showMessageDialog(null, "Local já está Cadastrado! Tente novamente...", "Assistente De Viagem", JOptionPane.PLAIN_MESSAGE, icone);
            }
        }
    }

    /**
     * Método responsável por ações de retorno/volta em interface gráfica.
     */
    private class VoltarAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            FrameEscolhaOperador frameEscolha = new FrameEscolhaOperador(controllerUsuario, controllerOperador);
            CriarJFrame jFrame = new CriarJFrame(frameEscolha, controllerOperador, 530, 260);
            setVisible(false);
            jFrame.iniciar();
        }
    }
}
