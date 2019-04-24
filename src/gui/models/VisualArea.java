package gui.models;

import java.awt.*;

import gui.layers.TerrainLayer;

public class VisualArea{

    /**
    *Statement of the variable position who define a position(X,Y)
    */
    private Point position;

    /**
    *Statement of a color
    */
    private Color color;

    /**
    *Constructor of the class VisualArea with a position and a color in arguments
    */
    public VisualArea(Point position, Color color){
        this.position = position;
        this.color = new Color(color.getRed(), color.getGreen(), color.getBlue(), 100);
    }

    /**
    *Constructor of the class VisualArea with an x, an y and a color in arguments
    */
    public VisualArea(int x, int y, Color color){
        this.position = new Point(x, y);
        this.color = color;
    }

    /**
    *Getter of Point who return a Point 
    */
    public Point getPosition(){
        return new Point(
            this.position.x * TerrainLayer.CELL_SIZE.width,
            this.position.y * TerrainLayer.CELL_SIZE.height
        );
    }

    /**
    *Getter of Color who return a color
    */
    public Color getColor(){
        return this.color;
    }
}