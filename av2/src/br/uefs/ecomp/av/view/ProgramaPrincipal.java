package br.uefs.ecomp.av.view;

import br.uefs.ecomp.av.controller.ControllerOperador;
import br.uefs.ecomp.av.controller.ControllerUsuario;

/**
 * Esta classe tem como função conter as ações e opções para as janelas da
 * interface gráfica.
 */
public class ProgramaPrincipal {

    // Declaração dos controllers e de um objeto para uso de login. 
    ControllerUsuario controlllerUsuario = new ControllerUsuario();
    ControllerOperador controllerOperador = new ControllerOperador();
    Login login = new Login(controlllerUsuario, controllerOperador);

    // Inicialização de um novo objeto do tipo ProgramaPrincipal.
    public static void main(String[] args) {
        ProgramaPrincipal programa = new ProgramaPrincipal();
        programa.iniciar();
    }

    /**
     * Método responsável por inicializar a interface gráfica e realizar o
     * carregamento das informações da tela inicial além de conter determinadas
     * informações de tamanho, por exemplo.
     */
    public void iniciar() {
        try {
            controllerOperador.carregarEstadoSistema();
        } catch (Exception ex) {
        }
        CriarJFrame jFrame = new CriarJFrame(login, controllerOperador, 530, 260);
        jFrame.iniciar();
    }
}
