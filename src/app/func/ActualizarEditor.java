package app.func;

import app.tiles.*;
import app.structures.*;
import app.util.*;
import app.editor.*;
import app.GamePanel;

public class ActualizarEditor {
  private MouseHandler mouseHandler;
  private TileEditor editorDeCuadros;
  private MapaManager mapaManager;
  private MouseHoverHandler mouseHoverHandler;
  private StructureEditor structureEditor;
  private boolean cooldown;

  public ActualizarEditor(MapaManager mapaManager, TileEditor editorDeCuadros, MouseHandler mouseHandler,
      MouseHoverHandler mouseHoverHandler, StructureEditor structureEditor) {
    this.mapaManager = mapaManager;
    this.editorDeCuadros = editorDeCuadros;
    this.mouseHandler = mouseHandler;
    this.mouseHoverHandler = mouseHoverHandler;
    this.structureEditor = structureEditor;
  }

  public void updateEditor() {
    editarCuadroPiso(); // pincel, solo si MouseHold == true
    aplicarClickSimple(); // individual, solo si MouseSingle == true
    mouseHoverHandler.ActualizarMouse();
  }

  private void editarCuadroPiso() {
    // Modo pincel: solo cuando leftPressed y MouseHold son verdaderos
    if (mouseHandler.getLeftM() && mouseHandler.getMouseHold()) {
      int col = mouseHandler.getX() / GamePanel.TILE_SIZE;
      int row = mouseHandler.getY() / GamePanel.TILE_SIZE;
      editorDeCuadros.apply(mapaManager.getPiso(), col, row);
    }
  }

  private void editarCuadroEstructura() {
    // Modo pincel: solo cuando leftPressed y MouseHold son verdaderos
    if (mouseHandler.getLeftM() && mouseHandler.getMouseHold()) {
      int col = mouseHandler.getX() / GamePanel.TILE_SIZE;
      int row = mouseHandler.getY() / GamePanel.TILE_SIZE;
      structureEditor.ApicarCambios(mapaManager.getBloques(), mapaManager.getPiso(), col, row);
    }
  }

  private void aplicarClickSimple() {
    if (mouseHandler.getMouseSingle()) {
      int col = mouseHandler.getX() / GamePanel.TILE_SIZE;
      int row = mouseHandler.getY() / GamePanel.TILE_SIZE;
      editorDeCuadros.apply(mapaManager.getPiso(), col, row);

      // opcional: resetear MouseSingle para no repetir
      mouseHandler.clearMouseSingle();
    }
  }

}
