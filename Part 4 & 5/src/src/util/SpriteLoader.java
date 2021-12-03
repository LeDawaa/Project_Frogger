package util;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class SpriteLoader {
    public HashMap<String, BufferedImage> spritesFrog = new HashMap<String, BufferedImage>();
    public HashMap<String, BufferedImage> spritesLTR = new HashMap<String, BufferedImage>();
    public HashMap<String, BufferedImage> spritesRTL = new HashMap<String, BufferedImage>();
    public HashMap<String, BufferedImage> backgrounds = new HashMap<String, BufferedImage>();

    public SpriteLoader() {
        this.spritesFrog.put("FrogUp", LoadSprite("FrogUp"));
        this.spritesFrog.put("FrogDown", LoadSprite("FrogDown"));
        this.spritesFrog.put("FrogLeft", LoadSprite("FrogLeft"));
        this.spritesFrog.put("FrogRight", LoadSprite("FrogRight"));

        this.backgrounds.put("ItsATrap", LoadSprite("ItsATrap"));
        this.backgrounds.put("Star", LoadSprite("Star"));
        this.backgrounds.put("Safe", LoadSprite("safe"));

        this.spritesLTR.put("Car_1_LTR", LoadSprite("Car_1_LTR"));
        this.spritesLTR.put("Car_2_LTR", LoadSprite("Car_2_LTR"));
        this.spritesLTR.put("Car_3_LTR", LoadSprite("Car_3_LTR"));
        this.spritesLTR.put("Car_4_LTR", LoadSprite("Car_4_LTR"));
        this.spritesLTR.put("Truck_1_LTR", LoadSprite("Truck_1_LTR"));
        this.spritesLTR.put("Truck_2_LTR", LoadSprite("Truck_2_LTR"));

        this.spritesRTL.put("Car_1_RTL", LoadSprite("Car_1_RTL"));
        this.spritesRTL.put("Car_2_RTL", LoadSprite("Car_2_RTL"));
        this.spritesRTL.put("Car_3_RTL", LoadSprite("Car_3_RTL"));
        this.spritesRTL.put("Car_4_RTL", LoadSprite("Car_4_RTL"));
        this.spritesRTL.put("Truck_1_RTL", LoadSprite("Truck_1_RTL"));
        this.spritesRTL.put("Truck_2_RTL", LoadSprite("Truck_2_RTL"));
    }

    public static BufferedImage LoadSprite (String filename) {
        try{
            return ImageIO.read(new File("squelette1FRog/src/sprites/" + filename + ".png"));
        } catch (IOException io) {
            System.out.println(filename);
            throw new RuntimeException(io);
        }
    }
}