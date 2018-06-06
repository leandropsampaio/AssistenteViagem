package br.uefs.ecomp.av.view;

import br.uefs.ecomp.av.controller.ControllerOperador;
import br.uefs.ecomp.av.controller.ControllerUsuario;
import br.uefs.ecomp.av.exception.EspacoEmBrancoException;
import br.uefs.ecomp.av.model.Local;
import br.uefs.ecomp.av.model.enums.CategoriaAtracao;
import br.uefs.ecomp.av.util.Coordenadas;
import br.uefs.ecomp.av.util.DadoDuplicadoException;
import br.uefs.ecomp.av.util.DadoNaoEncontradoException;
import br.uefs.ecomp.av.util.Iterador;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Esta classe tem como função: conter, criar, organizar as informações de
 * interface gráfica da tela de cadastro dos hoteis.
 */
public class CadastrarHotel extends JFrame {

    private ControllerUsuario controllerUsuario;
    private ControllerOperador controllerOperador;
    private JTextField nomeField;
    private JTextField bairroField;
    private JTextField longitudeField;
    private JTextField latitudeField;
    private JComboBox localComboBox;
    private JRadioButton simArCondicionado;
    private JRadioButton naoArCondicionado;
    private JRadioButton simTV;
    private JRadioButton naoTV;
    private JRadioButton simServicoQuarto;
    private JRadioButton naoServicoQuarto;
    private ButtonGroup grupoArCondicionado;
    private ButtonGroup grupoTV;
    private ButtonGroup grupoServicoQuarto;
    private JRadioButton duasEstrelas;
    private JRadioButton umaEstrela;
    private JRadioButton quatroEstrelas;
    private JRadioButton cincoEstrelas;
    private ButtonGroup grupoEstrelas;
    private JRadioButton tresEstrelas;

    /**
     * Construtor do tipo CadastrarHotel, onde são inicializadas os controllers
     * e é inicializado um formulario.
     *
     * @param controllerUsuario - Controller de Usuário.
     * @param controllerOperador - Controller do Operador.
     */
    public CadastrarHotel(ControllerUsuario controllerUsuario, ControllerOperador controllerOperador) {
        super("Assistente De Viagem");
        this.controllerUsuario = controllerUsuario;
        this.controllerOperador = controllerOperador;
        criarFormulario();
    }

