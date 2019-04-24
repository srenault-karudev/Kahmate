package gui.layers;

import gui.Images;

import javax.imageio.ImageIO;
import java.io.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.util.*;

/**
 * ButtonLayer
 */
public class ButtonLayer extends Layer{

    public static final int ACTIONS_SIZE_X = 240;
    public static final int ACTIONS_SIZE_Y = 60;

    private BufferedImage actions1Image;
    private BufferedImage actions2Image;
    private BufferedImage cardsImage;
    private BufferedImage nextImage;

    private Point actionsPosition = null;
    private int actionsMode = 0;

    private boolean isActionsShowed = false;
    private boolean isButtonsShowed = true;

    public ButtonLayer(){
        super();

        this.actions1Image = Images.get(Images.BUTTON_ACTIONS_1);
        this.actions2Image = Images.get(Images.BUTTON_ACTIONS_2);
        this.cardsImage = Images.get(Images.BUTTON_CARDS);
        this.nextImage = Images.get(Images.BUTTON_NEXT);

        this.virtualHeight = TerrainLayer.TERRAIN_TEXTURE_DIMENSION.height;
        this.virtualWidth = TerrainLayer.TERRAIN_TEXTURE_DIMENSION.width;

        this.actionsPosition = new Point(3, 3);
    }

    public void setActions(Point point, int mode){
        this.actionsPosition = point;
        this.actionsMode = mode;
        this.repaint();
    }
    public Point getActionsPosition(){
        return this.actionsPosition;
    }
    public int getActionsMode(){
        return this.actionsMode;
    }

    public void showButtons(boolean toggle){
        this.isButtonsShowed = toggle;
        this.repaint();
    }
    public boolean isButtonsShowed(){
        return this.isButtonsShowed;
    }
    public void showActions(boolean toggle){
        this.isActionsShowed = toggle;
        this.repaint();
    }
    public boolean isActionsShowed(){
        return this.isActionsShowed;
    }

    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        Graphics2D g2d = (Graphics2D)graphics.create();

        if(this.isButtonsShowed){

            //Draw cards button
            Rectangle draw = this.rectangleSpaceToViewSpace(new Rectangle(
                10, this.virtualHeight - TerrainLayer.CELL_SIZE.height,
                TerrainLayer.CELL_SIZE.width - 20, TerrainLayer.CELL_SIZE.height - 20
            ));

            g2d.drawImage(
                this.cardsImage, 
                (int)draw.getX(), (int)draw.getY(), 
                (int)draw.getWidth(), (int)draw.getHeight(), 
                null, this
            );

            //Draw next button
            draw = this.rectangleSpaceToViewSpace(new Rectangle(
                this.virtualWidth - TerrainLayer.CELL_SIZE.width + 10, this.virtualHeight - TerrainLayer.CELL_SIZE.height,
                TerrainLayer.CELL_SIZE.width - 20, TerrainLayer.CELL_SIZE.height - 20
            ));

            g2d.drawImage(
                this.nextImage, 
                (int)draw.getX(), (int)draw.getY(), 
                (int)draw.getWidth(), (int)draw.getHeight(), 
                null, this
            );

        }    
        //Draw actions
        if(this.actionsPosition != null && this.isActionsShowed){

            Rectangle draw = this.rectangleSpaceToViewSpace(new Rectangle(
                (this.actionsPosition.x * TerrainLayer.CELL_SIZE.width) - (ACTIONS_SIZE_X / 2) + (TerrainLayer.CELL_SIZE.width / 2), 
                (this.actionsPosition.y * TerrainLayer.CELL_SIZE.height) - (ACTIONS_SIZE_Y / 2) + (TerrainLayer.CELL_SIZE.height / 2),
                ACTIONS_SIZE_X, ACTIONS_SIZE_Y
            ));

            if(this.actionsMode == 0){
                g2d.drawImage(
                    this.actions1Image, 
                    (int)draw.getX(), (int)draw.getY(), 
                    (int)draw.getWidth(), (int)draw.getHeight(), 
                    null, this
                );
            } else if(this.actionsMode == 1){
                g2d.drawImage(
                    this.actions2Image,
                    (int)draw.getX(), (int)draw.getY(), 
                    (int)draw.getWidth(), (int)draw.getHeight(), 
                    null, this
                );
            }
        }
    }
}