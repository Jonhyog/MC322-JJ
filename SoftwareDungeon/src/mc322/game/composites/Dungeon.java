package mc322.game.composites;

import java.awt.Graphics2D;

import mc322.game.input.KeyManager;

public class Dungeon implements Entity{
	private int x, y;
	private int i, j;
	private Entity father;
	private Entity[][] tiles;
	
	public Dungeon () {
		tiles = null;
		this.i = 0;
		this.j = 0;
	}
	
	public void setSize(int x, int y) {
		this.x = x;
		this.y = y;
		this.tiles = new Entity[y][x];
	}
	
	public int[] getSize() {
		return new int[] {x, y};
	}
	
	public Entity getTile(int x, int y) {
		return tiles != null ? tiles[y][x] : null;
	}

	@Override
	public void addEntity(Entity ent) {
		if (i >= y)
			return;
		tiles[i][j] = ent;
		j++;
		if (j >= x) {
			j = 0;
			i++;
		}
	}

	@Override
	public void removeEntity(Entity ent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCallback(Entity father) {
		this.father = father;
		// NO JOGO TALVEZ NAO SEJA NECESSARIO
		// SUBSTITUIR POR STUB?
	}

	@Override
	public void render(Graphics2D g) {
		for (int posY = 0; posY < y; posY++) {
			for (int posX = 0; posX < x; posX++) {
				tiles[posY][posX].render(g);
			}
		}
	}

	@Override
	public void update(KeyManager key) {
		for (int posY = 0; posY < y; posY++) {
			for (int posX = 0; posX < x; posX++) {
				tiles[posY][posX].update(key);
			}
		}
	}
}
