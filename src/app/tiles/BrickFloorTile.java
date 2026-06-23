package app.tiles;

import app.GamePanel;
import javax.swing.*;
import java.awt.*;

public class BrickFloorTile extends Tile {
  public BrickFloorTile() {

    super(); // como grasstile hereda tile entonces uso super en lugar de this para
             // constructor creo
    infoDeTile(
        "Piso de ladrillos",
        "BrickFloorTile",
        "Piso de ladrillos 1x1");

  }

  private void dibujarConcreto(Graphics g, int x, int y, int width, int height) {
    g.setColor(new Color(122, 37, 22));
    g.fillRect(x, y, width, height);
    g.setColor(new Color(155, 67, 59));
    g.drawRect(x, y, width - 2, height - 2);

    for (int granos = 0; granos < width / 2; granos++) {
      for (int granosY = 0; granosY < height / 2; granosY++) {
        int gx = x + granos * 2;
        int gy = y + granosY * 2;
        g.setColor(new Color(31, 7, 4));
        g.fillRect(gx, gy, 1, 1);
      }
    }

  }

  public void draw(Graphics g, int x, int y) {
    // aquí va el dibujo del tile, relativo a (x, y)
    int width = GamePanel.TILE_SIZE;
    int height = GamePanel.TILE_SIZE;
    dibujarConcreto(g, x, y, width, height);
  }
}
