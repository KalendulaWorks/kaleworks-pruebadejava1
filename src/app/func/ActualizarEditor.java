package app.func;

import app.tiles.*;
import app.util.*;
import app.editor.*;
import app.GamePanel;

public class ActualizarEditor {
  private MouseHandler mouseHandler;
  private TileEditor editorDeCuadros;
  private MapaManager mapaManager;
  private MouseHoverHandler mouseHoverHandler;

  public ActualizarEditor(MapaManager mapaManager, TileEditor editorDeCuadros, MouseHandler mouseHandler,
      MouseHoverHandler mouseHoverHandler) {
    this.mapaManager = mapaManager;
    this.editorDeCuadros = editorDeCuadros;
    this.mouseHandler = mouseHandler;
    this.mouseHoverHandler = mouseHoverHandler;
  }

  public void updateEditor() {
    editarCuadro();
  }

  private void editarCuadro() {
    if (mouseHandler.getLeftM() == true) {
      int col = mouseHandler.getX() / GamePanel.TILE_SIZE;
      int row = mouseHandler.getY() / GamePanel.TILE_SIZE;
      editorDeCuadros.apply(mapaManager.getPiso(), col, row);
    }
  }

}
