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
public abstract class Peca {

    private int valor;
    private String cor;

    public Peca(int valor, String cor) {
        super();
        this.valor = valor;
        this.cor = cor;
    }
    
    public int getValor() {
        return valor;
    }
    
    public String getCor() {
        return cor;
    }

    public abstract boolean isJogadaValida(Peca[][] tabuleiro, int[] posInicial, int[] posFinal);

}
