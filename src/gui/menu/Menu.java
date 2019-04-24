package gui.menu;

import javax.swing.*;
import java.awt.*;

/**
 * La classe <code>Menu</code>  affiche le menu principal du jeu.
 *
 */

public class Menu extends JPanel {
	 /**
    * The Distribution Manager of our components.
    */
	private GridBagConstraints GridBagConstraints;
	/**
	* The Button to access the mode one player.
	*/
	private JButton onePlayer;
	/**
	* The Button to access the mode two players.
	*/
	private JButton twoPlayers;
	/**
	* The Button to access the rules.
	*/
	private JButton rules;
	/**
	* The Button to leave the game.
	*/
	private JButton leave;
	/**
	* The Title of the game.
	*/
	private JLabel title;
	/**
	* The Title of the game.
	*/
	private CardLayout cardLayout;
	/**
    *The manager that allows to have our graphic components.
    */
	private JFrame jFrame;
	 /**
    *The principal JFrame that contains our components.
    */

	public Menu(CardLayout cl, JFrame jf) {
		super();
		this.cardLayout = cl;
		jFrame = jf;

		this.title = new JLabel("Kahmate");
		this.title.setFont(new Font("Comics sans Ms", Font.BOLD, 40));
		this.title.setForeground(new Color(128, 202, 42));
		this.add(title);
		this.onePlayer = new JButton("1 joueur");
		this.add(onePlayer);
		this.twoPlayers = new JButton("2 joueurs");
		this.add(twoPlayers);
		this.rules = new JButton("Règles");
		this.add(rules);
		this.leave = new JButton("Quitter");

		this.GridBagConstraints = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		this.GridBagConstraints.insets = new Insets(40, 0, 0, 0);

		this.GridBagConstraints.gridx = 0;
		this.GridBagConstraints.gridy = 1;
		this.GridBagConstraints.ipadx = 85;
		this.GridBagConstraints.ipady = 35;
		this.GridBagConstraints.fill = GridBagConstraints.BOTH;
		this.add(this.onePlayer, this.GridBagConstraints);

		this.GridBagConstraints.gridx = 0;
		this.GridBagConstraints.gridy = 2;
		this.GridBagConstraints.ipadx = 85;
		this.GridBagConstraints.ipady = 35;
		this.GridBagConstraints.fill = GridBagConstraints.BOTH;
		this.add(this.twoPlayers, this.GridBagConstraints);

		this.GridBagConstraints.gridx = 0;
		this.GridBagConstraints.gridy = 3;
		this.GridBagConstraints.ipadx = 85;
		this.GridBagConstraints.ipady = 35;
		this.GridBagConstraints.fill = GridBagConstraints.BOTH;
		this.add(this.rules, this.GridBagConstraints);

		this.GridBagConstraints.gridx = 0;
		this.GridBagConstraints.gridy = 4;
		this.GridBagConstraints.ipadx = 85;
		this.GridBagConstraints.ipady = 35;
		this.GridBagConstraints.fill = GridBagConstraints.BOTH;
		this.add(this.leave, this.GridBagConstraints);

		MenuController MenuController = new MenuController(this, cardLayout, jFrame);
		this.onePlayer.addActionListener(MenuController);
		this.twoPlayers.addActionListener(MenuController);
		this.rules.addActionListener(MenuController);
		this.leave.addActionListener(MenuController);

		// PaintMenu paintMenu = new PaintMenu();
		// this.add(paintMenu,BorderLayout.CENTER);

	}

}