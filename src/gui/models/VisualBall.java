package gui.models;

import gui.layers.TerrainLayer;
import gui.models.*;

import java.awt.*;

public class VisualBall{

    /**
    *Statement and initialization of the ball dimension
    */ 
    public static Dimension BALL_DIMENSION = new Dimension(45, 45);

    /**
    *Statement of the position who define the actual position
    */
    private Point position;

    /**
    *Statement of the nextposition who define the next position
    */
    private Point nextPosition;

    /**
    *Statement of the player 
    */
    private VisualPlayer player;

    /**
    *Constructor of the class VisualBall with a position and a player in arguments
    */
    public VisualBall(Point position, VisualPlayer player){
        this.position = position;
        this.nextPosition = this.position;
        this.player = player;
    }

    /**
    *Method who set a position to a player
    */
    public void attachTo(VisualPlayer player){
        if(player != null){
            this.player = player;
        } else{
            this.position = this.player.getPosition();
            this.player = null;
        }
    }

    /**
    *Setteur of position of a player actual position
    */
    public void setPosition(Point position){
        this.position = position;
    }

    /**
    *Setteur of position of a player next position
    */
    public void setNextPosition(Point position){
        this.nextPosition = position;
    }

    /**
    *Getter of position who return a position 
    */
    public Point getPosition(){
        return this.position;
    }

    /**
    *Getter of postion with a double in argument who return a Point
    */
    public Point getPosition(double alpha){

        if(this.player != null){
            return this.player.getPosition(alpha);
        } else{
            Point A = new Point(
                (this.position.x + 1) * TerrainLayer.CELL_SIZE.width - VisualBall.BALL_DIMENSION.width - 10, 
                (this.position.y + 1) * TerrainLayer.CELL_SIZE.height - VisualBall.BALL_DIMENSION.height - 10
            );
            Point B = new Point(
                (this.nextPosition.x + 1) * TerrainLayer.CELL_SIZE.width - VisualBall.BALL_DIMENSION.width - 10, 
                (this.nextPosition.y + 1) * TerrainLayer.CELL_SIZE.height - VisualBall.BALL_DIMENSION.height - 10
            );

            //Cosine interpolation
            double alpha2 = (1.0 - Math.cos(alpha * Math.PI)) / 2.0;

            return new Point(
                A.x + (int)(alpha2 * (double)(B.x - A.x)),
                A.y + (int)(alpha2 * (double)(B.y - A.y))
            );
        }        
    }

    /**
    *Getter of the next position who return a position 
    */
    public Point getNextPosition(){
        return this.nextPosition;
    }

    /**
    *Getter who return true if a player has a position
    */
    public boolean isAttached(){
        return this.player != null;
    }
}