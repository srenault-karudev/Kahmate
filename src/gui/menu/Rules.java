package gui.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Rules extends JPanel {

    /**
     * Statement of the return button to allow to retunr to the Menu
     */
    private JButton retour;

    /**
     * Statement of the Rules JLabel
     */
    private JLabel Rules;

    /**
     * Statement of the rules1 JLabel
     */
    private JLabel rules1;

    /**
     * Statement of the rules2 JLabel
     */
    private JLabel rules2;

    /**
     * Statement of the rules3 JLabel
     */
    private JLabel rules3;

    /**
     * Statement of the rules4 JLabel
     */
    private JLabel rules4;

    /**
     * Statement of the rules5 JLabel
     */
    private JLabel rules5;

    /**
     * Statement of the rules6 JLabel
     */
    private JLabel rules6;

    /**
     * Statement of the rules7 JLabel
     */
    private JLabel rules7;

    /**
     * Statement of the cardlayout who is the administrator of JPanel
     */
    private CardLayout cardLayout;

    /**
     * Statement of the Jframe
     */
    private JFrame jFrame;

    /**
     * Constructor of the class Rules
     */
    public Rules(CardLayout cl, JFrame jf) {

        cardLayout = cl;
        jFrame = jf;
        /**
         * Calls to the method initComponents
         */
        initComponents();

    }

    /**
     * The method initComponents initialize all the elements of my JFrame and
     * position them
     */
    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        Rules = new JLabel();
        retour = new JButton();
        rules1 = new JLabel();
        rules2 = new JLabel();
        rules3 = new JLabel();
        rules4 = new JLabel();
        rules5 = new JLabel();
        rules6 = new JLabel();
        rules7 = new JLabel();

        setMinimumSize(new Dimension(750, 500));
        setPreferredSize(new Dimension(750, 500));
        setLayout(new GridBagLayout());

        Rules.setFont(new Font("Tahoma", 1, 36));
        Rules.setForeground(new Color(255, 51, 51));
        Rules.setText("Rules du jeu : ");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 19;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(36, 56, 0, 0);
        add(Rules, gridBagConstraints);

        retour.setText("return");

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 25;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(56, 32, 64, 0);
        add(retour, gridBagConstraints);
        retour.addActionListener(new RulesController(this, cardLayout, jFrame));

        rules1.setFont(new Font("Tahoma", 0, 20));
        rules1.setText("Le jeu de Kahmate est un jeu de plateau ou 2 equipes de 6 joueurs ");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(51, 32, 0, 0);
        add(rules1, gridBagConstraints);

        rules2.setFont(new Font("Tahoma", 0, 20));
        rules2.setText("de differentes couleurs s'affrontent. L'objecif du jeu est de marquer ");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(18, 32, 0, 0);
        add(rules2, gridBagConstraints);

        rules3.setFont(new Font("Tahoma", 0, 20));
        rules3.setText("un essai c'est-a-dire déplacer le ballon avec un joueur dans le camp ");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(18, 32, 0, 0);
        add(rules3, gridBagConstraints);

        rules4.setFont(new Font("Tahoma", 0, 20));
        rules4.setText("adversaire. Le jeu se joue en tour par tour. Le capitaine chaque tour");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(18, 32, 0, 0);
        add(rules4, gridBagConstraints);

        rules5.setFont(new Font("Tahoma", 0, 20));
        rules5.setText("peut deplacer 1 ou 2 joueurs, faire des passes en arriere, faire un ");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(18, 32, 0, 0);
        add(rules5, gridBagConstraints);

        rules6.setFont(new Font("Tahoma", 0, 20));
        rules6.setText("plaquage, un coup de pied ou marquer un essai. L'action de marquer ");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(18, 32, 0, 49);
        add(rules6, gridBagConstraints);

        rules7.setFont(new Font("Tahoma", 0, 20));
        rules7.setText("un essai termine le jeu et l'equipe qui l'a realiser gagne.");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(18, 32, 0, 0);
        add(rules7, gridBagConstraints);
    }

}
