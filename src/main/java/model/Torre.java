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
public class Torre extends Peca {

    public Torre(String cor) {
        super(5, cor);   
    }

    @Override
    public boolean isJogadaValida(Peca[][] tabuleiro, int[] posInicial, int[] posFinal) {
        return posInicial[0] == posFinal[0] || posInicial[1] == posFinal[1];
    }
    
}
