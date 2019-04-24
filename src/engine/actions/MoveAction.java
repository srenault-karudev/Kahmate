package engine.actions;

import engine.Engine;
import engine.api.*;
import engine.model.*;

import gui.models.*;

import java.awt.*;
import java.util.ArrayList;

public class MoveAction extends IPlayerAction {

    /**
     * Get visual areas
     * 
     * @param src         source action
     * @param information gamestate information
     * @return areas list
     */
    @Override
    public VisualArea[] getVisualArea(GameInformation information) {
        int indice = information.cells[this.source.x][this.source.y].playerMoves;
        boolean[][] tab = new boolean[Engine.BOARD_X][Engine.BOARD_Y];
        ArrayList<VisualArea> res = new ArrayList<VisualArea>();
        int x, y, posx, posy;
        posx = this.source.x;
        posy = this.source.y;
        /*
         * for (int f = 0; f < tab.length; f++) { for (int i = 0; i < tab[0].length;
         * i++) { x = f - posx; if (x < 0) x = -x; y = i - posy; if (y < 0) y = -y; if
         * (x + y <= indice) { if (information.cells[f][i].hasPlayer == false) {
         * 
         * res.add(new VisualArea(f, i, Color.WHITE));
         * 
         * }
         * 
         * } } }
         */
        Color c = new Color(255,
                information.cells[this.source.x][this.source.y].playerMovesRemaining * 255
                        / information.cells[this.source.x][this.source.y].playerMoves,
                information.cells[this.source.x][this.source.y].playerMovesRemaining * 255
                        / information.cells[this.source.x][this.source.y].playerMoves);
        if (information.cells[this.source.x][this.source.y].playerMovesRemaining > 0) {
            if (posx > 0) {
                if (information.cells[posx - 1][posy].hasPlayer == false)
                    res.add(new VisualArea(posx - 1, posy, c));
            }
            if (posx < Engine.BOARD_X - 1) {
                if (information.cells[posx + 1][posy].hasPlayer == false)
                    res.add(new VisualArea(posx + 1, posy, c));
            }
            if (posy > 0) {
                if (information.cells[posx][posy - 1].hasPlayer == false)
                    res.add(new VisualArea(posx, posy - 1, c));
            }
            if (posy < Engine.BOARD_Y - 1) {
                if (information.cells[posx][posy + 1].hasPlayer == false)
                    res.add(new VisualArea(posx, posy + 1, c));
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
        int indice = information.cells[this.source.x][this.source.y].playerMoves;
        boolean[][] tab = new boolean[Engine.BOARD_X][Engine.BOARD_Y];
        ArrayList<Point> res = new ArrayList<>();
        int x, y, posx, posy;
        posx = this.source.x;
        posy = this.source.y;

        if (posx > 0) {
            if (information.cells[posx - 1][posy].hasPlayer == false)
                res.add(new Point(posx - 1, posy));
        }
        if (posx < Engine.BOARD_X - 1) {
            if (information.cells[posx + 1][posy].hasPlayer == false)
                res.add(new Point(posx + 1, posy));
        }
        if (posy > 0) {
            if (information.cells[posx][posy - 1].hasPlayer == false)
                res.add(new Point(posx, posy - 1));
        }
        if (posy < Engine.BOARD_Y - 1) {
            if (information.cells[posx][posy + 1].hasPlayer == false)
                res.add(new Point(posx, posy + 1));
        }

        Point[] ret = new Point[res.size()];
        for (int t = 0; t < ret.length; t++) {
            ret[t] = res.get(t);
        }
        if (information.cells[this.source.x][this.source.y].playerMovesRemaining < 1 || res.size() == 0)
            return null;
        else
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
        if (information.getGameInformation().cells[this.source.x][this.source.y].playerMovesRemaining > 0) {
            information.movePlayer(this.source, this.destination);
            information.getGameInformation().cells[this.destination.x][this.destination.y].playerMovesRemaining--;
        }
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