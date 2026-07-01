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
  private StructureEditor structureEditor;
  private MouseHoverHandler mouseHoverHandler;
  private ActualizarGUI actualizarGUI;

  /*
   * actualizarEditor se declara como campo del panel (no como variable local)
   * porque ahora KeyboardHandler necesita una referencia a el para poder
   * llamar setModoEstructuras() cuando el usuario cambia de modo.
   *
   * El orden de construccion importa:
   *   1. Primero creamos todos los objetos que NO dependen de otros.
   *   2. Luego actualizarEditor, que depende de mapaManager, tileEditor,
   *      mouseHandler, mouseHoverHandler y structureEditor.
   *   3. Por ultimo keyboardHandler, que necesita actualizarEditor ya creado.
   *
   * Si intentaras crear keyboardHandler antes que actualizarEditor,
   * le estarias pasando null y cualquier llamada a setModoEstructuras() fallaria.
   */
  private ActualizarEditor actualizarEditor;

  public GamePanel() {
    this.setPreferredSize(new Dimension(1280, 720));
    this.setBackground(Color.DARK_GRAY);

    // --- Paso 1: objetos sin dependencias entre si ---
    mapaManager      = new MapaManager();
    mouseHandler     = new MouseHoverHandler(this).equals(null) ? null : new MouseHandler(); // solo separacion visual
    mouseHandler     = new MouseHandler();
    mouseHoverHandler = new MouseHoverHandler(this);
    tileEditor       = new TileEditor();
    structureEditor  = new StructureEditor();

    // --- Paso 2: GUI de debug (depende de mouseHoverHandler) ---
    actualizarGUI = new ActualizarGUI(this, mouseHoverHandler);

    // --- Paso 3: ActualizarEditor (depende de casi todo lo anterior) ---
    actualizarEditor = new ActualizarEditor(
        mapaManager,
        tileEditor,
        mouseHandler,
        mouseHoverHandler,
        structureEditor);

    // --- Paso 4: KeyboardHandler (depende de actualizarEditor) ---
    // Ahora recibe tres argumentos en lugar de dos.
    // Si solo pasaras tileEditor y structureEditor (como antes),
    // el compilador daria error porque el constructor espera tres parametros.
    keyboardHandler = new KeyboardHandler(tileEditor, structureEditor, actualizarEditor);

    // --- Paso 5: registrar listeners ---
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

        // Luego la estructura solo si esta celda es la celda ORIGEN.
        // Todas las celdas del area w x h contienen la misma referencia,
        // pero solo la origen dibuja para evitar que el bloque se
        // pinte varias veces solapado.
        if (bloques[col][row] != null) {
          Structure s = bloques[col][row];
          if (s.gOriginCol() == col && s.gOriginRow() == row) {
            s.draw(g, x, y);
          }
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

  // --- Getters ---

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

  public StructureEditor getStructureEditor() {
    return structureEditor;
  }

  /*
   * Getter de actualizarEditor por si el GameLoop u otra clase
   * necesita llamar a updateEditor() cada frame.
   */
  public ActualizarEditor getActualizarEditor() {
    return actualizarEditor;
  }
}
