package app.func;

import app.tiles.*;
import app.structures.*;
import app.util.*;
import app.editor.*;
import app.GamePanel;

/*
 * ActualizarEditor ahora solo delega en MapEditor.
 *
 * Se encarga de:
 *   - Leer el estado del mouse (MouseHandler).
 *   - Convertir la posicion del mouse en coordenadas de tile (col, row).
 *   - Llamar a mapEditor.apply(piso, bloques, col, row) cuando hay accion.
 *   - Actualizar el hover de debug en cada frame.
 */
public class ActualizarEditor {
  private MouseHandler mouseHandler;
  private MapaManager mapaManager;
  private MouseHoverHandler mouseHoverHandler;
  private MapEditor mapEditor;

  public ActualizarEditor(MapaManager mapaManager, MouseHandler mouseHandler,
      MouseHoverHandler mouseHoverHandler, MapEditor mapEditor) {
    this.mapaManager = mapaManager;
    this.mouseHandler = mouseHandler;
    this.mouseHoverHandler = mouseHoverHandler;
    this.mapEditor = mapEditor;
  }

  public void updateEditor() {
    despacharAccion();
    mouseHoverHandler.ActualizarMouse();
  }

  /*
   * despacharAccion() detecta si hay alguna accion de mouse:
   *   - MouseHold  → "pincel" continuo mientras se arrastra el mouse.
   *   - MouseSingle → click simple (un solo tile).
   *
   * Si hay accion, calcula la celda (col, row) y llama al MapEditor.
   * El MapEditor decide internamente si edita tiles o estructuras segun
   * su modo actual (Mode.TILE / Mode.STRUCTURE).
   */
  private void despacharAccion() {

    boolean pincelActivo = mouseHandler.getMouseHold();
    boolean clickSimple = mouseHandler.getMouseSingle();

    if (!pincelActivo && !clickSimple) {
      return;
    }

    int col = mouseHandler.getX() / GamePanel.TILE_SIZE;
    int row = mouseHandler.getY() / GamePanel.TILE_SIZE;

    // Llamar una sola vez: MapEditor decide que hacer segun su modo
    mapEditor.apply(mapaManager.getPiso(), mapaManager.getBloques(), col, row);

    if (clickSimple) {
      mouseHandler.clearMouseSingle();
    }
  }
}
