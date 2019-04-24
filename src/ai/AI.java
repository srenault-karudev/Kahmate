 package ai;
import ai.api.IAI;
import engine.api.*;
import engine.model.*;
import engine.actions.*;

import gui.models.*;

import java.awt.Point;


//https://fr.wikipedia.org/wiki/Algorithme_minimax 
//Expectimax 


/**
 * AI
 */
public class AI implements IAI, IActor{

    private IEngine engine;
    private Player playerOne;
    private Player playerTwo;
    private int team;

    public AI(IEngine engine){
        this.engine = engine;
    }

    /**
     * Classical turn play by an actor, actions should be thrown to the
     * engine
     */
    public void performActions(){
        GameInformation gi = this.engine.getGameInformation();

        if(gi.getTeam() == 0)
        this.engine.performPlayerAction(
            new MoveAction(src, dst)
        );

    }
    
    /**
     * Card duel is triggered by the other player
     * 
     * @return : card number returned
     */
    public int performDuel(){
        Random rand = new Random();
        int intRand;
        intRand = rand(6);
        while(this.engine.getGameInformation().cards[intRand][intRand] == 0 ){
            intRand = rand(6);
        }
        return intRand;
    }

    /**
    * Simulation is called when it's IA's turn to play 
    *
    *
    */
    public void simulation(){
        GameInformation gi = this.engine.getGameInformation();
        Point ball = gi.getBallPosition();
        Player playerSelected;
        int xTemp;
        team = gi.getTeam(ball.x , ball.y);

        //Attack
        if(team == gi.activeActor){
            selectAttackerPlayer(gi.getPlayerTeam(gi.activeActor));
            doAttackMoove(playerOne.getPosition(),  gi.cells[(int) playerOne.getPosition().getX()][(int) playerOne.getPosition().getY()].playerMoves);
            selectProtectPlayer(gi.getPlayerTeam(gi.activeActor));
            doProtectMoove(playerTwo.getPosition(), playerOne.getPosition() ,gi.cells[(int) playerTwo.getPosition().getX()][(int) playerTwo.getPosition().getY()].playerMoves);
            playerOne = null;
            playerTwo = null;
            this.engine.next();
        }
        else{//Defense
            selectCloserPlayer(gi.getPlayerTeam(gi.activeActor));
            doDefenseMoove(playerOne.getPosition(), this.engine.getGameInformation().getBallPosition() ,gi.cells[(int) playerOne.getPosition().getX()][(int) playerOne.getPosition().getY()].playerMoves);
            selectCloserPlayer(gi.getPlayerTeam(gi.activeActor));
            doDefenseMoove(playerOne.getPosition(), this.engine.getGameInformation().getBallPosition() ,gi.cells[(int) playerOne.getPosition().getX()][(int) playerOne.getPosition().getY()].playerMoves);
            TacklAction tackl = new TacklAction();
            tackl.setSource(playerOne.getPosition());
            VisualArea[] area;
            if ( area = tackl.getVisualArea(this.engine.getGameInformation() ) != null ){
                tackl.setDestination(area[0]);
                this.engine.performAction( tackl );
            }

            tackl.setSource(playerTwo.getPosition());

            if ( area = tackl.getVisualArea(this.engine.getGameInformation() ) != null ){
                tackl.setDestination(area[0]);
                this.engine.performAction( tackl );
            }
            playerOne = null;
            playerTwo = null;
        }
    }

    //Il ne prend jamais la balle Problème
    /**
    * Bot trying to do a defense move 
    *@param defensePlayer: the position of the  defender 
    *@param ball: the postion of the ball 
    *@param move: the remaining movement points of the defender 
    */
    public void doDefenseMoove(Point defensePlayer, Point ball ,int move){
        MoveAction action = new MoveAction();
        action.setSource(defensePlayer);
        VisualArea tab[] = action.getVisualArea( this.engine.getGameInformation() );
        Point better = defensePlayer;
        int diff = 100;
        for ( int i = 0 ; i < tab.length ; i++ ){
            int resTemp = (int) tab[i].getX()-ball.getX() + (int) tab[i].getY()- (int) ball.getY();
            if (resTemp < 0)resTemp = -resTemp;
            resTemp = resTemp - move;
            if (resTemp < diff && tab[i] != ball){
                better = tab[i];
                diff = resTemp;
            }
        }
        action.setDestination(better);
        this.engine.performAction( action );
    }




    /**
    *Select closer player to the ball  
    *If its called a second time the method  selects the second closer player to the ball 
    *@param players: the players beloging to the IA
    *
    */
    public void selectCloserPlayer(Player[] players){
        Point ball = this.engine.getGameInformation().getBallPosition();
        int diff = 100;
        for ( Player p : players ){
            if( playerOne == null ){
                int resTemp = p.getPosition().getX()-ball.getX() + p.getPosition().getY()-ball.getY() - move;
                if (resTemp < 0 ) resTemp = -resTemp;
                resTemp = resTemp - this.getGameInformation().cells[p.getPosition().getX()][p.getPosition().getY()].playerMoves;
                if ( resTemp < diff ){
                    diff = resTemp;
                    playerOne = p;
                }
            }else if (p != playerOne){
                int resTemp = (int) p.getPosition().getX()-ball.getX() + p.getPosition().getY()-ball.getY() - move;
                if (resTemp < 0 ) resTemp = -resTemp;
                resTemp = resTemp - this.getGameInformation().cells[(int) p.getPosition().getX()][(int) p.getPosition().getY()].playerMoves;
                if ( resTemp < diff ){
                    diff = resTemp;
                    playerTwo = p;
                }
            }
            
        }
    }

