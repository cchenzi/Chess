/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Exceptions.JogadaInvalidaException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author felip
 */
public class Xadrez extends Jogo {
    
    private Map<String, Peca> mapaPecaId = new HashMap<>();
    private PecasEnum[][] tabuleiro;
    private Observer observer;
  
    public enum PecasEnum {
        PEAO_PRETO, PEAO_BRANCO, BISPO_PRETO, BISPO_BRANCO, CAVALO_PRETO, CAVALO_BRANCO, RAINHA_PRETO, RAINHA_BRANCO, REI_PRETO,
        REI_BRANCO, TORRE_PRETO, TORRE_BRANCO;
    }
    
    private void inicializarMapaPecaId() {
        mapaPecaId.put("PEAO", new Peao());
        mapaPecaId.put("TORRE", new Torre());
        mapaPecaId.put("CAVALO", new Cavalo());
        mapaPecaId.put("BISPO", new Bispo());
        mapaPecaId.put("RAINHA", new Rainha());
        mapaPecaId.put("REI", new Rei());
    }

    public Xadrez(Jogador[] jogadores, Observer observer) {
        super(jogadores);
        inicializarMapaPecaId();
        inicializarTabuleiro();
        this.observer = observer;
    }
    
    public void setObserver(Observer observer) {
        this.observer = observer;
    }
    
    public PecasEnum[][] getTabuleiro() {
        return tabuleiro;
    }
    
    private void inicializarTabuleiro() {
        tabuleiro = new PecasEnum[8][8];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 0) {
                    switch (j) {
                        case 0: case 7:
                            tabuleiro[i][j] = PecasEnum.TORRE_PRETO;
                            tabuleiro[i+7][j] = PecasEnum.TORRE_BRANCO;
                            break;
                        case 1: case 6:
                            tabuleiro[i][j] = PecasEnum.CAVALO_PRETO;
                            tabuleiro[i+7][j] = PecasEnum.CAVALO_BRANCO;
                            break;
                        case 2: case 5:
                            tabuleiro[i][j] = PecasEnum.BISPO_PRETO;
                            tabuleiro[i+7][j] = PecasEnum.BISPO_BRANCO;
                            break;
                        case 3:
                            tabuleiro[i][j] = PecasEnum.RAINHA_PRETO;
                            tabuleiro[i+7][j] = PecasEnum.RAINHA_BRANCO;
                            break;
                        default: // CASE 4
                            tabuleiro[i][j] = PecasEnum.REI_PRETO;
                            tabuleiro[i+7][j] = PecasEnum.REI_BRANCO;
                            break;
                    }
                } else {
                    tabuleiro[i][j] = PecasEnum.PEAO_PRETO;
                    tabuleiro[i+5][j] = PecasEnum.PEAO_BRANCO;
                }
            }
        }
    }
    
    @Override
    protected void realizarJogada(Jogador jogador) {
        observer.requisitarJogada(jogador);
    }

    @Override
    protected boolean fimJogo() {
        return false;
    }
    
    public void movimentarPeca(Jogador jogador, int[] posInicial, int[] posFinal) {
        try {
            Peca peca = mapaPecaId.get(tabuleiro[posInicial[0]][posInicial[1]].toString().split("_")[0]);
            peca.movimentar(posInicial, posFinal);
        } catch (JogadaInvalidaException e) {
            realizarJogada(jogador);
        }
    }
    
}
