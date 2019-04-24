package engine;

import engine.api.*;
import engine.model.*;
import gui.Images;
import gui.*;
import gui.api.*;

import java.awt.Point;
import java.util.*;

public class Engine implements IEngine {

    private enum State {
        BOARD_VIEW, CARD_VIEW
    }

    private boolean nextActor = false;

    private State state = State.BOARD_VIEW;

    public static int BOARD_X = 13;
    public static int BOARD_Y = 8;
    public static int CARD_COUNT = 5;
    public static int TEAM_COUNT = 2;
    public static int PLAYER_COUNT = 6;

    private GameState gameState;

    private GUI gui;

    private IActor[] actors = new IActor[TEAM_COUNT];

    // Duel data
    private IPlayerAction action;
    private int[] actorCards = new int[TEAM_COUNT];
    private boolean nextCard = false;

    // Players models
    PlayerState[] bluePlayerModel = { new PlayerState(Images.PLAYER_BLUE_FAST, Images.PLAYER_BLUE_TACKL, 4, 1, 1),
            new PlayerState(Images.PLAYER_BLUE_FAT, Images.PLAYER_BLUE_TACKL, 2, 2, 1),
            new PlayerState(Images.PLAYER_BLUE_HARD, Images.PLAYER_BLUE_TACKL, 3, 1, 1),
            new PlayerState(Images.PLAYER_BLUE_NORMAL, Images.PLAYER_BLUE_TACKL, 3, 1, 1),
            new PlayerState(Images.PLAYER_BLUE_SMART, Images.PLAYER_BLUE_TACKL, 3, 1, 1) };
    PlayerState[] redPlayerModel = { new PlayerState(Images.PLAYER_RED_FAST, Images.PLAYER_RED_TACKL, 4, 1, 1),
            new PlayerState(Images.PLAYER_RED_FAT, Images.PLAYER_RED_TACKL, 2, 1, 1),
            new PlayerState(Images.PLAYER_RED_HARD, Images.PLAYER_RED_TACKL, 3, 1, 1),
            new PlayerState(Images.PLAYER_RED_NORMAL, Images.PLAYER_RED_TACKL, 3, 1, 1),
            new PlayerState(Images.PLAYER_RED_SMART, Images.PLAYER_RED_TACKL, 3, 1, 1) };

    /**
     * Constructor
     */
    public Engine() {
        this.gui = new GUI(this);

        this.actors[0] = this.gui;
        this.actors[1] = this.gui;

        this.gameState = new GameState(gui);
    }

    /**
     * Start the game
     */
    public void play() {

        Random random = new Random();

        // Generate blue players
        ArrayList<Point> bluePositions = new ArrayList<>();
        for (int x = 1; x < 3; x++) {
            for (int y = 0; y < Engine.BOARD_Y; y++) {
                bluePositions.add(new Point(x, y));
            }
        }
        ArrayList<Integer> bluePossibleIndex = new ArrayList<>();
        bluePossibleIndex.add(new Integer(0));
        bluePossibleIndex.add(new Integer(1));
        bluePossibleIndex.add(new Integer(2));
        bluePossibleIndex.add(new Integer(3));
        bluePossibleIndex.add(new Integer(3));
        bluePossibleIndex.add(new Integer(4));

        for (int player = 0; player < Engine.PLAYER_COUNT; player++) {
            int stateIndex = random.nextInt(bluePossibleIndex.size());
            PlayerState state = this.bluePlayerModel[bluePossibleIndex.get(stateIndex).intValue()];
            bluePossibleIndex.remove(stateIndex);

            int positionIndex = random.nextInt(bluePositions.size());
            Point position = bluePositions.get(positionIndex);
            bluePositions.remove(positionIndex);

            this.gameState.createPlayer(position, state.image, state.tacklImage, 0, state.moves, state.attack,
                    state.defense, false);
        }

        // Generate red players
        ArrayList<Point> redPositions = new ArrayList<>();
        for (int x = Engine.BOARD_X - 3; x < Engine.BOARD_X - 1; x++) {
            for (int y = 0; y < Engine.BOARD_Y; y++) {
                redPositions.add(new Point(x, y));
            }
        }
        ArrayList<Integer> redPossibleIndex = new ArrayList<>();
        redPossibleIndex.add(new Integer(0));
        redPossibleIndex.add(new Integer(1));
        redPossibleIndex.add(new Integer(2));
        redPossibleIndex.add(new Integer(3));
        redPossibleIndex.add(new Integer(3));
        redPossibleIndex.add(new Integer(4));

        for (int player = 0; player < Engine.PLAYER_COUNT; player++) {
            int stateIndex = random.nextInt(redPossibleIndex.size());
            PlayerState state = this.redPlayerModel[redPossibleIndex.get(stateIndex).intValue()];
            redPossibleIndex.remove(stateIndex);

            int positionIndex = random.nextInt(redPositions.size());
            Point position = redPositions.get(positionIndex);
            redPositions.remove(positionIndex);

            this.gameState.createPlayer(position, state.image, state.tacklImage, 1, state.moves, state.attack,
                    state.defense, false);
        }

        // Generate ball
        int ballY = random.nextInt(Engine.BOARD_Y);
        this.gameState.createBall(new Point(6, ballY));

        this.gameState.useCard(0, 2);
    }

