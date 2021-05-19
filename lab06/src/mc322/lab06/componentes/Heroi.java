package mc322.lab06.componentes;

import mc322.lab06.Caverna;

public class Heroi extends Componente {
	private String nome;
	private boolean alive;
	private boolean escaped;
	private int flechas;
	private boolean gold;
	
	public Heroi(String nome) {
		super("P", 0, 3, 3);
		this.nome= nome;
		alive = true;
		escaped = false;
		flechas = 0;
		gold = false;
	}
	
	public void updateScore(int num) {
		this.score += num;
	}
	
	public void visitarSala() {
		caverna.visitarSala(pos);
	}
	
	private void move(int target[]) {
		int hit;
		boolean podeMover;
		podeMover = caverna.moverComponente(this, pos, target);
		if (!podeMover) {
			System.out.println("Posicao nao eh valida");
			return;
		}
		
		updateScore(-15);
		pos = target;
		
		if (getFlechas() > 0) {
			shootArrow();
			hit = caverna.handleHeroAttack(this);
			
			if (hit == 4) {
				updateScore(500);
				System.out.println("O Wumpus foi derrotado!");
			}
		}
		
		visitarSala();
	}
	
	public void moveCima() {
		int target[] = new int[] {pos[0], pos[1] - 1};
		move(target);
	}
	
	public void moveBaixo() {
		int target[] = new int[] {pos[0], pos[1] + 1};
		move(target);
	}
	
	public void moveEsquerda() {
		int target[] = new int[] {pos[0] - 1, pos[1]};
		move(target);
	}
	
	public void moveDireita() {
		int target[] = new int[] {pos[0] + 1, pos[1]};
		move(target);
	}
	
	public Componente analisarSala() {
		return caverna.getPrincipal(pos);
	}
	
	public Caverna getCaverna() {
		return caverna;
	}
	
	private int getFlechas() {
		return this.flechas;
	}
	
	public void loadBow() {
		this.flechas++;
	}
	
	private void shootArrow() {
		updateScore(-100);
		this.flechas--;
	}
	
	public void exibirFlechas() {
		System.out.println("Voce tem " + this.flechas + " flechas.");
	}
	
	public boolean getGold() {
		return gold;
	}
	
	public void captureGold() {
		Componente ouro;
		ouro = analisarSala();
		
		if (ouro.getID() == 6) {
			gold = !gold;
			caverna.removerComponente(ouro, ouro.pos);
			System.out.println("Voce pega o ouro");				
		} else {				
			System.out.println("O ouro nao esta nessa sala.");
		}
	}
	
	public String getNome() {
		return nome;
	}
	
	public void die() {
		alive = false;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public boolean hasEscaped() {
		return escaped;
	}
	
	public void tryToEscape() {
		if ((pos[0] == 0 && pos[1] == 0) && getGold()) {
			escaped = true;
			updateScore(1000);
		}
	}
}
