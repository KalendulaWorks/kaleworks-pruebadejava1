package app.util;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import app.tiles.*;
import app.editor.*;
import app.func.ActualizarEditor;

public class KeyboardHandler implements KeyListener {
  private TileEditor tileEditor;
  private StructureEditor structureEditor;

  /*
   * Referencia a ActualizarEditor para poder llamar setModoEstructuras().
   *
   * Antes, KeyboardHandler solo conocia TileEditor y StructureEditor.
   * El problema era que ambos editores se ejecutaban siempre en paralelo
   * dentro de ActualizarEditor, sin saber cul estaba "activo".
   *
   * Ahora KeyboardHandler le dice directamente a ActualizarEditor
   * en qu modo estamos, y ActualizarEditor solo delega al editor correcto.
   */
  private ActualizarEditor actualizarEditor;

  /*
   * eraserMode se guarda localmente para poder togglearlo con E
   * y propagarlo a ambos editores al mismo tiempo.
   */
  private boolean eraserMode;

  public KeyboardHandler(TileEditor tileEditor, StructureEditor structureEditor, ActualizarEditor actualizarEditor) {
    this.tileEditor = tileEditor;
    this.structureEditor = structureEditor;
    this.actualizarEditor = actualizarEditor;
    this.eraserMode = false;
  }

  @Override
  public void keyPressed(KeyEvent e) {
    int code = e.getKeyCode();

    switch (code) {

      // ---- MODO TILE (teclas 1-5) ------------------------------------
      // Al seleccionar cualquier tile, desactivamos modoEstructuras.
      // Asi el dispatch en ActualizarEditor sabe que debe llamar a TileEditor.

      case KeyEvent.VK_1:
        tileEditor.setBrushType(0);        // GrassTile
        tileEditor.setEraserMode(false);
        actualizarEditor.setModoEstructuras(false); // <- cambio de modo
        eraserMode = false;
        break;

      case KeyEvent.VK_2:
        tileEditor.setBrushType(1);        // SandTile
        tileEditor.setEraserMode(false);
        actualizarEditor.setModoEstructuras(false);
        eraserMode = false;
        break;

      case KeyEvent.VK_3:
        tileEditor.setBrushType(2);        // StudTile
        tileEditor.setEraserMode(false);
        actualizarEditor.setModoEstructuras(false);
        eraserMode = false;
        break;

      case KeyEvent.VK_4:
        tileEditor.setBrushType(3);        // BrickFloorTile
        tileEditor.setEraserMode(false);
        actualizarEditor.setModoEstructuras(false);
        eraserMode = false;
        break;

      case KeyEvent.VK_5:
        tileEditor.setBrushType(4);        // reservado para futuros tiles
        tileEditor.setEraserMode(false);
        actualizarEditor.setModoEstructuras(false);
        eraserMode = false;
        break;

      // ---- MODO ESTRUCTURA (teclas 6-8) ------------------------------
      // Al seleccionar cualquier estructura, activamos modoEstructuras.
      // Asi el dispatch en ActualizarEditor sabe que debe llamar a StructureEditor.

      case KeyEvent.VK_6:
        structureEditor.setBrushType(0);   // Concrete1x1
        structureEditor.setEraserMode(false);
        actualizarEditor.setModoEstructuras(true); // <- cambio de modo
        eraserMode = false;
        break;

      case KeyEvent.VK_7:
        structureEditor.setBrushType(1);   // Concrete2x1
        structureEditor.setEraserMode(false);
        actualizarEditor.setModoEstructuras(true);
        eraserMode = false;
        break;

      case KeyEvent.VK_8:
        structureEditor.setBrushType(2);   // Concrete2x2
        structureEditor.setEraserMode(false);
        actualizarEditor.setModoEstructuras(true);
        eraserMode = false;
        break;

      // ---- BORRADOR (tecla E) ----------------------------------------
      // Toggle: si estaba en false pasa a true y viceversa.
      // Se propaga a ambos editores para que el que este activo borre
      // correctamente sin importar en que modo estemos.

      case KeyEvent.VK_E:
        eraserMode = !eraserMode;
        tileEditor.setEraserMode(eraserMode);
        structureEditor.setEraserMode(eraserMode);
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
