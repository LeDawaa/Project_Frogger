package gameCommons;

import java.util.Random;

import graphicalElements.Element;
import graphicalElements.IFroggerGraphics;

import util.SpriteLoader;

public class Game {

	public final Random randomGen = new Random();

	// Caracteristique de la partie
	public final int width;
	public final int height;
	public final int minSpeedInTimerLoops;
	public final double defaultDensity;

	public int Score = 0;
	public final long start = System.nanoTime();
	public final SpriteLoader images;
	public long invincibilityTime = 0;

	// Lien aux objets utilis�s
	private IEnvironment environment;
	private IFrog frog;
	private IFroggerGraphics graphic;

	/**
	 * 
	 * @param graphic
	 *            l'interface graphique
	 * @param width
	 *            largeur en cases
	 * @param height
	 *            hauteur en cases
	 * @param minSpeedInTimerLoop
	 *            Vitesse minimale, en nombre de tour de timer avant déplacement
	 * @param defaultDensity
	 *            densite de voiture utilisee par defaut pour les routes
	 */
	public Game(IFroggerGraphics graphic, int width, int height, int minSpeedInTimerLoop, double defaultDensity) {
		super();
		this.graphic = graphic;
		this.width = width;
		this.height = height;
		this.minSpeedInTimerLoops = minSpeedInTimerLoop;
		this.defaultDensity = defaultDensity;
		this.images = new SpriteLoader();
	}

	public void addLane(boolean forward){
		this.environment.addLane(forward);
	}

	/**
	 * Lie l'objet frog à la partie
	 * 
	 * @param frog
	 */
	public void setFrog(IFrog frog) {
		this.frog = frog;
	}

	public IFrog getFrog() {
		return this.frog;
	}

	/**
	 * Lie l'objet environment a la partie
	 * 
	 * @param environment
	 */
	public void setEnvironment(IEnvironment environment) {
		this.environment = environment;
	}

	/**
	 * 
	 * @return l'interface graphique
	 */
	public IFroggerGraphics getGraphic() {
		return graphic;
	}

	/**
	 * Teste si la partie est perdue et lance un écran de fin approprié si tel
	 * est le cas
	 * 
	 * @return true si le partie est perdue
	 */
	public boolean testLose() {
		if (!this.environment.isSafe(this.frog.getPosition())) {
			this.graphic.endGameScreen("You died.", "Your Score : " + this.Score, "Time : " + (System.nanoTime() - this.start)/1000000000 + "s");
			return true;
		}
		return false;
	}

	/**
	 * Teste si la partie est gagnee et lance un écran de fin approprié si tel
	 * est le cas
	 * 
	 * @return true si la partie est gagnée
	 */
	public boolean testWin() {
		if (this.environment.isWinningPosition(this.frog.getPosition())) {
			this.graphic.endGameScreen("You WIN", "You WIN" ,"You WIN");
			return true;
		} return false;
	}

	/**
	 * Actualise l'environnement, affiche la grenouille et verifie la fin de
	 * partie.
	 */
	public boolean update() {
		graphic.clear();
		environment.update();
		this.graphic.add(new Element(frog.getPosition().absc, 1, frog.getSprite()));
		return testLose();
	}
}