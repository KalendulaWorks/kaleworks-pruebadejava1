package app.func;

import app.tiles.*;
import app.util.*;
import app.editor.*;
import app.GamePanel;

public class ActualizarEditor {
  private MouseHandler mouseHandler;
  private TileEditor editorDeCuadros;
  private MapaManager mapaManager;

  public ActualizarEditor(MapaManager mapaManager, TileEditor editorDeCuadros, MouseHandler mouseHandler) {
    this.mapaManager = mapaManager;
    this.editorDeCuadros = editorDeCuadros;
    this.mouseHandler = mouseHandler;
  }

  public void updateEditor() {
    editarCuadro();
  }

  private void editarCuadro() {
    if (mouseHandler.getLeftM() == true) {
      int col = mouseHandler.getX() / GamePanel.TILE_SIZE;
      int row = mouseHandler.getY() / GamePanel.TILE_SIZE;
      editorDeCuadros.apply(mapaManager.getCuadros(), col, row);
    }
  }

}
