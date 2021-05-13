package mc322.lab06;

import java.util.Random;

public class Wumpus extends Componente {
    private boolean alive;

    Wumpus() {
        super("W", -1000, 4, 4);
        this.alive = true;
    }

    public boolean getAlive(){
        return this.alive;
    }

    public static boolean receiveDamage(){
        Random gerador = new Random();
        
        if(gerador.nextInt(2) == 0){
            return true;
        } else{
            return false;
        }
    }

    public int dealDamage(){
        return getValue();
    }

    public void die(){
        this.alive = false;
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
    	gerarFedor(pos);
    }
    
    public void gerarFedor(int pos[]){
    	int vizinhos[][] = gerarVizinhos();
        Componente comp;
        
        for (int i = 0; i < vizinhos.length; i++) {
        	comp = new Fedor();
        	this.caverna.adicionarComponente(comp, vizinhos[i]);
        	comp.conectarCaverna(this.caverna, vizinhos[i]);
        }
    }
}
