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
public class EspacoEmBrancoException extends Exception{
    
    public EspacoEmBrancoException(){
       super("Campo Vazio... Por Favor, Complete Todos os Campos!");
    }
}