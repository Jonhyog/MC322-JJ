package mc322.game.composites;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import mc322.game.input.KeyManager;

public class Cell implements Entity{
	private int x, y;
	private boolean solida;
	private Entity father;
	private BufferedImage texture;
	private ArrayList<Entity> entitys;
	
	public Cell(BufferedImage texture, boolean solida) {
		this.entitys = new ArrayList<Entity>();
		this.texture = texture;
		this.solida = solida;
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void addEntity(Entity ent) {
		entitys.add(ent);
	}

	@Override
	public void removeEntity(Entity ent) {
		entitys.remove(ent);
		// FIX-ME: TRATAR ERRO CASO TENTE REMOVER ENT N PRESENTE
	}

	@Override
	public void setCallback(Entity father) {
		this.father = father;
		// NO JOGO TALVEZ NAO SEJA NECESSARIO
		// SUBSTITUIR POR STUB?
	}

	@Override
	public void render(Graphics2D g) {
		// SUSBTITUIR BUFFERED IMAGE POR CLASSE SPRITE QUE CONHECE PROPRIO TAMANHO?
		g.drawImage(texture, x * 32, y * 32, 32, 32, null);
		for (Entity ent : entitys) {
			ent.render(g);
		}	
	}

	@Override
	public void update(KeyManager key) {
		for (Entity ent : entitys) {
			ent.update(key);
		}
	}
}
