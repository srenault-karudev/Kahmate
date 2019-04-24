package engine.model;

import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import engine.Engine;
import engine.api.*;
import gui.api.*;

/**
 * GameState
 */
public class GameState {

	private IGUI gui;

	private GameInformation gameInformation;

	private ArrayList<Player> players;
	private ArrayList<Ball> balls;

	public GameState(IGUI gui) {
		this.gui = gui;
		this.gameInformation = new GameInformation();
		this.players = new ArrayList<>();
		this.balls = new ArrayList<>();
	}

	public GameInformation getGameInformation() {
		return this.gameInformation;
	}

	/**
	 * Next round, reset players moves and changes cards
	 */
	public void next() {
		// Change actor
		this.gameInformation.activeActor++;
		if (this.gameInformation.activeActor >= Engine.TEAM_COUNT)
			this.gameInformation.activeActor = 0;

		// Reset players moves
		for (Player player : this.players) {
			Point position = player.getPosition();
			this.gameInformation.cells[position.x][position.y].playerMovesRemaining = this.gameInformation.cells[position.x][position.y].playerMoves;
		}

		// Changes graphical cards
		int actor = this.gameInformation.activeActor;
		for (int card = 0; card < Engine.CARD_COUNT; card++) {
			gui.toggleCard(Engine.CARD_COUNT + card, !this.gameInformation.cards[actor][card].used);
		}
		for (int card = 0; card < Engine.CARD_COUNT; card++) {
			gui.toggleCard(card, !this.gameInformation.cards[(actor + 1) % Engine.TEAM_COUNT][card].used);
		}

		// Reset players tackl
		int actorToReset = (this.gameInformation.activeActor + 1) % Engine.TEAM_COUNT;
		for (Player player : this.players) {
			CellInformation cell = this.gameInformation.cells[player.getPosition().x][player.getPosition().y];
			if (cell.playerTackled && cell.playerTeam == actorToReset) {
				this.untacklPlayer(player.getPosition());
			}
		}
	}

	/**
	 * Pass card to used
	 * 
	 * @param team team number
	 * @param card card number
	 */
	public void useCard(int team, int card) {
		this.gameInformation.cards[team][card].used = true;
		gui.toggleCard((((team + 1) % Engine.TEAM_COUNT) * Engine.CARD_COUNT) + card, !true);
	}

	/**
	 * Create new player at position
	 * 
	 * @param position player position
	 */
	public void createPlayer(Point position, String image, String tackledImage, int team, int moves, int attack,
			int defense, boolean tackled) {

		CellInformation src = this.gameInformation.cells[position.x][position.y];

		if (!src.hasPlayer) {
			src.hasPlayer = true;

			src.playerTeam = team;
			src.playerMoves = moves;
			src.playerMovesRemaining = moves;
			src.playerAttack = attack;
			src.playerDefense = defense;
			src.playerTackled = tackled;

			Player player = new Player(position.x, position.y, image, tackledImage, gui);
			if (tackled)
				player.setTackled(true);
			this.players.add(player);
			src.playerId = this.players.size() - 1;
		}
	}

	/**
	 * Move the player in the board
	 * 
	 * @param player player position
	 * @param dst    destination position+
	 **/
	public void movePlayer(Point player, Point dst) {

		CellInformation cellPlayer = this.gameInformation.cells[player.x][player.y];
		CellInformation cellDst = this.gameInformation.cells[dst.x][dst.y];

		if (cellPlayer.hasPlayer && !cellDst.hasPlayer) {
			if (!(cellPlayer.hasBall && cellDst.hasBall)) {
				this.players.get(cellPlayer.playerId).setPosition(dst.x, dst.y);
				CellInformation.movePlayer(cellPlayer, cellDst);

				// Should attach visual component
				if (cellDst.hasBall && !this.balls.get(cellDst.ballId).isAttached()) {
					this.balls.get(cellDst.ballId).attachTo(this.players.get(cellDst.playerId));
				}
			}
		}
		if (cellDst.hasPlayer && gameInformation.getTeam(dst.x, dst.y) == 0 && cellDst.hasBall
				&& dst.x == Engine.BOARD_X - 1) {
	
			this.gui.showVictory(0);
		}
		if (cellDst.hasPlayer && gameInformation.getTeam(dst.x, dst.y) == 1 && cellDst.hasBall && dst.x == 0) {
			
			this.gui.showVictory(1);
		}
	}

	/**
	 * Tackl player
	 * 
	 * @param player player position
	 */
	public void tackPlayer(Point player) {
		CellInformation cell = this.gameInformation.cells[player.x][player.y];

		if (cell.hasPlayer) {
			cell.playerTackled = true;
			gui.setTacklPlayer(cell.playerId, true);
		}
	}

	/**
	 * Untackl player
	 * 
	 * @param player player position
	 */
	public void untacklPlayer(Point player) {
		CellInformation cell = this.gameInformation.cells[player.x][player.y];

		if (cell.hasPlayer) {
			cell.playerTackled = false;
			gui.setTacklPlayer(cell.playerId, false);
		}
	}

	/**
	 * Create new ball at position
	 * 
	 * @param position initial ball position
	 */
	public void createBall(Point position) {

		CellInformation cell = this.gameInformation.cells[position.x][position.y];

		if (!cell.hasPlayer) {
			cell.hasBall = true;

			Ball ball = new Ball(position.x, position.y, gui);
			this.balls.add(ball);
			cell.ballId = this.balls.size() - 1;
		}
	}

	/**
	 * Move ball, the ball should not be attached and the target cell should not
	 * have a player
	 * 
	 * @param ball ball position
	 * @param dst  ball destination
	 */
	public void moveBall(Point ball, Point dst) {

		CellInformation cellSrc = this.gameInformation.cells[ball.x][ball.y];
		CellInformation cellDst = this.gameInformation.cells[dst.x][dst.y];

		if (cellSrc.hasBall && !cellSrc.hasPlayer && !cellSrc.hasPlayer) {
			CellInformation.moveBall(cellSrc, cellDst);

			this.balls.get(cellDst.ballId).setPosition(dst.x, dst.y);
		}
	}

	/**
	 * Detach ball from player and move it to another position If the ball hit a
	 * cell with a player, the ball becomes attached
	 * 
	 * @param player player position that has the ball
	 * @param dst    destination
	 */
	public void detachBall(Point player, Point dst) {
		CellInformation cellSrc = this.gameInformation.cells[player.x][player.y];
		CellInformation cellDst = this.gameInformation.cells[dst.x][dst.y];

		if (cellSrc.hasPlayer) {
			this.balls.get(cellSrc.ballId).detach();
			CellInformation.moveBall(cellSrc, cellDst);
			this.balls.get(cellSrc.ballId).setPosition(dst.x, dst.y);

			if (cellDst.hasPlayer) {
				this.balls.get(cellSrc.playerId).attachTo(this.players.get(cellDst.playerId));
			}
		}
	}
}