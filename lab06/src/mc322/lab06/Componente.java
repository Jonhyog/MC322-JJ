package mc322.lab06;

public class Componente {
	public static int prioridade = 0;
	
	protected int score, pos[];
	protected String sprite;
	protected Caverna caverna;
	
	Componente(String sprite, int score) {
		this.sprite = sprite;
		this.score = score;
		this.pos = null;
		this.caverna = null;
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
