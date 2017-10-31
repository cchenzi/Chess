# trabalho3-franciscoejoao
trabalho3-franciscoejoao created by GitHub Classroom

PROJETO - JOGO DE XADREZ

* FUNCIONALIDADES BÁSICAS
  - Permite a disputa de uma partida de xadrez entre dois jogadores humanos;
  - O jogo seguirá as regras oficiais de movimentação de peças;
  - O sistema identificará jogadas válidas e jogadas inválidas;
  - Uma partida poderá acabar a qualquer momento se um dos jogadores desejar (renúncia);
  - Poderá ser declarado empate a qualquer momento, se os dois jogadores aceitarem;
  
* FUNCIONALIDADES DESEJÁVEIS (serão aplicadas de acordo com a possibilidade posterior)
  - Identificar posições de xeque e xeque-mate;
  
* PADRÕES DE PROJETO
  1) TEMPLATE METHOD
    - Fluxo do jogo (controle das jogadas dos jogadores e verificação de fim de jogo)
    
  2) PADRÃO ESTRATÉGIA
    - PEÇA: classe abstrata que carrega os atributos e a assinatura dos métodos das classes concretas de cada peça específica (Ex.: Bispo, Cavalo);

// FALTA JUSTIFICAR AS CLASSES E AS INTERFACES
   
OBS: Peça é uma classe abstrata e não uma Interface Java;
  
  
