package engine.model;

import java.awt.*;

import gui.api.IGUI;

public class Player{
    
    private int x;
    private int y;

    private int renderHandle;

    private IGUI gui;
    
    /**
    *Initialize team and initial position
    *team 0 = left, team 1 = right
    *posInit=Y-Position
    *lineInit= first line or second line (1 or 2)
    * @param t: team
    * @param posx : initial Y-Position player
    * @param posy : initial line player 
    */
    public Player(int posx, int posy, String image, String tackled, IGUI gui){

        this.x = posx;
        this.y = posy;

        this.gui = gui;

        this.renderHandle = gui.createPlayer(new Point(this.x, this.y), image, tackled);
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;

        gui.movePlayer(this.renderHandle, new Point(x, y));
    }
    public Point getPosition(){
        return new Point(this.x, this.y);
    }
    public void setTackled(boolean toggle){
        gui.setTacklPlayer(this.renderHandle, toggle);
    }

    public int getRenderHandle(){
        return this.renderHandle;
    }
}