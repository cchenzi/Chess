package control;

import model.Jogador;
import model.Jogo;
import model.Xadrez;
import view.TelaInicial;
import view.TelaJogo;

public class Controller {
    
    private Jogo jogo;
    
    public Controller() {
        TelaInicial telaInicial = new TelaInicial(this);
    }
    
    public Jogo getJogo() {
        return jogo;
    }
    
    public void iniciarNovoXadrez(Jogador[] jogadores) {   
        jogo = new Xadrez(jogadores);
        TelaJogo telaJogo = new TelaJogo(this);
    }
    
}
