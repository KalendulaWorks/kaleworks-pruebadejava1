package app.structures.blocks;

import app.GamePanel;
import app.structures.*;

import javax.swing.*;
import java.awt.*;

public class Concrete1x1 extends Structure {

  public Concrete1x1() {

    super(false); // como grasstile hereda tile entonces uso super en lugar de this para
    // constructor creo
    infoDeStructure(
        "Bloque de Concreto 1x1",
        "Concrete1x1",
        "Un bloque de Concreto de 1x1 Tile",
        "False",
        "");

  }

  private void dibujarConcreto(Graphics g, int x, int y, int width, int height) {
    g.setColor(new Color(135, 135, 135));
    g.fillRect(x, y, width, height);
    g.setColor(new Color(155, 155, 155));
    g.drawRect(x, y, width - 2, height - 2);

    for (int granos = 0; granos < width / 2; granos++) {
      for (int granosY = 0; granosY < height / 2; granosY++) {
        int gx = x + granos * 2;
        int gy = y + granosY * 2;
        g.setColor(new Color(33, 33, 33));
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
