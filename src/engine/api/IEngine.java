package engine.api;

import java.awt.Point;

/**
 * Engine Interface
 */
public interface IEngine {

    /**
     * Start the game
     */
    void play();

    /**
     * Go to next round
     */
    void next();
    /**
     * Ask for a duel
     */
    void askDuel(IPlayerAction action);
    /**
     * Return asked card
     */
    void sendCard(int card);

    /**
     * Get active actor id
     * @return actor id
     */
    int getActiveActor();

    /**
     * Perform custom player action
     */
    void performPlayerAction(IPlayerAction action);

    /**
     * Get cell information from cell position
     * 
     * @param position : cell position
     * @return : return cell information
     */
    CellInformation getCellInformation(Point position);

    /**
     * Return general game information
     * 
     * @return : return game information
     */
    GameInformation getGameInformation();
}