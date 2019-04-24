import engine.model.GameState;
import engine.model.*;
import engine.api.*;
import gui.api.*;
import java.util.*;
import java.awt.*;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

public class GameStateTest{
    
    private GameState gameState;
    private IGUI gui;

    @Before
    public void initialize() throws Exception{
        this.gui = new NullGUI();
        this.gameState = new GameState(this.gui);
    }
    @After
    public void terminate() throws Exception{
        this.gameState = null;
        this.gui = null;
    }

    @Test
    public void movePlayer(){
        this.gameState.createPlayer(new Point(5, 5), "", "", 0, 3, 3, 3, false);
        this.gameState.movePlayer(new Point(5, 5), new Point(3, 3));

        GameInformation gameInformation = this.gameState.getGameInformation();

        boolean hasPlayer = gameInformation.cells[3][3].hasPlayer;
        assertEquals(hasPlayer, true);
        hasPlayer = gameInformation.cells[5][5].hasPlayer;        
        assertEquals(hasPlayer, false);
    }
}