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
public class Peao extends Peca {

    public Peao() {
        super(0);   // DEFINIR VALOR CORRETO
    }

    @Override
    public void movimentar(int[] posInicial, int[] posFinal) throws JogadaInvalidaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
