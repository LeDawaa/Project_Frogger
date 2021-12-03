package gameCommons;

import util.Case;

public interface IEnvironment {

	/**
	 * Teste si une case est sure, c'est à dire que la grenouille peut s'y poser
	 * sans mourir
	 * 
	 * @param c
	 *            la case à tester
	 * @return vrai s'il n'y a pas danger
	 */
	public boolean isSafe(Case c);

	/**
	 * Effectue une étape d'actualisation de l'environnement
	 */
	public void update();

	public void addLane(boolean forward);
}
