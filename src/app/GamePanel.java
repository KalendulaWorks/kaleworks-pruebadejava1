package app;

import app.editor.*;
import app.func.*;
import app.structures.*;
import app.tiles.*;
import app.util.*;
import java.awt.*;
import javax.swing.*;

/*
 * GamePanel es el lienzo principal del juego/editor.
 *
 * El loop de dibujo tiene DOS pasadas separadas:
 *
 *   PASADA 1 - Tiles:
 *     Recorre todas las celdas y dibuja el tile de piso de cada una.
 *     Tiene que hacerse completa antes de dibujar estructuras para
 *     que ningun tile tape una estructura grande.
 *
 *   PASADA 2 - Estructuras:
 *     Recorre todas las celdas y dibuja la estructura SOLO si la
 *     celda actual es el ORIGEN de esa estructura
 *     (s.gOriginCol() == col && s.gOriginRow() == row).
 *     De esta forma una Concrete2x2 se dibuja UNA sola vez pero
 *     ocupa visualmente 2x2 tiles completos, sin ser tapada por
 *     los tiles de las otras 3 celdas del area.
 *
 *   PASADA 3 - Grid de debug.
 *   PASADA 4 - GUI de debug (texto hover).
 */
public class GamePanel extends JPanel {

  public static final int TILE_SIZE = 32;

  private MapaManager mapaManager;
  private MouseHandler mouseHandler;
  private KeyboardHandler keyboardHandler;
  private MouseHoverHandler mouseHoverHandler;
  private ActualizarGUI actualizarGUI;
  private MapEditor mapEditor;

  public GamePanel() {
    this.setPreferredSize(new Dimension(1280, 720));
    this.setBackground(Color.DARK_GRAY);

    mapaManager       = new MapaManager();
    mouseHandler      = new MouseHandler();
    mouseHoverHandler = new MouseHoverHandler(this);
    actualizarGUI     = new ActualizarGUI(this, mouseHoverHandler);
    mapEditor         = new MapEditor();
    keyboardHandler   = new KeyboardHandler(mapEditor);

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

    // ---- PASADA 1: dibujar TODOS los tiles de piso ----------------------
    // Es obligatorio terminar esta pasada antes de dibujar estructuras.
    // Si mezclamos tiles y estructuras en el mismo loop, el tile de la
    // celda [col+1][row] se dibuja encima del area derecha de una
    // estructura 2x2, tapandola parcialmente.
    for (int col = 0; col < mapCols; col++) {
      for (int row = 0; row < mapRows; row++) {
        int x = col * TILE_SIZE;
        int y = row * TILE_SIZE;
        cuadros[col][row].draw(g, x, y);
      }
    }

    // ---- PASADA 2: dibujar estructuras SOLO desde su celda ORIGEN -------
    // Una estructura 2x2 llena 4 celdas en la matriz de bloques,
    // todas apuntando a la MISMA instancia.
    // Verificamos que col == s.gOriginCol() y row == s.gOriginRow()
    // para dibujarla exactamente una vez, desde la esquina superior
    // izquierda, con todo su ancho y alto en pixeles.
    for (int col = 0; col < mapCols; col++) {
      for (int row = 0; row < mapRows; row++) {
        Structure s = bloques[col][row];
        if (s != null && s.gOriginCol() == col && s.gOriginRow() == row) {
          int x = col * TILE_SIZE;
          int y = row * TILE_SIZE;
          s.draw(g, x, y);
        }
      }
    }

    // ---- PASADA 3: grid de debug ----------------------------------------
    g.setColor(Color.BLACK);
    for (int col = 0; col <= mapCols; col++) {
      int x = col * TILE_SIZE;
      g.drawLine(x, 0, x, mapRows * TILE_SIZE);
    }
    for (int row = 0; row <= mapRows; row++) {
      int y = row * TILE_SIZE;
      g.drawLine(0, y, mapCols * TILE_SIZE, y);
    }

    // ---- PASADA 4: GUI de debug -----------------------------------------
    actualizarGUI.drawGui(g);
  }

  public MapaManager getMapaManager() { return mapaManager; }
  public MouseHandler getMouseHandler() { return mouseHandler; }
  public MouseHoverHandler getMouseHoverHandler() { return mouseHoverHandler; }
  public MapEditor getMapEditor() { return mapEditor; }
  public ActualizarGUI getHoverGui() { return actualizarGUI; }
}
