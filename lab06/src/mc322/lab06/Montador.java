package mc322.lab06;

public class Montador {
	private String caveTemplate[][];
	private Caverna cave;
	private Heroi jogador;
	
	Montador() {
		caveTemplate = null;
		cave = null;
		jogador = null;
	}
	
	private void setJogador(Heroi jogador) {
		this.jogador = jogador;
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
	
	public void generateCave() {
		int pos[];
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
				setJogador(new Heroi("J"));
				componente = getJogador();
				break;
			case "W":
				componente = new Wumpus();
				break;
			case "O":
				componente = new Ouro();
				break;
			case "B":
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
			
			cave.adicionarComponente(componente, pos);
			componente.conectarCaverna(cave, pos);
		}
		
		setCaverna(cave);
	}
}
