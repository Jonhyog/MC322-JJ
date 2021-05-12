package mc322.lab06;

public class Componente {
	protected int prioridade;
	protected int score, pos[];
	protected String sprite;
	protected Caverna caverna;
	
	Componente(String sprite, int score, int prioridade) {
		this.sprite = sprite;
		this.score = score;
		this.prioridade = prioridade;
		this.pos = null;
		this.caverna = null;
	}
	
	public int getPrioridade() {
		return this.prioridade;
	}
	
	public String renderSprite() {
		return this.sprite;
	}
	
	public int getValue() {
		return this.score;
	}
	
	public int[] getPosition() {
		return pos;
	}
	
	public void conectarCaverna(Caverna caverna, int pos[]) {
		this.caverna = caverna;
		this.pos = pos;
	}
}
