package environment;

import java.util.ArrayList;
import java.util.Iterator;
import util.Case;
import gameCommons.Game;

public class Wouter {
	private Game game;
	private int ord;
	private int speed;
	private ArrayList<Log> logs = new ArrayList<>();
	private boolean leftToRight;
	private double density = 0.5;
	private int tick = 0;

	/* Prend en paramètre l'instance de game et une ordonnée.
	* La vitesse est choisie aléatoirement entre 0 et game.minSpeedInTimerLoops, on y ajoute 1 pour éviter une ligne immobile
	* le leftToRight est lui aussi un boolean aléatoire
	* la densité est celle déclaré dans game */
	public Wouter(Game game, int ord) {
		this.game = game;
		this.ord = ord;
		this.speed = this.game.randomGen.nextInt(game.minSpeedInTimerLoops) + 1;
		this.leftToRight = this.game.randomGen.nextBoolean();
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
		moveLogs(ok);
		removeLogOOB();
		if(ok){
			mayAddLog();
		}
		this.tick = (this.tick + 1) % (speed + 1);
	}

	/* Prend en paramètre une case c
	* on vérifie pour chaque voiture si tous les cas sont isSafe
	* si un seul cas n'est pas safe, return false */
	public boolean isSafe(Case c){
		if (this.game.invincibilityTime > System.nanoTime()) {
			return true;
		}
		
		for (Log log: this.logs){
			if(!log.isSafe(c)){
				return false;
			}
		} return true;
	}

	/* Itere à travers toutes les voitures de la ligne,
	* si une voiture est OutOfBound, elle est supprimée de l'array */
	public void removeLogOOB(){
		Iterator<Log> i = this.logs.iterator();
    	while (i.hasNext()){
			if(i.next().OOB()){
				i.remove();
			}
		}
	}

	/* Itere à travers toutes les voitures de la ligne pour leur appliquer la méthode moveCar */
	public void moveLogs(boolean b){
		Iterator<Log> i = this.logs.iterator();
    	while (i.hasNext()){
			i.next().moveLog(b);
		}
	}



	/*
	 * Fourni : mayAddCar(), getFirstCase() et getBeforeFirstCase() 
	 */

	/**
	 * Ajoute une voiture au début de la voie avec probabilité égale à la
	 * densité, si la première case de la voie est vide
	 */
	private void mayAddLog() {
		if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density) {
				logs.add(new Log(game, getBeforeFirstCase(), leftToRight));
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

