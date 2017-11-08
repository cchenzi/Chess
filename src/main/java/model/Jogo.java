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
    
    public Jogador[] getJogadores() {
        return jogadores;
    }
    
    // IMPLEMENTAR PADRÃO TEMPLATE METHOD
    public void jogar() {
        int i = 0;
        while (!fimJogo()) {
            realizarJogada(jogadores[i % jogadores.length]);
            i++;
        }
    }
    
    protected abstract void realizarJogada(Jogador jogador); 
    
    protected abstract boolean fimJogo();
    
}
