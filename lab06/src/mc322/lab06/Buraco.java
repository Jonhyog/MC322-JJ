package mc322.lab06;

public class Buraco extends Componente {
    public int prioridade;

    Buraco() {
        super("B", -1000, 4);
    }
    
    private int[][] gerarVizinhos() {
    	int vizinhos[][] = new int[4][2];
    	
    	vizinhos[0] = new int[] {this.pos[0] + 1, this.pos[1]};
    	vizinhos[1] = new int[] {this.pos[0] - 1, this.pos[1]};
    	vizinhos[2] = new int[] {this.pos[0], this.pos[1] + 1};
    	vizinhos[3] = new int[] {this.pos[0], this.pos[1] - 1};
    	
    	return vizinhos;
    	
    }
    
    public void conectarCaverna(Caverna caverna, int pos[]) {
    	super.conectarCaverna(caverna, pos);
    	gerarBrisa(pos);
    }
    
    public void gerarBrisa(int pos[]){
    	int vizinhos[][] = gerarVizinhos();
        Componente comp;
        
        for (int i = 0; i < vizinhos.length; i++) {
        	comp = new Brisa();
        	this.caverna.adicionarComponente(comp, vizinhos[i]);
        	comp.conectarCaverna(this.caverna, vizinhos[i]);
        }
    }
}
