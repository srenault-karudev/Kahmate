package engine.api;

import java.awt.*;

import engine.model.*;
import gui.models.*;

public abstract class IPlayerAction{

    protected Point source = null;
    protected Point destination = null;

    /**
     * Set source
     * @param src source
     */
    public void setSource(Point src){
        this.source = src;
    }
    /**
     * Set destination
     * @param dst destination
     */
    public void setDestination(Point dst){
        this.destination = dst;
    }


    /**
     * Get visual areas
     * @param src source action
     * @param information gamestate information
     * @return areas list
     */
    public abstract VisualArea[] getVisualArea(GameInformation information);

    /**
     * Get clickable areas
     * @param src source action
     * @param information gamestate information
     * @return areas list
     */
    public abstract Point[] getClickableArea(GameInformation information);

    /**
     * Perform action
     * @param information gamestate
     * @param engine engine
     */
    public abstract void perform(GameState information, IEngine engine);

    /**
     * Perform action after card dual
     * @param information gamestate
     * @param engine engine
     * @param winner actor that win the card duel
     */
    public abstract void perform(GameState information, IEngine engine, boolean win);
}
