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
    *OBS: o método se encontra no actionListener dos botões da GUI;
    
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

 * INTERFACE GRAFICA
    - TELA INICIAL: tela que trará uma breve explicação de como jogar, e uma área de preparação para o jogo, na qual se identificará quem são os jogadores;
        
    - TELA JOGO: tela que traz o jogo em si com o tabuleiro e a pontuação dos jogadores ao longo da partida.
    
    
    
--------------------------------------------------------------------------------------------------------------------------------------
    REGRAS A SEREM IMPLEMENTADAS:
    
    *MOVIMENTAÇÃO DAS PEÇAS
      -TORRE: movimenta-se em direções ortogonais, isto é, pelas linhas (horizontais) e colunas (verticais), não podendo se mover pelas diagonais. Ela pode mover quantas casas desejar se estiverem desocupadas pelas colunas e linhas, porém, apenas em um sentido em cada jogada.
      -BISPO: movimenta-se nas direções diagonais, não podendo se mover pelas ortogonais como as torres. Ele pode mover quantas casas quiser pelas diagonais, porém, apenas em um sentido em cada jogada e desde que as mesmas estejam desobstruídas.
      -RAINHA: pode movimentar-se quantas casas queira, tanto na diagonal, como na vertical ou na horizontal, porém, apenas em um sentido em cada jogada.
      -REI: pode mover-se em todas as direções, mas limitado somente à sua casa vizinha.
      -PEÃO: única peça do xadrez que nunca retrocede no tabuleiro. Portanto, quando se encontra na segunda fila sempre estará disponível para fazer o seu primeiro movimento. Nesse caso ele pode "optar" entre "andar" uma ou duas casas sempre na mesma coluna. Passando da segunda fila, não mais pode se deslocar duas casas, mesmo que não o tenha feito em seu primeiro movimento. Ao contrário das demais peças do xadrez, quando vai capturar uma peça, seu movimento é diferente: ele desloca-se na diagonal, andando apenas uma casa, sempre para frente. O peão não pode capturar para trás, e não captura peças que obstruam o seu caminho. Assim, qualquer peça (inclusive um outro peão), pode parar a marcha de um peão.
        *Promoção do Peão: caso ele alcance a última fileira do tabuleiro o jogador deve promover seu peão, transformando-o em qualquer outra peça menos o rei e peão (dama, torre, bispo ou cavalo). O peão pode se transformar em qualquer das quatro peças, mesmo que haja outras em jogo.
      -CAVALO: pode andar em "forma de L", ou seja, anda duas casas em linha reta e depois uma casa para o lado. Consegue pular qualquer peça, inclusive as do adversário. Captura as peças adversárias que estejam em sua casa de chegada, mas não pode ir para uma casa ocupada por uma peça amiga.
      
    *VITÓRIA
      Existem dois modos de se obter a vitória durante uma partida de Xadrez:
        -Adversário desistir ou abandonar o jogo;
        -Xeque-mate ao rei adversário.
        
    *VALOR DAS PEÇAS(PONTUAÇÃO):
      -PEÃO: 1;
      -CAVALO/BISPO: 3;
      -TORRE: 5;
      -RAINHA: 9.
      -REI: 0"
    "O valor do rei não é definido, visto que não pode ser capturado, muito menos trocado, durante o curso de um jogo. 
      
     *TABULEIRO:
      -64 casas de cores alternadas, formado por um conjunto de 8 linhas por 8 colunas(8x8).
      
      
      
      *REFERÊNCIAS UTILIZADAS:
      http://www.tabuleirodexadrez.com.br/index.html
--------------------------------------------------------------------------------------------------------------------------------------
    CODIFICAÇÃO A SER IMPLEMENTADA
    
    PEÇA          | NÚMERO
    Peão Branco   |   11
    Peão Preto    |   12
    Torre Branco  |   21 
    Torre Preto   |   22
    Cavalo Branco |   31
    Cavalo Preto  |   32
    Bispo Branco  |   41
    Bispo Preto   |   42
    Rainha Branco |   51
    Rainha Preto  |   52
    Rei Branco    |   61
    Rei Preto     |   62
    
 
    
