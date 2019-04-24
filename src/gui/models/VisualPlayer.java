package gui.models;

import java.awt.image.BufferedImage;
import java.awt.*;

import gui.layers.TerrainLayer;

public class VisualPlayer{

    /**
    *Statement and initialization of the player dimension(X,Y)
    */
    public static Dimension PLAYER_DIMENSION = new Dimension(
        (int)((double)TerrainLayer.CELL_SIZE.width * (90.0 / 100.0)),
        (int)((double)TerrainLayer.CELL_SIZE.height * (90.0 / 100.0))
    );

    /**
    *Statement of an image
    */
    private BufferedImage image;

    /**
    *Statement of an tacklImage
    */
    private BufferedImage tacklImage;

    /**
    *Statement of the variable position who define the actual position
    */
    private Point position;

    /**
    *Statement of the nextposition who define the next position
    */
    private Point nextPosition;

    /**
    *Statement of the variable isTackled
    */
    private boolean isTackled;

    /**
    *Constructor of the class VisualPlayer 
    */
    public VisualPlayer(
        BufferedImage image,
        BufferedImage tacklImage,
        Point position,
        boolean isTackled
    ){
        this.image = image;
        this.tacklImage = tacklImage;
        this.position = position;
        this.nextPosition = position;
        this.isTackled = isTackled;
    }

    /**
    *Getter of the image variable who return the image
    */
    public BufferedImage getImage(){
        return this.image;
    }

    /**
    *Getter of the tacklImage variable who return the tacklImage
    */
    public BufferedImage getTacklImage(){
        return this.tacklImage;
    }

    /**
    *Getter of position who return a position 
    */
    public Point getPosition(){
        return this.position;
    }

    //T(s) = A + s*(B-A)
    /**
    *Getter of postion with a double in argument who return a Point
    */
    public Point getPosition(double alpha){

        Point A = new Point(
            (this.position.x * TerrainLayer.CELL_SIZE.width) + (TerrainLayer.CELL_SIZE.width / 2) - (PLAYER_DIMENSION.width / 2),
            (this.position.y * TerrainLayer.CELL_SIZE.height) + (TerrainLayer.CELL_SIZE.height / 2) - (PLAYER_DIMENSION.height / 2)
        );
        Point B = new Point(
            (this.nextPosition.x * TerrainLayer.CELL_SIZE.width) + (TerrainLayer.CELL_SIZE.width / 2) - (PLAYER_DIMENSION.width / 2),
            (this.nextPosition.y * TerrainLayer.CELL_SIZE.height) + (TerrainLayer.CELL_SIZE.height / 2) - (PLAYER_DIMENSION.height / 2)
        );

        //Cosine interpolation
        double alpha2 = (1.0 - Math.cos(alpha * Math.PI)) / 2.0;

        return new Point(
            A.x + (int)(alpha2 * (double)(B.x - A.x)),
            A.y + (int)(alpha2 * (double)(B.y - A.y))
        );
    }
    public Point getNextPosition(){
        return this.nextPosition;
    }
    public void setPosition(Point position){
        this.position = position;
    }
    public void setNextPosition(Point position){
        this.nextPosition = position;
    }

    public void setTackled(boolean toggle){
        this.isTackled = toggle;
    }
    public boolean isTackled(){
        return this.isTackled;
    }
}