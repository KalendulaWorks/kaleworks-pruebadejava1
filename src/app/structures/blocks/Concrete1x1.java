package app.structures.blocks;

import app.GamePanel;
import app.structures.*;

import java.awt.*;

/*
 * Bloque de Concreto 1x1.
 *
 * Esta estructura ocupa exactamente 1x1 tiles.
 * Su draw() dibuja un rectángulo de tamaño TILE_SIZE x TILE_SIZE
 * empezando en la posición (x, y) de la celda origen.
 */
public class Concrete1x1 extends Structure {

  public Concrete1x1() {
    // collide = false, widthInTiles = 1, heightInTiles = 1
    super(false, 1, 1);

    infoDeStructure(
        "Bloque de Concreto 1x1",
        "Concrete1x1",
        "Un bloque de Concreto de 1x1 Tile",
        "False",
        "No es interactuable");
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

  @Override
  public void draw(Graphics g, int x, int y) {
    int width = gStructureWidth() * GamePanel.TILE_SIZE;   // 1 * TILE_SIZE
    int height = gStructureHeight() * GamePanel.TILE_SIZE; // 1 * TILE_SIZE
    dibujarConcreto(g, x, y, width, height);
  }
}
