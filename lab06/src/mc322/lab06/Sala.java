package mc322.lab06;

public class Sala {
	private Componente vComponentes[];
	private int n, max;
	private boolean visitada;
	
	Sala() {
		this.n = 0;
		this.max = 4;
		this.visitada = false;
		this.vComponentes = new Componente[4];
	}
	
	private void ordenarComponentes() {
		int i, j;
		Componente temp;
		
		for (i = 1; i < n; i++) {
			temp = vComponentes[i];
			for (j = 1; j > 0 && temp.getPrioridade() > vComponentes[j-1].getPrioridade(); j--) {
				vComponentes[j] = vComponentes[j-1];
			}
			vComponentes[j] = temp;
		}
	}
	
	private void visitarSala() {
		this.visitada = true;
	}
	
	public void adicionarComponente(Componente comp) {
		if (n < max) {
			vComponentes[n] = comp;
			n++;
			ordenarComponentes();
			
			// Se for heroi marca como visitada
			// Provavelmente ha uma forma melhor de fazer isto
			// FIX-ME
			if (comp.getPrioridade() == 3)
				visitarSala();
		}
	}
	
	public void removerComponente(Componente comp) {
		for (int i = 0; i < n; i++) {
			if (vComponentes[i] == comp) {
				if (i != n-1)
					vComponentes[i] = vComponentes[n-1];
				n--;
				break;
			}
		}
		
		ordenarComponentes();
	}
	
	public String exibirSala() {
		if (!visitada)
			return "-";
		
		if (n == 0)
			return "#";
		else
			return vComponentes[0].renderSprite();
	}
}
