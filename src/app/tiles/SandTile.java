package app.tiles;

import app.GamePanel;
import javax.swing.*;
import java.awt.*;

public class SandTile extends Tile {
  public SandTile() {

    super(); // como grasstile hereda tile entonces uso super en lugar de this para
             // constructor creo

    infoDeTile(
        "Arena",
        "SandTile",
        "Suelo de Arena");

  }

  private void dibujarArena(Graphics g, int x, int y, int width, int height) {
    g.setColor(new Color(242, 235, 174));
    g.fillRect(x, y, width, height);
    g.setColor(new Color(255, 241, 115));
    g.drawRect(x, y, width - 2, height - 2);

    for (int granos = 0; granos < width / 2; granos++) {
      for (int granosY = 0; granosY < height / 2; granosY++) {
        int gx = x + granos * 2;
        int gy = y + granosY * 2;
        g.setColor(new Color(94, 85, 4));
        g.fillRect(gx, gy, 1, 1);
      }
    }

  }

  public void draw(Graphics g, int x, int y) {
    // aquí va el dibujo del tile, relativo a (x, y)
    int width = 1 * GamePanel.TILE_SIZE;
    int height = 1 * GamePanel.TILE_SIZE;
    dibujarArena(g, x, y, width, height);
  }
}
