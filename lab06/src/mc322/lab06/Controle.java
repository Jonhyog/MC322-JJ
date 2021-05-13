package mc322.lab06;

import java.util.Scanner;

public class Controle {
	private Scanner keyboard;
	private Heroi jogador;
	
	Controle() {
		keyboard = new Scanner(System.in);
		jogador = null;
	}
	
	public void setJogador(Heroi jogador) {
		this.jogador = jogador;
	}
	
	public String nextCommand() {
		return keyboard.nextLine();
	}
	
	public void exibirHUD() {
		System.out.println("Player: " + jogador.getNome());
		System.out.println("Score: " + jogador.getValue());
	}
	
	public void tratarEventos() {
		Componente componenteSala = jogador.analisarSala();
		
		switch (componenteSala.getID()) {
		case 1:
			System.out.println("Voce sente uma brisa.");
			break;
		case 2:
			System.out.println("Um fedor empesteia o ambiente.");
			break;
		case 4:
			jogador.updateScore(componenteSala.getValue());
			jogador.die();
			System.out.println("O Wumpus te ataca!");
			break;
		case 5:
			jogador.updateScore(componenteSala.getValue());
			System.out.println("Voce caiu em um buraco.");		
			jogador.die();
			break;
		case 6:
			System.out.println("Um objeto brilha no centro da sala.");
			break;
		default:
			break;
		}
	}
	
	public boolean runCommand(String command) {
		Componente comp;
		
		switch (command) {
		case "w":
			jogador.moveCima();
			break;
		case "s":
			jogador.moveBaixo();
			break;
		case "d":
			jogador.moveDireita();
			break;
		case "a":
			jogador.moveEsquerda();
			break;
		case "k":
			jogador.loadBow();
			break;
		case "c":
			comp = jogador.analisarSala();
			
			// Pode substituir ID
			// if (comp.getClass() == Ouro.class)
				// System.out.println("Ok");
				
			if (comp.getID() == 6) {
				jogador.captureGold(comp);
				System.out.println("Voce pega o ouro");				
			} else {				
				System.out.println("O ouro nao esta nessa sala.");
			}
			
			break;
		case "q":
			System.out.println(jogador.caverna);
			exibirHUD();
			System.out.println("Volte sempre !");
			System.exit(0);
			break;
		default:
			System.out.println("Insira um comando valido.");
			return false;
		}
		
		tratarEventos();
		return true;
	}
	
	
}
