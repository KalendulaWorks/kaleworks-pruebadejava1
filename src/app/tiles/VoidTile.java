package app.tiles;

import app.GamePanel;
import javax.swing.*;
import java.awt.*;

public class VoidTile extends Tile {
  public VoidTile() {
    super(); // como grasstile hereda tile entonces uso super en lugar de this para
             // constructor creo
    infoDeTile(
        "Vacio",
        "VoidTile",
        "Tile sin nada esperando a ser rellenado");

  }

  private void dibujarVoid(Graphics g, int x, int y, int width, int height) {

  }

  public void draw(Graphics g, int x, int y) {
    // aquí va el dibujo del tile, relativo a (x, y)
    int width = GamePanel.TILE_SIZE;
    int height = GamePanel.TILE_SIZE;
    dibujarVoid(g, x, y, width, height);
  }
}
