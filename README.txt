# trabalho3-franciscoejoao
trabalho3-franciscoejoao created by GitHub Classroom

PROJETO - JOGO DE XADREZ

* FUNCIONALIDADES BÁSICAS
  - Permite a disputa de uma partida de xadrez entre dois jogadores humanos;
  - O jogo seguirá as regras oficiais de movimentação de peças;
  - O sistema identificará jogadas válidas e jogadas inválidas;
  - Uma partida poderá acabar a qualquer momento se um dos jogadores desejar (renúncia);
  - Poderá ser declarado empate a qualquer momento, se os dois jogadores aceitarem;
  - O sistema detecta quando um dos dois jogadores venceu a partida (morte do rei adversário);
  
* FUNCIONALIDADES DESEJÁVEIS (serão aplicadas de acordo com a possibilidade)
  - Identificar posições de xeque e xeque-mate;
  
* PADRÕES DE PROJETO
  1) TEMPLATE METHOD
    - JOGO: carrega o fluxo de programa de um jogo generico. (Ex.: jogaPlayer1, jogaPlayer2, verificaVencedor);
    
  2) PADRÃO ESTRATÉGIA
    - PEÇA: classe abstrata que carrega os atributos e a assinatura dos métodos das classes concretas de cada peça específica (Ex.: Bispo, Cavalo);

* JUSTIFICATIVA CLASSES E INTERFACES
    - Classe Abstrata PEÇA: define o padrão estrategia, dizendo o que cada classe concreta que representa uma peça possui (atributo valor) e os métodos
que estas devem implementar (movimentar-se);
    
    - Classes REI, RAINHA, TORRE, BISPO, CAVALO, PEAO: classes concretas que extendem PEÇA, e representam as peças do jogo em si;

    - Classe abstrata JOGO: implementa o Template Method que controla o fluxo da partida em si. Contem instancias dos jogadores de modo a organizar esse fluxo;

    - Classe XADREZ: extende a classe JOGO, implementando as regras específicas de uma partida de xadrez, carregando também uma matriz que representa o tabuleiro do mesmo;

    - Classe JOGADOR: define um jogador, de modo a tratar cada um de maneira independente dentro do jogo, tendo cada um sua pontuação tratada
individualmente, e sendo reconhecido de maneira singular dentro da partida.  
