package gui.layers;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.awt.Color;

public class Layer extends JPanel{

    /**
     * Panel virtual space width (in pixel)
     */
    protected int virtualWidth;
    /**
     * Panel virtual space height (in pixel)
     */
    protected int virtualHeight;
    /**
     * Animation blending alpha
     */
    protected double alpha = 0.0;

    public Layer(){
        this.setOpaque(false);
    }

    /**
     * Change alpha interpolation
     * @param alpha value
     */
    public void setAlpha(double alpha){
        this.alpha = alpha;
    }

    /** 
      * Convert position and dimension from component space (image) to view space (controller) 
      * @param rect rectangle to convert
      * @return the converted rectangle 
    **/
    public Rectangle viewSpaceToRectangleSpace(Rectangle rect){
        double imgWidth = (double)this.virtualWidth;
        double imgHeight = (double)this.virtualHeight;

        double x, y, width, height;
        width = (imgWidth * (double)this.getHeight()) / imgHeight;
        if(width <= (double)this.getWidth()){
            x = ((double)this.getWidth() / 2) - (width / 2);
            y = 0;
            height = (double)this.getHeight();
        } else{
            x = 0;
            y = 0;
            width = (double)this.getWidth();
            height = (imgHeight * (double)this.getWidth()) / imgWidth;
        }

        double transformFactorX = width / imgWidth;
        double transformFactorY = height / imgHeight;

        return new Rectangle(
            (int)((rect.getX() - x) / transformFactorX),
            (int)((rect.getY() - y) / transformFactorY),
            (int)(rect.getWidth() / transformFactorX),
            (int)(rect.getHeight() / transformFactorY)
        );
    }

    /**
      * Convert position and dimension from view space (controller) to component space (image)
      * @param rect rectangle to convert
      * @return the converted rectangle
    **/
    public Rectangle rectangleSpaceToViewSpace(Rectangle rect){
        double imgWidth = (double)this.virtualWidth;
        double imgHeight = (double)this.virtualHeight;

        double x, y, width, height;
        width = (imgWidth * (double)this.getHeight()) / imgHeight;
        if(width <= (double)this.getWidth()){
            x = ((double)this.getWidth() / 2) - (width / 2);
            y = 0;
            height = (double)this.getHeight();
        } else{
            x = 0;
            y = 0;
            width = (double)this.getWidth();
            height = ((double)this.virtualHeight * (double)this.getWidth()) / (double)this.virtualWidth;
        }

        double transformFactorX = width / imgWidth;
        double transformFactorY = height / imgHeight;

        return new Rectangle(
            (int)((rect.getX() * transformFactorX) + x),
            (int)((rect.getY() * transformFactorY) + y),
            (int)(rect.getWidth() * transformFactorX),
            (int)(rect.getHeight() * transformFactorY)
        );
    }

    /**
     * Retrieve virtual width
     */
    public int getVirtualWidth(){
        return this.virtualWidth;
    }
    /**
     * Retrieve virtual height
     */
    public int getVirtualHeight(){
        return this.virtualHeight;
    }
}