package frog;

import gameCommons.Game;
import gameCommons.IFrog;
import util.*;

public class Frog implements IFrog {

	private Game game;
	private Case position;
	private Direction direction;

	public Frog(Game game) {
		this.game = game;
		this.position = new Case(game.width / 2, 0);
		this.direction = Direction.up;
	}

	public Case getPosition() {
		return this.position;
	}

	public Direction getDirection() {
		return this.direction;
	}

	public void move(Direction key) {
		if (key == Direction.up && (this.position.ord < this.game.height - 1)) {
			this.position = new Case(this.position.absc, this.position.ord + 1);
		}
		if (key == Direction.down && (this.position.ord > 0)) {
			this.position = new Case(this.position.absc, this.position.ord - 1);
		}
		if (key == Direction.right && (this.position.absc < this.game.width - 1)) {
			this.position = new Case(this.position.absc + 1, this.position.ord);
			System.out.println(this.position.absc + "" + this.position.ord);
		}
		if (key == Direction.left && (this.position.absc > 0)) {
			this.position = new Case(this.position.absc - 1, this.position.ord);
			System.out.println(this.position.absc + "" + this.position.ord);
		}
	}

}