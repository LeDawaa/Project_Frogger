package environment;

import util.Case;
import gameCommons.Game;
import graphicalElements.Element;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Log {
	private Game game;
	private Case leftPosition;
	private boolean leftToRight;
	private int length = 3;
	private ArrayList<BufferedImage> sprite = new ArrayList<BufferedImage>(length);

	/* Prend en paramètre l'instance de game, la case sur laquelle elle va apparaitre, un boolean indiquant sa direction.
	* La taille est choisie aléatoirement et donnera une voiture entre 1 et 3 de longueur
	* l'emplacement de la 'partie principale' de la voiture est changée afin de simplifier les mesures de taille et le isSafe */
	public Log(Game game, Case firstPosition, boolean leftToRight){
		this.game = game;
		this.leftToRight = leftToRight;
		this.leftPosition = new Case(leftToRight ? firstPosition.absc - this.length : firstPosition.absc, firstPosition.ord);
		sprite.add(this.game.images.spritesLTR.get("Truck_2_LTR"));
		sprite.add(this.game.images.spritesLTR.get("Truck_2_LTR"));
		sprite.add(this.game.images.spritesLTR.get("Truck_2_LTR"));
	}

	/* Prend en paramètre un boolean move.
	* Si move = false la fonction ne fait que mettre a jour les graphics,
	* Sinon fait avancer/reculer la voiture selon sa direction */
	public void moveLog(boolean move){
		if (move) this.leftPosition = new Case(leftToRight ? this.leftPosition.absc + 1 : this.leftPosition.absc - 1, this.leftPosition.ord);
		if (move && isSafe(this.game.getFrog().getPosition())) this.game.getFrog().moveLeft(leftToRight);
		this.addToGraphics();
	}

	/* Si l'ordonnée de la case est différent de celle de la voiture return true,
	* Sinon test si la case se situe entre l'avant et l'arrière de la voiture */
	public boolean isSafe(Case c) {
		return !(c.ord != this.leftPosition.ord ? true : !(c.absc >= this.leftPosition.absc && c.absc < this.leftPosition.absc + this.length));
	}

	/* Test si la voiture se situe avant l'abscice 0 de la fenêtre
	* ou si elle se situe au dela de la largeur de la fenêtre */
	public boolean OOB(){
		return (this.leftToRight && this.leftPosition.absc - this.length > this.game.width)|| (!this.leftToRight && this.leftPosition.absc + this.length < 0);
	}
	
	/* Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture*/
	private void addToGraphics() {
		for (int i = 0; i < length; i++) {
			game.getGraphic().add(new Element(leftPosition.absc + i, leftPosition.ord - this.game.Score, this.sprite.get(i)));
		}
	}
}