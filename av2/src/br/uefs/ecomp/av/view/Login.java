package br.uefs.ecomp.av.view;

import br.uefs.ecomp.av.controller.ControllerOperador;
import br.uefs.ecomp.av.controller.ControllerUsuario;
import br.uefs.ecomp.av.exception.EspacoEmBrancoException;
import br.uefs.ecomp.av.model.Usuario;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Esta classe tem como função conter as ações e opções para as janelas de
 * interface gráfica para a tela de Login.
 */
public class Login extends JFrame {

    private JTextField emailField;
    private JPasswordField senhaField;
    private ControllerUsuario controllerUsuario;
    private ControllerOperador controllerOperador;
    private JLabel emailLabel;
    private JLabel titulo;

    /**
     * Construtor do tipo Login, onde são inicializadas os controllers de
     * operações e é inicializado uma janela/formulario(login).
     *
     * @param controllerUsuario - Controller de Usuário.
     * @param controllerOperador - Controller do Operador.
     */
    public Login(ControllerUsuario controllerUsuario, ControllerOperador controllerOperador) {
        super("Assistente De Viagem");
        this.controllerUsuario = controllerUsuario;
        this.controllerOperador = controllerOperador;
        criarMenu();
        criarFormulario();
    }

    /**
     * Método responsável por criar uma tela gráfica onde são desenvolvidos
     * informações de uma barra de tarefa.
     */
    private void criarMenu() {

        SobreAction sobreAction = new SobreAction();
        NovoAction novoAction = new NovoAction();
        FecharAction fecharAction = new FecharAction();

        JMenu menuArquivo = new JMenu("Arquivo");

        JMenuItem menuItemNovo = new JMenuItem("Novo");
        menuItemNovo.addActionListener(novoAction);

        JMenuItem menuItemFechar = new JMenuItem("Fechar");
        menuItemFechar.addActionListener(fecharAction);

        menuArquivo.add(menuItemNovo);
        menuArquivo.add(menuItemFechar);

        JMenu menuAjuda = new JMenu("Ajuda");
        JMenuItem menuItemSobre = new JMenuItem("Sobre...");
        menuItemSobre.addActionListener(sobreAction);

        menuAjuda.add(menuItemSobre);

        JMenuBar barra = new JMenuBar();
        barra.add(menuArquivo);
        barra.add(menuAjuda);
        setJMenuBar(barra);
    }

    /**
     * Método responsável por criar um formulário de modo a ser utilizado na
     * tela da interface grafica.
     */
    private void criarFormulario() {

        CadastrarAction cadastrarAction = new CadastrarAction();
        EntrarAction entrarAction = new EntrarAction();

        setLayout(new BorderLayout());

        JPanel painelTitulo = new JPanel();
        painelTitulo.setLayout(new FlowLayout());

        titulo = new JLabel("Login do Usuário");
        //titulo.setForeground(Color.BLACK);
        titulo.setFont(new Font("Verdana", Font.BOLD, 16));

        painelTitulo.add(titulo);

        JPanel painelCentro = new JPanel();
        painelCentro.setLayout(new FlowLayout());

        emailLabel = new JLabel("Email");
        emailField = new JTextField(40); // Mudar parâmetro.

        JLabel senhaLabel = new JLabel("Senha");
        senhaField = new JPasswordField(40);

        painelCentro.add(emailLabel);
        painelCentro.add(emailField);
        painelCentro.add(senhaLabel);
        painelCentro.add(senhaField);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout());

        Icon icone = new ImageIcon(getClass().getResource("/livro.png"));
        JButton butaoCadastrar = new JButton("Cadastre-se", icone);
        butaoCadastrar.addActionListener(cadastrarAction);

        icone = new ImageIcon(getClass().getResource("/ok.png"));
        JButton butaoEntrar = new JButton("Entrar", (Icon) icone);
        butaoEntrar.addActionListener(entrarAction);

        painelBotoes.add(butaoCadastrar);
        painelBotoes.add(butaoEntrar);

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
            CadastroUsuario cadastro = new CadastroUsuario(controllerUsuario, controllerOperador);
            CriarJFrame jFrame = new CriarJFrame(cadastro, controllerOperador, 530, 260);
            setVisible(false);
            jFrame.iniciar();
        }
    }

    /**
     * Método responsável por criar uma tela de ação e verificação de acordo com
     * o email e senha do usuário.
     */
    private class EntrarAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Usuario usuario;
            Icon icone = new ImageIcon(getClass().getResource("/perigo.png"));
            try {
                usuario = controllerUsuario.fazerLogin(emailField.getText(), senhaField.getText());
                if (usuario == null) {
                    JOptionPane.showMessageDialog(null, "Email ou senha incorreta, tente novamente...", "Assistente De Viagem", JOptionPane.PLAIN_MESSAGE, icone);
                } else {
                    JOptionPane.showMessageDialog(null, "Seja bem-vindo...  Sr(a)." + usuario.getNomeCompleto(), "Assistente De Viagem", JOptionPane.PLAIN_MESSAGE);
                    FrameEscolha frameEscolha = new FrameEscolha(controllerUsuario, controllerOperador);
                    CriarJFrame jFrame = new CriarJFrame(frameEscolha, controllerOperador, 530, 260);
                    setVisible(false);
                    jFrame.iniciar();
                }
            } catch (EspacoEmBrancoException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Assistente De Viagem", JOptionPane.PLAIN_MESSAGE, icone);
            }
        }
    }

    /**
     * Método responsável por criar uma tela de barra de tarefa onde contem
     * determinadas informações.
     */
    private class SobreAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            JOptionPane.showMessageDialog(null, "Programa criado por:\n"
                    + "- Leandro Pereira Sampaio", "Sobre...", JOptionPane.PLAIN_MESSAGE);
        }
    }

    /**
     * Método responsável por criar uma tela de barra de tarefa onde os campos
     * de textos serão limpos.
     */
    public class NovoAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            emailField.setText("");
            senhaField.setText("");
        }
    }

    /**
     * Método responsável por salvar e encerrar a execução do programa.
     */
    public class FecharAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                controllerOperador.salvarEstadoSistema();
            } catch (Exception ex) {
            }
            System.exit(0);
        }
    }
}
