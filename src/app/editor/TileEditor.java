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

    // Comprobación de límites de columna
    if (col < 0 || col >= matriz.length) {
      return;
    }
    // Comprobación de límites de fila
    if (row < 0 || row >= matriz[0].length) {
      return;
    }

    // Modo borrador: poner VoidTile y salir
    if (eraserMode) {
      matriz[col][row] = new VoidTile();
      return;
    }

    Tile nuevoTile = null;

    switch (brushType) {
      case 0:
        nuevoTile = new GrassTile();
        break;
      case 1:
        nuevoTile = new SandTile();
        break;
      case 2:
        nuevoTile = new StudTile();
        break;
      case 3:
        nuevoTile = new BrickFloorTile();
        break;
      default:
        // Si brushType no coincide con nada, no tocar el tile
        return;
    }

    matriz[col][row] = nuevoTile;
  }
}
