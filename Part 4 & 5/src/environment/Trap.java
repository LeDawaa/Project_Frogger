package environment;

import util.Case;
import gameCommons.Game;
import graphicalElements.Element;
import java.awt.image.BufferedImage;

public class Trap {
	private Game game;
	private Case leftPosition;
	private BufferedImage sprite;

	/* Prend en paramètre l'instance de game, la case sur laquelle elle va apparaitre, un boolean indiquant sa direction.
	* La taille est choisie aléatoirement et donnera une voiture entre 1 et 3 de longueur
	* l'emplacement de la 'partie principale' de la voiture est changée afin de simplifier les mesures de taille et le isSafe */
	public Trap(Game game, Case firstPosition){
		this.game = game;
		this.leftPosition = firstPosition;
		this.sprite = game.images.spritesFrog.get("ItsATrap");
	}

	/* Si l'ordonnée de la case est différent de celle de la voiture return true,
	* Sinon test si la case se situe entre l'avant et l'arrière de la voiture */
	public boolean isBadlySpecial(Case c) {
		return (c.equals(this.leftPosition));
	}
	
	/* Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture*/
	public void addToGraphics() {
		game.getGraphic().addBonus(new Element(leftPosition.absc, leftPosition.ord - this.game.Score, this.sprite));
	}
}