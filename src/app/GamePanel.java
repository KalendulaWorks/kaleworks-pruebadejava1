package app;

import javax.swing.*;
import java.awt.*;

import app.func.*;
import app.tiles.*;
import app.util.*;
import app.editor.*;
import app.structures.*;

/*
 * GamePanel es el lienzo principal del juego/editor.
 *
 * Extiende JPanel para poder dibujar con paintComponent.
 *
 * Responsabilidades:
 *   - Crear e inicializar todos los componentes del sistema.
 *   - Registrar los listeners de mouse y teclado.
 *   - Dibujar el mapa (piso + estructuras) y la GUI de debug
 *     en cada llamada a paintComponent (que el GameLoop dispara
 *     con repaint() cada ~33ms).
 *
 * Campos:
 *   TILE_SIZE (static final) → tamaño de cada celda del mapa en píxeles.
 *   mapaManager              → almacena las dos matrices: piso y bloques.
 *   mouseHandler             → escucha eventos del mouse.
 *   keyboardHandler          → escucha eventos del teclado.
 *   mouseHoverHandler        → calcula la celda donde está el cursor.
 *   actualizarGUI            → dibuja información de debug en pantalla.
 *   mapEditor                → editor unificado de tiles y estructuras.
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

  /*
   * paintComponent se llama cada vez que el GameLoop invoca repaint().
   *
   * Dibuja en este orden:
   *   1. Fondo (super.paintComponent limpia el panel).
   *   2. Piso: cada tile de la matriz piso.
   *   3. Estructuras: solo dibuja en la celda ORIGEN de cada estructura.
   *      Para saber si la celda actual es el origen, comparamos col y row
   *      contra s.gOriginCol() y s.gOriginRow().
   *      Esto evita que una estructura 2x2 se dibuje 4 veces (una por celda);
   *      solo se dibuja una vez desde la esquina superior izquierda.
   *      La estructura misma se encarga de dibujar todo su tamaño w*h tiles
   *      usando gStructureWidth/Height * TILE_SIZE como ancho/alto.
   *   4. Grid de debug (lineas negras).
   *   5. GUI de debug (texto de hover).
   */
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

        // Paso 1: dibujar el tile de piso
        cuadros[col][row].draw(g, x, y);

        // Paso 2: dibujar estructura solo si esta celda es el ORIGEN
        // Una estructura 2x2 llena 4 celdas en la matriz, pero
        // solo queremos dibujarla una vez desde la celda origen.
        Structure s = bloques[col][row];
        if (s != null && s.gOriginCol() == col && s.gOriginRow() == row) {
          s.draw(g, x, y);
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

  // Getters usados por EditorBootstrap y otras clases

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
