/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.av.exception;

/**
 *
 * @author Leandro
 */
public class UsuarioCadastradoException extends Exception{
    
    public UsuarioCadastradoException(){
       super("Usuário já foi Cadastrado...Tente Novamente...");
    }
}