    /**
     * Método responsável por criar um formulario de modo a ser utilizado na
     * tela da interface gráfica.
     *
     */
    private void criarFormulario() {

        CadastrarAction cadastrarAction = new CadastrarAction();
        VoltarAction voltarAction = new VoltarAction();

        setLayout(new BorderLayout(30, 0));

        JPanel painelTitulo = new JPanel();
        painelTitulo.setLayout(new FlowLayout());

        JLabel titulo = new JLabel("Cadastro de Hotel");
        //titulo.setForeground(Color.BLACK);
        titulo.setFont(new Font("Verdana", Font.BOLD, 16));

        painelTitulo.add(titulo);

        localComboBox = new JComboBox();
        Iterador it = controllerOperador.listarLocais();
        while (it.temProximo()) {
            Local local = (Local) it.obterProximo();
            localComboBox.addItem(local.getNome());
        }

        JPanel painelCentro = new JPanel();
        painelCentro.setLayout(new GridLayout(14, 2));

        JPanel painelCentro2 = new JPanel();
        painelCentro2.setLayout(new FlowLayout());

        JLabel label = new JLabel("");
        JLabel label2 = new JLabel("");
        JLabel label3 = new JLabel("");
        JLabel label4 = new JLabel("");

        JLabel nomeLabel = new JLabel("Nome");
        nomeField = new JTextField(30); // Mudar parâmetro.

        JLabel bairroLabel = new JLabel("Bairro");
        bairroField = new JTextField(30);

        JLabel longitudeLabel = new JLabel("Longitude");
        longitudeField = new JTextField(10);

        JLabel latitudeLabel = new JLabel("Latitude");
        latitudeField = new JTextField(10);

        JLabel arLabel = new JLabel("Ar Condicionado");
        grupoArCondicionado = new ButtonGroup();
        simArCondicionado = new JRadioButton("Sim");
        naoArCondicionado = new JRadioButton("Não");

        grupoArCondicionado.add(simArCondicionado);
        grupoArCondicionado.add(naoArCondicionado);

        JLabel tvLabel = new JLabel("TV");
        grupoTV = new ButtonGroup();
        simTV = new JRadioButton("Sim");
        naoTV = new JRadioButton("Não");

        grupoTV.add(simTV);
        grupoTV.add(naoTV);

        JLabel servicoQuartoLabel = new JLabel("Serviço de Quarto");
        grupoServicoQuarto = new ButtonGroup();
        simServicoQuarto = new JRadioButton("Sim");
        naoServicoQuarto = new JRadioButton("Não");

        grupoServicoQuarto.add(simServicoQuarto);
        grupoServicoQuarto.add(naoServicoQuarto);

        JLabel estrelasLabel = new JLabel("Estrelas do Hotel");
        grupoEstrelas = new ButtonGroup();
        umaEstrela = new JRadioButton("1");
        duasEstrelas = new JRadioButton("2");
        tresEstrelas = new JRadioButton("3");
        quatroEstrelas = new JRadioButton("4");
        cincoEstrelas = new JRadioButton("5");

        grupoEstrelas.add(umaEstrela);
        grupoEstrelas.add(duasEstrelas);
        grupoEstrelas.add(tresEstrelas);
        grupoEstrelas.add(quatroEstrelas);
        grupoEstrelas.add(cincoEstrelas);

        painelCentro2.add(localComboBox);
        painelCentro.add(nomeLabel);
        painelCentro.add(nomeField);
        painelCentro.add(bairroLabel);
        painelCentro.add(bairroField);
        painelCentro.add(longitudeLabel);
        painelCentro.add(longitudeField);
        painelCentro.add(latitudeLabel);
        painelCentro.add(latitudeField);
        painelCentro.add(arLabel);
        painelCentro.add(label);
        painelCentro.add(simArCondicionado);
        painelCentro.add(naoArCondicionado);
        painelCentro.add(tvLabel);
        painelCentro.add(label2);
        painelCentro.add(simTV);
        painelCentro.add(naoTV);
        painelCentro.add(servicoQuartoLabel);
        painelCentro.add(label3);
        painelCentro.add(simServicoQuarto);
        painelCentro.add(naoServicoQuarto);
        painelCentro.add(estrelasLabel);
        painelCentro.add(label4);
        painelCentro.add(umaEstrela);
        painelCentro.add(duasEstrelas);
        painelCentro.add(tresEstrelas);
        painelCentro.add(quatroEstrelas);
        painelCentro.add(cincoEstrelas);

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
        add(painelCentro2, BorderLayout.WEST);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    /**
     * Método responsável por criar uma tela de ação para o cadastro das
     * informações.
     */
    private class CadastrarAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int i = 0;
            Icon icone = new ImageIcon(getClass().getResource("/perigo.png"));
            try {
                Double latitude = Double.parseDouble(latitudeField.getText());
                Double longitude = Double.parseDouble(longitudeField.getText());

                boolean arCondicionado = false;
                if (simArCondicionado.isSelected()) {
                    arCondicionado = true;
                } else if (naoArCondicionado.isSelected()) {
                    arCondicionado = false;
                } else {
                    throw new EspacoEmBrancoException();
                }

                boolean tv = false;
                if (simTV.isSelected()) {
                    tv = true;
                } else if (naoTV.isSelected()) {
                    tv = false;
                } else {
                    throw new EspacoEmBrancoException();
                }

                boolean servicoQuarto = false;
                if (simServicoQuarto.isSelected()) {
                    servicoQuarto = true;
                } else if (naoServicoQuarto.isSelected()) {
                    servicoQuarto = false;
                } else {
                    throw new EspacoEmBrancoException();
                }

                int estrelas = 0;
                if (umaEstrela.isSelected()) {
                    estrelas = 1;
                } else if (duasEstrelas.isSelected()) {
                    estrelas = 2;
                } else if (tresEstrelas.isSelected()) {
                    estrelas = 3;
                } else if (quatroEstrelas.isSelected()) {
                    estrelas = 4;
                } else if (cincoEstrelas.isSelected()) {
                    estrelas = 5;
                }

                //Local local, String nome, Coordenadas localizacao, String bairro,
                //CategoriaAtracao categoria, int estrelas, boolean arCondicionado, 
                //boolean tv, boolean servicoQuarto.
                Local local = controllerUsuario.buscarLocal((String) localComboBox.getSelectedItem());
                controllerUsuario.cadastrarHotel(local, nomeField.getText().toLowerCase(), new Coordenadas(latitude, longitude),
                        bairroField.getText(), CategoriaAtracao.Hotel, estrelas, arCondicionado,
                        tv, servicoQuarto);
                JOptionPane.showMessageDialog(null, "Hotel Cadastrado Com Sucesso!", "Assistente De Viagem", JOptionPane.PLAIN_MESSAGE);
                VoltarAction voltar = new VoltarAction();
                voltar.actionPerformed(e);

            } catch (EspacoEmBrancoException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Assistente De Viagem", JOptionPane.PLAIN_MESSAGE, icone);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Digite um Número Correto em Latitude e Longitude...", "Assistente De Viagem", JOptionPane.PLAIN_MESSAGE, icone);
            } catch (DadoDuplicadoException ex) {
                JOptionPane.showMessageDialog(null, "Hotel já está Cadastrado! Tente novamente...", "Assistente De Viagem", JOptionPane.PLAIN_MESSAGE, icone);
            } catch (DadoNaoEncontradoException ex) {

            }
        }
    }

    /**
     * Método responsável por ações de retorno/volta em interface grafica.
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
