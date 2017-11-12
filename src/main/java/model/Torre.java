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

    public Torre() {
        super(5);   
    }

    @Override
    public boolean isJogadaValida(int[] posInicial, int[] posFinal) {
        return posInicial[0] == posFinal[0] || posInicial[1] == posFinal[1];
    }
    
}
