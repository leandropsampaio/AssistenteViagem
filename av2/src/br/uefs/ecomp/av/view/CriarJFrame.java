package br.uefs.ecomp.av.view;

import br.uefs.ecomp.av.controller.ControllerOperador;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Esta classe de grande importância, tem como função ser utilizada em momentos
 * de criação e organização de tela na interface grafica, sendo assim tal é
 * responsavel pela criação dos frames de interface.
 */
public final class CriarJFrame extends JFrame {

    private ControllerOperador controllerOperador;
    private final JFrame classe;
    private final int largura;
    private final int altura;

    /**
     * Construtor do tipo CriarJFrame, onde são inicializadas atributos que
     * contém informações para a janela de interface gráfica.
     *
     * @param classe - Classe que vai ser criada o frame.
     * @param controller - Controller operador.
     * @param largura - Largura da frame.
     * @param altura - Altura do frame.
     */
    public CriarJFrame(JFrame classe, ControllerOperador controller, int largura, int altura) {
        this.largura = largura;
        this.altura = altura;
        this.classe = classe;
        this.controllerOperador = controller;
    }

    /**
     * Método responsável por inicializar a janela gráfica da interface.
     */
    public void iniciar() {
        URL caminhoImagem = this.getClass().getClassLoader().getResource("arduino.png");
        Image icone = Toolkit.getDefaultToolkit().getImage(caminhoImagem);
        classe.setIconImage(icone);
        classe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        classe.setSize(largura, altura);
        classe.setResizable(false);
        classe.setLocationRelativeTo(null);
        classe.setVisible(true);
        classe.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int opcao = JOptionPane.showConfirmDialog(null, "Deseja Realmente Sair...", "Assistente De Viagem", JOptionPane.YES_NO_OPTION);
                if (opcao == JOptionPane.OK_OPTION) {
                    try {
                        controllerOperador.salvarEstadoSistema();
                    } catch (Exception ex) {
                    }
                    System.exit(0);
                }
            }
        });
    }
}
