package engine.actions;

import engine.Engine;
import engine.api.GameInformation;
import engine.api.IEngine;
import engine.api.IPlayerAction;
import engine.model.*;

import gui.models.*;

import java.awt.*;
import java.util.ArrayList;

public class ShootAction extends IPlayerAction {

    /**
     * Get visual areas
     * 
     * @param src         source action
     * @param information gamestate information
     * @return areas list
     */
    @Override
    public VisualArea[] getVisualArea(GameInformation information) {
        // System.out.println(information.getTeam(0, 0));
        int compt = 0;
        ArrayList<VisualArea> res = new ArrayList<VisualArea>();
        boolean[][] tab = new boolean[Engine.BOARD_X][Engine.BOARD_Y];
        if (information.getTeam(this.source.x, this.source.y) == 1) {
            for (int x = 0; x <= this.source.x; x++) {
                for (int y = 0; y < Engine.BOARD_Y; y++) {
                    if (information.getTeam(x, y) == information.getTeam(this.source.x, this.source.y)
                            && x < this.source.x && information.cells[x][y].hasPlayer == true) {
                        compt++;
                    }
                }
            }
            for (int f = this.source.x - 3; f < this.source.x; f++) {
                for (int i = this.source.y - 3; i < this.source.y + 4; i++) {
                    if (f < tab.length && f >= 0 && i < tab[0].length && i >= 0) {
                        if (information.cells[f][i].hasPlayer == false && compt == 0)
                            res.add(new VisualArea(f, i, Color.WHITE));
                    }

                }
            }

        } else {
            for (int x = this.source.x; x < Engine.BOARD_X; x++) {
                for (int y = 0; y < Engine.BOARD_Y; y++) {
                    if (information.getTeam(x, y) == information.getTeam(this.source.x, this.source.y)
                            && x > this.source.x && information.cells[x][y].hasPlayer == true) {

                        compt++;
                    }
                }
            }
            for (int f = this.source.x + 3; f > this.source.x; f--) {
                for (int i = this.source.y - 3; i < this.source.y + 4; i++) {
                    if (f < tab.length && f >= 0 && i < tab[0].length && i >= 0) {
                        if (information.cells[f][i].hasPlayer == false && compt == 0)
                            res.add(new VisualArea(f, i, Color.WHITE));
                    }

                }
            }

        }
        if (compt > 0 || res.size() == 0) {
            return null;
        }
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
        int compt = 0;
        ArrayList<Point> res = new ArrayList<Point>();
        boolean[][] tab = new boolean[Engine.BOARD_X][Engine.BOARD_Y];
        if (information.getTeam(this.source.x, this.source.y) == 1) {
            for (int x = 0; x <= this.source.x; x++) {
                for (int y = 0; y < Engine.BOARD_Y; y++) {
                    if (information.getTeam(x, y) == information.getTeam(this.source.x, this.source.y)
                            && x < this.source.x && information.cells[x][y].hasPlayer == true) {
                        compt++;
                    }
                }
            }
            for (int f = this.source.x - 3; f < this.source.x; f++) {
                for (int i = this.source.y - 3; i < this.source.y + 4; i++) {
                    if (f < tab.length && f >= 0 && i < tab[0].length && i >= 0) {
                        if (information.cells[f][i].hasPlayer == false && compt == 0)
                            res.add(new Point(f, i));
                    }

                }
            }

        } else {
            for (int x = this.source.x; x < Engine.BOARD_X; x++) {
                for (int y = 0; y < Engine.BOARD_Y; y++) {
                    if (information.getTeam(x, y) == information.getTeam(this.source.x, this.source.y)
                            && x > this.source.x && information.cells[x][y].hasPlayer == true) {

                        compt++;
                    }
                }
            }
            for (int f = this.source.x + 3; f > this.source.x; f--) {
                for (int i = this.source.y - 3; i < this.source.y + 4; i++) {
                    if (f < tab.length && f >= 0 && i < tab[0].length && i >= 0) {
                        if (information.cells[f][i].hasPlayer == false && compt == 0)
                            res.add(new Point(f, i));
                    }

                }
            }

        }

        if (compt > 0 || res.size() == 0) {
            return null;
        }

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
        information.detachBall(this.source, this.destination);
    }

    /**
     * 
     * 
     * Perform actio r card dual
     * 
     * @param inform tion gamestate
     * @param engine engine
     * @param winner actor that win the card duel
     */
    @Override
    public void perform(GameState information, IEngine engine, boolean win) {

    }
}