package app.editor;

import app.tiles.*;

public class TileEditor {
  private int brushType;
  private boolean eraserMode;

  public void setBrushType(int brushType) {
    this.brushType = brushType;
  }

  public void setEraserMode(boolean eraserMode) {
    this.eraserMode = eraserMode;
  }

  public void apply(Tile[][] matriz, int col, int row) {

    if (col < 0 || col >= matriz.length) {
      return;
    }
    if (row < 0 || row >= matriz.length) {
      return;
    }

    if (eraserMode) {
      matriz[col][row] = new VoidTile(1, 1);
    }

    Tile nuevoTile = null;
    switch (brushType) {

      case 0:
        nuevoTile = new GrassTile(1, 1);
        break;
      case 1:
        nuevoTile = new SandTile(1, 1);
        break;
      case 2:
        nuevoTile = new StudTile(1, 1);
        break;

      default:
        break;
    }
    matriz[col][row] = nuevoTile;
  }
}
