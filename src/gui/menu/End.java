package gui.menu;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * La classe <code>End</code> affiche la fin du jeu.
 *
 */

public class End extends JPanel {

    /**
     * The Button to access the menu.
     */
    private JButton retour;
    /**
     * The Button to leave the game.
     */
    private JButton quitter;
    /**
     * The end game title.
     */
    private JLabel titre;
    /**
     * The felicitation text.
     */
    private JLabel felicitation;
    /**
     * The victory text;
     */
    private JLabel win;
    /**
     * The manager that allows to have our graphic components.
     */
    private CardLayout cardLayout;
    /**
     * The principal JFrame that contains our components.
     */
    private JFrame jFrame;

    public End(CardLayout cl, JFrame jf) {
        super();
        cardLayout = cl;
        jFrame = jf;
        this.initComponents();

    }

    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        titre = new JLabel();
        felicitation = new JLabel();
        win = new JLabel();
        retour = new JButton();
        quitter = new JButton();

        setMinimumSize(new Dimension(750, 500));
        setPreferredSize(new Dimension(750, 500));
        setLayout(new java.awt.GridBagLayout());

        titre.setFont(new java.awt.Font("Tahoma", 1, 48));
        titre.setText("Kahmate");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(63, 251, 0, 0);
        add(titre, gridBagConstraints);

        felicitation.setFont(new java.awt.Font("Tahoma", 2, 40));
        felicitation.setText("Félicitation");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 22;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(96, 251, 0, 0);
        add(felicitation, gridBagConstraints);

        win.setFont(new java.awt.Font("Tahoma", 0, 35));
        win.setText("Vous avez gagné la partie");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(49, 157, 0, 0);
        add(win, gridBagConstraints);

        retour.setFont(new java.awt.Font("Tahoma", 0, 25));
        retour.setText("retour Menu");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(120, 157, 68, 0);
        add(retour, gridBagConstraints);

        quitter.setFont(new java.awt.Font("Tahoma", 0, 25));
        quitter.setText("quitter");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(120, 106, 68, 188);
        add(quitter, gridBagConstraints);

        EndController EndController = new EndController(this, cardLayout, jFrame);
        this.retour.addActionListener(EndController);
        this.quitter.addActionListener(EndController);

        // EndController endController = new EndController(this);
        // this.retour.addActionListener(endController);
        // this.quitter.addActionListener(endController);

    }

}
