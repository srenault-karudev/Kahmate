package gui.controllers;

import gui.GUI;
import gui.api.*;
import gui.layers.*;
import gui.models.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import engine.api.*;
import engine.actions.*;

public class MouseController implements MouseListener, MouseMotionListener{

    private enum State{
        WAITING_ANYTHING,
        WAITING_ACTIONS,
        WAITING_ACTION_ARGUMENT,
        WAITING_CARD,
        WAITING_CARD_FORCED,
        WAITING_NEXT_PLAYER,
        BLACK_SCREEN
    }

    private State state = State.WAITING_ANYTHING;
    private IPlayerAction activeAction = null;
    private ArrayList<Point> clickableArea = null;

    private IGUI gui;
    private IEngine engine;
    private AreaLayer area;
    private CardLayer card;
    private ButtonLayer button;

    public MouseController(
        IGUI gui,
        IEngine engine,
        AreaLayer area,
        CardLayer card,
        ButtonLayer button
    ){
        this.area = area;
        this.card = card;
        this.button = button;
        //DO NOT ADD MOUSE LISTENER TO CARD LAYER, IT WILL BLOCK AREA INPUTS

        this.gui = gui;
        this.engine = engine;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        Rectangle rectangle = new Rectangle(
            e.getX(), e.getY(),
            0, 0
        );

        Rectangle rectangleSpace = this.area.viewSpaceToRectangleSpace(rectangle);

        //Dispatch states
        if(this.state == State.BLACK_SCREEN){

            this.engine.next();
            this.state = State.WAITING_ANYTHING;

        } else if(this.state == State.WAITING_ANYTHING){ //Default state

            int x = (int)rectangleSpace.getX();
            int y = (int)rectangleSpace.getY();

            if(this.button.isButtonsShowed()){

                if(cardButtonFromCoordinate(new Point(x, y))){
                    if(!this.card.isShowed()){
                        //Show cards
                        this.gui.toggleCardDeck(true);
                        this.area.setSelectionArea(null);
                        this.area.repaint();
                        this.state = State.WAITING_CARD;
                    }
                }

                if(nextButtonFromCoordinate(new Point(x, y))){
                    //Engine next round

                    this.engine.next();
                    this.state = State.BLACK_SCREEN;
                }
            }

            Point positionSelection = new Point(
                rectangleSpace.x / TerrainLayer.CELL_SIZE.width,
                rectangleSpace.y / TerrainLayer.CELL_SIZE.height
            );

            if(positionSelection.x >= 0 && positionSelection.x < TerrainLayer.TERRAIN_CELL_DIMENSION.width &&
                positionSelection.y >= 0 && positionSelection.y < TerrainLayer.TERRAIN_CELL_DIMENSION.height){
                
                CellInformation ci = engine.getCellInformation(positionSelection);
                if(ci.hasPlayer && ci.playerTeam == engine.getActiveActor() && !ci.playerTackled){

                    if(e.getButton() == MouseEvent.BUTTON3){

                        //Display actions
                        if(ci.hasBall){
                            button.setActions(positionSelection, 0);
                        } else{
                            button.setActions(positionSelection, 1);
                        }
                        button.showActions(true);
                        button.showButtons(false);
                        this.state = State.WAITING_ACTIONS;
                        this.button.repaint();

                    } else if(e.getButton() == MouseEvent.BUTTON1){
        
                        //Player move action
                        button.showActions(false);
                        button.showButtons(false);
                        this.activeAction = new MoveAction();
                        this.activeAction.setSource(positionSelection);
                        if(this.activeAction.getClickableArea(engine.getGameInformation()) != null){
                            this.gui.setArea(this.activeAction.getVisualArea(engine.getGameInformation()));
                            this.state = State.WAITING_ACTION_ARGUMENT;
                        } else{
                            //No arguments required
                            this.engine.performPlayerAction(this.activeAction);
                            this.state = State.WAITING_ANYTHING;
                            button.showActions(false);
                            button.showButtons(true);
                        }
                    }
                }
            }

        } else if(this.state == State.WAITING_ACTIONS){

            int x = (int)rectangleSpace.getX();
            int y = (int)rectangleSpace.getY();
            
            int actionX = (this.button.getActionsPosition().x * TerrainLayer.CELL_SIZE.width) - (ButtonLayer.ACTIONS_SIZE_X / 2) + (TerrainLayer.CELL_SIZE.width / 2);
            int actionY = (this.button.getActionsPosition().y * TerrainLayer.CELL_SIZE.height) - (ButtonLayer.ACTIONS_SIZE_Y / 2) + (TerrainLayer.CELL_SIZE.height / 2);

            if(x >= actionX && x <= actionX + ButtonLayer.ACTIONS_SIZE_X && y >= actionY && y <= actionY + ButtonLayer.ACTIONS_SIZE_Y){
                int action = (x - actionX) / (ButtonLayer.ACTIONS_SIZE_X / 4);

                this.activeAction = null;
                if(this.button.getActionsMode() == 0){
                    if(action == 0){
                        this.activeAction = new ShootAction();
                    } else if(action == 2){
                        this.activeAction = new PassAction();
                    } else if(action == 3){
                        this.activeAction = new ForceAction();
                    }
                } else if(this.button.getActionsMode() == 1){
                    if(action == 1){
                        this.activeAction = new TacklAction();
                    }
                }

                if(this.activeAction != null){
                    this.activeAction.setSource(this.button.getActionsPosition());

                    this.button.showActions(false);
                    this.gui.setArea(this.activeAction.getVisualArea(engine.getGameInformation()));
                    this.state = State.WAITING_ACTION_ARGUMENT;
                }
            } else{
                this.state = State.WAITING_ANYTHING;
                this.button.showActions(false);
                this.button.showButtons(true);
            }

        } else if(this.state == State.WAITING_ACTION_ARGUMENT){

            Point positionSelection = new Point(
                rectangleSpace.x / TerrainLayer.CELL_SIZE.width,
                rectangleSpace.y / TerrainLayer.CELL_SIZE.height
            );

            if(positionSelection.x >= 0 && positionSelection.x < TerrainLayer.TERRAIN_CELL_DIMENSION.width &&
                positionSelection.y >= 0 && positionSelection.y < TerrainLayer.TERRAIN_CELL_DIMENSION.height){
            
                boolean isValid = false;
                Point[] validArea = this.activeAction.getClickableArea(engine.getGameInformation());
                for(Point area : validArea){
                    if(area.equals(positionSelection)){
                        isValid = true;
                        break;
                    }
                }

                if(isValid){
                    this.activeAction.setDestination(positionSelection);
                    this.engine.performPlayerAction(this.activeAction);
                    this.state = State.WAITING_ANYTHING;
                    this.area.setAreas(null);
                    this.button.showButtons(true);
                } else{
                    this.activeAction = null;
                    this.state = State.WAITING_ANYTHING;
                    this.area.setAreas(null);
                }
            }

        } else if(this.state == State.WAITING_CARD){

            int x = (int)rectangleSpace.getX();
            int y = (int)rectangleSpace.getY();

            if(this.button.isButtonsShowed()){

                if(cardButtonFromCoordinate(new Point(x, y))){
                    if(this.card.isShowed()){
                        //Show cards
                        this.gui.toggleCardDeck(false);
                        this.state = State.WAITING_ANYTHING;
                    }
                }

                if(nextButtonFromCoordinate(new Point(x, y))){
                    //Engine next round
                }
            }

        } else if(this.state == State.WAITING_CARD_FORCED){

            System.out.println("wait");

            int cardNumber = this.cardFromCoordinate(new Point((int)rectangleSpace.getX(), (int)rectangleSpace.getY()));
            if(cardNumber != -1){
                
                //ENGINE CALL
                this.state = State.WAITING_ACTIONS;
                this.engine.sendCard(cardNumber);

            }

        } else if(this.state == State.WAITING_NEXT_PLAYER){

            this.state = State.WAITING_ANYTHING;
            this.button.showButtons(true);

        }
    }
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e){

        Rectangle rectangle = new Rectangle(
            e.getX(), e.getY(),
            0, 0
        );
        Rectangle rectangleSpace = this.area.viewSpaceToRectangleSpace(rectangle);

        //Dispatch states
        if(this.state == State.WAITING_ANYTHING){ //Default state

            Point positionSelection = new Point(
                rectangleSpace.x / TerrainLayer.CELL_SIZE.width,
                rectangleSpace.y / TerrainLayer.CELL_SIZE.height
            );

            boolean repaint = false;

            if(positionSelection.x >= 0 && positionSelection.x < TerrainLayer.TERRAIN_CELL_DIMENSION.width &&
            positionSelection.y >= 0 && positionSelection.y < TerrainLayer.TERRAIN_CELL_DIMENSION.height){
                if(!positionSelection.equals(this.area.getSelectionArea())){
                    repaint = true;
                }
                this.area.setSelectionArea(positionSelection);
            } else{
                if(!positionSelection.equals(this.area.getSelectionArea())){
                    repaint = true;
                }
                this.area.setSelectionArea(null);
            }

            if(repaint){
                this.area.repaint();
            }

        } else if(this.state == State.WAITING_ACTIONS){



        } else if(this.state == State.WAITING_ACTION_ARGUMENT){

            Point positionSelection = new Point(
                rectangleSpace.x / TerrainLayer.CELL_SIZE.width,
                rectangleSpace.y / TerrainLayer.CELL_SIZE.height
            );

            boolean repaint = false;

            if(positionSelection.x >= 0 && positionSelection.x < TerrainLayer.TERRAIN_CELL_DIMENSION.width &&
            positionSelection.y >= 0 && positionSelection.y < TerrainLayer.TERRAIN_CELL_DIMENSION.height){
                if(!positionSelection.equals(this.area.getSelectionArea())){
                    repaint = true;
                }
                this.area.setSelectionArea(positionSelection);
            } else{
                if(!positionSelection.equals(this.area.getSelectionArea())){
                    repaint = true;
                }
                this.area.setSelectionArea(null);
            }

            if(repaint){
                this.area.repaint();
            }

        } else if(this.state == State.WAITING_CARD){

        } else if(this.state == State.WAITING_CARD_FORCED){
            
            System.out.println("test");

            int focusCard = this.cardFromCoordinate(new Point((int)rectangleSpace.getX(), (int)rectangleSpace.getY()));
            this.card.setFocusCard(focusCard);

        } else if(this.state == State.WAITING_NEXT_PLAYER){
            
        }

        /*if(!this.card.isShowed()){

            Point positionSelection = new Point(
                rectangleSpace.x / TerrainLayer.CELL_SIZE.width,
                rectangleSpace.y / TerrainLayer.CELL_SIZE.height
            );

            boolean repaint = false;

            if(positionSelection.x >= 0 && positionSelection.x < TerrainLayer.TERRAIN_CELL_DIMENSION.width &&
            positionSelection.y >= 0 && positionSelection.y < TerrainLayer.TERRAIN_CELL_DIMENSION.height){
                if(!positionSelection.equals(this.area.getSelectionArea())){
                    repaint = true;
                }
                this.area.setSelectionArea(positionSelection);
            } else{
                if(!positionSelection.equals(this.area.getSelectionArea())){
                    repaint = true;
                }
                this.area.setSelectionArea(null);
            }

            if(repaint){
                this.area.repaint();
            }
        } else{
            Rectangle rectangle = new Rectangle(
                e.getX(), e.getY(),
                0, 0
            );
            Rectangle rectangleSpace = this.area.viewSpaceToRectangleSpace(rectangle);

            int focusCard = this.cardFromCoordinate(new Point((int)rectangleSpace.getX(), (int)rectangleSpace.getY()));
            this.card.setFocusCard(focusCard);
        }*/
    }
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseDragged(MouseEvent e) {}

    private int cardFromCoordinate(Point coordinate){
        int x = (int)coordinate.getX();
        int y = (int)coordinate.getY();

        //Check Y cusor position
        if((y < this.card.getVirtualHeight() - VisualCard.MARGIN_Y) &&
        (y > this.card.getVirtualHeight() - VisualCard.MARGIN_Y - VisualCard.CARD_DIMENSION.height)){
            
            final int rowLength = (VisualCard.CARD_DIMENSION.width + VisualCard.MARGIN_X) * VisualCard.DECK_COUNT - VisualCard.MARGIN_X;
            final int offsetX = (this.card.getVirtualWidth() / 2) - (rowLength / 2);    
            
            //Check X cursor position
            if(x >= offsetX && x <= this.card.getVirtualWidth() - offsetX){
                x -= offsetX;
                
                //Check final card position
                int remainingOffset = x % (VisualCard.CARD_DIMENSION.width + VisualCard.MARGIN_X);
                if(remainingOffset <= VisualCard.CARD_DIMENSION.width){
                    int cardNumber = x / (VisualCard.CARD_DIMENSION.width + VisualCard.MARGIN_X);
                    
                    //ENGINE CALL
                    return cardNumber;

                }
            }
        }

        return -1;
    }

    private boolean cardButtonFromCoordinate(Point coordinate){
        int x = (int)coordinate.getX();
        int y = (int)coordinate.getY();

        if(x >= 10 && x <= 10 + TerrainLayer.CELL_SIZE.width - 20 &&
            y >= this.button.getVirtualHeight() - TerrainLayer.CELL_SIZE.height && 
            y <= this.button.getVirtualHeight() - TerrainLayer.CELL_SIZE.height + TerrainLayer.CELL_SIZE.height - 20){
            
            return true;
        }

        return false;
    }
    private boolean nextButtonFromCoordinate(Point coordinate){
        int x = (int)coordinate.getX();
        int y = (int)coordinate.getY();

        if(x >= this.button.getVirtualWidth() - TerrainLayer.CELL_SIZE.width + 10 &&
            x <= this.button.getVirtualWidth() - TerrainLayer.CELL_SIZE.width + 10 + TerrainLayer.CELL_SIZE.width - 20 &&
            y >= this.button.getVirtualHeight() - TerrainLayer.CELL_SIZE.height &&
            y <= this.button.getVirtualHeight() - TerrainLayer.CELL_SIZE.height + TerrainLayer.CELL_SIZE.height - 20){

            return true;
        }

        return false;
    }

    private int actionFromCoordinate(Point coordinate){
        int x = (int)coordinate.getX();
        int y = (int)coordinate.getY();

        return 0;
    }
}
