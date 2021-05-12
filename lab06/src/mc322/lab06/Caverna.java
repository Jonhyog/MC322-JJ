package mc322.lab06;

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
	
	public void adicionarComponente(Componente comp, int posSala[]) {
		if ((posSala[0] >= 0 && posSala[0] < x) && (posSala[1] >= 0 && posSala[1] < y))
			vSalas[posSala[1]][posSala[0]].adicionarComponente(comp);
	}
	
	public boolean moverComponente(Componente comp, int[] source, int target[]) {
		vSalas[source[1]][source[0]].removerComponente(comp);
		vSalas[target[1]][target[0]].adicionarComponente(comp);
		
		// FIX-ME: A sala deve verificar se pode mover o componente
		return true;
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
