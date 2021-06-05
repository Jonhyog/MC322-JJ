package mc322.game.composites;

import java.awt.Graphics2D;

import mc322.game.input.KeyManager;

public interface Entity {
	public void addEntity(Entity ent);
	public void removeEntity(Entity ent);
	public void setCallback(Entity father);
	public void render(Graphics2D g);
	public void update(KeyManager key);
}
