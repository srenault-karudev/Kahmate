package gui.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RulesController implements ActionListener {

    /**
    *Statement of the rules Jpanel
    */
    private Rules rules;

    /**
    *Statement of the cardlayout who is the administrator of JPanel
    */  
    private CardLayout cardLayout;

    /**
    *Statement of the Jframe
    */
    private JFrame jFrame;

    /**
    *Constructor of the class RulesController
    */
    public RulesController(Rules r, CardLayout cl, JFrame jf) {
        this.rules = r;
        this.cardLayout = cl;
        this.jFrame = jf;
    }

    /**
    *perform an action based on the button pressed.
    */
    public void actionPerformed(ActionEvent e) {
        String nomButton = e.getActionCommand();
        if (nomButton.equals("Retour")) {
            cardLayout.show(jFrame.getContentPane(), "MENU");

        }

    }
}