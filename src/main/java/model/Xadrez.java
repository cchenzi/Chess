/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Exceptions.JogadaInvalidaException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author felip
 */
public class Xadrez extends Jogo {

    private Map<String, Peca> mapaPecaId = new HashMap<>();
    private PecasEnum[][] tabuleiro;

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

    public Xadrez(Jogador[] jogadores) {
        super(jogadores);
        inicializarMapaPecaId();
        inicializarTabuleiro();
        jogadorVez = 0;
    }

    public PecasEnum[][] getTabuleiro() {
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
        tabuleiro = new PecasEnum[8][8];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 0) {
                    switch (j) {
                        case 0:
                        case 7:
                            tabuleiro[i][j] = PecasEnum.TORRE_PRETO;
                            tabuleiro[i + 7][j] = PecasEnum.TORRE_BRANCO;
                            break;
                        case 1:
                        case 6:
                            tabuleiro[i][j] = PecasEnum.CAVALO_PRETO;
                            tabuleiro[i + 7][j] = PecasEnum.CAVALO_BRANCO;
                            break;
                        case 2:
                        case 5:
                            tabuleiro[i][j] = PecasEnum.BISPO_PRETO;
                            tabuleiro[i + 7][j] = PecasEnum.BISPO_BRANCO;
                            break;
                        case 3:
                            tabuleiro[i][j] = PecasEnum.RAINHA_PRETO;
                            tabuleiro[i + 7][j] = PecasEnum.RAINHA_BRANCO;
                            break;
                        default: // CASE 4
                            tabuleiro[i][j] = PecasEnum.REI_PRETO;
                            tabuleiro[i + 7][j] = PecasEnum.REI_BRANCO;
                            break;
                    }
                } else {
                    tabuleiro[i][j] = PecasEnum.PEAO_PRETO;
                    tabuleiro[i + 5][j] = PecasEnum.PEAO_BRANCO;
                }
            }
        }
    }

    @Override
    public void fazerJogada(int[] posInicial, int[] posDestino) throws JogadaInvalidaException {
       
        Peca peca = mapaPecaId.get(tabuleiro[posInicial[0]][posInicial[1]].toString().split("_")[0]);
       
        
 
        if(peca.getValor()==1 && tabuleiro[posDestino[0]][posDestino[1]] != null){
            if(posDestino[1] == posInicial[1]+1 || posDestino[1] == posInicial[1]-1){
                Peca pecaAux = mapaPecaId.get(tabuleiro[posDestino[0]][posDestino[1]].toString().split("_")[0]);
                tabuleiro[posDestino[0]][posDestino[1]] = tabuleiro[posInicial[0]][posInicial[1]];
                tabuleiro[posInicial[0]][posInicial[1]] = null; 
                jogadores[jogadorVez].setPontuacao( jogadores[jogadorVez].getPontuacao()+pecaAux.getValor());
            //TESTE PARA PROMOÇÃO
                if(peca.getValor()==1){
                     if(posDestino[0] == 0 || posDestino[0] == 7){
                     if(tabuleiro[posInicial[0]][posInicial[1]] == PecasEnum.PEAO_BRANCO){
                        tabuleiro[posDestino[0]][posDestino[1]] = PecasEnum.RAINHA_BRANCO;  
                     }else{
                        tabuleiro[posDestino[0]][posDestino[1]] = PecasEnum.RAINHA_PRETO; 
                     }
                     //FAZER UM TESTE DECENTE PARA VER SE É BRANCO OU PRETO, PENSAR SE IMPLEMENTA ESCOLHA OU SÓ RAINHA
                     
            }
        }
            }
        }
        else if (peca.isJogadaValida(posInicial, posDestino)) {
            if(tabuleiro[posDestino[0]][posDestino[1]] != null){
                Peca pecaAux = mapaPecaId.get(tabuleiro[posDestino[0]][posDestino[1]].toString().split("_")[0]);
                jogadores[jogadorVez].setPontuacao( jogadores[jogadorVez].getPontuacao()+pecaAux.getValor());
            }
            tabuleiro[posDestino[0]][posDestino[1]] = tabuleiro[posInicial[0]][posInicial[1]];
            tabuleiro[posInicial[0]][posInicial[1]] = null;
           
        } else {
            throw new JogadaInvalidaException();
        } 
        
    }

    @Override
    public boolean verificaFimJogo() {
        PecasEnum reiAlvo;
        if (jogadorVez == 0) {
            reiAlvo = PecasEnum.REI_PRETO;
        } else {
            reiAlvo = PecasEnum.REI_BRANCO;
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tabuleiro[i][j] == reiAlvo) {
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
    
   

}
