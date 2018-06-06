package br.uefs.ecomp.av.view;

import br.uefs.ecomp.av.controller.ControllerOperador;
import br.uefs.ecomp.av.controller.ControllerUsuario;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Esta classe tem como função conter as ações e opções para as janelas de
 * interface graficas já desenvolvidas para o operador.
 */
public class FrameEscolhaOperador extends JFrame {

    private ControllerUsuario controllerUsuario;
    private ControllerOperador controllerOperador;

    /**
     * Construtor do tipo FrameEscolhaOperador, onde são inicializadas os
     * controllers de operações e é inicializado uma janela/formulario.
     *
     * @param controllerUsuario - Controller de Usuário.
     * @param controllerOperador - Controller do Operador.
     */
    public FrameEscolhaOperador(ControllerUsuario controllerUsuario, ControllerOperador controllerOperador) {
        super("Assistente De Viagem");
        this.controllerUsuario = controllerUsuario;
        this.controllerOperador = controllerOperador;
        criarFormulario();
    }

    /**
     * Método responsável por criar um formulário de modo a ser utilizado na
     * tela da interface gráfica.
     */
    private void criarFormulario() {

        VoltarAction voltarAction = new VoltarAction();
        SairAction sairAction = new SairAction();
        CadastrarLocalAction cadastrarLocalAction = new CadastrarLocalAction();

        setLayout(new BorderLayout());

        JPanel painelTitulo = new JPanel();
        painelTitulo.setLayout(new FlowLayout());

        JLabel titulo = new JLabel("Escolha dentre as opções listadas");
        //titulo.setForeground(Color.BLACK);
        titulo.setFont(new Font("Verdana", Font.BOLD, 16));

        painelTitulo.add(titulo);

        JPanel painelCentro = new JPanel();
        painelCentro.setLayout(new FlowLayout());

        Icon icone = new ImageIcon(getClass().getResource("/livro.png"));
        JButton cadastrarLocal = new JButton("Cadastrar Local", icone);
        cadastrarLocal.addActionListener(cadastrarLocalAction);

        painelCentro.add(cadastrarLocal);

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
            FrameEscolha frame = new FrameEscolha(controllerUsuario, controllerOperador);
            CriarJFrame jFrame = new CriarJFrame(frame, controllerOperador, 530, 260);
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
    private class CadastrarLocalAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            CadastrarLocal cadastrarLocal = new CadastrarLocal(controllerUsuario, controllerOperador);
            CriarJFrame jFrame = new CriarJFrame(cadastrarLocal, controllerOperador, 510, 350);
            setVisible(false);
            jFrame.iniciar();
        }
    }
}
