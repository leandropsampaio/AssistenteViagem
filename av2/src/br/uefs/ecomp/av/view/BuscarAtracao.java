package br.uefs.ecomp.av.view;

import br.uefs.ecomp.av.controller.ControllerOperador;
import br.uefs.ecomp.av.controller.ControllerUsuario;
import br.uefs.ecomp.av.exception.EspacoEmBrancoException;
import br.uefs.ecomp.av.model.Atracao;
import br.uefs.ecomp.av.model.Hotel;
import br.uefs.ecomp.av.model.Restaurante;
import br.uefs.ecomp.av.model.enums.CategoriaAtracao;
import br.uefs.ecomp.av.util.Coordenadas;
import br.uefs.ecomp.av.util.DadoNaoEncontradoException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Esta classe tem como função: conter, criar, organizar as informações de
 * interface gráfica das buscas de Atrações.
 *
 */
public class BuscarAtracao extends JFrame {

    private ControllerUsuario controllerUsuario;
    private ControllerOperador controllerOperador;
    private JTextField nomeField;
    private JPanel painelCentro;
    private JTextArea informacaoArea;

    /**
     * Construtor do tipo BuscarAtracao, onde são inicializadas os controllers e
     * é inicializado um formulario.
     *
     * @param controllerUsuario - Controller de Usuário.
     * @param controllerOperador - Controller do Operador.
     */
    public BuscarAtracao(ControllerUsuario controllerUsuario, ControllerOperador controllerOperador) {
        super("Assistente De Viagem");
        this.controllerUsuario = controllerUsuario;
        this.controllerOperador = controllerOperador;
        criarFormulario();
    }

    /**
     * Método responsável por criar um formulario de modo a ser utilizado na
     * tela de Formulario de busca das atrações na interface grafica.
     */
    private void criarFormulario() {

        BuscarAction buscarAction = new BuscarAction();
        VoltarAction voltarAction = new VoltarAction();

        setLayout(new BorderLayout());

        JPanel painelTitulo = new JPanel();
        painelTitulo.setLayout(new FlowLayout());

        JLabel titulo = new JLabel("Buscar Atração");
        //titulo.setForeground(Color.BLACK);
        titulo.setFont(new Font("Verdana", Font.BOLD, 16));

        painelTitulo.add(titulo);

        painelCentro = new JPanel();
        painelCentro.setLayout(new FlowLayout());

        JLabel nomeLabel = new JLabel("Nome da Atração");
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
                Atracao atracao = controllerUsuario.buscarAtracao(nomeField.getText().toLowerCase());
                Coordenadas coordenadas = atracao.getLocalizacao();
                informacaoArea.setForeground(Color.red);
                informacaoArea.setFont(new Font("Verdana", Font.PLAIN, 20));
                if (atracao.getCategoria() == CategoriaAtracao.RESTAURANTE) {
                    Restaurante restaurante = (Restaurante) controllerUsuario.buscarAtracao(nomeField.getText().toLowerCase());
                    informacaoArea.setText("Nome: " + atracao.getNome() + "\nPais: " + atracao.getBairro()
                            + "\nLocal: " + atracao.getLocal().getNome() + "\nLatitude: " + coordenadas.getLatitude()
                            + "\nLongitude: " + coordenadas.getLongitude() + "\nCategoria: " + restaurante.getCategoria()
                            + "\nPreço: " + restaurante.getPreco() + "\nTipo de cozinha: " + restaurante.getCozinha());
                } else if (atracao.getCategoria() == CategoriaAtracao.Hotel) {
                    Hotel hotel = (Hotel) controllerUsuario.buscarAtracao(nomeField.getText().toLowerCase());
                    informacaoArea.setText("Nome: " + atracao.getNome() + "\nPais: " + atracao.getBairro()
                            + "\nLocal: " + atracao.getLocal().getNome() + "\nLatitude: " + coordenadas.getLatitude()
                            + "\nLongitude: " + coordenadas.getLongitude() + "\nEstrelas: " + hotel.getEstrelas()
                            + "\nAr Condicionado: " + hotel.getArCondicionado() + "\nServiço de Quarto: " + hotel.getServicoQuarto()
                            + "\nTV: " + hotel.getTv());
                }
            } catch (EspacoEmBrancoException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Assistente De Viagem", JOptionPane.PLAIN_MESSAGE, icone);
            } catch (DadoNaoEncontradoException ex) {
                JOptionPane.showMessageDialog(null, "Atração não Encontrada, Tente Novamente...", "Assistente De Viagem", JOptionPane.PLAIN_MESSAGE, icone);
            }

        }
    }

    /**
     * Método responsável por ações de retorno/volta na interface grafica.
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
