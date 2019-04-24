package gui.api;

import gui.api.*;
import gui.controllers.*;
import gui.layers.*;
import gui.models.*;
import engine.api.*;
import engine.*;

import javax.swing.*;
import java.awt.*;

public class NullGUI implements IGUI{

    /**
     * Create new player entity associated to
     * a graphical resource
     * @param position : initial player position
     * @param image : main image
     * @param tackled : tackled image
     * @return : player handler
     */
    public int createPlayer(Point position, String image, String tackled){
        return 0;
    }
    /**
     * Control if the player is turned or not
     * 
     * @param id     : player entity id
     * @param toggle : turned or not
     */
    public void setTacklPlayer(int id, boolean toggle){}
    /**
     * Move graphical player
     * 
     * @param id       : player id
     * @param position : new player position
     */
    public void movePlayer(int id, Point position){}

    /**
     * Change selection area
     * @param areas : areas list
     */
    public void setArea(VisualArea[] areas){}

    /**
     * Create visual ball object
     * @param position initial ball position
     * @return ball id
     */
    public int createBall(Point position){return 0;}
    /**
     * Move ball graphical entity
     * 
     * @param position : new ball position
     * @param id : ball id
     */
    public void moveBall(int id, Point position){}
    /**
     * Attach ball to a player. When the player move, the ball move also.
     * 
     * @param id : ball id
     * @param player : player entity id
     */
    public void attachBall(int id, int player){}
    /**
     * Detach ball from player
     * @param id : ball id
     */
    public void detachBall(int id){}

    /**
     * Control if the card has already been use
     * 
     * @param card   : card number
     * @param toggle : use or not
     */
    public void toggleCard(int card, boolean toggle){}
    /**
     * Display card deck or not
     * 
     * @param toggle : control
     */
    public void toggleCardDeck(boolean toggle){}

    /**
     * Show black screen or not
     * 
     * @param toggle  : control
     * @param actor : active actor (eg: "player 1")
     */
    public void toggleBlackScreen(boolean toggle, int actor){}

    /**
     * Show victory screen
     * @param team team id
     */
    public void showVictory(int team){}
}