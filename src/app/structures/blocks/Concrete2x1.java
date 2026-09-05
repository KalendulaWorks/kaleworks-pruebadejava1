package app.structures.blocks;

import app.GamePanel;
import app.structures.*;

import java.awt.*;

/*
 * Bloque de Concreto 2x1.
 *
 * Esta estructura ocupa 1 columna x 2 filas de tiles.
 * draw() dibuja un rectángulo de tamaño
 *   width = 1 * TILE_SIZE
 *   height = 2 * TILE_SIZE
 * empezando en la posición (x, y) de la celda ORIGEN.
 */
public class Concrete2x1 extends Structure {

  public Concrete2x1() {
    // collide = false, widthInTiles = 1, heightInTiles = 2
    super(false, 1, 2);

    infoDeStructure(
        "Bloque de Concreto 2x1",
        "Concrete2x1",
        "Un bloque de Concreto de 1x2 Tiles",
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
    int height = gStructureHeight() * GamePanel.TILE_SIZE; // 2 * TILE_SIZE
    dibujarConcreto(g, x, y, width, height);
  }
}
