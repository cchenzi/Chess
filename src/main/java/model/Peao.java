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

    public Peao(String cor) {
        super(1, cor);
    }

    @Override
    public boolean isJogadaValida(Peca[][] tabuleiro, int[] posInicial, int[] posFinal) {
        String cor = tabuleiro[posInicial[0]][posInicial[1]].getCor();

        if (tabuleiro[posFinal[0]][posFinal[1]] == null) {
            if (Math.abs(posFinal[0] - posInicial[0]) <= 2 && posFinal[1] == posInicial[1]) {
                if (cor.equals("BRANCO")) {
                    return posInicial[0] == 6 || (posFinal[0] - posInicial[0] == -1);
                } else {
                    return posInicial[0] == 1 || (posFinal[0] - posInicial[0] == 1);
                }
            } else {
                return false;
            }
        } else {
            if (Math.abs(posFinal[1] - posInicial[1]) == 1) {
                if (cor.equals("BRANCO")) {
                    return posFinal[0] - posInicial[0] == -1;
                } else {
                    return posFinal[0] - posInicial[0] == 1;
                }
            } else {
                return false;
            }
        }
    }
}
