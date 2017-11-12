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
public class Peao extends Peca {

    public Peao() {
        super(1);  
    }

    @Override
    public boolean isJogadaValida(int[] posInicial, int[] posFinal) {
        // IMPLEMENTAR REGRA DE MORTE E SALTO DUPLO INICIAL
        if(posInicial[0] == 1 || posInicial[0] == 6){
            if(Math.abs(posFinal[0]-posInicial[0])<=2){
                return posFinal[1] == posInicial[1];
            }
        }
        return Math.abs(posFinal[0]-posInicial[0]) == 1 && posFinal[1] == posInicial[1];
        // IMPLEMENTAR PROMOCAO
    }
    
}
