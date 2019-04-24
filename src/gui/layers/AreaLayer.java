package gui.layers;

import javax.swing.JPanel;

import gui.*;
import gui.models.*;

import javax.imageio.ImageIO;
import java.io.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.util.*;

public class AreaLayer extends Layer{

    private VisualArea[] areas;
    private Point selection;

    public AreaLayer(){
        super();

        this.virtualHeight = TerrainLayer.TERRAIN_TEXTURE_DIMENSION.height;
        this.virtualWidth = TerrainLayer.TERRAIN_TEXTURE_DIMENSION.width;
    }

    public void setAreas(VisualArea[] areas){
        this.areas = areas;
        this.repaint();
    }

    public void setSelectionArea(Point position){
        this.selection = position;
    }
    public Point getSelectionArea(){
        return this.selection;
    }

    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        Graphics2D g2d = (Graphics2D)graphics.create();

        if(this.areas != null){
            for(VisualArea area : this.areas){

                g2d.setColor(new Color(
                    area.getColor().getRed(),
                    area.getColor().getGreen(),
                    area.getColor().getBlue(),
                    100));

                Rectangle draw = this.rectangleSpaceToViewSpace(new Rectangle(
                    area.getPosition().x, area.getPosition().y,
                    TerrainLayer.CELL_SIZE.width, TerrainLayer.CELL_SIZE.height
                ));

                g2d.fillRect(
                    (int)draw.getX(), (int)draw.getY(), 
                    (int)draw.getWidth(), (int)draw.getHeight()
                );
            }
        }

        if(this.selection != null){
            g2d.setColor(new Color(1.0f, 1.0f, 1.0f, 0.5f));

            Rectangle draw = this.rectangleSpaceToViewSpace(new Rectangle(
                selection.x * TerrainLayer.CELL_SIZE.width, selection.y * TerrainLayer.CELL_SIZE.height,
                TerrainLayer.CELL_SIZE.width, TerrainLayer.CELL_SIZE.height
            ));

            g2d.fillRect(
                (int)draw.getX(), (int)draw.getY(), 
                (int)draw.getWidth(), (int)draw.getHeight()
            );
        }
    }
}