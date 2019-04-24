package engine.actions;

import engine.Engine;
import engine.api.GameInformation;
import engine.api.IEngine;
import engine.api.IPlayerAction;
import engine.model.*;

import gui.models.*;

import java.awt.*;
import java.util.ArrayList;

public class PassAction extends IPlayerAction {
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
        boolean[][] tab = new boolean[Engine.BOARD_X][Engine.BOARD_Y];
        if (information.getTeam(this.source.x, this.source.y) == 1) {

            for (int f = this.source.x + 1; f < this.source.x + 3; f++) {
                for (int i = this.source.y - 2; i < this.source.y + 3; i++) {
                    if (f < tab.length && f >= 0 && i < tab[0].length && i >= 0) {
                        if (information.getTeam(f, i) != information.getTeam(this.source.x, this.source.y)
                                && information.cells[f][i].hasPlayer) {

                        } else {

                            res.add(new VisualArea(f, i, Color.WHITE));
                        }

                    }

                }
            }

            if (information.getTeam(this.source.x + 1, this.source.y + 1) != information.getTeam(this.source.x,
                    this.source.y) && information.cells[this.source.x + 1][this.source.y + 1].hasPlayer) {
                res.add(new VisualArea(this.source.x + 2, this.source.y + 1, Color.RED));
                res.add(new VisualArea(this.source.x + 2, this.source.y + 2, Color.RED));
                res.add(new VisualArea(this.source.x + 1, this.source.y + 2, Color.RED));
            }
            if (information.getTeam(this.source.x + 1, this.source.y) != information.getTeam(this.source.x,
                    this.source.y) && information.cells[this.source.x + 1][this.source.y].hasPlayer) {
                res.add(new VisualArea(this.source.x + 2, this.source.y, Color.RED));

            }
            if (information.getTeam(this.source.x + 1, this.source.y - 1) != information.getTeam(this.source.x,
                    this.source.y) && information.cells[this.source.x + 1][this.source.y - 1].hasPlayer) {
                res.add(new VisualArea(this.source.x + 2, this.source.y - 1, Color.RED));
                res.add(new VisualArea(this.source.x + 2, this.source.y - 2, Color.RED));
                res.add(new VisualArea(this.source.x + 1, this.source.y - 2, Color.RED));
            }

            if (information.getTeam(this.source.x + 1, this.source.y + 2) != information.getTeam(this.source.x,
                    this.source.y) && information.cells[this.source.x + 1][this.source.y + 2].hasPlayer) {
                res.add(new VisualArea(this.source.x + 2, this.source.y + 2, Color.RED));

            }
            if (information.getTeam(this.source.x + 1, this.source.y - 2) != information.getTeam(this.source.x,
                    this.source.y) && information.cells[this.source.x + 1][this.source.y - 2].hasPlayer) {
                res.add(new VisualArea(this.source.x + 2, this.source.y - 2, Color.RED));

            }

        } else {

            for (int f = this.source.x - 1; f > this.source.x - 3; f--) {
                for (int i = this.source.y - 2; i < this.source.y + 3; i++) {
                    if (i < tab.length && i >= 0 && f < tab[0].length && f >= 0) {
                        if (information.getTeam(f, i) != information.getTeam(this.source.x, this.source.y)
                                && information.cells[f][i].hasPlayer) {

                        } else {

                            res.add(new VisualArea(f, i, Color.WHITE));
                        }
                    }

                }
            }

            if (information.getTeam(this.source.x - 1, this.source.y + 1) != information.getTeam(this.source.x,
                    this.source.y) && information.cells[this.source.x - 1][this.source.y + 1].hasPlayer) {
                res.add(new VisualArea(this.source.x - 2, this.source.y + 1, Color.RED));
                res.add(new VisualArea(this.source.x - 2, this.source.y + 2, Color.RED));
                res.add(new VisualArea(this.source.x - 1, this.source.y + 2, Color.RED));
            }
            if (information.getTeam(this.source.x - 1, this.source.y) != information.getTeam(this.source.x,
                    this.source.y) && information.cells[this.source.x - 1][this.source.y].hasPlayer) {
                res.add(new VisualArea(this.source.x - 2, this.source.y, Color.RED));

            }
            if (information.getTeam(this.source.x - 1, this.source.y - 1) != information.getTeam(this.source.x,
                    this.source.y) && information.cells[this.source.x - 1][this.source.y - 1].hasPlayer) {
                res.add(new VisualArea(this.source.x - 2, this.source.y - 1, Color.RED));
                res.add(new VisualArea(this.source.x - 2, this.source.y - 2, Color.RED));
                res.add(new VisualArea(this.source.x - 1, this.source.y - 2, Color.RED));
            }

            if (information.getTeam(this.source.x - 1, this.source.y + 2) != information.getTeam(this.source.x,
                    this.source.y) && information.cells[this.source.x - 1][this.source.y + 2].hasPlayer) {
                res.add(new VisualArea(this.source.x - 2, this.source.y + 2, Color.RED));

            }
            if (information.getTeam(this.source.x - 1, this.source.y - 2) != information.getTeam(this.source.x,
                    this.source.y) && information.cells[this.source.x - 1][this.source.y - 2].hasPlayer) {
                res.add(new VisualArea(this.source.x - 2, this.source.y - 2, Color.RED));

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
        boolean[][] tab = new boolean[Engine.BOARD_X][Engine.BOARD_Y];
        if (information.getTeam(this.source.x, this.source.y) == 1) {

            for (int f = this.source.x + 1; f < this.source.x + 3; f++) {
                for (int i = this.source.y - 2; i < this.source.y + 3; i++) {
                    if (f < tab.length && f >= 0 && i < tab[0].length && i >= 0) {
                        if (information.getTeam(f, i) != information.getTeam(this.source.x, this.source.y)
                                && information.cells[f][i].hasPlayer) {

                        } else
                            res.add(new Point(f, i));

                    }

                }
            }

        } else {

            for (int f = this.source.x - 1; f > this.source.x - 3; f--) {
                for (int i = this.source.y - 2; i < this.source.y + 3; i++) {
                    if (f < tab.length && f >= 0 && f < tab[0].length && f >= 0) {
                        if (information.getTeam(f, i) != information.getTeam(this.source.x, this.source.y)
                                && information.cells[f][i].hasPlayer) {

                        } else
                            res.add(new Point(f, i));

                    }

                }
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
        information.detachBall(this.source, this.destination);
        /*
         * if (information.getGameInformation().cells[this.source.x][this.source.y].
         * hasPlayer && information.getGameInformation().getTeam(this.source.x,
         * this.source.y) == 0) {
         * 
         * }
         * 
         * if (information.getGameInformation().cells[this.source.x][this.source.y].
         * hasPlayer && information.getGameInformation().getTeam(this.source.x,
         * this.source.y) == 1) { System.out.println("equipe rouge"); if
         * (information.getGameInformation().getTeam(this.source.x + 1, this.source.y +
         * 1) != information .getGameInformation().getTeam(this.source.x, this.source.y)
         * && information.getGameInformation().cells[this.source.x + 1][this.source.y +
         * 1].hasPlayer) { System.out.println("Inter"); if ((this.destination.x ==
         * this.source.x + 2 && this.destination.y == this.source.y + 1) ||
         * (this.destination.x == this.source.x + 2 && this.destination.y ==
         * this.source.y + 2) || (this.destination.x == this.source.x + 1 &&
         * this.destination.y == this.source.y + 2)) {
         * System.out.println("Interception"); information.detachBall(this.source, new
         * Point(this.source.x + 1, this.source.y + 1)); }
         * 
         * } else {
         * 
         * }
         * 
         * if (information.getTeam(this.source.x + 1, this.source.y) !=
         * information.getTeam(this.source.x, this.source.y) &&
         * information.cells[this.source.x + 1][this.source.y].hasPlayer) { res.add(new
         * VisualArea(this.source.x + 2, this.source.y, Color.RED));
         * 
         * } if (information.getTeam(this.source.x + 1, this.source.y - 1) !=
         * information.getTeam(this.source.x, this.source.y) &&
         * information.cells[this.source.x + 1][this.source.y - 1].hasPlayer) {
         * res.add(new VisualArea(this.source.x + 2, this.source.y - 1, Color.RED));
         * res.add(new VisualArea(this.source.x + 2, this.source.y - 2, Color.RED));
         * res.add(new VisualArea(this.source.x + 1, this.source.y - 2, Color.RED)); }
         * 
         * if (information.getTeam(this.source.x + 1, this.source.y + 2) !=
         * information.getTeam(this.source.x, this.source.y) &&
         * information.cells[this.source.x + 1][this.source.y + 2].hasPlayer) {
         * res.add(new VisualArea(this.source.x + 2, this.source.y + 2, Color.RED));
         * 
         * } if (information.getTeam(this.source.x + 1, this.source.y - 2) !=
         * information.getTeam(this.source.x, this.source.y) &&
         * information.cells[this.source.x + 1][this.source.y - 2].hasPlayer) {
         * res.add(new VisualArea(this.source.x + 2, this.source.y - 2, Color.RED));
         * 
         * }
         */

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

    }
}