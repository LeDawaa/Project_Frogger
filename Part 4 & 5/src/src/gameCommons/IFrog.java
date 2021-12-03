package gameCommons;

import util.Case;
import util.Direction;
import java.awt.image.BufferedImage;

public interface IFrog {
	
	/**
	 * Donne la position actuelle de la grenouille
	 * @return
	 */
	public Case getPosition();
	
	/**
	 * Donne la direction de la grenouille, c'est à dire de son dernier mouvement 
	 * @return
	 */
	public Direction getDirection();
	
	/**
	 * Donne le sprite de la grenouille
	 * @return
	 */
	public BufferedImage getSprite();

	/**
	 * Déplace la grenouille dans la direction donnée
	 * @param key
	 */
	public void move(Direction key);
}
