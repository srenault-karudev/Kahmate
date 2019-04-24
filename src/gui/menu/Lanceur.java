package gui.menu;

import javax.swing.*;
import java.awt.*;

public class Lanceur extends JFrame {

    /**
     * The manager that allows to have our graphic components.
     */
    private CardLayout cardLayout;
    /**
     * The panel that will contain the menu of the game.
     */
    private Menu menu;
    /**
     * The panel that will contain the rules of the game.
     */
    private Rules regle;
    /**
     * The panel that will contain the end of the game.
     */
    private End end;

    public Lanceur() {
        super("Kahmate");
        this.setSize(1200, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);
        menu = new Menu(cardLayout, this);
        regle = new Rules(cardLayout, this);
        end = new End(cardLayout, this);
        this.add(menu, "MENU");
        this.add(regle, "REGLE");
        this.add(end, "END");

        this.setVisible(true);

    }

}
