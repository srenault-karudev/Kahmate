package gui.layers;

import javax.swing.*;
import java.awt.*;

import gui.Images;
import gui.models.*;

import javax.imageio.ImageIO;
import java.io.*;
import java.awt.image.BufferedImage;
import java.util.*;

public class BlackLayer extends Layer{

    private JLabel label;

    public BlackLayer(){
        super();

        this.virtualHeight = TerrainLayer.TERRAIN_TEXTURE_DIMENSION.height;
        this.virtualWidth = TerrainLayer.TERRAIN_TEXTURE_DIMENSION.width;

        this.setLayout(new GridBagLayout());

        this.label = new JLabel();
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.label.setVerticalAlignment(JLabel.CENTER);
        this.label.setForeground(Color.WHITE);
        this.add(label, new GridBagConstraints());
    }

    public void setMessage(String message, Color color){
        this.label.setText(message);
        this.label.setForeground(color);
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        Graphics2D g2d = (Graphics2D)graphics.create();

        Rectangle draw = this.rectangleSpaceToViewSpace(new Rectangle(
            0, 0,
            this.virtualWidth, this.virtualHeight
        ));

        g2d.setColor(Color.BLACK);

        g2d.fillRect((int)draw.getX(), (int)draw.getY(), 
            (int)draw.getWidth(), (int)draw.getHeight());
    }
}