    /**
    * Bot trying an  attack move 
    *@param player : postion of the attacker 
    *@param move: the remaining movement points of the attacker
    *
    */
    public void doAttackMoove(Point player, int move){
        MoveAction action = new MoveAction();
        action.setSource(this.player);
        VisualArea tab[] = action.getVisualArea( this.engine.getGameInformation() );
        Point better = player;
        for( int i = 0 ; i < tab.length ; i++ ){
            if( team == 0 ){
                if( tab[i].getX() > better.getX() )better = tab[i];
            }else if ( team == 1 ) {
                if( tab[i].getX() < better.getX() )better = tab[i];
            }else{
                new JOptionPane("Error");
            }
            action.setDestination(better);
            this.engine.performAction( action );
        }
    }
/**
    * Bot mooving a player to protect the ball holder 
    *
    *@param protectPlayer: postion of the player protecting the attacker 
    *@param attackPlayer: position of the attacker 
    *@param move : the remaining movement points of the protector 
    */
    public void doProtectMoove(Point protectPlayer, Point attackPlayer ,int move){
        MoveAction action = new MoveAction();
        action.setSource(this.protectPlayer);
        VisualArea tab[] = action.getVisualArea( this.engine.getGameInformation() );
        Point better = protectPlayer;
        int diff = 100;
        for ( int i = 0 ; i < tab.length ; i++ ){
            int resTemp = (int) tab[i].getX()-attackPlayer.getX() + tab[i].getY()-attackPlayer.getY();
            if (resTemp < 0)resTemp = -resTemp;
            resTemp = resTemp - move;
            if (resTemp < diff && tab[i] != attackPlayer){
                better = tab[i];
                diff = resTemp;
            }
        }
        action.setDestination(better);
        this.engine.performAction( action );
    }
    /**
    * Select the closer player to the attacker
    *@param players : the players beloging to the IA
    *
    *
    */
    public void selectProtectPlayer(Player[] players){
        int x,xTemp;
        Player playerSelected;
        if( team == 0){
            xTemp = 0;
            for(Player p : players ){
                x = (int) p.getPosition().getX();
                if( x > xTemp && p != playerOne ){
                    xTemp = x;
                    playerSelected = p;
                }
            }
            playerOne = playerSelected;
        }else{
            xTemp = 14;
            for(Player p : players ){
                x = (int) p.getPosition().getX();
                if( x < xTemp && p != playerOne ){
                    xTemp = x;
                    playerSelected = p;
                }
            }
            playerOne = playerSelected;
        }
    }
    /**
    *Selecting  the  IA player holding the ball 
    *@param players: the players beloging to the IA
    *
    *
    */
    public void selectAttackerPlayer(Player[] players){
        int x,xTemp;
        Player playerSelected;
        if( team == 0){
            xTemp = 0;
            for(Player p : players ){
                x = (int) p.getPosition().getX();
                if( x > xTemp ){
                    xTemp = x;
                    playerSelected = p;
                }
            }
            playerOne = playerSelected;
        }else{
            xTemp = 14;
            for(Player p : players ){
                x = (int) p.getPosition().getX();
                if( x < xTemp ){
                    xTemp = x;
                    playerSelected = p;
                }
            }
            playerOne = playerSelected;
        }
    }

    /* ------------- ATTACK --------------------------------------*/

    //public Player chooseBestPlayer(CellInformation tab); //Regarde si joueur avec balle a peut bouger vers l'avant. (Preference tout droit)
    /* 
    si le joueur avec la balle peut franchir la ligne go
    Si joueur2 allié peut se rapprocher du joueur avec balle (preference pas sur la meme ligne y)
    Si joueur avec balle n'est pas choisi en premier joueur alors le deuxieme joueur ne doit pas le dépasser en X et se rapprocher le plus possible pour être à hauteur de passe
    */

    //public void setMovementToLine(Player player);

    //public Player getBallPlayer(CellInformation tab);

    //public void doPass(Player player);

    //public void doShoot(Point point);

    

// -------------------- DEFENSE -------------------------------


    //public Player getCloserPlayerToBall(CellInformation tab);

    //public void setMovementToBall(Player player);

    //public Player getCloserPlayerOne(Player playerOne);

    //public void setMovementToPlayerOne(Player playerTwo);

    //public void doTackle(Player player, Player enemy);

// ------------- Hybride ----------------------------
    /**
    *@param diff: Difference of points between attack and defense 
    */
    //public void chooseCard(int diff);

}