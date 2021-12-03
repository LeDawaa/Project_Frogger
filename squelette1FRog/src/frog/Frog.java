package frog;

import gameCommons.Game;
import gameCommons.IFrog;
import util.*;
import java.awt.image.BufferedImage;

public class Frog implements IFrog {
	
	private Game game;
	private Case position;
	private Direction direction;
	private BufferedImage sprite;
	
	public Frog(Game game) {
		this.game = game;
		this.position = new Case(game.width/2, 1);
		this.direction = Direction.up;
		this.sprite = game.images.spritesFrog.get("FrogUp");
	}

	public Case getPosition(){
		return this.position;
	}

	public Direction getDirection(){
		return this.direction;
	}

	public BufferedImage getSprite(){
		return this.sprite;
	}

	public void move(Direction key){
		if (key == Direction.up){
			this.position = new Case(this.position.absc, this.position.ord + 1);
			this.sprite = SpriteLoader.LoadSprite("FrogUp");
			this.game.Score++;
			this.game.addLane(true);
		}
		else if (key == Direction.down && (this.position.ord > 0)){
			this.position = new Case(this.position.absc, this.position.ord - 1);
			this.sprite = SpriteLoader.LoadSprite("FrogDown");
			this.game.Score--;
			this.game.addLane(false);
		}
		else if (key == Direction.right && (this.position.absc < this.game.width - 1)){
			this.position = new Case(this.position.absc + 1, this.position.ord);
			this.sprite = SpriteLoader.LoadSprite("FrogRight");
		}
		else if (key == Direction.left && (this.position.absc > 0)){
			this.position = new Case(this.position.absc - 1, this.position.ord);
			this.sprite = SpriteLoader.LoadSprite("FrogLeft");
		}
		System.out.println("(" + this.position.absc + "; " + this.position.ord+ ")");
	}
}