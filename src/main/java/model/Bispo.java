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
public class Bispo extends Peca {

    public Bispo(String cor) {
        super(3, cor);  
    }

    @Override
    public boolean isJogadaValida(Peca[][] tabuleiro, int[] posInicial, int[] posFinal) {
        return Math.abs(posInicial[0] - posFinal[0]) == Math.abs(posInicial[1] - posFinal[1]);
    }
    
}
