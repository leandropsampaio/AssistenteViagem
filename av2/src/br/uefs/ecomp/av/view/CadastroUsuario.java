package br.uefs.ecomp.av.view;

import br.uefs.ecomp.av.controller.ControllerOperador;
import br.uefs.ecomp.av.controller.ControllerUsuario;
import br.uefs.ecomp.av.exception.EspacoEmBrancoException;
import br.uefs.ecomp.av.exception.UsuarioCadastradoException;
import br.uefs.ecomp.av.model.enums.Sexo;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Esta classe tem como função: conter, criar, organizar as informações de
 * interface gráfica da tela de cadastro dos usuarios.
 */
public class CadastroUsuario extends JFrame {

    private ControllerUsuario controllerUsuario;
    private ControllerOperador controllerOperador;
    private JTextField emailField;
    private JPasswordField senhaField;
    private JTextField nomeField;
    private ButtonGroup grupo;
    private JRadioButton masculinoButao;
    private JRadioButton femininoButao;

    /**
     * Construtor do tipo CadastroUsuario, onde são inicializadas os controllers
     * e é inicializado um formulario.
     *
     * @param controllerUsuario - Controller de Usuário.
     * @param controllerOperador - Controller do Operador.
     */
    public CadastroUsuario(ControllerUsuario controllerUsuario, ControllerOperador controllerOperador) {
        super("Cadastrar Usuário");
        this.controllerUsuario = controllerUsuario;
        this.controllerOperador = controllerOperador;
        criarFormulario();
    }

    /**
     * Construtor do tipo CadastrarRestaurante, onde são inicializadas os
     * controllers e é inicializado um formulario.
     */
    private void criarFormulario() {

        VoltarAction voltarAction = new VoltarAction();
        EntrarAction entrarAction = new EntrarAction();

        setLayout(new BorderLayout());

        JPanel painelTitulo = new JPanel();
        painelTitulo.setLayout(new FlowLayout());

        JLabel titulo = new JLabel("Cadastro do Usuário");
        //titulo.setForeground(Color.BLACK);
        titulo.setFont(new Font("Verdana", Font.BOLD, 16));

        painelTitulo.add(titulo);

        JPanel painelCentro = new JPanel();
        painelCentro.setLayout(new FlowLayout());

        JLabel nomeLabel = new JLabel("Nome ");
        nomeField = new JTextField(40);

        JLabel emailLabel = new JLabel(" Email");
        emailField = new JTextField(40); // Mudar parâmetro.

        JLabel senhaLabel = new JLabel("Senha");
        senhaField = new JPasswordField(40);

        JLabel sexoLabel = new JLabel("  Sexo");
        grupo = new ButtonGroup();
        masculinoButao = new JRadioButton("Masculino");
        femininoButao = new JRadioButton("Feminino");

        grupo.add(masculinoButao);
        grupo.add(femininoButao);

        painelCentro.add(nomeLabel);
        painelCentro.add(nomeField);
        painelCentro.add(emailLabel);
        painelCentro.add(emailField);
        painelCentro.add(senhaLabel);
        painelCentro.add(senhaField);
        painelCentro.add(sexoLabel);
        painelCentro.add(masculinoButao);
        painelCentro.add(femininoButao);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout());

        Icon icone = new ImageIcon(getClass().getResource("/voltar.png"));
        JButton butaoVoltar = new JButton("Voltar", icone);
        butaoVoltar.addActionListener(voltarAction);

        icone = new ImageIcon(getClass().getResource("/ok.png"));
        JButton butaoEntrar = new JButton("Cadastrar", icone);
        butaoEntrar.addActionListener(entrarAction);

        painelBotoes.add(butaoVoltar);
        painelBotoes.add(butaoEntrar);

        add(painelTitulo, BorderLayout.NORTH);
        add(painelCentro, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    /**
     * Método responsável por ações de retorno/volta em interface grafica.
     */
    private class VoltarAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Login login = new Login(controllerUsuario, controllerOperador);
            CriarJFrame jFrame = new CriarJFrame(login, controllerOperador, 530, 260);
            dispose();
            jFrame.iniciar();
        }
    }

    /**
     * Método responsável por ações de avanço em interface gráfica, dando origem
     * a uma nova tela e realizando operanções de verificação e cadastro.
     */
    private class EntrarAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Icon icone = new ImageIcon(getClass().getResource("/perigo.png"));
            try {
                Sexo sexo = null;
                if (masculinoButao.isSelected()) {
                    sexo = Sexo.MASCULINO;
                } else if (femininoButao.isSelected()) {
                    sexo = Sexo.FEMININO;
                }
                controllerUsuario.cadastrarUsuario(emailField.getText(), senhaField.getText(), sexo, nomeField.getText());
                JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso...", "Assistente De Viagem", JOptionPane.PLAIN_MESSAGE);
                Login login = new Login(controllerUsuario, controllerOperador);
                CriarJFrame jFrame = new CriarJFrame(login, controllerOperador, 530, 260);
                dispose();
                jFrame.iniciar();
            } catch (EspacoEmBrancoException | UsuarioCadastradoException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Assistente De Viagem", JOptionPane.PLAIN_MESSAGE, icone);
            }
        }
    }
}
