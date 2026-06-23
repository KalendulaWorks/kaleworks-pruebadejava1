package app;

import javax.swing.*;
import java.awt.*;

import app.func.*;
import app.tiles.*;
import app.util.*;
import app.editor.*;
import app.structures.*;

public class GamePanel extends JPanel {

  public static final int TILE_SIZE = 32;

  private MapaManager mapaManager;
  private MouseHandler mouseHandler;
  private KeyboardHandler keyboardHandler;
  private TileEditor tileEditor;
  private MouseHoverHandler mouseHoverHandler;

  public GamePanel() {
    this.setPreferredSize(new Dimension(1280, 720));
    this.setBackground(Color.DARK_GRAY);
    mapaManager = new MapaManager();
    mouseHandler = new MouseHandler();
    mouseHoverHandler = new MouseHoverHandler(this);
    tileEditor = new TileEditor();
    keyboardHandler = new KeyboardHandler(tileEditor);
    this.addMouseListener(mouseHandler);
    this.addKeyListener(keyboardHandler);
    this.setFocusable(true);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Tile[][] cuadros = mapaManager.getPiso();
    Structure[][] bloques = mapaManager.getBloques();

    int mapCols = cuadros.length;
    int mapRows = cuadros[0].length;
    for (int col = 0; col < mapCols; col++) {
      for (int row = 0; row < mapRows; row++) {
        int x = col * TILE_SIZE;
        int y = row * TILE_SIZE;
        cuadros[col][row].draw(g, x, y);
      }
    }

    g.setColor(Color.BLACK);
    for (int col = 0; col <= mapCols; col++) {
      int x = col * TILE_SIZE;
      g.drawLine(x, 0, x, mapRows * TILE_SIZE);
    }

    for (int row = 0; row <= mapRows; row++) {
      int y = row * TILE_SIZE;
      g.drawLine(0, y, mapCols * TILE_SIZE, y);
    }

  }

  public MapaManager getMapaManager() {
    return mapaManager;
  }

  public MouseHandler getMouseHandler() {
    return mouseHandler;
  }

  public TileEditor getTileEditor() {
    return tileEditor;
  }

  public MouseHoverHandler getMouseHoverHandler() {
    return mouseHoverHandler;
  }
}
