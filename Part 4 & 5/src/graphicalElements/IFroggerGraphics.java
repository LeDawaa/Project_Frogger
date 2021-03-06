package graphicalElements;

import gameCommons.IFrog;

import java.util.ArrayList;

public interface IFroggerGraphics {
	
	/**
	 * Ajoute l'�l�ment aux �l�ments � afficher
	 * @param e
	 */
    public void add(Element e);

    public void addBonus(Element e);

	public void addBackground(Element e);
    
    /**
     * Enl�ve tous les �l�ments actuellement affich�s
     */
    public void clear();
    
    /***
     * Actualise l'affichage
     */
    public void repaint();
    
    /**
     * Lie la grenouille � l'environneemnt graphique
     * @param frog
     */
    public void setFrog(IFrog frog);
    
    public ArrayList<Element> getElementToDisplay();

    public void setScore(int score);

	public void setTime(int time);

    /**
     * Lance un �cran de fin de partie
     * @param message le texte � afficher
     */
    public void endGameScreen(String s, String t, String u);
}
