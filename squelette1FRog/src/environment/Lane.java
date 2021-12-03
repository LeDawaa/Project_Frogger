package environment;

import java.util.ArrayList;
import java.util.Iterator;
import util.Case;
import gameCommons.Game;

public class Lane {
	private Game game;
	private int ord;
	private int speed;
	private ArrayList<Car> cars = new ArrayList<>();
	private ArrayList<Trap> traps = new ArrayList<>();
	private ArrayList<Star> stars = new ArrayList<>();
	private boolean leftToRight;
	private double density;
	private int tick = 0;

	/* Prend en paramètre l'instance de game et une ordonnée.
	* La vitesse est choisie aléatoirement entre 0 et game.minSpeedInTimerLoops, on y ajoute 1 pour éviter une ligne immobile
	* le leftToRight est lui aussi un boolean aléatoire
	* la densité est celle déclaré dans game */
	public Lane(Game game, int ord){
		this.game = game;
		this.ord = ord;
		this.speed = this.game.randomGen.nextInt(game.minSpeedInTimerLoops) + 1;
		this.leftToRight = this.game.randomGen.nextBoolean();
		this.density = game.defaultDensity;
		if (game.randomGen.nextInt(100) < 10) {
			this.traps.add(new Trap(game, new Case (game.randomGen.nextInt(game.width), ord)));
		} if (game.randomGen.nextInt(100) < 10) {
			this.stars.add(new Star(game, new Case (game.randomGen.nextInt(game.width), ord)));
		}
	}

	/* Prend en paramètre l'instance de game, une ordonnée et une densité.
	* appel au constructeur précédent
	* la densité est modifié pour correspondre au paramètre */
	public Lane(Game game, int ord, int density){
		this(game, ord);
		this.density = density;
		traps = new ArrayList<>();
		stars = new ArrayList<>();
	}

	/* Retourne l'ordonnée de la ligne */
	public int getOrd(){
		return this.ord;
	}

	/*
	Toutes les voitures se déplacent d'une case au bout d'un nombre "tic
	d'horloge" égal à leur vitesse
	Notez que cette méthode est appelée à chaque tic d'horloge

	Les voitures doivent etre ajoutes a l interface graphique meme quand
	elle ne bougent pas

	A chaque tic d'horloge, une voiture peut être ajoutée */
	public void update() {
		boolean ok = (tick == speed);
		moveCars(ok);
		removeCarOOB();
		if(ok){
			mayAddCar();
		}
		for (Trap sneakyBoy : this.traps) {
			sneakyBoy.addToGraphics();
		} for (Star twinklingStar : this.stars) {
			twinklingStar.addToGraphics();
		}
		if (this.game.invincibilityTime > 0) this.game.invincibilityTime--;
		System.out.println(this.game.invincibilityTime);
		this.tick = (this.tick + 1) % (speed + 1);
	}

	/* Prend en paramètre une case c
	* on vérifie pour chaque voiture si tous les cas sont isSafe
	* si un seul cas n'est pas safe, return false */
	public boolean isSafe(Case c){
		for (Star twinklingStar : this.stars) {
			if(twinklingStar.isReallyReallyGood(c)){
				this.game.invincibilityTime += 1000;
			}
		}
		if (this.game.invincibilityTime > 0) {
			return true;
		}
		
		for (Car car: this.cars){
			if(!car.isSafe(c)){
				return false;
			}
		} for (Trap sneakyBoy : this.traps) {
			if(sneakyBoy.isBadlySpecial(c)){
				return false;
			}
		} return true;
	}

	/* Itere à travers toutes les voitures de la ligne,
	* si une voiture est OutOfBound, elle est supprimée de l'array */
	public void removeCarOOB(){
		Iterator<Car> i = this.cars.iterator();
    	while (i.hasNext()){
			if(i.next().OOB()){
				i.remove();
			}
		}
	}

	/* Itere à travers toutes les voitures de la ligne pour leur appliquer la méthode moveCar */
	public void moveCars(boolean b){
		Iterator<Car> i = this.cars.iterator();
    	while (i.hasNext()){
			i.next().moveCar(b);
		}
	}



	/*
	 * Fourni : mayAddCar(), getFirstCase() et getBeforeFirstCase() 
	 */

	/**
	 * Ajoute une voiture au début de la voie avec probabilité égale à la
	 * densité, si la première case de la voie est vide
	 */
	private void mayAddCar() {
		if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density) {
				cars.add(new Car(game, getBeforeFirstCase(), leftToRight));
			}
		}
	}

	private Case getFirstCase() {
		if (leftToRight) {
			return new Case(0, ord);
		} else
			return new Case(game.width - 1, ord);
	}

	private Case getBeforeFirstCase() {
		if (leftToRight) {
			return new Case(-1, ord);
		} else
			return new Case(game.width, ord);
	}
}
