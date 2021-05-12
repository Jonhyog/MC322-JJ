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
	
	public boolean runCommand(String command) {
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
			break;
		case "c":
			break;
		case "q":
			break;
		default:
			System.out.println("Insira um comando valido.");
			return false;
		}
		
		return true;
	}
	
	
}
