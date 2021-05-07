package mc322.lab06;

import java.util.Random;

public class Wumpus extends Componente {
    public static int prioridade = 4;
    private boolean alive;

    Wumpus(String sprite, int score) {
        super.Componentes("W", -1000);
        this.alive = true;
    }

    public getAlive(){
        return this.alive;
    }

    public boolean receiveDamage(){
        Random dano = new Random(1);
        if(dano == 0){
            die();
            return true;
        }else{
            return false;
        }
    }

    public int dealDamage(){
        return getValue();
    }

    public void die(){
        this.alive = false;
    }

    public void gerarFedor(Caverna caverna, int []pos){
        
    }
}
