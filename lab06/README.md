# Lab06 - O Mundo de Wumpus
## Arquivos Java
Acionando o Jogo: Utilize o comando `java mc322.lab06.AppMundoWumpus <caminho do jogo> <nome do jogador>` substituindo os itens entre `<>` por um arquivo csv com o caminho da caverna e o nome do jogador.  
  [Jogo](src/mc322/lab06)
  
## Arquivos de Teste
  [Testes](data)
  
## Manual do Jogo
Bem-vindo corajoso herói.
Você está preparado para se empreitar em uma jornada pela infame Caverna do Wumpus?

Nesta caverna existem vários abismos infindos capazes de tirar o fôlego e o aterrorizador Wumpus, um monstro capaz de tornar o mais valente guerreiro em um palito de dentes após sua refeição. "Mas de que me vale entrar então nesta caverna?" você me pergunta. Posso lhe afirmar que se você for um destemido guerreiro com vontade de conquistar honra e glória ao derrotar tal criatura não estaria me perguntando isso, mas se está necessitando mais uma motivação posso lhe dá-la. Dentro desta caverna está uma grande pilha de ouro, capaz de criar um novo reino do zero. Como foi parar lá? É uma boa pergunta. Nunca me disseram como também! Caso esteja disposto a enfrentar tal empreitada creio que seja necessário que você leia as instruções abaixo para que consiga adentrar a caverna.

Você será o tipo de herói que enfrentará o temível Wumpus e conquistaŕa o ouro como um guerreiro, ou será aquele que consegue o ouro para si de modo que nem o próprio Wumpus consiga perceber?

Adentre tal caverna herói, quem sabe o que lhe aguarda nesta jornada?

Para inicializar o jogo deve ser passado para o jogo o caminho da caverna, ou seja, como está disposta a caverna para que o jogo possa ser inicializado.

Para ser criada a caverna deve ser inserido, por meio de um arquivo csv, as posições de cada elemento, predispostas de forma `X:Y,C` em cada parágrafo. `X` e `Y` significam as coordenadas da localização do componente, que devem ser escritas de forma que nunca se repita a mesma coordenada. Em `C` pode ser escrito os seguintes caracteres:
  * `P`: que indica o jogador. Sempre deve ser inicializado na posição `1:1`;
  * `W`: que indica o Wumpus. Nunca pode haver mais de um Wumpus;
  * `O`: que indica o Ouro. Nunca pode haver mais de um ouro, além de nunca poder ser inicializado nas extrmidades, ou seja, nas coordenadas `1:1`,`1,4`,`4:1` e `4:4`;
  * `B`: que indica o Buraco. Pode haver apenas 2 ou 3 buracos;
  * `_`: que indica que a coordenada está vazia.
  
Ao inicializar o jogo os comandos que podem ser utilizados são:
  * `w`: mover para cima;
  * `s`: mover para baixo;
  * `a`: mover para a direita;
  * `d`: mover para a direita;
  * `k`: carrega o arco com a flecha, assim na próxima sala que o jogador entrar ele irá disparar a flecha;
  * `c`: capturar o ouro;
  * `q`: sair da caverna.
  
Durante a jornada irão aparecer frases que irão lhe ajudar na jornada de forma que se indicar haver um fedor ou uma brisa isso indica que em uma sala adjacente há o Wumpus ou um Buraco, respectivamente.

Bom jogo e se divirta!

## Destaques da Arquitetura
### `Expansão dos Componentes`
```Java
public class Sala {
  private Componente vComponentes[];
  
  private ordenarComponentes() {
    ...
  }
   
  public adicionarComponentes() {
    ...
    ordenarComponentes()
  }
}
```

O trecho demonstra a facilidade de expansão dos componentes. A sala não precisa conhecer as especificidades de cada subclasse de `Componente`. É possível realizar diversas ações como `adicionarComponentes` e `ordenarComponentes` sabendo somente as informações comuns a superclasse `Componente`, como `prioridade`. Dessa forma, mantemos a consistência com a arquitetura proposta permitindo a expansão do jogo com outros componentes. 

### `Tratamento de Posições`
```Java
public Class PositionParser {
  ...
  public int[] parsePosition() {
    ...
    return pos;
  }
}
```

O trecho demonstra como a tarefa de converter uma posição da forma `"x:y"` para uma posição que pode ser utilizada pela caverna foi abstraida e permite expandir o jogo. Considere que vamos expandir o jogo adicionando um componente `Portal` que permite que o jogador se mova de uma sala com um portal para outra com um portal mesmo que não adjacentes. Ao selecionar a ação de teleportar, em uma sala com portal, o jogador deve inserir a posição, na forma `"x:y"`, da sala onde está localizado o portal de destino. Dessa forma, não cabe ao jogador se preocupar em como deve ser feita a conversão da posição para um formato que pode ser utilizado pela caverna o que demonstra uma especialização das tarefas de cada componente.

### `Encapsulamento das funções do Herói`
```Java
private void move(int target[]) {
  if (!podeMover) {
    ...
  }
}

public void moveCima() {
  int target[] = new int[] {pos[0], pos[1] - 1};
  move(target);
}
```

O trecho demonstra o encapsulamento das funções do `Heroi`. Só são expostos os métodos, como `moverCima`, que representam o movimento em uma direção válida. Dessa forma, especializam-se as capacidades do heroi evitando que classes externas saibam como ocorre o movimento. Com isso, a lógica do jogo é melhor distribuída.

### `Construção dos componentes secundários e polimorfismo`

```Java
public class Wumpus extends Componente {
  ...
  public void conectarCaverna(...) {
    super.conectarCaverna(...);
    gerarFedor(...);
  }
}
```

O trecho demonstra a utilização do polimorfismo para gerar os componentes secundários `Fedor`. Sobrescrevemos a método `conectarCaverna(...)` de `Componente` para permitir que quando o `Wumpus` fosse inserido na caverna e devidamente conectado os componentes secundários seriam gerados sem uma chamada explícita de um método. Dessa forma, encapsulando a construção dos componente secundários e explorando o polimorfismo para a simplificação do código. Além disso, vale a pena notar que este método de geração dos componentes secundários foi aplicado também a construção da `Brisa` pelo componente `Buraco`.
