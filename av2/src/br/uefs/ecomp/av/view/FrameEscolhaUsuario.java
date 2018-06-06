package br.uefs.ecomp.av.view;

import br.uefs.ecomp.av.controller.ControllerOperador;
import br.uefs.ecomp.av.controller.ControllerUsuario;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Esta classe tem como função conter as ações e opções para as janelas de
 * interface gráficas já desenvolvidas para o usuário.
 */
public class FrameEscolhaUsuario extends JFrame {

    private ControllerUsuario controllerUsuario;
    private ControllerOperador controllerOperador;

    /**
     * Construtor do tipo FrameEscolhaOperador, onde são inicializadas os
     * controllers de operações e é inicializado uma janela/formulario.
     *
     * @param controllerUsuario - Controller de Usuário.
     * @param controllerOperador - Controller do Operador.
     */
    public FrameEscolhaUsuario(ControllerUsuario controllerUsuario, ControllerOperador controllerOperador) {
        super("Assistente De Viagem");
        this.controllerUsuario = controllerUsuario;
        this.controllerOperador = controllerOperador;
        criarFormulario();
    }

    /**
     * Método responsável por criar um formulário de modo a ser utilizado na
     * tela de Formulario no modo de interface gráfica.
     */
    private void criarFormulario() {

        VoltarAction voltarAction = new VoltarAction();
        SairAction sairAction = new SairAction();
        CadastrarRestauranteAction cadastrarRestauranteAction = new CadastrarRestauranteAction();
        CadastrarHotelAction cadastrarHotelAction = new CadastrarHotelAction();
        BuscarLocalAction buscarLocalAction = new BuscarLocalAction();
        BuscarAtracoesAction buscarAtracoesAction = new BuscarAtracoesAction();
        ListarAtracoesAction listarAtracoesAction = new ListarAtracoesAction();

        setLayout(new BorderLayout());

        JPanel painelTitulo = new JPanel();
        painelTitulo.setLayout(new FlowLayout());

        JLabel titulo = new JLabel("Escolha dentre as opções listadas");
        //titulo.setForeground(Color.BLACK);
        titulo.setFont(new Font("Verdana", Font.BOLD, 16));

        painelTitulo.add(titulo);

        JPanel painelCentro = new JPanel();
        painelCentro.setLayout(new FlowLayout());

        Icon icone = new ImageIcon(getClass().getResource("/restaurante.png"));
        JButton cadastrarRestaurante = new JButton("Cadastrar Restaurante", icone);
        cadastrarRestaurante.addActionListener(cadastrarRestauranteAction);

        icone = new ImageIcon(getClass().getResource("/hotel.png"));
        JButton cadastrarHotel = new JButton("Cadastrar Hotel", icone);
        cadastrarHotel.addActionListener(cadastrarHotelAction);

        icone = new ImageIcon(getClass().getResource("/buscar.png"));
        JButton buscarLocal = new JButton("Buscar Local", icone);
        buscarLocal.addActionListener(buscarLocalAction);

        icone = new ImageIcon(getClass().getResource("/buscar.png"));
        JButton buscarAtracoes = new JButton("Buscar Atrações", icone);
        buscarAtracoes.addActionListener(buscarAtracoesAction);

        icone = new ImageIcon(getClass().getResource("/livro.png"));
        JButton listarAtracoes = new JButton("Listar Atrações", icone);
        listarAtracoes.addActionListener(listarAtracoesAction);

        painelCentro.add(cadastrarRestaurante);
        painelCentro.add(cadastrarHotel);
        painelCentro.add(listarAtracoes);
        painelCentro.add(buscarAtracoes);
        painelCentro.add(buscarLocal);

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
     * Método responsável por criar uma tela de ação para o cadastro das
     * informações.
     */
    private class CadastrarHotelAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            CadastrarHotel cadastrarHotel = new CadastrarHotel(controllerUsuario, controllerOperador);
            CriarJFrame jFrame = new CriarJFrame(cadastrarHotel, controllerOperador, 530, 350);
            setVisible(false);
            jFrame.iniciar();
        }
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
     * Método responsável por criar e cadastrar uma tela de ação para o cadastro
     * das informações do restaurante.
     */
    private class CadastrarRestauranteAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            CadastrarRestaurante cadastrarRestaurante = new CadastrarRestaurante(controllerUsuario, controllerOperador);
            CriarJFrame jFrame = new CriarJFrame(cadastrarRestaurante, controllerOperador, 530, 350);
            setVisible(false);
            jFrame.iniciar();
        }
    }

    /**
     * Método responsável por criar um frame para a busca de locais.
     */
    private class BuscarLocalAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            BuscarLocal buscarLocal = new BuscarLocal(controllerUsuario, controllerOperador);
            CriarJFrame jFrame = new CriarJFrame(buscarLocal, controllerOperador, 530, 350);
            setVisible(false);
            jFrame.iniciar();
        }
    }

    /**
     * Método responsável por criar um frame para a busca de atrações.
     */
    private class BuscarAtracoesAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            BuscarAtracao buscarAtracao = new BuscarAtracao(controllerUsuario, controllerOperador);
            CriarJFrame jFrame = new CriarJFrame(buscarAtracao, controllerOperador, 530, 430);
            setVisible(false);
            jFrame.iniciar();
        }
    }

    /**
     * Método responsável por criar um frame para a listagem de atrações.
     */
    private class ListarAtracoesAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ListarAtracoes listarAtracoes = new ListarAtracoes(controllerUsuario, controllerOperador);
            CriarJFrame jFrame = new CriarJFrame(listarAtracoes, controllerOperador, 530, 400);
            setVisible(false);
            jFrame.iniciar();
        }
    }
}
