package engine.api;

import engine.model.*;

public class CellInformation {
    // General information
    public boolean hasBall = false;
    public boolean hasPlayer = false;

    // Player
    public int playerTeam = 0;
    public int playerMoves = 0;
    public int playerMovesRemaining = 0;
    public int playerAttack = 0;
    public int playerDefense = 0;
    public boolean playerTackled = false;
    public boolean playerPlayed = false;
    public int playerId = 0;

    // Balls
    public int ballId = 0;

    public CellInformation() {

    }

    public CellInformation(CellInformation cell) {
        this.hasBall = cell.hasBall;
        this.hasPlayer = cell.hasPlayer;

        this.playerMoves = cell.playerMoves;
        this.playerMovesRemaining = cell.playerMovesRemaining;
        this.playerTeam = cell.playerTeam;
        this.playerAttack = cell.playerAttack;
        this.playerDefense = cell.playerDefense;
        this.playerTackled = cell.playerTackled;
        this.playerPlayed = cell.playerPlayed;
        this.playerId = cell.playerId;

        this.ballId = cell.ballId;
    }

    public static void movePlayer(CellInformation src, CellInformation dst) {
        src.hasPlayer = false;
        dst.hasPlayer = true;

        if(src.hasBall){
            CellInformation.moveBall(src, dst);
        }

        dst.playerTeam = src.playerTeam;
        dst.playerMoves = src.playerMoves;
        dst.playerMovesRemaining = src.playerMovesRemaining;
        dst.playerAttack = src.playerAttack;
        dst.playerDefense = src.playerDefense;
        dst.playerTackled = src.playerTackled;
        dst.playerPlayed = src.playerPlayed;
        dst.playerId = src.playerId;
    }

    public static void moveBall(CellInformation src, CellInformation dst) {
        src.hasBall = false;
        dst.hasBall = true;

        dst.playerId = src.playerId;
    }
}