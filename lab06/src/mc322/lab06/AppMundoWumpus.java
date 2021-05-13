package mc322.lab06;

public class AppMundoWumpus {
	public static void main(String args[]) {
		String cavePath = null, caveTemplate[][], command = null;
		Caverna cave = null;
		Montador mount = new Montador();
		CSVHandling csv = new CSVHandling();
		Heroi jogador = null;
		Controle ctrl = null;
		
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
		jogador.visitarSala();
		
		ctrl = new Controle();
		ctrl.setJogador(jogador);
		
		System.out.println(cave);
		ctrl.exibirHUD();
		
		while (jogador.isAlive() && !jogador.hasEscaped()) {
			command = ctrl.nextCommand();
			ctrl.runCommand(command);
			
			System.out.println(cave);
			jogador.exibirFlechas();
			ctrl.exibirHUD();
			jogador.tryToEscape();
		}
		
		System.out.println(cave);
		ctrl.exibirHUD();
		if (!jogador.isAlive())
			System.out.println("Voce perdeu =( ...");
		else if (jogador.hasEscaped())
			System.out.println("Voce ganhou =D !!!");
	}
}
