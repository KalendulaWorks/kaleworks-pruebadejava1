package app.tiles;

import app.GamePanel;
import javax.swing.*;
import java.awt.*;

public class GrassTile extends Tile {
  public GrassTile() {

    super(); // como grasstile hereda tile entonces uso super en lugar de this para
             // constructor creo
    infoDeTile(
        "Cesped",
        "GrassTile",
        "Tile de cesped 1x1");
  }

  private void dibujarGrass(Graphics g, int x, int y, int width, int height) {
    g.setColor(Color.GREEN);
    g.fillRect(x, y, width, height);
    for (int granos = 0; granos < width / 2; granos++) {
      for (int granosY = 0; granosY < height / 2; granosY++) {
        int gx = x + granos * 2;
        int gy = y + granosY * 2;
        g.setColor(new Color(4, 94, 40));
        g.fillRect(gx, gy, 1, 1);
      }
    }

  }

  public void draw(Graphics g, int x, int y) {
    // aquí va el dibujo del tile, relativo a (x, y)
    int width = GamePanel.TILE_SIZE;
    int height = GamePanel.TILE_SIZE;
    dibujarGrass(g, x, y, width, height);
  }
}
