package gui.layers;

import javax.swing.JPanel;

import gui.Images;
import gui.models.*;

import javax.imageio.ImageIO;
import java.io.*;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.awt.Color;
import java.util.*;

public class BallLayer extends Layer {

    private BufferedImage image;
    private ArrayList<VisualBall> balls;

    public BallLayer(ArrayList<VisualBall> balls) {
        super();

        this.image = Images.get(Images.BALL);
        this.balls = balls;

        this.virtualHeight = TerrainLayer.TERRAIN_TEXTURE_DIMENSION.height;
        this.virtualWidth = TerrainLayer.TERRAIN_TEXTURE_DIMENSION.width;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D g2d = (Graphics2D) graphics.create();

        for (VisualBall ball : this.balls) {
            Rectangle draw = this.rectangleSpaceToViewSpace(new Rectangle(ball.getPosition(this.alpha).x,
                    ball.getPosition(this.alpha).y, VisualBall.BALL_DIMENSION.width, VisualBall.BALL_DIMENSION.height));

            // g2d.rotate(alpha * Math.PI * 2.0, this.getWidth() / 2, this.getHeight() / 2);

            g2d.drawImage(this.image, (int) draw.getX(), (int) draw.getY(), (int) draw.getWidth(),
                    (int) draw.getHeight(), null, this);
        }
    }
}