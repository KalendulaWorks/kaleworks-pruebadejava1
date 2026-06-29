package app.structures.blocks;

import app.GamePanel;
import app.structures.*;

import javax.swing.*;
import java.awt.*;

public class Concrete2x1 extends Structure {

  public Concrete2x1() {

    super(false,
        2,
        1);

    // como grasstile hereda tile entonces uso super en lugar de this para
    // constructor creo
    infoDeStructure(
        "Bloque de Concreto 2x2",
        "Concrete2x2",
        "Un bloque de Concreto de 2x2 Tile",
        "False",
        "No es interactuable");

  }

  private void dibujarConcreto(Graphics g, int x, int y, int width, int height) {
    g.setColor(new Color(135, 135, 135));
    g.fillRect(x, y, width, height);
    g.setColor(new Color(155, 155, 155));
    g.drawRect(x, y, width - 2, height - 2);

    for (int granos = 0; granos < width / 4; granos++) {
      for (int granosY = 0; granosY < height / 4; granosY++) {
        int gx = x + granos * 3;
        int gy = y + granosY * 3;
        g.setColor(new Color(33, 33, 33));
        g.fillRect(gx, gy, 1, 1);
      }
    }

  }

  public void draw(Graphics g, int x, int y) {
    // aquí va el dibujo del tile, relativo a (x, y)
    int width = gStructureWidth() * GamePanel.TILE_SIZE;
    int height = gStructureHeight() * GamePanel.TILE_SIZE;
    dibujarConcreto(g, x, y, width, height);
  }
}
