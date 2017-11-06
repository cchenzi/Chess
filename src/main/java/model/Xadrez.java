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
public class Xadrez extends Jogo {
    
    int[][] tabuleiro;

    public Xadrez(Jogador[] jogadores) {
        super(jogadores);
        inicializarTabuleiro();
    }
    
    public int[][] getTabuleiro() {
        return tabuleiro;
    }
    
    private void inicializarTabuleiro() {
        tabuleiro = new int[8][8];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 0) {
                    switch (j) {
                        case 0: case 7:
                            tabuleiro[i][j] = 22;
                            tabuleiro[i+7][j] = 21;
                            break;
                        case 1: case 6:
                            tabuleiro[i][j] = 32;
                            tabuleiro[i+7][j] = 31;
                            break;
                        case 2: case 5:
                            tabuleiro[i][j] = 42;
                            tabuleiro[i+7][j] = 41;
                            break;
                        case 3:
                            tabuleiro[i][j] = 52;
                            tabuleiro[i+7][j] = 51;
                            break;
                        default: // CASE 4
                            tabuleiro[i][j] = 62;
                            tabuleiro[i+7][j] = 61;
                            break;
                    }
                } else {
                    tabuleiro[i][j] = 12;
                    tabuleiro[i+5][j] = 11;
                }
            }
        }
    }
    
}