    /**
     * Go to next round
     */
    public void next() {

        if (this.nextActor) {
            this.nextActor = false;
            this.gui.toggleBlackScreen(false, 0);
        } else {
            if (this.state == State.BOARD_VIEW) {

                this.changeActor();

            } else if (this.state == State.CARD_VIEW) {

            }
        }
    }

    /**
     * Change actor
     */
    public void changeActor() {
        this.gameState.next();

        this.gui.toggleBlackScreen(true, this.gameState.getGameInformation().activeActor);
        this.nextActor = true;
    }

    /**
     * Ask for a duel
     * 
     * @param action action to call
     */
    public void askDuel(IPlayerAction action) {
        this.action = action;
        this.nextCard = false;

        this.state = State.CARD_VIEW;
        this.actors[this.gameState.getGameInformation().activeActor]
                .performDuel(this.gameState.getGameInformation().activeActor);
    }

    /**
     * Return asked card
     */
    public void sendCard(int card) {

        int actor0 = this.gameState.getGameInformation().activeActor;
        int actor1 = (this.gameState.getGameInformation().activeActor + 1) % TEAM_COUNT;

        if (!nextCard) {
            nextCard = true;
            this.actorCards[actor0] = card;
            this.gameState.useCard(actor0, card);
            this.actors[actor1].performDuel(actor1);
        } else {
            this.actorCards[actor1] = card;
            this.gameState.useCard(actor1, card);

            boolean win = this.actorCards[actor0] >= this.actorCards[actor1];
            this.action.perform(this.gameState, this, win);

            this.action = null;
            this.nextCard = false;
            this.state = State.BOARD_VIEW;

            // Should refill card deck ?
            GameInformation gi = this.gameState.getGameInformation();
            boolean refill = false;
            for (int i = 0; i < Engine.CARD_COUNT; i++) {
                if (gi.cards[0][i].used) {
                    refill = true;
                    break;
                }
            }
            if (refill) {
                for (int i = 0; i < Engine.CARD_COUNT; i++) {
                    gi.cards[0][i].used = false;
                    gi.cards[1][i].used = false;
                }
            }
        }
    }

    /**
     * Perform custom player action
     */
    public void performPlayerAction(IPlayerAction action) {
        action.perform(this.gameState, this);
    }

    /**
     * Get cell information from cell position
     * 
     * @param position : cell position
     * @return : return cell information
     */
    public CellInformation getCellInformation(Point position) {
        return this.gameState.getGameInformation().cells[position.x][position.y];
    }

    /**
     * Get active actor id
     * 
     * @return actor id
     */
    public int getActiveActor() {
        return gameState.getGameInformation().activeActor;
    }

    /**
     * Return general game information
     * 
     * @return : return game information
     */
    public GameInformation getGameInformation() {
        return this.gameState.getGameInformation();
    }
}