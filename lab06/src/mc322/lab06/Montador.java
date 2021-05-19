package mc322.lab06;

import mc322.lab06.componentes.Brisa;
import mc322.lab06.componentes.Buraco;
import mc322.lab06.componentes.Componente;
import mc322.lab06.componentes.Fedor;
import mc322.lab06.componentes.Heroi;
import mc322.lab06.componentes.Ouro;
import mc322.lab06.componentes.Wumpus;

public class Montador {
	private String caveTemplate[][], playerName;
	private Caverna cave;
	private Heroi jogador;
	
	Montador() {
		caveTemplate = null;
		cave = null;
		jogador = null;
		playerName = null;
	}
	
	private void setJogador(Heroi jogador) {
		this.jogador = jogador;
	}
	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public Heroi getJogador() {
		return this.jogador;
	}
	
	private void setCaverna(Caverna cave) {
		this.cave = cave;
	}
	
	public Caverna getCaverna() {
		return this.cave;
	}
	
	public void setCaveTemplate(String caveTemplate[][]) {
		this.caveTemplate = caveTemplate;
	}
	
	private void verificarLimiteComponentes(int nWumpus, int nBuracos, int nOuro, int nHeroi) {
		if (nHeroi > 1) {
			System.out.println("O limite de entidades Heroi foi atingido.");
			System.exit(1);
		}
		
		if (nWumpus > 1) {
			System.out.println("O limite de entidades Wupus foi atingido.");
			System.exit(1);
		}
		
		if (nOuro > 1) {
			System.out.println("O limite de entidades Ouro foi atingido.");
			System.exit(1);
		}
		
		if (nBuracos > 3) {
			System.out.println("O limite de entidades Buraco foi atingido.");
			System.exit(1);
		}
	}
	
	private void verificarMinimoComponentes(int nWumpus, int nBuracos, int nOuro, int nHeroi) {
		if (nWumpus < 1) {
			System.out.println("O numero minimo de entidades Wumpus nao foi atingido");
			System.exit(1);
		}
		
		if (nOuro < 1) {
			System.out.println("O numero minimo de entidades Ouro nao foi atingido");
			System.exit(1);
		}
		
		if (nBuracos < 2) {
			System.out.println("O numero minimo de entidades Buraco nao foi atingido");
			System.exit(1);			
		}
	}
	
	private void verificarPosicaoHeroi(int pos[]) {
		if (pos[0] != 0 || pos[1] != 0) {
			System.out.println("A posicao de inicio do heroi eh invalida");
			System.exit(1);
		}
	}
	
	public void generateCave() {
		int pos[], nWumpus = 0, nBuracos = 0, nOuro = 0, nHeroi = 0;
		String comp;
		PositionParser parser = new PositionParser();
		Componente componente = null;
		Caverna cave = new Caverna();
		
		if (caveTemplate == null)
			return;
		
		for (int i = 0; i < caveTemplate.length; i++) {
			parser.setPosition(caveTemplate[i][0]);
			comp = caveTemplate[i][1];
			pos = parser.parsePosition();
			
			switch (comp) {
			case "P":
				nHeroi++;
				verificarPosicaoHeroi(pos);
				setJogador(new Heroi(playerName));
				componente = getJogador();
				break;
			case "W":
				nWumpus++;
				componente = new Wumpus();
				break;
			case "O":
				nOuro++;
				componente = new Ouro();
				break;
			case "B":
				nBuracos++;
				componente = new Buraco();
				break;
			case "b":
				componente = new Brisa();
				break;
			case "f":
				componente = new Fedor();
				break;
			case "_":
				continue;
			default:
				break;
			}
			
			verificarLimiteComponentes(nWumpus, nBuracos, nOuro, nHeroi);
			cave.adicionarComponente(componente, pos);
			componente.conectarCaverna(cave, pos);
		}
		
		verificarMinimoComponentes(nWumpus, nBuracos, nOuro, nHeroi);
		setCaverna(cave);
	}
}
