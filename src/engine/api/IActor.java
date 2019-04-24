package engine.api;

/**
 * IActor
 */
public interface IActor {

    /**
     * Classical turn play by an actor, actions should be thrown to the
     * engine
     */
    void performActions();
    
    /**
     * Card duel is triggered by the other player
     */
    void performDuel(int actor);
}