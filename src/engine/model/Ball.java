package engine.model;

import java.awt.*;

import gui.api.*;

public class Ball {

    private int x;
    private int y;

    private IGUI gui;
    private int renderHandle;

    private Player player;

    /**
     * Initialize ball position
     */
    public Ball(int x, int y, IGUI gui) {
        this.x = x;
        this.y = y;

        this.gui = gui;

        this.renderHandle = gui.createBall(new Point(x, y));
    }

    public void setPosition(int x, int y){
        if(this.player == null){
            this.x = x;
            this.y = y;

            gui.moveBall(this.renderHandle, new Point(x, y));
        }
    }
    public Point getPosition(){

        if(this.player != null){
            return this.player.getPosition();
        } 

        return new Point(this.x, this.y);
    }

    public void attachTo(Player player){
        gui.attachBall(this.renderHandle, player.getRenderHandle());
    
        this.player = player;
    }
    public void detach(){
        gui.detachBall(this.renderHandle);

        this.player = null;
    }
    public boolean isAttached(){
        return this.player != null;
    }
}