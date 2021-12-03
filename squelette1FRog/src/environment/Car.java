package environment;

import util.Case;
import gameCommons.Game;
import graphicalElements.Element;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Car {
	private Game game;
	private Case leftPosition;
	private boolean leftToRight;
	private int length;
	private ArrayList<BufferedImage> sprite = new ArrayList<BufferedImage>(length);

	/* Prend en paramètre l'instance de game, la case sur laquelle elle va apparaitre, un boolean indiquant sa direction.
	* La taille est choisie aléatoirement et donnera une voiture entre 1 et 3 de longueur
	* l'emplacement de la 'partie principale' de la voiture est changée afin de simplifier les mesures de taille et le isSafe */
	public Car(Game game, Case firstPosition, boolean leftToRight){
		this.game = game;
		this.leftToRight = leftToRight;
		this.length = ((game.randomGen.nextInt(10)) < 3) ? 2 : 1;
		this.leftPosition = new Case(leftToRight ? firstPosition.absc - this.length : firstPosition.absc, firstPosition.ord);
		for (int i = 0; i < length; i++) {
			if (!this.leftToRight && this.length == 2) {
				this.sprite.add(this.game.images.spritesRTL.get((i==0) ? "Truck_1_RTL" : "Truck_2_RTL"));
			} else if (this.leftToRight && this.length == 2) {
				this.sprite.add(this.game.images.spritesLTR.get((i==0) ? "Truck_1_LTR" : "Truck_2_LTR"));
			} else {
				switch(game.randomGen.nextInt(4)) {
					case 0: this.sprite.add(this.leftToRight ? this.game.images.spritesLTR.get("Car_1_LTR") : this.game.images.spritesRTL.get("Car_1_RTL")); break;
					case 1: this.sprite.add(this.leftToRight ? this.game.images.spritesLTR.get("Car_2_LTR") : this.game.images.spritesRTL.get("Car_2_RTL")); break;
					case 2: this.sprite.add(this.leftToRight ? this.game.images.spritesLTR.get("Car_3_LTR") : this.game.images.spritesRTL.get("Car_3_RTL")); break;
					case 3: this.sprite.add(this.leftToRight ? this.game.images.spritesLTR.get("Car_4_LTR") : this.game.images.spritesRTL.get("Car_4_RTL")); break;
					default: break;
				}
			}
		}
	}

	/* Prend en paramètre un boolean move.
	* Si move = false la fonction ne fait que mettre a jour les graphics,
	* Sinon fait avancer/reculer la voiture selon sa direction */
	public void moveCar(boolean move){
		if (move) this.leftPosition = new Case(leftToRight ? this.leftPosition.absc + 1 : this.leftPosition.absc - 1, this.leftPosition.ord);
		this.addToGraphics();
	}

	/* Si l'ordonnée de la case est différent de celle de la voiture return true,
	* Sinon test si la case se situe entre l'avant et l'arrière de la voiture */
	public boolean isSafe(Case c) {
		return (c.ord != this.leftPosition.ord ? true : !(c.absc >= this.leftPosition.absc && c.absc < this.leftPosition.absc + this.length));
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