package gui.menu;

import javax.swing.JComponent;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.*;

public class PaintMenu extends JComponent {
  private Image fond;

  public PaintMenu() {
    super();
    fond = Toolkit.getDefaultToolkit().getImage("fond.png");
  }

  @Override
  public void paintComponent(Graphics pinceau) {

    Graphics secondPinceau = pinceau.create();

    if (this.isOpaque()) {

      secondPinceau.setColor(this.getBackground());
      secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    secondPinceau.setColor(Color.BLUE);
    secondPinceau.setFont(new Font("Comics sans Ms", Font.BOLD, 70));
    secondPinceau.drawString("Kahmate", 300, 100);

    secondPinceau.drawImage(fond, 150, 180, this);
  }
}