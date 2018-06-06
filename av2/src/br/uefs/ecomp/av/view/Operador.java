package br.uefs.ecomp.av.view;

import br.uefs.ecomp.av.controller.ControllerOperador;
import br.uefs.ecomp.av.controller.ControllerUsuario;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Esta classe tem como função verificar se o usuário pode acessar as
 * informações do operador.
 */
public class Operador extends JFrame {

    private ControllerUsuario controllerUsuario;
    private ControllerOperador controllerOperador;
    private JPasswordField senha;

    /**
     * Construtor do tipo Operador, onde são inicializadas os controllers de
     * operações e é inicializado um frame do operador.
     *
     * @param controllerUsuario - Controller de Usuário.
     * @param controllerOperador - Controller do Operador.
     */
    public Operador(ControllerUsuario controllerUsuario, ControllerOperador controllerOperador) {
        super("Assistente De Viagem");
        this.controllerUsuario = controllerUsuario;
        this.controllerOperador = controllerOperador;
        senhaOperador();
    }

    /**
     * Método responsável por criar uma tela gráfica do frame, para digitar a
     * senha no operador.
     */
    private void senhaOperador() {
        EntrarAction entrarAction = new EntrarAction();
        VoltarAction voltarAction = new VoltarAction();

        setLayout(new BorderLayout());

        JPanel painelTitulo = new JPanel();
        painelTitulo.setLayout(new FlowLayout());

        JLabel titulo = new JLabel("Acesso Restrito");
        titulo.setForeground(Color.red);
        titulo.setFont(new Font("Verdana", Font.BOLD, 16));

        painelTitulo.add(titulo);

        JPanel painelCentro = new JPanel();
        painelCentro.setLayout(new FlowLayout());

        JLabel nome = new JLabel("Senha:");
        senha = new JPasswordField(40);

        painelCentro.add(nome);
        painelCentro.add(senha);

        JPanel painelBotoes = new JPanel();
        painelCentro.setLayout(new FlowLayout());

        Icon icone = new ImageIcon(getClass().getResource("/ok.png"));
        JButton entrar = new JButton("Entrar", icone);
        entrar.addActionListener(entrarAction);

        icone = new ImageIcon(getClass().getResource("/voltar.png"));
        JButton voltar = new JButton("Voltar", icone);
        voltar.addActionListener(voltarAction);

        painelBotoes.add(voltar);
        painelBotoes.add(entrar);

        add(painelTitulo, BorderLayout.NORTH);
        add(painelCentro, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    /**
     * Método responsável por ações de avanço da interface gráfica, verificando
     * se o usuário tem acesso a área do operador, de acordo com a senha
     * digitada.
     */
    private class EntrarAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (senha.getText().equals("admin")) {
                FrameEscolhaOperador frameEscolha = new FrameEscolhaOperador(controllerUsuario, controllerOperador);
                CriarJFrame jFrame = new CriarJFrame(frameEscolha, controllerOperador, 530, 260);
                setVisible(false);
                jFrame.iniciar();
            } else {
                Icon icone = new ImageIcon(getClass().getResource("/perigo.png"));
                JOptionPane.showMessageDialog(null, "Senha incorreta, tente novamente...", "Assistente De Viagem", JOptionPane.PLAIN_MESSAGE, icone);
            }
        }
    }

    /**
     * Método responsável por ações de retorno/volta em interface gráfica.
     */
    private class VoltarAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            FrameEscolha frame = new FrameEscolha(controllerUsuario, controllerOperador);
            CriarJFrame jFrame = new CriarJFrame(frame, controllerOperador, 530, 260);
            setVisible(false);
            jFrame.iniciar();
        }
    }
}
