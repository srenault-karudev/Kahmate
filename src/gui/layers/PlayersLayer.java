package gui.layers;

import javax.swing.JPanel;

import gui.Images;
import gui.models.VisualPlayer;

import javax.imageio.ImageIO;
import java.io.*;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.awt.Color;
import java.util.*;

public class PlayersLayer extends Layer{

    private ArrayList<VisualPlayer> players;

    public PlayersLayer(ArrayList<VisualPlayer> players){
        super();

        this.players = players;

        this.virtualHeight = TerrainLayer.TERRAIN_TEXTURE_DIMENSION.height;
        this.virtualWidth = TerrainLayer.TERRAIN_TEXTURE_DIMENSION.width;
    }

    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        Graphics2D g2d = (Graphics2D)graphics.create();

        for(VisualPlayer player : this.players){
            Rectangle draw = this.rectangleSpaceToViewSpace(new Rectangle(
                player.getPosition(this.alpha).x, player.getPosition(this.alpha).y,
                VisualPlayer.PLAYER_DIMENSION.width, VisualPlayer.PLAYER_DIMENSION.height
            ));

            if(player.isTackled()){
                g2d.drawImage(
                    player.getTacklImage(), 
                    (int)draw.getX(), (int)draw.getY(), 
                    (int)draw.getWidth(), (int)draw.getHeight(), 
                    null, this
                );
            } else{
                g2d.drawImage(
                    player.getImage(), 
                    (int)draw.getX(), (int)draw.getY(),
                    (int)draw.getWidth(), (int)draw.getHeight(), 
                    null, this
                );
            }
        }
    }
}