/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Exceptions.JogadaInvalidaException;

/**
 *
 * @author felip
 */
public abstract class Jogo {

    protected Jogador[] jogadores;
    protected int jogadorVez;

    public Jogo(Jogador[] jogadores) {
        this.jogadores = jogadores;
    }

    public Jogador[] getJogadores() {
        return jogadores;
    }

    public int getJogadorVez() {
        return jogadorVez;
    }
    
    protected abstract void trocarJogadorVez();

    // SUB-METODOS TEMPLATE METHOD  // OBS: Template Method esta no ActionListener dos buttons na GUI
    public abstract void fazerJogada(int[] posInicial, int[] posDestino) throws JogadaInvalidaException;

    public abstract boolean verificaFimJogo();

    public abstract void preparaProximaJogada();

}
