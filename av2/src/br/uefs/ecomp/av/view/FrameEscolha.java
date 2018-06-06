package br.uefs.ecomp.av.view;

import br.uefs.ecomp.av.controller.ControllerOperador;
import br.uefs.ecomp.av.controller.ControllerUsuario;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Esta classe tem como função conter as ações e opções para as janelas de
 * interfaces gráficas já desenvolvidas.
 */
public class FrameEscolha extends JFrame {

    private ControllerUsuario controllerUsuario;
    private ControllerOperador controllerOperador;

    /**
     * Construtor do tipo FrameEscolha, onde são inicializadas os controllers de
     * operações e é inicializado uma janela/formulario.
     *
     * @param controllerUsuario - Controller de Usuário.
     * @param controllerOperador - Controller do Operador.
     */
    public FrameEscolha(ControllerUsuario controllerUsuario, ControllerOperador controllerOperador) {
        super("Assistente De Viagem");
        this.controllerUsuario = controllerUsuario;
        this.controllerOperador = controllerOperador;
        criarFormulario();
    }

    /**
     * Método responsável por criar um formulário na janela da interface grafica
     * contendo opções para o usuário.
     */
    private void criarFormulario() {

        VoltarAction voltarAction = new VoltarAction();
        SairAction sairAction = new SairAction();
        UsuarioAction cadastrarLocalAction = new UsuarioAction();
        OperadorAction cadastrarRestauranteAction = new OperadorAction();

        setLayout(new BorderLayout());

        JPanel painelTitulo = new JPanel();
        painelTitulo.setLayout(new FlowLayout());

        JLabel titulo = new JLabel("Escolha dentre as opções listadas");
        //titulo.setForeground(Color.BLACK);
        titulo.setFont(new Font("Verdana", Font.BOLD, 16));

        painelTitulo.add(titulo);

        JPanel painelCentro = new JPanel();
        painelCentro.setLayout(new FlowLayout());

        Icon icone = new ImageIcon(getClass().getResource("/usuario.png"));
        JButton cadastrarLocal = new JButton("Usuário", icone);
        cadastrarLocal.addActionListener(cadastrarLocalAction);

        icone = new ImageIcon(getClass().getResource("/usuarioRestrito.png"));
        JButton cadastrarRestaurante = new JButton("Operador", icone);
        cadastrarRestaurante.addActionListener(cadastrarRestauranteAction);

        painelCentro.add(cadastrarLocal);
        painelCentro.add(cadastrarRestaurante);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout());

        icone = new ImageIcon(getClass().getResource("/fechar.png"));
        JButton butaoSair = new JButton("Sair", icone);
        butaoSair.addActionListener(sairAction);

        icone = new ImageIcon(getClass().getResource("/voltar.png"));
        JButton butaoVoltar = new JButton("Voltar", icone);
        butaoVoltar.addActionListener(voltarAction);

        painelBotoes.add(butaoSair);
        painelBotoes.add(butaoVoltar);

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
            setVisible(false);
            jFrame.iniciar();
        }
    }

    /**
     * Método responsável por encerrar o programa.
     */
    private class SairAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                controllerOperador.salvarEstadoSistema();
            } catch (Exception ex) {
            }
            System.exit(0);
        }
    }

    /**
     * Método responsável por criar uma tela de ação para o cadastro das
     * informações.
     */
    private class UsuarioAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            FrameEscolhaUsuario usuario = new FrameEscolhaUsuario(controllerUsuario, controllerOperador);
            CriarJFrame jFrame = new CriarJFrame(usuario, controllerOperador, 530, 260);
            setVisible(false);
            jFrame.iniciar();
        }
    }

    /**
     * Método responsável por criar uma tela de ação para o cadastro das
     * informações.
     */
    private class OperadorAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Operador operador = new Operador(controllerUsuario, controllerOperador);
            CriarJFrame jFrame = new CriarJFrame(operador, controllerOperador, 530, 300);
            setVisible(false);
            jFrame.iniciar();
        }
    }
}
