package mc322.game.gfx;

import java.awt.image.BufferedImage;
import java.util.Hashtable;
import java.util.Scanner;

import mc322.game.util.loaders.ImageLoader;
import mc322.game.util.loaders.SpriteMapLoader;

public class Assets {
	
	private Hashtable<String, BufferedImage> spritesTable;
	private Hashtable<Integer, String> spritesIDs;
	
	private Scanner spriteMap;
	private BufferedImage spriteSheet;
	
	public Assets() {
		spritesTable = new Hashtable<String, BufferedImage>();
		spritesIDs = new Hashtable<Integer, String>();
	}
	
	public void setMap(String spriteMapPath) {
		this.spriteMap = SpriteMapLoader.loadSpriteMap(spriteMapPath);
	}
	
	public void setSpriteSheet(String spriteSheetPath) {
		this.spriteSheet = ImageLoader.loadImage(spriteSheetPath);
	}
	
	public void loadSprite() { 
		//FIX-ME: NAO FUNCIONA COM SPRITES DE TAMANHOS DIFERENTES
		String[] line;
		String name;
		int sizeX, sizeY, x, y, id;
		
		while(spriteMap.hasNextLine()) {
			
			line = spriteMap.nextLine().split(" ");
			
			sizeX = Integer.parseInt(line[0]);
			sizeY = Integer.parseInt(line[1]);
			name = line[2];
			x = Integer.parseInt(line[3]);
			y = Integer.parseInt(line[4]);
			id = Integer.parseInt(line[5]);
			
			// FIX-ME: FAZER CLASSE SPRITESHEET PODE MELHORAR ABSTRACAO
			add(name, spriteSheet.getSubimage(x, y, sizeX, sizeY), id);
			System.out.println("Loading Sprite - " + name + " id: " + id);
		}
	}
	
	public void add(String name, BufferedImage img, int id) {
		spritesTable.put(name, img);
		spritesIDs.put(id, name);
	}
	
	public BufferedImage getSprite(String name) {
		return spritesTable.get(name);
	}
	
	public BufferedImage getSprite(int id) {
		return spritesTable.get(spritesIDs.get(id));
	}
}
