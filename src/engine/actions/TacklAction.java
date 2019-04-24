package engine.actions;

import engine.Engine;
import engine.api.GameInformation;
import engine.api.IEngine;
import engine.api.IPlayerAction;
import engine.model.*;

import gui.models.*;

import java.awt.*;
import java.util.ArrayList;

/**
 * TacklAction
 */
public class TacklAction extends IPlayerAction {

    /**
     * Get visual areas
     * 
     * @param src         source action
     * @param information gamestate information
     * @return areas list
     */
    @Override
    public VisualArea[] getVisualArea(GameInformation information) {
        ArrayList<VisualArea> res = new ArrayList<VisualArea>();
        if (this.source.x - 1 > 0) {

            if (information.getTeam(this.source.x - 1, this.source.y) != information.getTeam(this.source.x,
                    this.source.y) && information.cells[this.source.x - 1][this.source.y].hasPlayer
                    && information.cells[this.source.x - 1][this.source.y].hasBall) {
                res.add(new VisualArea(this.source.x - 1, this.source.y, Color.WHITE));
            }

        }
        if (this.source.x + 1 < Engine.BOARD_X) {
            if (information.getTeam(this.source.x + 1, this.source.y) != information.getTeam(this.source.x,
                    this.source.y) && information.cells[this.source.x + 1][this.source.y].hasPlayer
                    && information.cells[this.source.x + 1][this.source.y].hasBall) {
                res.add(new VisualArea(this.source.x + 1, this.source.y, Color.WHITE));
            }

        }
        if (this.source.y - 1 > 0) {
            if (information.getTeam(this.source.x, this.source.y - 1) != information.getTeam(this.source.x,
                    this.source.y) && information.cells[this.source.x][this.source.y - 1].hasPlayer
                    && information.cells[this.source.x][this.source.y - 1].hasBall) {
                res.add(new VisualArea(this.source.x, this.source.y - 1, Color.WHITE));
            }

        }
        if (this.source.y + 1 < Engine.BOARD_Y) {
            if (information.getTeam(this.source.x, this.source.y + 1) != information.getTeam(this.source.x,
                    this.source.y) && information.cells[this.source.x][this.source.y + 1].hasPlayer
                    && information.cells[this.source.x][this.source.y + 1].hasBall) {
                res.add(new VisualArea(this.source.x, this.source.y + 1, Color.WHITE));
            }

        }

        if (res.size() == 0)
            return null;
        VisualArea[] ret = new VisualArea[res.size()];
        for (int t = 0; t < ret.length; t++) {
            ret[t] = res.get(t);
        }
        return ret;
    }

    /**
     * Get clickable areas
     * 
     * @param src         source action
     * @param information gamestate information
     * @return areas list
     */
    @Override
    public Point[] getClickableArea(GameInformation information) {
        ArrayList<Point> res = new ArrayList<Point>();
        if (this.source.x - 1 > 0) {

            if (information.getTeam(this.source.x - 1, this.source.y) != information.getTeam(this.source.x,
                    this.source.y) && information.cells[this.source.x - 1][this.source.y].hasPlayer
                    && information.cells[this.source.x - 1][this.source.y].hasBall) {
                res.add(new Point(this.source.x - 1, this.source.y));
            }

        }
        if (this.source.x + 1 < Engine.BOARD_X) {
            if (information.getTeam(this.source.x + 1, this.source.y) != information.getTeam(this.source.x,
                    this.source.y) && information.cells[this.source.x + 1][this.source.y].hasPlayer
                    && information.cells[this.source.x + 1][this.source.y].hasBall) {
                res.add(new Point(this.source.x + 1, this.source.y));
            }

        }
        if (this.source.y - 1 > 0) {
            if (information.getTeam(this.source.x, this.source.y - 1) != information.getTeam(this.source.x,
                    this.source.y) && information.cells[this.source.x][this.source.y - 1].hasPlayer
                    && information.cells[this.source.x][this.source.y - 1].hasBall) {
                res.add(new Point(this.source.x, this.source.y - 1));
            }

        }
        if (this.source.y + 1 < Engine.BOARD_Y) {
            if (information.getTeam(this.source.x, this.source.y + 1) != information.getTeam(this.source.x,
                    this.source.y) && information.cells[this.source.x][this.source.y + 1].hasPlayer
                    && information.cells[this.source.x][this.source.y + 1].hasBall) {
                res.add(new Point(this.source.x, this.source.y + 1));
            }

        }

        if (res.size() == 0)
            return null;
        Point[] ret = new Point[res.size()];
        for (int t = 0; t < ret.length; t++) {
            ret[t] = res.get(t);
        }
        return ret;
    }

    /**
     * Perform action
     * 
     * @param information gamestate
     * @param engine      engine
     */
    @Override
    public void perform(GameState information, IEngine engine) {
        engine.askDuel(this);
    }

    /**
     * Perform action after card dual
     * 
     * @param information gamestate
     * @param engine      engine
     * @param winner      actor that win the card duel
     */
    @Override
    public void perform(GameState information, IEngine engine, boolean win) {
        if (win) {
            if (information.getGameInformation().getTeam(this.source.x, this.source.y) == 1) {
                information.detachBall(this.destination, new Point(this.destination.x - 1, this.destination.y));
                information.tackPlayer(this.destination);
            } else {
                information.detachBall(this.destination, new Point(this.destination.x + 1, this.destination.y));
                information.tackPlayer(this.destination);
            }
        } else {
            information.tackPlayer(this.source);
        }
    }
}