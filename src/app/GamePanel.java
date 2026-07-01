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
  private MouseHoverHandler mouseHoverHandler;
  private ActualizarGUI actualizarGUI;

  // Usamos MapEditor como editor unificado de tiles + estructuras
  private MapEditor mapEditor;

  public GamePanel() {
    this.setPreferredSize(new Dimension(1280, 720));
    this.setBackground(Color.DARK_GRAY);

    // Inicializar componentes base
    mapaManager = new MapaManager();
    mouseHandler = new MouseHandler();
    mouseHoverHandler = new MouseHoverHandler(this);
    actualizarGUI = new ActualizarGUI(this, mouseHoverHandler);

    // Editor unificado
    mapEditor = new MapEditor();

    // KeyboardHandler ahora solo necesita el MapEditor
    keyboardHandler = new KeyboardHandler(mapEditor);

    // Registrar listeners
    this.addMouseListener(mouseHandler);
    this.addMouseMotionListener(mouseHandler);
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

        // Primero el piso
        cuadros[col][row].draw(g, x, y);

        // Luego la estructura, si existe
        if (bloques[col][row] != null) {
          bloques[col][row].draw(g, x, y);
        }
      }
    }

    // Grid de debug
    g.setColor(Color.BLACK);
    for (int col = 0; col <= mapCols; col++) {
      int x = col * TILE_SIZE;
      g.drawLine(x, 0, x, mapRows * TILE_SIZE);
    }
    for (int row = 0; row <= mapRows; row++) {
      int y = row * TILE_SIZE;
      g.drawLine(0, y, mapCols * TILE_SIZE, y);
    }

    actualizarGUI.drawGui(g);
  }

  // Getters usados por EditorBootstrap

  public MapaManager getMapaManager() {
    return mapaManager;
  }

  public MouseHandler getMouseHandler() {
    return mouseHandler;
  }

  public MouseHoverHandler getMouseHoverHandler() {
    return mouseHoverHandler;
  }

  public MapEditor getMapEditor() {
    return mapEditor;
  }
}
