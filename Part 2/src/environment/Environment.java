package environment;

import java.util.ArrayList;
import java.util.Iterator;
import util.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

public class Environment implements IEnvironment {
	private Game game;
	private ArrayList<Lane> Lanes;

	/*
	 * Prend en paramètre l'instance de game.
	 * Les 3 premières lignes sont initialisées comme vides afin de mourrir dès le
	 * début
	 * ensuite game game.height - 3 lignes sont initialisées avec une densité
	 * aléatoire
	 * le jeu est update game.width * 5 fois afin que les voitures soient déjà
	 * disposées aléatoirement
	 */
	public Environment(Game game) {
		this.game = game;
		this.Lanes = new ArrayList<Lane>();
		this.Lanes.add(new Lane(game, 0, 0));
		for (int i = 2; i < game.height - 1; i++) {
			this.Lanes.add(new Lane(game, i));
		} this.Lanes.add(new Lane(game, game.height, 0));

		for (int i = 1; i < game.width * 5; i++) {
			update();
		}
	}

	/*
	 * Prend en paramètre une case c
	 * la grenouille ne change virtuellement pas de place et reste à 2 d'ordonné
	 * on vérifie donc seulement pour la deuxième ligne
	 */
	public boolean isSafe(Case c){
		for(Lane l : this.Lanes){
			if(!l.isSafe(c)) return false;
		} return true;
	}

	/*
	 * Prend en paramètre une case c
	 * teste si l'ordonnée de la case c correspond à la ligne la plus haute / ligne
	 * d'arrivée
	 */
	public boolean isWinningPosition(Case c) {
		if (c.ord == game.height - 1)
			return true;
		return false;
	}

	/* Itere à travers toutes les lignes pour les update */
	public void update() {
		Iterator<Lane> i = this.Lanes.iterator();
		while (i.hasNext()) {
			Lane lane = i.next();
			lane.update();
		}
	}
}