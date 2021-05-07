package mc322.lab06;

public class Heroi extends Componente {
	public static int prioridade = 3;
	
	private String nome;
	private boolean alive;
	private boolean loadedBow;
	private boolean gold;
	
	Heroi(String nome) {
		super("P", 0);
		this.nome= nome;
		alive = true;
		loadedBow = false;
		gold = false;
	}
	
	private void updateScore(int num) {
		this.score += num;
	}
	
	public void move(int pos[]) {
		this.pos = pos;
		// FIX-ME: Enviar sinal para atualizar posicao na caverna
	}
	
	public void useBow() {
		loadedBow = !loadedBow;
	}
	
	public void shootArrow() {
		updateScore(-100);
		useBow();
	}
	
	public boolean getGold() {
		return gold;
	}
	
	public void captureGold() {
		if (!gold) {
			gold = !gold;
		}
	}
	
	public String getNome() {
		return nome;
	}
	
	public boolean isAlive() {
		return alive;
	}
}
