package gui.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class EndController implements ActionListener{

	/**
    *The panel that will contain the end of the game.
    */
	
	private End end;
	/**
    *The manager that allows to have our graphic components.
    */
    private CardLayout cardLayout;
     /**
    *The principal JFrame that contains our components.
    */
    private JFrame jFrame;
	

	public EndController(End e,CardLayout cl, JFrame jf){
		this.end=e;
		this.cardLayout = cl;
        this.jFrame = jf;
	}

	public void actionPerformed(ActionEvent e){
		String nomButton=e.getActionCommand();


		if(nomButton.equals("retour Menu")){
			 cardLayout.show(jFrame.getContentPane(), "MENU");
	}
	else if(nomButton.equals("quitter")){

		this.jFrame.dispose();
		

		}
}
}