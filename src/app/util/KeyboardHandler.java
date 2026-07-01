package app.util;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import app.editor.MapEditor;

/*
 * KeyboardHandler ahora solo controla el MapEditor unificado.
 *
 * Responsabilidades:
 *   - Cambiar el modo del editor (TILE / STRUCTURE) segun la tecla.
 *   - Seleccionar el brush correspondiente (tileBrushType / structureBrushType).
 *   - Toggle del modo borrador con la tecla E.
 */
public class KeyboardHandler implements KeyListener {
  private MapEditor mapEditor;
  private boolean eraserMode;

  public KeyboardHandler(MapEditor mapEditor) {
    this.mapEditor = mapEditor;
    this.eraserMode = false;
  }

  @Override
  public void keyPressed(KeyEvent e) {
    int code = e.getKeyCode();

    switch (code) {

      // ---- MODO TILE (teclas 1-5) ------------------------------------
      // Al seleccionar cualquier tile, activamos Mode.TILE.

      case KeyEvent.VK_1:
        mapEditor.setMode(MapEditor.Mode.TILE);
        mapEditor.setTileBrushType(0); // GrassTile
        mapEditor.setEraserMode(false);
        eraserMode = false;
        break;

      case KeyEvent.VK_2:
        mapEditor.setMode(MapEditor.Mode.TILE);
        mapEditor.setTileBrushType(1); // SandTile
        mapEditor.setEraserMode(false);
        eraserMode = false;
        break;

      case KeyEvent.VK_3:
        mapEditor.setMode(MapEditor.Mode.TILE);
        mapEditor.setTileBrushType(2); // StudTile
        mapEditor.setEraserMode(false);
        eraserMode = false;
        break;

      case KeyEvent.VK_4:
        mapEditor.setMode(MapEditor.Mode.TILE);
        mapEditor.setTileBrushType(3); // BrickFloorTile
        mapEditor.setEraserMode(false);
        eraserMode = false;
        break;

      case KeyEvent.VK_5:
        mapEditor.setMode(MapEditor.Mode.TILE);
        mapEditor.setTileBrushType(4); // reservado para futuros tiles
        mapEditor.setEraserMode(false);
        eraserMode = false;
        break;

      // ---- MODO ESTRUCTURA (teclas 6-8) ------------------------------
      // Al seleccionar cualquier estructura, activamos Mode.STRUCTURE.

      case KeyEvent.VK_6:
        mapEditor.setMode(MapEditor.Mode.STRUCTURE);
        mapEditor.setStructureBrushType(0); // Concrete1x1
        mapEditor.setEraserMode(false);
        eraserMode = false;
        break;

      case KeyEvent.VK_7:
        mapEditor.setMode(MapEditor.Mode.STRUCTURE);
        mapEditor.setStructureBrushType(1); // Concrete2x1
        mapEditor.setEraserMode(false);
        eraserMode = false;
        break;

      case KeyEvent.VK_8:
        mapEditor.setMode(MapEditor.Mode.STRUCTURE);
        mapEditor.setStructureBrushType(2); // Concrete2x2
        mapEditor.setEraserMode(false);
        eraserMode = false;
        break;

      // ---- BORRADOR (tecla E) ----------------------------------------
      // Toggle: si estaba en false pasa a true y viceversa.
      // El MapEditor decide qué canal borrar según el modo actual.

      case KeyEvent.VK_E:
        eraserMode = !eraserMode;
        mapEditor.setEraserMode(eraserMode);
        break;

      default:
        break;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    // sin uso por ahora
  }

  @Override
  public void keyTyped(KeyEvent e) {
    // sin uso por ahora
  }
}
