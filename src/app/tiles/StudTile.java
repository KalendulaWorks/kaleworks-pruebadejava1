package app.tiles;

import app.GamePanel;
import javax.swing.*;
import java.awt.*;

public class StudTile extends Tile {
  public StudTile() {

    super(); // como grasstile hereda tile entonces uso super en lugar de this para
             // constructor creo
    infoDeTile(
        "Piso de Stud",
        "StudTile",
        "Piso de plastico con una R, referencia");

  }

  private void dibujarStud(Graphics g, int x, int y, int width, int height) {
    g.setColor(new Color(3, 40, 252));
    g.fillRect(x, y, width, height);
    g.setColor(new Color(5, 100, 156));
    g.fillOval(x + (width / 4), y + (height / 4), width / 2, height / 2);
    g.setColor(Color.BLACK);
    g.drawString("R", x + (width / 2) - 4, y + (height / 2) + 6);
  }

  public void draw(Graphics g, int x, int y) {
    // aquí va el dibujo del tile, relativo a (x, y)
    int width = 1 * GamePanel.TILE_SIZE;
    int height = 1 * GamePanel.TILE_SIZE;
    dibujarStud(g, x, y, width, height);
  }
}
