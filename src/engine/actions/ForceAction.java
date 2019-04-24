package engine.actions;

import engine.Engine;
import engine.api.*;
import engine.model.*;

import gui.models.*;

import java.awt.*;
import java.util.ArrayList;

public class ForceAction extends IPlayerAction {

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
        int indice = 2;
        boolean[][] tab = new boolean[Engine.BOARD_X][Engine.BOARD_Y];

        int x, y, posx, posy;
        posx = this.source.x;
        posy = this.source.y;

        for (int f = 0; f < tab.length; f++) {
            for (int i = 0; i < tab[0].length; i++) {
                if (information.getTeam(this.source.x, this.source.y) == 1
                        && information.cells[this.source.x - 1][this.source.y].hasPlayer) {
                    x = f - posx;
                    if (x < 0)
                        x = -x;
                    y = i - posy;
                    if (y < 0)
                        y = -y;
                    if (x + y <= indice && f < posx) {
                        if (f == posx - 1 && i == posy) {

                        } else {
                            res.add(new VisualArea(f, i, Color.WHITE));
                        }
                    }
                }
                if (information.getTeam(this.source.x, this.source.y) == 1
                        && information.cells[this.source.x + 1][this.source.y].hasPlayer) {
                    x = f - posx;
                    if (x < 0)
                        x = -x;
                    y = i - posy;
                    if (y < 0)
                        y = -y;
                    if (x + y <= indice && f > posx) {
                        if (f == posx + 1 && i == posy) {

                        } else {
                            res.add(new VisualArea(f, i, Color.WHITE));
                        }

                    }
                }
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
        int indice = information.cells[this.source.x][this.source.y].playerMovesRemaining;
        boolean[][] tab = new boolean[Engine.BOARD_X][Engine.BOARD_Y];

        int x, y, posx, posy;
        posx = this.source.x;
        posy = this.source.y;

        for (int f = 0; f < tab.length; f++) {
            for (int i = 0; i < tab[0].length; i++) {
                if (information.getTeam(this.source.x, this.source.y) == 1) {
                    x = f - posx;
                    if (x < 0)
                        x = -x;
                    y = i - posy;
                    if (y < 0)
                        y = -y;
                    if (x + y <= indice && f < posx) {
                        if (f == posx - 1 && i == posy) {

                        } else {
                            res.add(new Point(f, i));
                        }
                    }
                } else {
                    x = f - posx;
                    if (x < 0)
                        x = -x;
                    y = i - posy;
                    if (y < 0)
                        y = -y;
                    if (x + y <= indice && f > posx) {
                        if (f == posx + 1 && i == posy) {

                        } else {
                            res.add(new Point(f, i));
                        }

                    }
                }
            }
        }

        if (res.size() == 0 || information.cells[this.source.x][this.source.y].playerMovesRemaining < 1)
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
            information.movePlayer(this.source, this.destination);
        } else {
            information.tackPlayer(this.source);
            if (information.getGameInformation().getTeam(this.source.x, this.source.y) == 1) {
                information.detachBall(this.source, new Point(this.source.x + 1, this.source.y));
            } else {
                information.detachBall(this.source, new Point(this.source.x - 1, this.source.y));
            }
        }
    }
}