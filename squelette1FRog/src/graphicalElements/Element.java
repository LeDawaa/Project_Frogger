package graphicalElements;

import util.Case;
import java.awt.image.BufferedImage;


public class Element extends Case {
    public final BufferedImage sprite;

    public Element(int absc, int ord, BufferedImage sprite) {
        super(absc, ord);
        this.sprite = sprite;
    }
    
    public Element(Case c, BufferedImage sprite) {
        super(c.absc, c.ord);
        this.sprite = sprite;
    }
}