package mc322.lab06;

public class PositionParser {
	private String position;
	
	PositionParser(){
		this.position = null;
	}
	
	/**
	 * Prepara o parser para traduzir as string do movimento
	 * de source para target em coordenadas para o tabuleiro.
	 *  
	 * @param source
	 * @param target
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	
	public int[] parsePosition() {
		int pos[] = new int[2];
		
		pos[0] = this.position.charAt(2) - '1';
		pos[1] = this.position.charAt(0) - '1';
		
		return pos;
	}
}
