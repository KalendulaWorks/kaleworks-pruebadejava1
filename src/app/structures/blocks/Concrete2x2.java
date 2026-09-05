package app.structures.blocks;

import app.GamePanel;
import app.structures.*;

import java.awt.*;

/*
 * Bloque de Concreto 2x2.
 *
 * Esta estructura ocupa 2 columnas x 2 filas de tiles.
 * draw() dibuja un rectángulo de tamaño
 *   width  = 2 * TILE_SIZE
 *   height = 2 * TILE_SIZE
 * empezando en la posición (x, y) de la celda ORIGEN.
 *
 * Gracias a que GamePanel solo dibuja la estructura en la celda origen,
 * no se repite el dibujo en las otras celdas del área; pero la matriz
 * de bloques sí contiene la misma referencia en cada celda para poder
 * detectar colisiones y ocupación del espacio.
 */
public class Concrete2x2 extends Structure {

  public Concrete2x2() {
    // collide = false, widthInTiles = 2, heightInTiles = 2
    super(false, 2, 2);

    infoDeStructure(
        "Bloque de Concreto 2x2",
        "Concrete2x2",
        "Un bloque de Concreto de 2x2 Tiles",
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

  @Override
  public void draw(Graphics g, int x, int y) {
    int width = gStructureWidth() * GamePanel.TILE_SIZE;   // 2 * TILE_SIZE
    int height = gStructureHeight() * GamePanel.TILE_SIZE; // 2 * TILE_SIZE
    dibujarConcreto(g, x, y, width, height);
  }
}
