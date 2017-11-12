/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.Xadrez.PecasEnum;

/**
 *
 * @author felip
 */
public class Peao extends Peca {

    public Peao() {
        super(1);  
    }

    @Override
    public boolean isJogadaValida(PecasEnum[][] tabuleiro, int[] posInicial, int[] posFinal) {
        // REGRA DE MORTE
        if (tabuleiro[posFinal[0]][posFinal[1]] != null) {
            if (Math.abs(posFinal[1] - posInicial[1]) == 1 && Math.abs(posFinal[0] - posInicial[0]) == 1) {
                return true;
            } else {
                return false;
            }
        }
        
        // LOGICA DE SALTO DUPLO
        if(posInicial[0] == 1 || posInicial[0] == 6){   
            if(Math.abs(posFinal[0]-posInicial[0])<=2){
                return posFinal[1] == posInicial[1];
            }
        }
        
        // LOGICA DE AVANCO SIMPLES 
        return Math.abs(posFinal[0]-posInicial[0]) == 1 && posFinal[1] == posInicial[1];          
    }
    
}