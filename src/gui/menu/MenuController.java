package gui.menu;

import javax.swing.*;

import engine.Engine;
import engine.api.IEngine;

import java.awt.*;
import java.awt.event.*;

public class MenuController implements ActionListener {

	/**
    *The panel that will contain the rules of the game.
    */
	private Menu menu;
    /**
    *The manager that allows to have our graphic components.
    */
	private CardLayout cardLayout;
    /**
    *The principal JFrame that contains our components.
    */
	private JFrame jFrame;

	public MenuController(Menu m, CardLayout cl, JFrame jf) {
		this.menu = m;
		this.cardLayout = cl;
		this.jFrame = jf;
	}
	/**
    *perform an action based on the button pressed.
    */

	public void actionPerformed(ActionEvent e) {
		String nomButton = e.getActionCommand();

		if (nomButton.equals("1 joueur")) {
			IEngine engine = new Engine();
			engine.play();
			this.jFrame.dispose();
		} else if (nomButton.equals("2 joueurs")) {
			IEngine engine = new Engine();
			engine.play();
			this.jFrame.dispose();
		} else if (nomButton.equals("Règles")) {
			cardLayout.show(jFrame.getContentPane(), "END");
		} else if (nomButton.equals("Quitter")) {
			this.jFrame.dispose();

		}

	}
}