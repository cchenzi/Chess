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
public abstract class Peca {

    private int valor;

    public Peca(int valor) {
        super();
        this.valor = valor;
    }
    
    public int getValor() {
        return valor;
    }

    public abstract void movimentar(int[] posInicial, int[] posFinal) throws JogadaInvalidaException;

}
