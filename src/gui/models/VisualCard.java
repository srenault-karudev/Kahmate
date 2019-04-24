package gui.models;

import java.awt.image.*;
import java.awt.color.*;
import java.awt.*;

public class VisualCard{

    /**
    *Statement and initialization of the dimension card   
    */
    public static Dimension CARD_DIMENSION = new Dimension(170, 233);

    /**
    *Statement and initialization of the number of deck
    */
    public static int DECK_COUNT = 5;

    /**
    *Statement and initialization of the marge in X   
    */
    public static int MARGIN_X = 20;
    

    /**
    *Statement and initialization of the marge in Y   
    */
    public static int MARGIN_Y = 50;

    /**
    *Statement and initialization the variable active   
    */
    private boolean active = true;

    /**
    *Statement of an image 
    */
    private BufferedImage image;

    /**
    *Statement of an grayImage 
    */    
    private BufferedImage grayImage;

    /**
    *Constructor of the class VisualCard with an image in argument
    */
    public VisualCard(BufferedImage image){
        this.image = image;

        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
        ColorConvertOp op = new ColorConvertOp(cs, null);

        this.grayImage = new BufferedImage(this.image.getWidth(), this.image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        this.grayImage = op.filter(this.image, null);
    }

    /**
    *Method who set a the active variable to the toggle argument past in argument
    */
    public void setActive(boolean toggle){
        this.active = toggle;
    }

    /**
    *Getter of the active variable who return a boolean
    */
    public boolean isActive(){
        return this.active;
    }

    /**
    *Getter of the image variable who return an image
    */
    public BufferedImage getImage(){
        return this.image;
    }

    /**
    *Getter of the GrayImage variable who return an grayImage
    */
    public BufferedImage getGrayImage(){
        return this.grayImage;
    }
}