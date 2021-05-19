package mc322.lab06;

import mc322.lab06.componentes.Componente;
import mc322.lab06.componentes.Wumpus;

public class Caverna {
	private int x, y;
	private Sala vSalas[][];
	
	Caverna() {
		this.x = 4;
		this.y = 4;
		
		this.vSalas = new Sala[this.y][this.x];
		
		for (int i = 0; i < this.y; i++) {
			for (int j = 0; j < this.x; j++) {
				vSalas[i][j] = new Sala();
			}
		}
	}
	
	public boolean ehPosValida(int pos[]) {
		if ((pos[0] >= 0 && pos[0] < 4) && (pos[1] >= 0 && pos[1] < 4))
			return true;
		return false;
	}
	
	public void adicionarComponente(Componente comp, int posSala[]) {
		if ((posSala[0] >= 0 && posSala[0] < x) && (posSala[1] >= 0 && posSala[1] < y))
			vSalas[posSala[1]][posSala[0]].adicionarComponente(comp);
	}
	
	public boolean moverComponente(Componente comp, int[] source, int target[]) {
		if (!ehPosValida(source) || !ehPosValida(target))
			return false;
		vSalas[source[1]][source[0]].removerComponente(comp);
		vSalas[target[1]][target[0]].adicionarComponente(comp);
		return true;
	}
	
	public void removerComponente(Componente comp, int posSala[]) {
		vSalas[posSala[1]][posSala[0]].removerComponente(comp);
	}
	
	public Componente getPrincipal(int posSala[]) {
		return vSalas[posSala[1]][posSala[0]].getPrincipal();
	}
	
	public int handleHeroAttack(Componente heroi) {
		int pos[] = heroi.getPosition();
		int id = 0; // Id do componente que recebeu dano
		Componente comp = vSalas[pos[1]][pos[0]].getPrincipal();
		
		if (comp == null)
			return id;
		
		if (comp.getID() == 4) {
			if (Wumpus.receiveDamage()) {
				id = comp.getID();
				removerComponente(comp, comp.getPosition());
			} else {				
				id = heroi.getID();
			}
		}		
		
		return id;
	}
	
	public void visitarSala(int pos[]) {
		vSalas[pos[1]][pos[0]].visitarSala();
	}
	
	public String exibirCaverna() {
		String caverna = "";
		
		for (int i = 0; i < vSalas.length; i++) {
			caverna += i + 1;
			caverna += " ";
			for (int j = 0; j < vSalas[0].length; j++) {
				caverna += vSalas[i][j].exibirSala();
				caverna += " ";
			}
			caverna += '\n';
		}
		
		caverna += " ";
		for (int i = 0; i < vSalas.length; i++) {
			caverna += i + 1;
			caverna += " ";
		}
		caverna += '\n';
		
		return caverna;
	}
	
	public String toString() {
		return exibirCaverna();
	}
}
