package mc322.lab06;

public class AppMundoWumpus {
	public static void main(String args[]) {
		String cavePath = null, caveTemplate[][], command = null;
		Caverna cave = null;
		Montador mount = new Montador();
		CSVHandling csv = new CSVHandling();
		Heroi jogador = null;
		Controle ctrl = null;
		Componente componenteSala = null;
		
		if (args.length == 0) {
			System.out.println("Insira o caminho da caverna.");
			return;
		}
		
		cavePath = args[0];
		csv.setDataSource(cavePath);
		caveTemplate = csv.requestCommands();
		
		mount.setCaveTemplate(caveTemplate);
		mount.generateCave();
		cave = mount.getCaverna();
		jogador = mount.getJogador();
		
		System.out.println(cave);
		System.out.println(jogador.getNome());
		
		ctrl = new Controle();
		ctrl.setJogador(jogador);
		
		while (true) {
			command = ctrl.nextCommand();
			if (command.equals("q"))
				break;
			ctrl.runCommand(command);
			componenteSala = jogador.analisarSala();
			
			System.out.println(cave);
			switch (componenteSala.renderSprite()) {
			case "W":
				System.out.println("O Wumpus te ataca!");
				break;
			case "O":
				System.out.println("Um objeto brilha no centro da sala.");
				break;
			case "B":
				System.out.println("Voce caiu em um buraco.");
				break;
			case "b":
				System.out.println("Voce sente uma brisa.");
				break;
			case "f":
				System.out.println("Um fedor empesteia o ambiente.");
				break;
			default:
				break;
			}
		}
	}
}
