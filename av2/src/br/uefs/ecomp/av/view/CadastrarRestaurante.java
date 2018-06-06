package br.uefs.ecomp.av.view;

import br.uefs.ecomp.av.controller.ControllerOperador;
import br.uefs.ecomp.av.controller.ControllerUsuario;
import br.uefs.ecomp.av.exception.EspacoEmBrancoException;
import br.uefs.ecomp.av.model.Local;
import br.uefs.ecomp.av.model.enums.*;
import br.uefs.ecomp.av.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Esta classe tem como função: conter, criar, organizar as informações de
 * interface gráfica da tela de cadastro de restaurante.
 */
public class CadastrarRestaurante extends JFrame {

    private ControllerUsuario controllerUsuario;
    private ControllerOperador controllerOperador;
    private JTextField nomeField;
    private JTextField bairroField;
    private JTextField longitudeField;
    private JTextField latitudeField;
    private JComboBox localComboBox;
    private JRadioButton precoB;
    private JRadioButton precoM;
    private JRadioButton precoS;
    private JRadioButton cozinhaI;
    private JRadioButton cozinhaN;
    private ButtonGroup grupoPreco;
    private ButtonGroup grupoCozinha;

    /**
     * Construtor do tipo CadastrarHotel, onde são inicializadas os controllers
     * e é inicializado um formulario.
     *
     * @param controllerUsuario - Controller de Usuário.
     * @param controllerOperador - Controller do Operador.
     */
    public CadastrarRestaurante(ControllerUsuario controllerUsuario, ControllerOperador controllerOperador) {
        super("Assistente De Viagem");
        this.controllerUsuario = controllerUsuario;
        this.controllerOperador = controllerOperador;
        criarFormulario();
    }

    /**
     * Construtor do tipo CadastrarRestaurante, onde são inicializadas os
     * controllers e é inicializado um formulario.
     */
    private void criarFormulario() {

        CadastrarAction cadastrarAction = new CadastrarAction();
        VoltarAction voltarAction = new VoltarAction();

        setLayout(new BorderLayout(30, 0));

        JPanel painelTitulo = new JPanel();
        painelTitulo.setLayout(new FlowLayout());

        JLabel titulo = new JLabel("Cadastro de Restaurante");
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
        painelCentro.setLayout(new GridLayout(13, 2));

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

        JLabel precoLabel = new JLabel("Faixa de Preço");
        grupoPreco = new ButtonGroup();
        precoB = new JRadioButton("Barato");
        precoM = new JRadioButton("Médio");
        precoS = new JRadioButton("Sofisticado");

        grupoPreco.add(precoB);
        grupoPreco.add(precoM);
        grupoPreco.add(precoS);

        JLabel cozinhaLabel = new JLabel("Tipo de Cozinha");
        //INTERNACIONAL, NACIONAL;
        grupoCozinha = new ButtonGroup();
        cozinhaI = new JRadioButton("Internacional");
        cozinhaN = new JRadioButton("Nacional");

        grupoCozinha.add(cozinhaI);
        grupoCozinha.add(cozinhaN);

        painelCentro2.add(localComboBox);
        painelCentro.add(nomeLabel);
        painelCentro.add(nomeField);
        painelCentro.add(bairroLabel);
        painelCentro.add(bairroField);
        painelCentro.add(longitudeLabel);
        painelCentro.add(longitudeField);
        painelCentro.add(latitudeLabel);
        painelCentro.add(latitudeField);
        painelCentro.add(precoLabel);
        painelCentro.add(label2);
        painelCentro.add(precoB);
        painelCentro.add(precoM);
        painelCentro.add(precoS);
        painelCentro.add(label3);
        painelCentro.add(cozinhaLabel);
        painelCentro.add(label4);
        painelCentro.add(cozinhaI);
        painelCentro.add(cozinhaN);

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
            Icon icone = new ImageIcon(getClass().getResource("/perigo.png"));
            try {
                Double latitude = Double.parseDouble(latitudeField.getText());
                Double longitude = Double.parseDouble(longitudeField.getText());

                TipoCozinha tipoCozinha = null;
                if (cozinhaI.isSelected()) {
                    tipoCozinha = TipoCozinha.INTERNACIONAL;
                } else if (cozinhaN.isSelected()) {
                    tipoCozinha = TipoCozinha.NACIONAL;
                }

                FaixaPreco faixaPreco = null;
                if (precoB.isSelected()) {
                    faixaPreco = FaixaPreco.BARATO;
                } else if (precoM.isSelected()) {
                    faixaPreco = FaixaPreco.MEDIO;
                } else if (precoS.isSelected()) {
                    faixaPreco = FaixaPreco.SOFISTICADO;
                }

                //Local local, String nome, Coordenadas localizacao, String bairro,
                //CategoriaAtracao categoria, FaixaPreco preco, TipoCozinha cozinha.
                //Local local = controllerUsuario.buscarLocal((String) localComboBox.getSelectedItem());
                controllerUsuario.cadastrarRestaurante(controllerUsuario.buscarLocal((String) localComboBox.getSelectedItem()), nomeField.getText().toLowerCase(),
                        new Coordenadas(latitude, longitude), bairroField.getText(), CategoriaAtracao.RESTAURANTE,
                        faixaPreco, tipoCozinha);
                JOptionPane.showMessageDialog(null, "Restaurante Cadastrado Com Sucesso!", "Assistente De Viagem", JOptionPane.PLAIN_MESSAGE);
                VoltarAction voltar = new VoltarAction();
                voltar.actionPerformed(e);
            } catch (EspacoEmBrancoException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Assistente De Viagem", JOptionPane.PLAIN_MESSAGE, icone);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Digite um Número Correto em Latitude e Longitude...", "Assistente De Viagem", JOptionPane.PLAIN_MESSAGE, icone);
            } catch (DadoDuplicadoException ex) {
                JOptionPane.showMessageDialog(null, "Restaurante já está Cadastrado! Tente novamente...", "Assistente De Viagem", JOptionPane.PLAIN_MESSAGE, icone);
            } catch (DadoNaoEncontradoException ex) {

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
