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
public class Xadrez extends Jogo {

    private Peca[][] tabuleiro;

    public Xadrez(Jogador[] jogadores) {
        super(jogadores);
        inicializarTabuleiro();
        jogadorVez = 0;
    }

    public Peca[][] getTabuleiro() {
        return tabuleiro;
    }

    @Override
    protected void trocarJogadorVez() {
        if (jogadorVez == 0) {
            jogadorVez = 1;
        } else {
            jogadorVez = 0;
        }
    }

    private void inicializarTabuleiro() {
        tabuleiro = new Peca[8][8];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 0) {
                    switch (j) {
                        case 0: case 7:                       
                            tabuleiro[i][j] = new Torre("PRETO");
                            tabuleiro[i + 7][j] = new Torre("BRANCO");
                            break;
                        case 1: case 6:                      
                            tabuleiro[i][j] = new Cavalo("PRETO");
                            tabuleiro[i + 7][j] = new Cavalo("BRANCO");
                            break;
                        case 2: case 5:                       
                            tabuleiro[i][j] = new Bispo("PRETO");
                            tabuleiro[i + 7][j] = new Bispo("BRANCO");
                            break;
                        case 3:
                            tabuleiro[i][j] = new Rainha("PRETO");
                            tabuleiro[i + 7][j] = new Rainha("BRANCO");
                            break;
                        default: // CASE 4
                            tabuleiro[i][j] = new Rei("PRETO");
                            tabuleiro[i + 7][j] = new Rei("BRANCO");
                            break;
                    }
                } else {
                    tabuleiro[i][j] = new Peao("PRETO");
                    tabuleiro[i + 5][j] = new Peao("BRANCO");
                }
            }
        }
    }

    @Override
    public void fazerJogada(int[] posInicial, int[] posDestino) throws JogadaInvalidaException {
        Peca peca = tabuleiro[posInicial[0]][posInicial[1]];
        if (testaJogada(peca, posInicial, posDestino)) {
            // TESTE DE MORTE E ATUALIZACAO DA PONTUACAO CASO VERDADEIRO  
            if (tabuleiro[posDestino[0]][posDestino[1]] != null) {
                Peca pecaAux = tabuleiro[posDestino[0]][posDestino[1]];
                jogadores[jogadorVez].setPontuacao(jogadores[jogadorVez].getPontuacao() + pecaAux.getValor());
            }

            //TESTE PARA PROMOÇÃO DE PEAO   
            if (peca instanceof Peao && ((posDestino[0] == 0 && peca.getCor().equals("BRANCO")) || (posDestino[0] == 7 && peca.getCor().equals("PRETO")))) {
                    tabuleiro[posDestino[0]][posDestino[1]] = new Rainha(peca.getCor());
            } else {
                // ATUALIZACAO REGULAR DO TABULEIRO
                tabuleiro[posDestino[0]][posDestino[1]] = tabuleiro[posInicial[0]][posInicial[1]];
            }
            tabuleiro[posInicial[0]][posInicial[1]] = null;
        } else {
            throw new JogadaInvalidaException();
        }
    }

    public boolean testaJogada(Peca peca, int[] posInicial, int[] posDestino) {
        boolean jogadaValida = peca.isJogadaValida(tabuleiro, posInicial, posDestino);
        boolean testaSalto = aconteceSalto(posInicial, posDestino);
        
        return jogadaValida && (!testaSalto || peca instanceof Cavalo) && 
                (tabuleiro[posDestino[0]][posDestino[1]] == null || !tabuleiro[posDestino[0]][posDestino[1]].getCor().equals(peca.getCor()));
    }

    @Override
    public boolean verificaFimJogo() {
        String corAdversario;
        if (jogadorVez == 0) {
            corAdversario = "PRETO";
        } else {
            corAdversario = "BRANCO";
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tabuleiro[i][j] instanceof Rei && tabuleiro[i][j].getCor().equals(corAdversario)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void preparaProximaJogada() {
        trocarJogadorVez();
    }

    public boolean aconteceSalto(int[] posInicial, int[] posFinal) {
        int diffLinhas = posFinal[0] - posInicial[0];
        int diffColunas = posFinal[1] - posInicial[1];
        if (Math.abs(diffLinhas) > 1) {
            if (Math.abs(diffColunas) > 1) {
                if (diffLinhas == -diffColunas) {
                    // DIAGONAL INVERTIDA
                    return aconteceSaltoDiagonalInvertida(posInicial, posFinal);
                } else {
                    // DIAGONAL
                    return aconteceSaltoDiagonal(posInicial, posFinal);
                }
            } else {
                // VERTICAL
                return aconteceSaltoVertical(posInicial, posFinal);
            }
        } else {
            if (Math.abs(diffColunas) > 1) {
                // HORIZONTAL
                return aconteceSaltoHorizontal(posInicial, posFinal);
            } else {
                return false;
            }
        }
    }

    public boolean aconteceSaltoVertical(int[] posInicial, int[] posFinal) {
        int i = Math.min(posInicial[0], posFinal[0]) + 1;
        int j = posInicial[1];
        while (i < Math.max(posInicial[0], posFinal[0])) {
            if (tabuleiro[i][j] != null) {
                return true;
            }
            i++;
        }
        return false;
    }

    public boolean aconteceSaltoHorizontal(int[] posInicial, int[] posFinal) {
        int i = posFinal[0];
        int j = Math.min(posInicial[1], posFinal[1]) + 1;
        while (j < Math.max(posInicial[1], posFinal[1])) {
            if (tabuleiro[i][j] != null) {
                return true;
            }
            j++;
        }
        return false;
    }

    public boolean aconteceSaltoDiagonal(int[] posInicial, int[] posFinal) {
        int i = Math.min(posInicial[0], posFinal[0]) + 1;
        int j = Math.min(posInicial[1], posFinal[1]) + 1;
        while (i < Math.max(posInicial[0], posFinal[0])) {
            if (tabuleiro[i][j] != null) {
                return true;
            }
            i++;
            j++;
        }
        return false;
    }

    public boolean aconteceSaltoDiagonalInvertida(int[] posInicial, int[] posFinal) {
        int i = Math.min(posInicial[0], posFinal[0]) + 1;
        int j = Math.max(posInicial[1], posFinal[1]) - 1;
        while (i < Math.max(posInicial[0], posFinal[0])) {
            if (tabuleiro[i][j] != null) {
                return true;
            }
            i++;
            j--;
        }
        return false;
    }

}
