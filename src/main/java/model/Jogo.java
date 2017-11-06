/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author felip
 */
public abstract class Jogo {

    private Jogador[] jogadores;
    
    public Jogo(Jogador[] jogadores) {
        this.jogadores = jogadores;
    }
    
    // IMPLEMENTAR PADRÃO TEMPLATE METHOD
    
}
