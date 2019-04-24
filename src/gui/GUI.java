package gui;

import gui.api.*;
import gui.controllers.*;
import gui.layers.*;
import gui.models.*;
import engine.api.*;
import engine.*;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class GUI implements IGUI, IActor{

    private IEngine engine;

    private ArrayList<VisualPlayer> players;
    private ArrayList<VisualArea> areas;
    private ArrayList<VisualBall> balls;
    private VisualCard[] cards;

    private TerrainLayer terrainLayer;
    private PlayersLayer playersLayer;
    private AreaLayer areaLayer;
    private BallLayer ballLayer;
    private CardLayer cardLayer;
    private ButtonLayer buttonLayer;
    private BlackLayer blackLayer;

    private MouseController controller;

    public GUI(IEngine engine){
        this.engine = engine;

        //Load images
        Images.load();

        //Initialize
        this.players = new ArrayList<>();
        this.areas = new ArrayList<>();
        this.balls = new ArrayList<>();
        this.cards = new VisualCard[]{
            new VisualCard(Images.get(Images.CARD_FORM_1)),
            new VisualCard(Images.get(Images.CARD_FORM_2)),
            new VisualCard(Images.get(Images.CARD_FORM_3)),
            new VisualCard(Images.get(Images.CARD_FORM_4)),
            new VisualCard(Images.get(Images.CARD_FORM_5)),
            new VisualCard(Images.get(Images.CARD_FORM_1)),
            new VisualCard(Images.get(Images.CARD_FORM_2)),
            new VisualCard(Images.get(Images.CARD_FORM_3)),
            new VisualCard(Images.get(Images.CARD_FORM_4)),
            new VisualCard(Images.get(Images.CARD_FORM_5))
        };

        this.terrainLayer = new TerrainLayer();
        this.playersLayer = new PlayersLayer(this.players);
        this.areaLayer = new AreaLayer();
        this.ballLayer = new BallLayer(this.balls);
        this.cardLayer = new CardLayer(this.cards);
        this.buttonLayer = new ButtonLayer();
        this.blackLayer = new BlackLayer();

        //Create frames
        JFrame frame = new JFrame();

        JPanel panel = new JPanel();
        panel.setLayout(new OverlayLayout(panel));

        this.controller = new MouseController(this, engine, this.areaLayer, this.cardLayer, this.buttonLayer);
        KeyboardController keyboardController = new KeyboardController(this, this.areaLayer, this.cardLayer);

        panel.addMouseMotionListener(this.controller);
        panel.addMouseListener(this.controller);
        panel.setFocusable(true);
        panel.addKeyListener(keyboardController);

        panel.add(this.blackLayer);
        panel.add(this.buttonLayer);
        panel.add(this.cardLayer);
        panel.add(this.ballLayer);
        panel.add(this.playersLayer);
        panel.add(this.areaLayer);
        panel.add(this.terrainLayer);

        this.blackLayer.setVisible(false);
        
        frame.add(panel);
        frame.setSize(1024, 576);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Create new player entity associated to
     * a graphical resource
     * @param position : initial player position
     * @param image : main image
     * @param tackled : tackled image
     * @return : player handler
     */
    @Override
    public int createPlayer(Point position, String image, String tackled){
        VisualPlayer player = new VisualPlayer(Images.get(image), Images.get(tackled), position, false);
        this.players.add(player);

        return this.players.size() - 1;
    }
    /**
     * Control if the player is turned or not
     * @param id : player entity id
     * @param toggle : turned or not
     */
    @Override
    public void setTacklPlayer(int id, boolean toggle){
        this.players.get(id).setTackled(toggle);
    }
    /**
     * Move graphical player
     * @param id : player id
     * @param position : new player position
     */
    @Override
    public void movePlayer(int id, Point position){

        VisualPlayer player = this.players.get(id);

        boolean redrawBall = false;
        for(VisualBall ball : this.balls){
            if(ball.isAttached()){
                redrawBall = true;
                break;
            }
        }

        //Run animation
        if(!player.getPosition().equals(position)){
            player.setNextPosition(position);
            Thread thread;
            if(redrawBall){
                thread = new Thread(new AnimationRunnable(new Layer[]{this.playersLayer, this.ballLayer}, 0.5));
            } else{
                thread = new Thread(new AnimationRunnable(new Layer[]{this.playersLayer}, 0.5));
            }
            thread.start();
            try{
                thread.join();
            } catch(InterruptedException exception){
                System.out.println("Failed to join");
            }
            player.setPosition(position);
        }
    }

    /**
     * Create visual ball object
     * @param position initial ball position
     * @return ball id
     */
    @Override
    public int createBall(Point position){
        VisualBall ball = new VisualBall(position, null);
        this.balls.add(ball);

        return this.balls.size() - 1;
    }
    /**
     * Move ball graphical entity
     * @param position : new ball position
     * @param id : ball id
     */
    @Override
    public void moveBall(int id , Point position){

        VisualBall ball = this.balls.get(id);

        if(!ball.isAttached()){
            //Run animation
            if(!ball.getPosition().equals(position)){
                ball.setNextPosition(position);
                Thread thread = new Thread(new AnimationRunnable(new Layer[]{this.ballLayer}, 0.5));
                thread.start();
                try{
                    thread.join();
                } catch(InterruptedException exception){
                    System.out.println("Failed to join");
                }
                ball.setPosition(position);
            }
        }
    }
    /**
     * Attach ball to a player. When the player move,
     * the ball move also.
     * @param player : player entity id
     * @param ball : ball id
     */
    @Override
    public void attachBall(int ball, int player){
        this.balls.get(ball).attachTo(this.players.get(player));
    }
    /**
     * Detach ball from player
     */
    @Override
    public void detachBall(int id){
        this.balls.get(id).attachTo(null);
    }

    /**
     * Change selection area
     * @param areas : areas list
     */
    @Override
    public void setArea(VisualArea[] areas){
        this.areaLayer.setAreas(areas);
        this.areaLayer.repaint();
    }

    /**
     * Control if the card has already been use
     * @param card : card number
     * @param toggle : use or not
     */
    @Override
    public void toggleCard(int card, boolean toggle){
        this.cards[card].setActive(toggle);
        this.cardLayer.repaint();
    }
    /**
     * Display card deck or not
     * @param toggle : control
     */
    @Override
    public void toggleCardDeck(boolean toggle){
        Thread thread = new Thread(new AnimationRunnable(new Layer[]{this.cardLayer}, 1.0));
        thread.start();
        try{
            thread.join();
        } catch(InterruptedException exception){
            System.out.println("Failed to join");
        }
        this.cardLayer.showCard(!this.cardLayer.isShowed());
    }

    /**
     * Show black screen or not
     * 
     * @param toggle  : control
     * @param actor : active actor (eg: "player 1")
     */
    @Override
    public void toggleBlackScreen(boolean toggle, int actor){
        this.blackLayer.setVisible(toggle);
        if(actor == 0){
            this.blackLayer.setMessage("Player 1", Color.BLUE);
        } else if(actor == 1){
            this.blackLayer.setMessage("Player 2", Color.RED);
        }
        this.blackLayer.repaint();
    }

    /**
     * Show victory screen
     * @param team team id
     */
    public void showVictory(int team){
        this.blackLayer.setVisible(true);
        if(team == 0){
            this.blackLayer.setMessage("Blue won !", Color.BLUE);
        } else if(team == 1){
            this.blackLayer.setMessage("Red won !", Color.RED);
        }
        this.blackLayer.repaint();
    }


    /**
     * Classical turn play by an actor, actions should be thrown to the
     * engine
     */
    public void performActions(){

    }
    
    /**
     * Card duel is triggered by the other player
     * 
     * @return : card number returned
     */
    public void performDuel(int actor){
        JPanel panel = new JPanel();
        String message = "";
        if(actor == 0){
            message = "Player 1:";
        } else if(actor == 1){
            message = "Player 2:";
        }
        panel.add(new JLabel(message));
        DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<Integer>();

        GameInformation gi = this.engine.getGameInformation();
        for(int i = 0; i < Engine.CARD_COUNT; i++){
            if(!gi.cards[actor][i].used){
                model.addElement(new Integer(i + 1));
            }
        }
        JComboBox<Integer> comboBox = new JComboBox<Integer>(model);
        panel.add(comboBox);

        JOptionPane.showConfirmDialog(null, panel, "Dual", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        int card = ((Integer)comboBox.getSelectedItem()).intValue() - 1;
        engine.sendCard(card);
    }
}