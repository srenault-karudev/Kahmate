package gui.layers;

import gui.*;
import engine.*;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.image.BufferedImage;
import java.awt.*;

public class TerrainLayer extends Layer{
    /**
    *  Define the dimension of the field.
    */
    public static Dimension TERRAIN_TEXTURE_DIMENSION = new Dimension(1107, 680);
    
    public static Dimension TERRAIN_CELL_DIMENSION = new Dimension(Engine.BOARD_X, Engine.BOARD_Y);

    public static Dimension CELL_SIZE = new Dimension(
        TERRAIN_TEXTURE_DIMENSION.width / TERRAIN_CELL_DIMENSION.width,
        TERRAIN_TEXTURE_DIMENSION.height / TERRAIN_CELL_DIMENSION.height
    );

    /**
    * image of the playground.
    */
    private BufferedImage image;

    public TerrainLayer(){
        super();

        this.image = Images.get(Images.TERRAIN);

        this.virtualHeight = TERRAIN_TEXTURE_DIMENSION.height;
        this.virtualWidth = TERRAIN_TEXTURE_DIMENSION.width;
    }
    /**
    * return the  height of the field.
    * @return : hieght of field.
    */
    public int getTerrainHeight(){
        return this.virtualHeight;
    }
    /**
    *return the width of the field.
    * @return : width of field.
    */
        public int getTerrainWidth(){
        return this.virtualWidth;
    }
    /**
    * draws the different components of the game.
    */

    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        Graphics2D g2d = (Graphics2D)graphics.create();

        //Draw image
        Rectangle draw = this.rectangleSpaceToViewSpace(new Rectangle(
            0, 0,
            this.image.getWidth(), this.image.getHeight()
        ));
        g2d.drawImage(this.image, (int)draw.getX(), (int)draw.getY(), (int)draw.getWidth(), (int)draw.getHeight(), Color.WHITE, this);

        //Draw background terrain
        draw = this.rectangleSpaceToViewSpace(new Rectangle(
            TerrainLayer.CELL_SIZE.width, 0,
            TerrainLayer.TERRAIN_TEXTURE_DIMENSION.width - (TerrainLayer.CELL_SIZE.width * 2), TerrainLayer.TERRAIN_TEXTURE_DIMENSION.height
        ));

        g2d.setColor(Color.decode("#4c942e"));
        g2d.fillRect((int)draw.getX(), (int)draw.getY(), (int)draw.getWidth(), (int)draw.getHeight());

        //Draw remaining cells
        g2d.setColor(Color.decode("#036f31"));
        for(int x = 1; x < TerrainLayer.TERRAIN_CELL_DIMENSION.width - 1; x++){
            for(int y = 0; y < TerrainLayer.TERRAIN_CELL_DIMENSION.height; y++){
                if((x + y) % 2 == 1){
                    draw = this.rectangleSpaceToViewSpace(new Rectangle(
                        x * TerrainLayer.CELL_SIZE.width, y * TerrainLayer.CELL_SIZE.height,
                        TerrainLayer.CELL_SIZE.width, TerrainLayer.CELL_SIZE.height
                    ));

                    g2d.fillRect((int)draw.getX(), (int)draw.getY(), (int)draw.getWidth(), (int)draw.getHeight());
                }
            }
        }

        //Draw lines
        g2d.setColor(Color.WHITE);
        draw = this.rectangleSpaceToViewSpace(new Rectangle(
            TerrainLayer.CELL_SIZE.width * 6, 0,
            5, TerrainLayer.TERRAIN_TEXTURE_DIMENSION.height
        ));
        g2d.fillRect((int)draw.getX(), (int)draw.getY(), (int)draw.getWidth(), (int)draw.getHeight());
        draw = this.rectangleSpaceToViewSpace(new Rectangle(
            TerrainLayer.CELL_SIZE.width * 7, 0,
            5, TerrainLayer.TERRAIN_TEXTURE_DIMENSION.height
        ));
        g2d.fillRect((int)draw.getX(), (int)draw.getY(), (int)draw.getWidth(), (int)draw.getHeight());
    }
}