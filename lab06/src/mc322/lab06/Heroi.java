package mc322.lab06;

public class Heroi extends Componente {
	private String nome;
	private boolean alive;
	private boolean loadedBow;
	private boolean gold;
	
	Heroi(String nome) {
		super("P", 0, 3);
		this.nome= nome;
		alive = true;
		loadedBow = false;
		gold = false;
	}
	
	private void updateScore(int num) {
		this.score += num;
	}
	
	public void moveCima() {
		int target[] = new int[] {pos[0], pos[1] - 1};
		caverna.moverComponente(this, pos, target);
		pos = target;
	}
	
	public void moveBaixo() {
		int target[] = new int[] {pos[0], pos[1] + 1};
		caverna.moverComponente(this, pos, target);
		pos = target;
	}
	
	public void moveEsquerda() {
		int target[] = new int[] {pos[0] - 1, pos[1]};
		caverna.moverComponente(this, pos, target);
		pos = target;
	}
	
	public void moveDireita() {
		int target[] = new int[] {pos[0] + 1, pos[1]};
		caverna.moverComponente(this, pos, target);
		pos = target;
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
