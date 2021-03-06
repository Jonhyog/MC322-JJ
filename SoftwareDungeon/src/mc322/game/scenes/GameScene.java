package mc322.game.scenes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import mc322.game.composites.Cell;
import mc322.game.composites.Dungeon;
import mc322.game.composites.Entity;
import mc322.game.factory.DungeonBuilder;
import mc322.game.gfx.Assets;
import mc322.game.input.KeyManager;

public class GameScene extends JPanel implements Scene {
	
	private static final long serialVersionUID = 1453255777619821491L;
	
	private SceneManager sceneMan;
	private int width, height;
	private SceneControl sceneCtrl;
	private Dungeon dg;
	
	public GameScene(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		
		System.out.println("GameScene: loading");
		super.setSize(width, height);
		super.setLayout(null);
		super.setDoubleBuffered(true);
		super.setOpaque(false);
		super.setMaximumSize(new Dimension(width, height));
		super.setMinimumSize(new Dimension(width, height));
		super.setFocusable(false);
		
		this.sceneCtrl = new SceneControl(); 
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graficos = (Graphics2D) g;
		
		graficos.setColor(new Color(0, 0, 0));
		graficos.fillRect(0, 0, width, height);
		dg.render(graficos);
	}
	
	@Override
	public void update(KeyManager key) {
		render();
	}

	@Override
	public void render() {
		repaint();
	}

	@Override
	public void setCallback(SceneManager sceneMan) {
		this.sceneMan = sceneMan;
	}

	@Override
	public void initScene(Assets gameAssets) {
		DungeonBuilder builder = new DungeonBuilder();
		builder.setDungeonMap("res/dungeons/dungeon.dg");
		builder.loadDungeonTiles(gameAssets);
		
		int[] size = builder.getSize();
		this.dg = new Dungeon();
		dg.setSize(size[0], size[1]);
		System.out.println("\tDungeon (" + size[0] + ", " + size[1] + "): Loading");
		for (int i = 0; i < size[1]; i++) {
			for (int j = 0; j < size[0]; j++) {
				dg.addEntity(builder.nextCell(gameAssets));
			}
		}
		System.out.println("\tCaverna: ok");
		System.out.println("GameScene: ok");
	}
	
	
}
