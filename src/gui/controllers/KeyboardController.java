package gui.controllers;

import gui.GUI;
import gui.layers.*;
import gui.models.*;

import java.awt.*;
import java.awt.event.*;

public class KeyboardController implements KeyListener{

    private GUI gui;
    private AreaLayer area;
    private CardLayer card;

    public KeyboardController(
        GUI gui,
        AreaLayer area,
        CardLayer card
    ){
        this.gui = gui;
        this.area = area;
        this.card = card;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            this.area.setSelectionArea(null);
            this.area.repaint();
            this.gui.toggleCardDeck(!this.card.isShowed());
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}
