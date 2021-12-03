package environment;

import java.util.ArrayList;
import java.util.Iterator;
import util.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

public class Environment implements IEnvironment {
	private Game game;
	private ArrayList<Lane> Lanes = new ArrayList<Lane>();;
	private ArrayList<Wouter> Wouters = new ArrayList<Wouter>();;

	/* Prend en paramètre l'instance de game.
	* Les 3 premières lignes sont initialisées comme vides afin de mourrir dès le début
	* ensuite game game.height - 3 lignes sont initialisées avec une densité aléatoire
	* le jeu est update game.width * 5 fois afin que les voitures soient déjà disposées aléatoirement*/
	public Environment(Game game){
		this.game = game;
		this.Lanes.add(new Lane(game, 0, 0));
		this.Lanes.add(new Lane(game, 1, 0));
		this.Lanes.add(new Lane(game, 2, 0));
		for (int i = 2; i < game.height; i++){
			if (game.randomGen.nextInt(100) < 25) {
				this.Wouters.add(new Wouter(game, i));
			} else {
				this.Lanes.add(new Lane(game, i));
			}
		}
		for (int i = 1; i < game.width * 5; i++){ update(); }
	}

	/* Prend en paramètre une case c
	* la grenouille ne change virtuellement pas de place et reste à 2 d'ordonné
	* on vérifie donc seulement pour la deuxième ligne */
	public boolean isSafe(Case c){
		return this.Lanes.get(1).isSafe(c); /* && this.Wouters.get(2).isSafe(c); */
	}

	/* Prend en paramètre un boolean forward
	* si forward == true, la fonction ajoutera une ligne en fin d'array et supprimera la première de l'array
	* si forward == false, la fonction ajoutera une ligne en première place de l'array et supprimera celle en fin d'array */
	public void addLane(boolean forward){
		if (forward) {
			this.Lanes.remove(0);
			Lane l = new Lane(game, (this.Lanes.get(this.Lanes.size() - 1).getOrd()) + 1);
			for(int i = 1; i < game.width * 5; i++){ l.update(); }
			this.Lanes.add(l);
		} else {
			this.Lanes.remove(this.Lanes.size() - 1);
			Lane l = new Lane(game, (this.Lanes.get(0).getOrd()) - 1);
			for(int i = 1; i < game.width * 5; i++){ l.update(); }
			this.Lanes.add(0, l);
		}
	}
	
	/* Itere à travers toutes les lignes pour les update */
	public void update(){
		Iterator<Lane> i = this.Lanes.iterator();
    	while (i.hasNext()){
			i.next().update();
		}
		Iterator<Wouter> j = this.Wouters.iterator();
    	while (j.hasNext()){
			j.next().update();
		}
	}


	/* Prend en paramètre une case c
	* teste si l'ordonnée de la case c correspond à la ligne la plus haute
	* NOT USED ANYMORE */
	public boolean isWinningPosition(Case c){
		return (c.ord == game.height - 1 ? true : false);
	}
}